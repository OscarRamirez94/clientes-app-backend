package com.example.clientes.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.clientes.IService.IClienteService;
import com.example.clientes.dao.IClienteDao;
import com.example.clientes.entity.Cliente;
import com.example.clientes.entity.Region;

@Service
public class ClienteService implements IClienteService {
@Autowired
private IClienteDao clienteDao;

@Override
@Transactional(readOnly = true)
public List<Cliente> findAll() {
	return clienteDao.findAll();
}

@Override
@Transactional(readOnly = true)
public Cliente findyById(Long id) {
	
	return clienteDao.findById(id).orElse(null);
}

@Override
@Transactional
public Cliente save(Cliente cliente) {
	return clienteDao.save(cliente);
}

@Override

public void eliminar(Long id) {
	clienteDao.deleteById(id);
	
}

@Override
@Transactional(readOnly = true)
public Page<Cliente> findAll(Pageable page) {
	// TODO Auto-generated method stub
	return clienteDao.findAll(page);
}

@Override
@Transactional(readOnly = true)
public List<Region> findAllRegion() {
	// TODO Auto-generated method stub
	return clienteDao.findAllRegion();
}
}
