package com.example.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.integration.Userinfo;
import com.example.model.Maquina;
import com.example.model.Rede;
import com.example.model.Usuario;
import com.example.system.Computer;
import com.example.utils.database.Config;

public class RedeDAO {
    Computer computer = new Computer();
    Maquina maquina;
    Userinfo userinfo = new Userinfo();
    Usuario usuario;
    Rede rede;

    public Optional<Rede> criar() throws SQLException {
        try (Connection conexao = Config.conexion()) {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO rede (nome, interface, sinal, transmissao, bssid, fkUsuario) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, rede.getNome());
            preparedStatement.setString(2, rede.getInterfaceRede());
            preparedStatement.setInt(3, rede.getSinal());
            preparedStatement.setFloat(4, rede.getTransmissao());
            preparedStatement.setString(5, rede.getBssid());
            preparedStatement.setInt(6, usuario.getIdUsuario());
        
            preparedStatement.executeUpdate();

            try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
                if (keys.next()) {
                    Integer idRede = keys.getInt(1);
                    rede.setId(idRede);
                    System.out.println("ID criado com sucesso : "+ idRede);
                } else {
                    System.out.println("Erro ao obter o ID da rede criada.");
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao criar a rede.");
            e.printStackTrace();
        }
        return null;
    }

    public void listar(Maquina maquina) throws SQLException {
        try (Connection conexao = Config.conexion()) {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT bssid FROM rede \n" + //
                                "\tJOIN usuario as u ON fkUsuario = u.idUsuario \n" + //
                                "\t\tJOIN ipv4 as ip ON idUsuario = ip.fkUsuario\n" + //
                                "\t\t\tJOIN maquina ON fkMaquina = idMaquina \n" + //
                                "\t\t\t\tWHERE fkMaquina = ?");

            
            preparedStatement.setInt(1, maquina.getIdMaquina());
            this.maquina = maquina;


            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String bssid = resultSet.getString("bssid");
                    listarIps(bssid);
                }
            }
        } catch (Exception e) {
            System.out.println("Ocorreu um erro ao listar os dispositivos.");
            e.printStackTrace();
        }
    }

    public void listarUser(Rede rede, Usuario usuario) throws SQLException {
        try (Connection conexao = Config.conexion()) {
            PreparedStatement preparedStatement = conexao.prepareStatement(
                "SELECT u.nome, u.idUsuario FROM usuario u " +
                "JOIN rede r ON u.idUsuario = r.fkUsuario " +
                "WHERE r.bssid = ?"
            );

            preparedStatement.setString(1, rede.getBssid());

            this.rede = rede;
            this.usuario = usuario;

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (!resultSet.next()) {
                    this.criar();
                } else{
                    System.out.println("Usuário já está relacionado à rede.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocorreu um erro ao listar os dispositivos.");
            e.printStackTrace();
            throw e;
        }
    }

    public void listarIps(String bssid) {
        try (Connection conexao = Config.conexion()) {
            PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM ipv4 as ip JOIN usuario ON ip.fkUsuario = idUsuario JOIN rede as r ON idUsuario = r.fkUsuario WHERE bssid = ?");
    
            preparedStatement.setString(1, bssid);
    
            StringBuilder sb = new StringBuilder();

            List<String> listaIp = new ArrayList<>();

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    String numeroIP = resultSet.getString("numeroIP");
                    listaIp.add(numeroIP);
                }
            }

            List<List<String>> listaDevices = computer.listarDispositivos(userinfo.ipv4());
            
            for (List<String> list : listaDevices) {
                for (int i = 0; i < listaIp.size(); i++) {
                    if (
                        list.get(0).toLowerCase().equals(listaIp.get(i).toLowerCase())
                    ) {
                        sb.append("IPV4 : %s - Corresponde a um de seus funcionários.\n".formatted(list.get(0)));
                        break;
                    } else if (i == listaIp.size()-1) {
                        sb.append("IPV4 : %s - Não corresponde a nenhum de seus funcionários.\n".formatted(list.get(0)));
                    }

                }
            }

            System.out.println(sb.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
     
}

