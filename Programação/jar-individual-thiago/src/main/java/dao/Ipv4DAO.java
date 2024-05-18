package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Ipv4;
import model.Maquina;
import util.database.MySQLConnection;
import util.logs.Logger;

public class Ipv4DAO {
    
    public Ipv4 insert(Ipv4 ipv4) throws SQLException {
        try (Connection conexao = MySQLConnection.ConBD()) {
            PreparedStatement preparedStatement = conexao.prepareStatement("INSERT INTO ipv4 (numeroIP, nomeLocal, fkMaquina, fkUsuario) VALUES (?, ?, ?, ?)");

            preparedStatement.setString(1, ipv4.getNumeroIp());
            preparedStatement.setString(2, ipv4.getNomeLocal());
            preparedStatement.setInt(3, ipv4.getFkMaquina());
            preparedStatement.setDouble(4, ipv4.getFkUsuario());
        
            preparedStatement.executeUpdate();
            
            return ipv4;

        } catch (SQLException e) {
            Logger.logError("Não foi possível inserir valores à entidade 'ipv4': ", e.getMessage(), e);
			throw new RuntimeException("Erro ao inserir dados à tabela ipv4", e);
        }
    } 

    public List<Object> select(Ipv4 ipv4) throws SQLException {
        try (Connection connection = MySQLConnection.ConBD()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ipv4 WHERE numeroIP = ? AND nomeLocal = ?");

            preparedStatement.setString(1, ipv4.getNumeroIp());
            preparedStatement.setString(2, ipv4.getNomeLocal());
            
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Object> atributosIpv4 = new ArrayList<>();
                while (resultSet.next()) {
                    atributosIpv4.add(resultSet.getInt("idIpv4"));
                    atributosIpv4.add(resultSet.getString("numeroIP"));
                    atributosIpv4.add(resultSet.getString("nomeLocal"));
                    atributosIpv4.add(resultSet.getInt("fkMaquina"));
                    atributosIpv4.add(resultSet.getDouble("fkUsuario"));
                }
                return atributosIpv4;
            }

        } catch (SQLException e) {
            Logger.logError("Não foi possível listar a ipv4:", e.getMessage(), e);
			throw new RuntimeException("Erro ao listar ipv4 com o numero = "+ ipv4.getNumeroIp() +": ", e);
        }
    }

    public Boolean existe(Ipv4 ipv4) {
        try (Connection connection = MySQLConnection.ConBD()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ipv4 WHERE numeroIP = ? AND nomeLocal = ?");

            preparedStatement.setString(1, ipv4.getNumeroIp());
            preparedStatement.setString(2, ipv4.getNomeLocal());

            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) return true;

            return false;

        } catch (SQLException e) {
            Logger.logError("Não foi possível listar as informações do ipv4: ", e.getMessage(), e);
			throw new RuntimeException("Erro ao listar as informações do ipv4 com o numero ip = "+ ipv4.getNumeroIp() +": ", e);
        }
    }

    public void atribuirId(Ipv4 ipv4) throws SQLException {
        List<Object> ipv4Obj = this.select(ipv4);

        String idIpv4 = ipv4Obj.get(0).toString();

        ipv4.setIdIpv4(Integer.parseInt(idIpv4));
    }

    public Integer contem(Maquina maquina, String ip) {
        try (Connection connection = MySQLConnection.ConBD()) {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ipv4 WHERE numeroIP = ? AND fkMaquina = ?");

            preparedStatement.setString(1, ip);
            preparedStatement.setInt(2, maquina.getIdMaquina());

            ResultSet resultSet = preparedStatement.executeQuery();
            
            Integer count = 0;
            while (resultSet.next()) {
                count++;
            }

            return count;

        } catch (SQLException e) {
            Logger.logError("Não foi possível listar as informações do ipv4: ", e.getMessage(), e);
			throw new RuntimeException("Erro ao listar as informações do ipv4 com o numeroIp = "+ ip +": ", e);
        }
    }

}
