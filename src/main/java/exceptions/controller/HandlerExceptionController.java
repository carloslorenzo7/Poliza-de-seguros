package exceptions.controller;

import java.security.PublicKey;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import exceptions.ErrorResponse;
import exceptions.exception.ErrorAlCrearUsuarioException;
import exceptions.exception.ErrorAlEliminarUsuarioException;
import exceptions.exception.ErrorAlModificarUsuarioException;
import exceptions.exception.PolizaException;
import exceptions.exception.SeguroException;
import exceptions.exception.UsuarioNoEncontradoException;
import exceptions.exception.ValidacionUsuarioException;

@ControllerAdvice
public class HandlerExceptionController {

	@ExceptionHandler(UsuarioNoEncontradoException.class)
	public ResponseEntity<ErrorResponse> handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Usuario No Encontrado", ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	// @ExceptionHandler(UsuarioNoEncontradoException.class)
	// public ResponseEntity<String>
	// handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
	// System.out.println("Manejando UsuarioNoEncontradoException: " +
	// ex.getMessage());
	// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	// }

	 @ExceptionHandler(ErrorAlCrearUsuarioException.class)
	    public ResponseEntity<ErrorResponse> handleErrorAlCrearUsuario(ErrorAlCrearUsuarioException ex) {
	        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error al crear usuario", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }

	 @ExceptionHandler(ValidacionUsuarioException.class)
	    public ResponseEntity<ErrorResponse> handleValidacionUsuarioException(ValidacionUsuarioException ex) {
	        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de validación", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	    }
	
	
	

	 @ExceptionHandler(ErrorAlModificarUsuarioException.class)
	    public ResponseEntity<ErrorResponse> handleErrorAlModificarUsuario(ErrorAlModificarUsuarioException ex) {
	        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al modificar usuario", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 @ExceptionHandler(ErrorAlEliminarUsuarioException.class)
	    public ResponseEntity<ErrorResponse> handleErrorAlEliminarUsuario(ErrorAlEliminarUsuarioException ex) {
	        ErrorResponse error = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error al eliminar usuario", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	 
	 
	 @ExceptionHandler(PolizaException.class)
		 public ResponseEntity<ErrorResponse>handlePolizaError(PolizaException ex){
			 ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de validación", ex.getMessage());
		        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
		 }
	 
	 @ExceptionHandler(SeguroException.class)
	 public ResponseEntity<ErrorResponse>handlePolizaError(SeguroException ex){
		 ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Error de validación", ex.getMessage());
	        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	 }
	 
	 
	 }



