package com.polizaDeSeguros.model.dto;

import java.time.LocalDate;

import com.polizaDeSeguros.enums.EstadoPoliza;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PolizaRequest {

	@NotNull
	private Long usuarioId; 

	@NotNull
	@Size(min = 1)
	private String numeroDePoliza;

	@NotNull
	private LocalDate fechaDeInicio;

	@NotNull
	private LocalDate fechaDeVencimiento;

	@NotNull
	private double montoAsegurado;

	@NotNull
	private String tipoDeSeguro; 

	@NotNull
    private EstadoPoliza estado;
	
	////////////// Campos de SeguroAuto
	private String descripcionAuto;
	private String marcaAuto;
	private String modeloAuto;
	private String patenteAuto;

	/////////////////// Campos de SeguroInmueble
	private String descripcionInmueble;
	private String direccionInmueble;
	private String tipoDeConstruccionInmueble;

	/////// Campos SeguroCelular
	private String descripcionCelular;
	private String marcaCelular;
	private String modeloCelular;
	private String numeroDeSerieCelular;

	

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
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

	public String getTipoDeSeguro() {
		return tipoDeSeguro;
	}

	public void setTipoDeSeguro(String tipoDeSeguro) {
		this.tipoDeSeguro = tipoDeSeguro;
	}

	
	// Getters y setters para SeguroAuto
	
	
	public EstadoPoliza getEstado() {
		return estado;
	}

	public void setEstado(EstadoPoliza estado) {
		this.estado = estado;
	}

	public String getDescripcionAuto() {
		return descripcionAuto;
	}

	public void setDescripcionAuto(String descripcionAuto) {
		this.descripcionAuto = descripcionAuto;
	}

	public String getMarcaAuto() {
		return marcaAuto;
	}

	public void setMarcaAuto(String marcaAuto) {
		this.marcaAuto = marcaAuto;
	}

	public String getModeloAuto() {
		return modeloAuto;
	}

	public void setModeloAuto(String modeloAuto) {
		this.modeloAuto = modeloAuto;
	}

	public String getPatenteAuto() {
		return patenteAuto;
	}

	public void setPatenteAuto(String patenteAuto) {
		this.patenteAuto = patenteAuto;
	}
	
	// Getters y setters para SeguroInmueble
	
	public String getDescripcionInmueble() {
		return descripcionInmueble;
	}

	public void setDescripcionInmueble(String descripcionInmueble) {
		this.descripcionInmueble = descripcionInmueble;
	}

	public String getDireccionInmueble() {
		return direccionInmueble;
	}

	public void setDireccionInmueble(String direccionInmueble) {
		this.direccionInmueble = direccionInmueble;
	}

	public String getTipoDeConstruccionInmueble() {
		return tipoDeConstruccionInmueble;
	}

	public void setTipoDeConstruccionInmueble(String tipoDeConstruccionInmueble) {
		this.tipoDeConstruccionInmueble = tipoDeConstruccionInmueble;
	}
		
	// Getters y setters para SeguroCelular
	
	public String getDescripcionCelular() {
		return descripcionCelular;
	}

	public void setDescripcionCelular(String descripcionCelular) {
		this.descripcionCelular = descripcionCelular;
	}

	public String getMarcaCelular() {
		return marcaCelular;
	}

	public void setMarcaCelular(String marcaCelular) {
		this.marcaCelular = marcaCelular;
	}

	public String getModeloCelular() {
		return modeloCelular;
	}

	public void setModeloCelular(String modeloCelular) {
		this.modeloCelular = modeloCelular;
	}

	public String getNumeroDeSerieCelular() {
		return numeroDeSerieCelular;
	}

	public void setNumeroDeSerieCelular(String numeroDeSerieCelular) {
		this.numeroDeSerieCelular = numeroDeSerieCelular;
	}

	@Override
	public String toString() {
		return "PolizaRequest [numeroDePoliza=" + numeroDePoliza + ", fechaDeInicio=" + fechaDeInicio
				+ ", fechaDeVencimiento=" + fechaDeVencimiento + ", montoAsegurado=" + montoAsegurado
				+ ", tipoDeSeguro=" + tipoDeSeguro + ", estado=" + estado + ", descripcionAuto=" + descripcionAuto
				+ ", marcaAuto=" + marcaAuto + ", modeloAuto=" + modeloAuto + ", patenteAuto=" + patenteAuto
				+ ", descripcionInmueble=" + descripcionInmueble + ", direccionInmueble=" + direccionInmueble
				+ ", tipoDeConstruccionInmueble=" + tipoDeConstruccionInmueble + ", descripcionCelular="
				+ descripcionCelular + ", marcaCelular=" + marcaCelular + ", modeloCelular=" + modeloCelular
				+ ", numeroDeSerieCelular=" + numeroDeSerieCelular + "]";
	}

	

	
	
	
}
