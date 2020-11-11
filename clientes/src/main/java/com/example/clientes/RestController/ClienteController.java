package com.example.clientes.RestController;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.clientes.IService.IClienteService;
import com.example.clientes.IService.IUploadService;
import com.example.clientes.entity.Cliente;
import com.example.clientes.entity.Region;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class ClienteController {
	private final Logger log = LoggerFactory.getLogger(ClienteController.class);
	@Autowired
	private IClienteService clienteService;

	@Autowired
	private IUploadService uploadService;

	@GetMapping("/clientes")
	public List<Cliente> listaCliente() {
		return clienteService.findAll();
	}

	@GetMapping("/clientes/page/{page}")
	public Page<Cliente> listaCliente(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 2);
		return clienteService.findAll(pageable);
	}

	//@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> clienteByid(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> response = new HashMap();

		try {
			cliente = clienteService.findyById(id);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error en la base de datos");
			response.put("error", e.getMessage() + " : " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		if (cliente == null) {
			response.put("mensaje", "El id : " + id + " no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}
		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	@Secured("ROLE_ADMIN")
	@PostMapping("/clientes")

	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Map<String, Object> response = new HashMap();

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> err.getField() + " :: " + err.getDefaultMessage()).collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		Cliente clienteAdd = null;
		try {
			clienteAdd = clienteService.save(cliente);
		} catch (DataAccessException e) {
			response.put("mensaje", "Error al insertar en la base de datos");
			response.put("error", e.getMessage() + " : " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "El cliente creado con éxito");
		response.put("cliente", clienteAdd);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, @PathVariable Long id, BindingResult result) {
		Map<String, Object> response = new HashMap();
		Cliente clienteUpdated = null;

		if (result.hasErrors()) {

			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Campo :: " + err.getField() + " :: " + err.getDefaultMessage())
					.collect(Collectors.toList());

			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}

		Cliente clienteActual = clienteService.findyById(id);

		if (clienteActual == null) {
			response.put("mensaje", "El cliente id : " + id + " no existe");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);

		}

		try {
			clienteActual.setNombre(cliente.getNombre());
			clienteActual.setApellido(cliente.getApellido());
			clienteActual.setCorreo(cliente.getCorreo());
			clienteActual.setCreatAt(cliente.getCreateAt());
			clienteActual.setRegion(cliente.getRegion());
			clienteUpdated = clienteService.save(clienteActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al modificar cliente en la base de datos");
			response.put("error", e.getMessage() + " : " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}
		response.put("mensaje", "El cliente se modificó con éxito");
		response.put("cliente", clienteUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> elimina(@PathVariable Long id) {
		Map<String, Object> response = new HashMap();

		try {
			Cliente cliente = clienteService.findyById(id);
			String nombreArchivoAnt = cliente.getFoto();
			uploadService.eliminar(nombreArchivoAnt);

			clienteService.eliminar(id);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al eliminar cliente en la base de datos");
			response.put("error", e.getMessage() + " : " + e.getMostSpecificCause().getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

		}

		response.put("mensaje", "Se eliminó correctamente");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@PostMapping("/clientes/upload")
	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id) {
		Map<String, Object> response = new HashMap();
		Cliente cliente = clienteService.findyById(id);
		if (!archivo.isEmpty()) {

			String nombreArchivo = null;
			try {
				nombreArchivo = uploadService.copiarFoto(archivo);
			} catch (IOException e) {

				response.put("mensaje", "Error al cargar imagen");
				response.put("error", e.getMessage() + " : " + e.getCause());
				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
			}

			String nombreArchivoAnt = cliente.getFoto();
			uploadService.eliminar(nombreArchivoAnt);
			cliente.setFoto(nombreArchivo);
			clienteService.save(cliente);
			response.put("cliente", cliente);
			response.put("mensaje", "Has subido correctamente el archivo " + nombreArchivo);
		}

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

	}

	@GetMapping("uploads/img/{nombreFoto:.+}")
	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto) {

		Resource recurso = null;

		try {
			recurso = uploadService.cargarFoto(nombreFoto);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		HttpHeaders cabecera = new HttpHeaders();
		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");

		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);

	}

	@Secured("ROLE_ADMIN")
	@GetMapping("/clientes/regiones")
	public List<Region> listaRegiones() {
		return clienteService.findAllRegion();
	}

}
