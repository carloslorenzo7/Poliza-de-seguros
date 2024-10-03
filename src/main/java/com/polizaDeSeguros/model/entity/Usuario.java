package com.polizaDeSeguros.model.entity;

import com.polizaDeSeguros.enums.Roles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
public class Usuario {

	@Id // primary key de la entidad
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // establece que el id se va a generar de forma automatica y
														// secuencial en la bd
	private Long id;

	@NotEmpty 
	private String name;

	@NotEmpty 
	private String lastname;
	
	@NotEmpty 
	@Column(unique = true) 
	private String dni;

	@NotEmpty 
	@Column(unique = true)
	private String email;

	@Enumerated(EnumType.STRING) // almaceno el enum como string
	private Roles rol;

	public Usuario() {

	}

	public Usuario(String name, String lastname, String dni, String email, Roles rol) {

		this.name = name;
		this.lastname = lastname;
		this.dni = dni;
		this.email = email;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Roles getRol() {
		return rol;
	}

	public void setRol(Roles rol) {
		this.rol = rol;
	}

}
