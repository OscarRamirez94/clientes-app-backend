package com.example.clientes.dao;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.example.clientes.entity.Factura;


@Repository
public interface IFacturaDao  extends JpaRepository<Factura, Long>{
	
	

}
