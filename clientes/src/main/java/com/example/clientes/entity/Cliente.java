package com.example.clientes.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
	
	@Column(nullable = false, unique= true)
	@NotBlank(message = "No puede ser vacio")
	@Email(message = "Formato de correo invalido")
	private String correo;
	
	@Column(name = "create_at")
	@NotNull(message = "No puede ser vacio")
	@Temporal(TemporalType.DATE)
	private Date createAt;
	
	@Column(name = "foto")
	private String foto;
	
	@NotNull@NotNull(message = "Region No puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "region_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
	private Region region;
	@JsonIgnoreProperties(value={"cliente","hibernateLazyInitializer","handler"}, allowSetters = true)
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente", cascade = CascadeType.ALL)
	private List<Factura> facturas;
	
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
	/*
	@PrePersist
	public void asignaFecha() {
		createAt = new Date();
	}
	*/
	public Cliente() {
		this.facturas = new ArrayList<>();
	}

	public Cliente(Long id,
			@Size(min = 4, max = 30, message = "Longitud minima 4 maxima 30") @NotBlank(message = "No puede ser vacio") String nombre,
			@NotBlank(message = "No puede ser vacio") String apellido,
			@NotBlank(message = "No puede ser vacio") @Email(message = "Formato de correo invalido") String correo,
			@NotNull(message = "No puede ser vacio") Date createAt, String foto, Region region) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.correo = correo;
		this.createAt = createAt;
		this.foto = foto;
		this.region = region;
	}
	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", correo=" + correo
				+ ", createAt=" + createAt + ", foto=" + foto + ", region=" + region + "]";
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}

	public List<Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(List<Factura> facturas) {
		this.facturas = facturas;
	}

	private static final long serialVersionUID = 1L;
	

}
