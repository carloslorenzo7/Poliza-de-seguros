package com.polizaDeSeguros.model;

import jakarta.persistence.Entity;


@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
public class SeguroInmueble extends Seguro {

	private String direccion;
	private String tipoDeConstruccion;

	public SeguroInmueble() {

	}

	public SeguroInmueble(String direccion, String tipoDeConstruccion) {

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
