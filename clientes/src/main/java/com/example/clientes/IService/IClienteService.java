package com.example.clientes.IService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.clientes.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> findAll();
	public Page<Cliente> findAll(Pageable page);
	public Cliente findyById(Long id);
	public Cliente save(Cliente cliente );
	public void eliminar(Long id);
	
	
}
