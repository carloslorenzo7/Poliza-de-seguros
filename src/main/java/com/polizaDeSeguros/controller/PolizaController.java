package com.polizaDeSeguros.controller;


import java.util.List;


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

import exceptions.ErrorResponse;

import exceptions.exception.PolizaException;
import exceptions.exception.SeguroException;
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
	public ResponseEntity<?> crearPoliza(@Valid @RequestBody PolizaRequest polizaRequest) throws SeguroException, PolizaException {
		try {
			
			Poliza nuevaPoliza = polizaService.crearPoliza(polizaRequest);
			return new ResponseEntity<>(nuevaPoliza, HttpStatus.CREATED);
		} catch (SeguroException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de seguro", e.getMessage()));
	    } catch (PolizaException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de póliza", e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno", "Se produjo un error inesperado"));
	    }
	}
	
	
	//Endpoint para obetenr polizas
	@GetMapping("/polizas")
	public ResponseEntity<List<Poliza>> listaPolizas(){
		List<Poliza> polizas= polizaService.obtenerTodasLasPolizas();
		return new ResponseEntity<>(polizas,HttpStatus.OK);
	}
	
	// Endpoint para obtener poliza por id
	@GetMapping("/polizas/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws PolizaException {
		try {
			Poliza poliza = polizaService.obtenerPolizaPorId(id); 
			return new ResponseEntity<>(poliza, HttpStatus.OK);
			
		} catch (PolizaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de póliza", e.getMessage()));
		}
	}
	
	//Endpoint para actulizar poliza
	@PutMapping("/polizas/{id}")
	public ResponseEntity<?> actualizarPoliza(@PathVariable Long id, @RequestBody PolizaRequest polizaRequest) {
	    try {
	        Poliza polizaActualizada = polizaService.actualizarPoliza(id, polizaRequest);
	        return new ResponseEntity<>(polizaActualizada, HttpStatus.OK);
	    } catch (SeguroException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de seguro", e.getMessage()));
	    } catch (PolizaException e) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de póliza", e.getMessage()));
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno", "Se produjo un error inesperado"));
	    }
	}

	
	//Enppoint para eliminar poliza
	@DeleteMapping("/polizas/{id}")
	public ResponseEntity<?> eliminarPoliza(@PathVariable Long id) throws PolizaException{
		try {
			polizaService.eliminarPoliza(id);
			return new ResponseEntity<>("Poliza eliminada exitosamente", HttpStatus.OK);
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
	                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de póliza", e.getMessage()));
		}
	}
}
