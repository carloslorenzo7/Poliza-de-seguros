package com.polizaDeSeguros.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity // especifica la creación de una entidad. Se coloca al inicio de la clase
public class Usuario {

	@Id // primary key de la entidad
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // establece que el id se va a generar de forma automática y  secuencial en la bd
														
	private Long id;

	@NotEmpty
	private String nombre;

	@NotEmpty
	private String apellido;

	@NotEmpty
	@Column(unique = true) // asegura que el DNI sea único
	private String dni;

	@NotEmpty
	private String telefono;

	@NotEmpty
	private String direccion;

	@NotEmpty
	@Column(unique = true)
	private String email;

	@NotEmpty
	private String rol;

	@NotEmpty
	private String password; 

	public Usuario() {
	}

	public Usuario( String nombre,String apellido, String dni,
			String telefono, String direccion,  String email,  String rol,
			 String password) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.dni = dni;
		this.telefono = telefono;
		this.direccion = direccion;
		this.email = email;
		this.rol = rol;
		this.password = password;
	}

	public Long getId() {
		return id;
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

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
