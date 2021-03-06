package com.example.clientes.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.clientes.IService.IFacturaService;
import com.example.clientes.dao.IFacturaDao;
import com.example.clientes.dao.IProductoDao;
import com.example.clientes.entity.Factura;
import com.example.clientes.entity.Producto;

@Service
public class FacturaService implements IFacturaService{
	@Autowired 
	private IFacturaDao facturaDao;
	@Autowired 
	private IProductoDao productoDao;

	@Override
	@Transactional(readOnly = true)
	public List<Factura> findAll() {
		
		return facturaDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Factura findyById(Long id) {
		
		return facturaDao.findById(id).orElse(null);
	}

	@Override
	public Factura save(Factura factura) {
		
		return facturaDao.save(factura);
	}

	@Override
	public void eliminar(Long id) {
		facturaDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findProductoByNombre(String nombre) {	
		return productoDao.findByNombreContainingIgnoreCase(nombre);
	}

}
