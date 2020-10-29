package com.example.clientes.IService;

import com.example.clientes.entity.Usuario;

public interface IUsuarioService {
	public Usuario findByUsername(String username);
}
