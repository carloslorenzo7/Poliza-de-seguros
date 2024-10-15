package com.polizaDeSeguros.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;


@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
@Table(name = "seguro_celular")
public class SeguroCelular extends Seguro {

	private String marca;
	private String modelo;
	private String numeroDeSerie;

	public SeguroCelular() {

	}

	

	public SeguroCelular( String descripcion, String marca, String modelo, String numeroDeSerie) {
		super(descripcion);
		this.marca = marca;
		this.modelo = modelo;
		this.numeroDeSerie = numeroDeSerie;
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

	public String getNumeroDeSerie() {
		return numeroDeSerie;
	}

	public void setNumeroDeSerie(String numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}

}
