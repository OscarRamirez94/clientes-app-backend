package com.example.clientes.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.clientes.entity.Cliente;
import com.example.clientes.entity.Region;

@Repository
public interface IClienteDao  extends JpaRepository<Cliente, Long>{
	
	@Query("from Region")
	public List<Region> findAllRegion();

}
