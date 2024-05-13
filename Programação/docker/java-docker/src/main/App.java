public class App {

    public static void main(String[] args) {
        mostrar("Maven");
    }

    private static void mostrar(String subject) {
        System.out.println("Comando para criar um projeto Apache Maven:: ");
        System.out.println("mvn archetype:generate -DgroupId=com.example.projeto -DartifactId=nome-pacote -Dversion=versao.\n\n");
        
        Historia.maven();
    }

}