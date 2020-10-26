package com.example.clientes.IService;


import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {
	public Resource cargarFoto(String nombreFoto) throws MalformedURLException;
	public String copiarFoto(MultipartFile archivo) throws IOException;
	public boolean eliminar(String nombreFoto);
	public Path getPath(String nombreFoto);

}
