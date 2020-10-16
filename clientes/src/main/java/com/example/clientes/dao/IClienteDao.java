package com.example.clientes.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.clientes.entity.Cliente;

@Repository
public interface IClienteDao  extends JpaRepository<Cliente, Long>{

}
