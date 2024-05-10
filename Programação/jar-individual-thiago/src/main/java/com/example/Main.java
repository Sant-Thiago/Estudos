package com.example;

import com.example.DAO.RedeDAO;
import com.example.model.Maquina;
import com.example.model.Rede;
import com.example.model.Usuario;
import com.example.service.ServicePC;
import com.example.system.Creator;
import com.example.utils.logs.Logger;
import com.example.utils.security.Login;

import java.io.Console;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


public class Main {

    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final Login login = new Login();

    private static Maquina maquina = new Maquina();
    private static ServicePC servicePC = new ServicePC();
    private static Creator creator = new Creator();
    private static RedeDAO redeDao = new RedeDAO();
    private static Rede rede = new Rede();

    public static void main(String[] args) throws Exception {

        Logger.logInfo("Servidor iniciando.");
        int quadros = 50;

        for (int i = 0; i <= quadros; i++) {
            int porcentagem = i * 100 / quadros;

            StringBuilder barraCarregamento = new StringBuilder("\u001B[34m[");
            for (int j = 0; j < quadros; j++) {
                barraCarregamento.append(j < i ? "=" : " ");
            }
            barraCarregamento.append("] ");

            barraCarregamento.append(porcentagem).append("%");

            System.out.print("\r" + barraCarregamento);

            try {
                Thread.sleep(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Logger.logInfo("Servidor iniciado com sucesso.");
        System.out.print("\r" + " ".repeat(quadros + 10));
        Usuario usuarioLogado;
        Scanner scanner = new Scanner(System.in);

        System.out.println("""
                \n
                __     _____ ____  _   _   _    _           ___  ____  ____ \s
                \\ \\   / /_ _/ ___|| | | | / \\  | |         / _ \\|  _ \\/ ___|\s
                 \\ \\ / / | |\\___ \\| | | |/ _ \\ | |   _____| | | | |_) \\___ \\\s
                  \\ V /  | | ___) | |_| / ___ \\| |__|_____| |_| |  __/ ___) |
                   \\_/  |___|____/ \\___/_/   \\_\\_____|     \\___/|_|   |____/\s
                _____________________________________________________________
                Vamos verificar suas permissões para iniciar o monitoramento.
                _____________________________________________________________
                """);
        String email = "";
        String senha = "";
        while (true) {
            System.out.print(" - Insira seu email: ");
            email = scanner.next();
            Console console = System.console();

            if (console == null) {
                System.out.print(" - Insira sua senha: ");
                senha = scanner.next();
            } else {
                char[] senhaArray = console.readPassword(" - Insira sua senha: ");
                senha = new String(senhaArray);

                java.util.Arrays.fill(senhaArray, ' ');
            }

            try {
                usuarioLogado = login.login(email, senha);
                System.out.println(" - Terminamos a verificação de seu acesso...   ");
                if (usuarioLogado != null) {
                    System.out.println("""
                                
                            --- ACESSO CONCEDIDO ---
                                
                            Bem-vindo %s
                            email: %s
                                
                            Vamos verificar as permissões da sua máquina...
                            """.formatted(usuarioLogado.getNome(), usuarioLogado.getEmail()));

                    maquina = servicePC.verificarMaquina(usuarioLogado);

                    if (maquina == null) {
                        Logger.logWarning("Não foi possível acessar a máquina do usuário");
                        System.out.println("Não foi possível encontrar sua máquina.");
                        break;
                    }

                    rede = creator.criarRede();                    

                    if (rede == null) {
                        Logger.logWarning("Não foi possível definir a rede para a máquina.");
                        System.out.println("Não foi possível definir a rede para a máquina.");
                        break;
                    }
                    
                    redeDao.listarUser(rede, usuarioLogado);

                    Logger.logInfo("Usuário logado com sucesso: " + usuarioLogado.getEmail());
                    System.out.println("""
                            Informações da máquina
                             - Lista IPV4 da máquina: %s
                            """.formatted((new StringBuilder(maquina.getIpv4().stream()
                                    .map(e -> e).collect(Collectors.joining("; "))
                            ).toString())));

                    iniciarMonitoramento();
                    scanner.close();
                    break;
                } else {
                    System.out.println("""
                                
                            --- ACESSO NEGADO ---
                                
                            """);
                    Logger.logWarning("Tentativa de login falhou para o email: " + email);
                }
            } catch (Exception e) {
                System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
                e.printStackTrace();
            }
        }
        scanner.close();

    }

    public static void iniciarMonitoramento() {

        try {
            Scanner scanner = new Scanner(System.in);
            boolean running = true;

            while (running) {
                System.out.println("""
                        +----------------------------------------------+
                        | Seu monitoramento está em segundo plano      |
                        +----------------------------------------------+
                        | Opções extras :                              |
                        +---------------------------------+------------+
                        | t - Listar dispositivos na rede | d - Voltar |                   
                        +---------------------------------+------------+
                        """);
                String input = scanner.next();

                switch (input) {
                    case "t":
                        clearTerminal();
                        redeDao.listar(maquina);
                        break;
                    case "d":
                        clearTerminal();
                        break;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            Logger.logError("Erro ao iniciar monitoramento: ", e.getMessage(), e);
        }
    }

    public static void clearTerminal() {
        String os = System.getProperty("os.name").toLowerCase();
        try {
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else if (os.contains("nix") || os.contains("nux") || os.contains("aix")) {
                new ProcessBuilder("scripts/bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
