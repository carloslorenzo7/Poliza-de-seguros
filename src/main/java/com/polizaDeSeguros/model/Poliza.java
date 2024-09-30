package com.polizaDeSeguros.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
public class Poliza {

	@Id // primary key de la entidad
	@GeneratedValue(strategy = GenerationType.SEQUENCE) // establece que el id se va a generar de forma automatica y
														// secuencial en la bd
	private Long id;

	private String numeroDePoliza;

	@Temporal(TemporalType.DATE)
	private Date fechaDeInicio;

	@Temporal(TemporalType.DATE)
	private Date fechaDeVencimiento;

	private double montoAsegurado;

	@ManyToOne // Relación muchos a uno con Usuario (un cliente puede tener muchas pólizas)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@ManyToOne // Relación muchos a uno con Seguro (una póliza tiene un tipo de seguro)
	@JoinColumn(name = "seguro_id")
	private Seguro tipoDeSeguro; // referencia a la clase abstracta seguro

	public Poliza() {

	}

	public Poliza(Long id, String numeroDePoliza, Date fechaDeInicio, Date fechaDeVencimiento, double montoAsegurado,
			Usuario usuario, Seguro tipoDeSeguro) {
		this.id = id;
		this.numeroDePoliza = numeroDePoliza;
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.montoAsegurado = montoAsegurado;
		this.usuario = usuario;
		this.tipoDeSeguro = tipoDeSeguro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroDePoliza() {
		return numeroDePoliza;
	}

	public void setNumeroDePoliza(String numeroDePoliza) {
		this.numeroDePoliza = numeroDePoliza;
	}

	public Date getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(Date fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public Date getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(Date fechaDeVencimiento) {
		this.fechaDeVencimiento = fechaDeVencimiento;
	}

	public double getMontoAsegurado() {
		return montoAsegurado;
	}

	public void setMontoAsegurado(double montoAsegurado) {
		this.montoAsegurado = montoAsegurado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Seguro getTipoDeSeguro() {
		return tipoDeSeguro;
	}

	public void setTipoDeSeguro(Seguro tipoDeSeguro) {
		this.tipoDeSeguro = tipoDeSeguro;
	}

}
