package com.example.service;


import java.util.Optional;

import com.example.DAO.UsuarioDAO;
import com.example.model.Usuario;

public class ServiceUser {
	UsuarioDAO usuarioDao = new UsuarioDAO();

	public ServiceUser() {
		this.usuarioDao = usuarioDao;
	}

	public Optional<Usuario> autenticarUsuario(String email, String senha) {
		return usuarioDao.findByEmailAndSenha(email, senha);
	}
}
