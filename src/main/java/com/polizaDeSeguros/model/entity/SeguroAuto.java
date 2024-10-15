package com.polizaDeSeguros.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
@Table(name = "seguro_auto")
public class SeguroAuto extends Seguro {

	private String marca;
	private String modelo;
	private String patente;

	public SeguroAuto() {

	}

	

	public SeguroAuto( String descripcion, String marca, String modelo, String patente) {
		super(descripcion);
		this.marca = marca;
		this.modelo = modelo;
		this.patente = patente;
	}



	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPatente() {
		return patente;
	}

	public void setPatente(String patente) {
		this.patente = patente;
	}

}
