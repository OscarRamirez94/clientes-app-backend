package com.example.clientes.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.clientes.IService.IUsuarioService;
import com.example.clientes.dao.IUsuarioDao;
import com.example.clientes.entity.Usuario;

@Service
public class UsuarioService implements IUsuarioService, UserDetailsService {
	private final Logger log = LoggerFactory.getLogger(UsuarioService.class);
	@Autowired 
	private IUsuarioDao usuarioDao;
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioDao.findByUsername(username);
		if(usuario == null) {
			log.error("No existe usuario en la base de datos"+ username);
			throw new UsernameNotFoundException("No existe usuario en la base de datos"+ username);
		}
		List<GrantedAuthority> authorities =  usuario.getRoles()
				.stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getRolename()))
				.peek(authority -> log.info("Rol: " +authority.getAuthority()))
				.collect(Collectors.toList());
				
		return new User(usuario.getUsername(), usuario.getPassword(),usuario.getEnabled(), 
				true, true, true, authorities);
	}
	@Override
	@Transactional(readOnly = true)
	public Usuario findByUsername(String username) {
		return usuarioDao.findByUsername(username);
		 
	}

}
