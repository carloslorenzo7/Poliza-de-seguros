package exceptions.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import exceptions.exception.ContraseñaInvalidaException;
import exceptions.exception.ErrorAlCrearUsuarioException;
import exceptions.exception.ErrorAlEliminarUsuarioException;
import exceptions.exception.ErrorAlModificarUsuarioException;
import exceptions.exception.UsuarioNoEncontradoException;

@ControllerAdvice
public class HandlerExceptionController {

    @ExceptionHandler(UsuarioNoEncontradoException.class)
    public ResponseEntity<String> handleUsuarioNoEncontrado(UsuarioNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(ErrorAlCrearUsuarioException.class)
    public ResponseEntity<String> handleErrorAlCrearUsuario(ErrorAlCrearUsuarioException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ContraseñaInvalidaException.class)
    public ResponseEntity<String> handleContraseñaInvalida(ContraseñaInvalidaException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(ErrorAlModificarUsuarioException.class)
    public ResponseEntity<String> handleErrorAlModificarUsuario(ErrorAlModificarUsuarioException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(ErrorAlEliminarUsuarioException.class)
    public ResponseEntity<String> handleErrorAlEliminarUsuario(ErrorAlEliminarUsuarioException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
}
