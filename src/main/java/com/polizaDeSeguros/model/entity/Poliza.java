package com.polizaDeSeguros.model.entity;

import java.time.LocalDate;

import com.polizaDeSeguros.enums.EstadoPoliza;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity // especifica la creacion de una entidad. Se coloca al inciio de la clase
@Table(name = "poliza")
public class Poliza {

	@Id // primary key de la entidad
	@GeneratedValue(strategy = GenerationType.IDENTITY) // establece que el id se va a generar de forma automatica y secuencial en la bd
	private Long id;

	@NotEmpty
	@Column(unique = true)
	private String numeroDePoliza;
	
	@NotNull
	private LocalDate fechaDeInicio; // LocalDate para que guarde solo fechas sin horario
	@NotNull
	private LocalDate fechaDeVencimiento;

	@Column(nullable = false)
	private double montoAsegurado;

	@ManyToOne // Relación muchos a uno con Usuario (un cliente puede tener muchas pólizas)
	@JoinColumn(name = "usuario_id",nullable = false)
	private Usuario usuario;

	@ManyToOne // Relación muchos a uno con Seguro (una póliza tiene un tipo de seguro)
	@JoinColumn(name = "seguro_id",nullable = false)
	private Seguro tipoDeSeguro; // referencia a la clase abstracta seguro

	@Enumerated(EnumType.STRING) // Almacena el enum como texto en la base de datos
	@Column(nullable = false)
	private EstadoPoliza estado; // Enum para el estado de la póliza

	public Poliza() {

	}

	public Poliza( String numeroDePoliza, LocalDate fechaDeInicio, LocalDate fechaDeVencimiento,
			double montoAsegurado, Usuario usuario, Seguro tipoDeSeguro, EstadoPoliza estado) {
		this.numeroDePoliza = numeroDePoliza;
		this.fechaDeInicio = fechaDeInicio;
		this.fechaDeVencimiento = fechaDeVencimiento;
		this.montoAsegurado = montoAsegurado;
		this.usuario = usuario;
		this.tipoDeSeguro = tipoDeSeguro;
		this.estado = estado;
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

	public LocalDate getFechaDeInicio() {
		return fechaDeInicio;
	}

	public void setFechaDeInicio(LocalDate fechaDeInicio) {
		this.fechaDeInicio = fechaDeInicio;
	}

	public LocalDate getFechaDeVencimiento() {
		return fechaDeVencimiento;
	}

	public void setFechaDeVencimiento(LocalDate fechaDeVencimiento) {
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

	public EstadoPoliza getEstado() {
		return estado;
	}

	public void setEstado(EstadoPoliza estado) {
		this.estado = estado;
	}

	

}
