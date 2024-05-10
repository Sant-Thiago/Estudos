// package com.example.DAO;

// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.util.ArrayList;
// import java.util.List;
// import java.util.Optional;

// import com.example.database.Config;
// import com.example.model.Rede;
// import com.example.system.Computer;

// public class RedeDAO {
//     Rede redeModel = new Rede();
//     Computer computer = new Computer();
//     Rede rede = new Rede();

//     // MELHORAR ESSA LÓGICA

//     public Optional<Rede> criar() throws SQLException {
//         try (Connection conexao = Config.conexion()) {
//             PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO rede (nome, interface, sinal, transmissao, bssid, fkIpv4) VALUES (?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);

//             preparedStatement.setString(1, rede.getNome());
//             preparedStatement.setString(2, rede.getInterfaceRede());
//             preparedStatement.setInt(3, rede.getSinal());
//             preparedStatement.setFloat(4, rede.getTransmissao());
//             preparedStatement.setString(5, rede.getBssid());
//             preparedStatement.setInt(6, 1);
        
//             preparedStatement.executeUpdate();

//             try (ResultSet keys = preparedStatement.getGeneratedKeys()) {
//                 if (keys.next()) {
//                     Integer idRede = keys.getInt(1);
//                     rede.setId(idRede);
//                     System.out.println("ID criado com sucesso : "+ idRede);
//                 } else {
//                     System.out.println("Erro ao obter o ID da rede criada.");
//                 }
//             }
//         } catch (Exception e) {
//             System.out.println("Ocorreu um erro ao criar a rede.");
//             e.printStackTrace();
//         }
//         return null;
//     }

//     public void listar() throws SQLException {
//         try (Connection conexao = Config.conexion()) {
//             // PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM rede JOIN ipv4 ON id = idIpv4 JOIN maquina ON fkMaquina = idMaquina WHERE idMaquina = ?");
//             PreparedStatement preparedStatement = conexao.prepareStatement("SELECT bssid.rede FROM rede JOIN ipv4 ON fkIpv4 = idIpv4 WHERE fkMaquina = ?");

//             preparedStatement.setString(1, maquina.getIdMaquina());

//             try (ResultSet resultSet = preparedStatement.executeQuery();) {
//                 String bssid = resultSet.getString("bssid");
//                 listarIps(bssid);
//             }
//         } catch (Exception e) {
//             System.out.println("Ocorreu um erro ao listar os dispositivos.");
//             e.printStackTrace();
//         }
//     }

//     public void listarIps(String bssid) {
//         try (Connection conexao = Config.conexion()) {
//             PreparedStatement preparedStatement = conexao.prepareStatement("SELECT * FROM ipv4 JOIN rede ON idIpv4 = fkIpv4 WHERE bssid = ?");
    
//             preparedStatement.setString(1, bssid);
    
//             StringBuilder sb = new StringBuilder();

//             List<String> listaIp = new ArrayList<>();

//             try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                 while (resultSet.next()) {
//                     String numeroIP = resultSet.getString("numeroIP");
//                     listaIp.add(numeroIP);
//                 }
//             }

//             List<List<String>> listaDevices = computer.listarDispositivos(ipv4.getNumeroIP());

//             for (List<String> list : listaDevices) {
//                 for (int i = 0; i < listaIp.size(); i++) {
//                     if (
//                         list.get(0).toLowerCase().equals(listaIp.get(i).toLowerCase())
//                     ) {
//                         sb.append("IPV4 : %s - Corresponde a um de seus funcionários.");
//                         sb.append("\n");
//                         break;
//                     } else if (i == listaIp.size()-1) {
//                         sb.append("IPV4 : s - Não corresponde a nenhum de seus funcionários");
//                     }

//                 }
//             }

//         } catch (Exception e) {
//             e.printStackTrace();
//         }
//     }
     
// }

