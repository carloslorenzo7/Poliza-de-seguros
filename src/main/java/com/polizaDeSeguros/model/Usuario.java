package com.polizaDeSeguros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
public class Usuario {

	@Id // primary key de la entidad
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // establece que el id se va a generar de forma automatica y secuencial en la bd									
	private Long id;

	private String name;
	private String lastname;
	private String dni;
	private String email;

	public Usuario() {

	}

	public Usuario( String name, String lastname, String dni, String email) {
		
		this.name = name;
		this.lastname = lastname;
		this.dni = dni;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
