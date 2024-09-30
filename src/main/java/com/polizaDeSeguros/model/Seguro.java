package com.polizaDeSeguros.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
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

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
