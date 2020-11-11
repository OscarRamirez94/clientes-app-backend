package com.example.clientes.IService;

import java.util.List;

import com.example.clientes.entity.Factura;


public interface IFacturaService {
	
	public List<Factura> findAll();
	//public Page<Cliente> findAll(Pageable page);
	public Factura findyById(Long id);
	public Factura save(Factura factura);
	public void eliminar(Long id);

	
	
}
