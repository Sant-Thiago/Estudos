package dao;

import model.Maquina;
import model.Usuario;
import oshi.SystemInfo;
import oshi.hardware.ComputerSystem;
import oshi.hardware.HardwareAbstractionLayer;
import util.database.MySQLConnection;
import util.logs.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import app.integration.Computer;

public class MaquinaDAO {
	public Optional<Maquina> monitorarMaquina(Usuario usuario) throws SQLException {
		try (Connection conexao = MySQLConnection.ConBD()) {
			PreparedStatement preparedStatement = conexao.prepareStatement(
					"SELECT * FROM maquina JOIN usuario on maquina.fkUsuario = usuario.idUsuario JOIN ipv4 ON ipv4.fkMaquina = maquina.idMaquina WHERE idUsuario = ?");

			preparedStatement.setInt(1, usuario.getIdUsuario());

			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				Maquina maquina = null;
				while (resultSet.next()) {
					if (maquina == null) {
						maquina = this.criaMaquina(resultSet, usuario);
						this.especificarMaquina(maquina);
					}

					// maquina.getIpv4().add(resultSet.getString("numeroIP"));
				}
				return Optional.of(maquina);
			}
		} catch (SQLException e) {
			Logger.logError("Não foi possível encontrar a máquina do usuário:", e.getMessage(), e);
			throw new RuntimeException("Erro ao buscar máquina do usuário", e);
		}
	}

	public void especificarMaquina(Maquina maquina) {
		
		try (Connection con = MySQLConnection.ConBD()) {
			verifyMaquina(maquina);
			
			PreparedStatement preparedStatement = con.prepareStatement("UPDATE Maquina SET numeroIdentificacao = ?, modelo = ?, marca = ?, username = ?, hostname = ? WHERE idMaquina = ?");

			preparedStatement.setString(1, maquina.getNumeroSerial());
			preparedStatement.setString(2, maquina.getModelo());
			preparedStatement.setString(3, maquina.getMarca());
			preparedStatement.setString(4, maquina.getUsername());
			preparedStatement.setString(5, maquina.getHostname());
			preparedStatement.setInt(6, maquina.getIdMaquina());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			Logger.logError("Não foi possível encontrar a máquina:", e.getMessage(), e);
		}
		
	}
	
	public static String getIpv4() {
		Computer userinfo = new Computer();
		return userinfo.getIpv4();
	}
	
	private void verifyMaquina(Maquina maquina) {
		HardwareAbstractionLayer hardware = new SystemInfo().getHardware();
		ComputerSystem computerSystem = hardware.getComputerSystem();
	
		String fabricante = computerSystem.getManufacturer();
		String modelo = computerSystem.getModel();
		String numeroDeSerie = computerSystem.getSerialNumber();

		Computer userinfo = new Computer();
		String hostname = userinfo.getHostname();
		String username = userinfo.getUsername();

		try (Connection con = MySQLConnection.ConBD()) {
			PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM Maquina WHERE idMaquina = ?");
			
			preparedStatement.setInt(1, maquina.getIdMaquina());
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					maquina.setNumeroSerial(resultSet.getString("numeroIdentificacao") == numeroDeSerie ? maquina.getNumeroSerial() : numeroDeSerie);
					maquina.setModelo(resultSet.getString("modelo") == modelo ? maquina.getModelo() : modelo);
					maquina.setMarca(resultSet.getString("marca") == fabricante ? maquina.getMarca() : fabricante);
					maquina.setUsername(resultSet.getString("username") == username ? maquina.getUsername() : username);
					maquina.setHostname(resultSet.getString("hostname") == hostname ? maquina.getHostname() : hostname);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Maquina criaMaquina(ResultSet resultSet, Usuario usuario) throws SQLException {
		return new Maquina(resultSet.getInt("idMaquina"), usuario);
	}
}
