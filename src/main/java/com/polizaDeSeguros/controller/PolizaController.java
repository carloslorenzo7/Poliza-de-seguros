package com.polizaDeSeguros.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.polizaDeSeguros.model.dto.PolizaRequest;
import com.polizaDeSeguros.model.entity.Poliza;
import com.polizaDeSeguros.service.PolizaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PolizaController {

	private final PolizaService polizaService;

	public PolizaController(PolizaService polizaService) {
		this.polizaService = polizaService;
	}
	
	// Endpoint para crear una nueva poliza
	@PostMapping("/polizas")
	public ResponseEntity<Poliza> crearPoliza(@Valid @RequestBody PolizaRequest polizaRequest) {
	    Poliza nuevaPoliza = polizaService.crearPoliza(polizaRequest);
	    return new ResponseEntity<>(nuevaPoliza, HttpStatus.CREATED);
	}
	
	
	//Endpoint para obetenr polizas
	@GetMapping("/polizas")
	public ResponseEntity<List<Poliza>> listaPolizas(){
		List<Poliza> polizas= polizaService.obtenerTodasLasPolizas();
		return new ResponseEntity<>(polizas,HttpStatus.OK);
	}
	
	// Endpoint para obtener poliza por id
	@GetMapping("/polizas/{id}")
	public ResponseEntity<Poliza> buscarPorId(@PathVariable Long id) {
	    Optional<Poliza> poliza = polizaService.obtenerPolizaPorId(id);
	    
	    if (poliza.isPresent()) {
	        return new ResponseEntity<>(poliza.get(), HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
	
	//Endpoint para actulizar poliza
	@PutMapping("/polizas/{id}")
	public ResponseEntity<Poliza> actualizarPoliza(@PathVariable Long id, @RequestBody PolizaRequest polizaRequest) {
	    try {
	        Poliza polizaActualizada = polizaService.actualizarPoliza(id, polizaRequest);
	        return new ResponseEntity<>(polizaActualizada, HttpStatus.OK);
	    } catch (RuntimeException e) {
	        // Manejo de excepciones si la póliza no se encuentra
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    } catch (Exception e) {
	        // Manejo de excepciones genéricas
	        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}

	
	//Enppoint para eliminar poliza
	@DeleteMapping("/polizas/{id}")
	public ResponseEntity<String> eliminarPoliza(@PathVariable Long id){
		polizaService.eliminarPoliza(id);
		return new ResponseEntity<>("Poliza eliminada exitosamente", HttpStatus.OK);
	}
}
