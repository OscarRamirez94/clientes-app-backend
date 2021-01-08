package com.example.clientes.dao;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.clientes.entity.Producto;


@Repository
public interface IProductoDao  extends JpaRepository<Producto, Long>{
	
	@Query("select p from Producto p where p.nombre  LIKE %?1%")
	public List<Producto> findByNombre(String nombre);
	
	public List<Producto> findByNombreContainingIgnoreCase(String nombre);
	
	public List<Producto> findByNombreStartingWithIgnoreCase(String nombre);

}
