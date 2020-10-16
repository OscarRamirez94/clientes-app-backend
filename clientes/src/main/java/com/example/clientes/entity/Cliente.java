package com.example.clientes.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name ="clientes")
public class Cliente implements Serializable {


	@Id
	@GeneratedValue (strategy =GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@Size(min=4,max=30, message = "Longitud minima 4 maxima 30")
	@NotBlank(message = "No puede ser vacio")
	private String nombre;

	@Column(nullable = false)
	@NotBlank(message = "No puede ser vacio")
	private String apellido;
	
	@Column(nullable = false, unique= false)
	@NotBlank(message = "No puede ser vacio")
	@Email(message = "Formato de correo invalido")
	private String correo;
	
	@Column(name = "create_at")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreatAt(Date createAt) {
		this.createAt = createAt;
	}
	@PrePersist
	public void asignaFecha() {
		createAt = new Date();
	}
	public Cliente() {}
	
	public Cliente(Long id, String nombre, String apellido, String correo, Date creatAt) {
	
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.createAt = createAt;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", creatAt=" + createAt + "]";
	}

	private static final long serialVersionUID = 1L;
	

}
