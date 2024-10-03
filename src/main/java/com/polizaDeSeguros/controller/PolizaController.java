package com.polizaDeSeguros.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polizaDeSeguros.model.entity.Poliza;
import com.polizaDeSeguros.service.PolizaService;

@RestController
@RequestMapping("/api")
public class PolizaController {

	private final PolizaService polizaService;

	public PolizaController(PolizaService polizaService) {
		this.polizaService = polizaService;
	}
	
	// Endpoint para crear una nueva poliza
	@PostMapping("/polizas")
	public ResponseEntity<Poliza> crearPoliza(@RequestBody Poliza poliza){
	Poliza nuevaPoliza= polizaService.crearPoliza(poliza);
	return new ResponseEntity<>(nuevaPoliza, HttpStatus.CREATED);
	}
}
