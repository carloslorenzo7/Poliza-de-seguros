package com.polizaDeSeguros.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
@Table(name = "seguro_inmueble")
public class SeguroInmueble extends Seguro {

	private String direccion;
	private String tipoDeConstruccion;

	public SeguroInmueble() {

	}

	

	public SeguroInmueble( String descripcion, String direccion, String tipoDeConstruccion) {
		super(descripcion);
		this.direccion = direccion;
		this.tipoDeConstruccion = tipoDeConstruccion;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getTipoDeConstruccion() {
		return tipoDeConstruccion;
	}



	public void setTipoDeConstruccion(String tipoDeConstruccion) {
		this.tipoDeConstruccion = tipoDeConstruccion;
	}



	
}
