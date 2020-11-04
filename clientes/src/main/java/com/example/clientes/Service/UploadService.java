package com.example.clientes.Service;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.clientes.IService.IUploadService;

@Service
public class UploadService implements IUploadService{
	private final Logger log = LoggerFactory.getLogger(UploadService.class);
	private  final static String DIRECTORIO_UPLOAD ="uploads"; 
	@Override
	public Resource cargarFoto(String nombreFoto) throws MalformedURLException {
		Path rutaArchivo = getPath(nombreFoto);
		Resource recurso =  new UrlResource(rutaArchivo.toUri());
		
		if(!recurso.exists() && !recurso.isReadable()) {
			rutaArchivo = Paths.get("src/main/resource/static/images/no-user.jpg").resolve(nombreFoto).toAbsolutePath();
			recurso = new UrlResource(rutaArchivo.toUri());
			
			
		}
		return recurso;
	}

	@Override
	public String copiarFoto(MultipartFile archivo) throws IOException {
		String nombreArchivo = UUID.randomUUID().toString()+"_"+archivo.getOriginalFilename().replace(" ", "");
		Path rutaArchivo = getPath(nombreArchivo);
		
		Files.copy(archivo.getInputStream(), rutaArchivo);
		
		return nombreArchivo;
	}

	@Override
	public boolean eliminar(String nombreFoto) {
		if(nombreFoto != null && nombreFoto.length() > 0) {
			Path rutaArchivoAnt = getPath(nombreFoto);
			File archivoAnt = rutaArchivoAnt.toFile(); 
			if(archivoAnt.exists() && archivoAnt.canRead()) {
				archivoAnt.delete();
				return true;
			}
			
		}
		return false;
	}

	@Override
	public Path getPath(String nombreFoto) {
		
		return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
	}

}
