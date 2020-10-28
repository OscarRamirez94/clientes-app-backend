package com.example.clientes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.clientes.entity.Usuario;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{
	
	public Usuario findByUsername(String username);
}
