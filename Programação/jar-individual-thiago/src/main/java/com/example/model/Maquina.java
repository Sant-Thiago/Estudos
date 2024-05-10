package com.example.model;

import java.util.ArrayList;
import java.util.List;

public class Maquina {
	private Integer idMaquina;
	private String numeroSerial;
	private String modelo;
	private String marca;
	private String hostname;
	private String username;
	private List<String> ipv4;
	private Usuario usuario;

	public Maquina() {
		this.ipv4 = new ArrayList<>();
	}

	public Maquina(Integer idMaquina, Usuario usuario) {
		this();
		this.idMaquina = idMaquina;
		this.usuario = usuario;
	}

	public int getIdMaquina() {
		return idMaquina;
	}

	public void setIdMaquina(int idMaquina) {
		this.idMaquina = idMaquina;
	}

	public List<String> getIpv4() {
		return ipv4;
	}

	public void setIdMaquina(Integer idMaquina) {
		this.idMaquina = idMaquina;
	}

	public String getNumeroSerial() {
		return numeroSerial;
	}

	public void setNumeroSerial(String numeroSerial) {
		this.numeroSerial = numeroSerial;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public void setIpv4(List<String> ipv4) {
		this.ipv4 = ipv4;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
