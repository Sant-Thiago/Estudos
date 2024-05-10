package com.example.utils.security;

import com.mysql.cj.util.StringUtils;
import java.util.Optional;

import com.example.model.Usuario;
import com.example.service.ServiceUser;
import com.example.utils.logs.Logger;

public class Seguranca {

	private ServiceUser serviceUser;

	public Seguranca() {
		this.serviceUser = new ServiceUser();
	}

	public boolean autenticarUsuario(String email, String senha) throws Exception {
		if (StringUtils.isNullOrEmpty(email) || StringUtils.isNullOrEmpty(senha)) {
			Logger.logWarning("Credencias de acesso inválidas.");
			throw new Exception("Email e/ou senha não podem estar vazios!");
		} else {
			Optional<Usuario> usuario = serviceUser.autenticarUsuario(email, senha);
			if (!usuario.isPresent()) {
				Logger.logWarning("Usuário não encontrado.");
				throw new Exception("Falha ao autenticar o usuário. Verifique suas credenciais.");
			}
			return true;
		}
	}
}
