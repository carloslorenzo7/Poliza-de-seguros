package com.polizaDeSeguros.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
@Inheritance(strategy = InheritanceType.JOINED) // Va a crear una tabla para la clase padre y una tabla para cada subclase, uniendo los datos con una clave primaria
public abstract class Seguro {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	private String descripcion;

	public Seguro() {

	}

	public Seguro(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
