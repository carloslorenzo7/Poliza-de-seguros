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

import com.polizaDeSeguros.model.dto.UsuarioDto;
import com.polizaDeSeguros.model.entity.Usuario;
import com.polizaDeSeguros.service.UsuarioService;

import exceptions.ErrorResponse;
import exceptions.exception.ContraseñaInvalidaException;
import exceptions.exception.ErrorAlCrearUsuarioException;
import exceptions.exception.ErrorAlEliminarUsuarioException;
import exceptions.exception.ErrorAlModificarUsuarioException;
import exceptions.exception.UsuarioNoEncontradoException;
import exceptions.exception.ValidacionUsuarioException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	//////////// CLIENTE//////////////////////

	// (POST)Registro de Usuario(cliente)
	@PostMapping("/nuevoUsuario")
	public ResponseEntity<?> crearUsuario(@Valid @RequestBody Usuario usuario) {
		try {
			Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
			return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
		} catch (ValidacionUsuarioException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", e.getMessage()));
		} catch (ErrorAlCrearUsuarioException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
							"Error al crear usuario: " + e.getMessage()));
		}
	}

	// (GET)Ver perfil de usuario (cliente DTO)
	@GetMapping("/perfil/{id}")
	public ResponseEntity<?> verPerfil(@PathVariable Long id) throws UsuarioNoEncontradoException {
		try {
			UsuarioDto usuarioDto = usuarioService.usuarioDtoPorId(id);
			return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
		} catch (UsuarioNoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Not Found", e.getMessage()));
		}
	}

	//////////// ADMIN//////////////

	// (GET)Lista de todos los usuarios (admin)
	@GetMapping("/todos")
	public ResponseEntity<List<Usuario>> listarUsuarios() {
		List<Usuario> usuarios = usuarioService.obtenerTodosLosUsuarios();
		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}

	// (GET)Buscar por id un usuario(admin)
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable Long id) throws UsuarioNoEncontradoException {
		try {
			Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
			return new ResponseEntity<>(usuario, HttpStatus.OK);

		} catch (UsuarioNoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Not Found", e.getMessage()));

		}
	}

	// (PUT)Modificar un usuario(admin)
	@PutMapping("/{id}")
	public ResponseEntity<?> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario)
			throws ErrorAlModificarUsuarioException, UsuarioNoEncontradoException {
		try {
			Usuario usuarioActualizado = usuarioService.modificarUsuario(id, usuario);
			return new ResponseEntity<>(usuarioActualizado, HttpStatus.OK);
		} catch (ErrorAlModificarUsuarioException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
							"Error al modificar usuario: " + e.getMessage()));
		} catch (UsuarioNoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
					"Not Found", "Usuario no encontrado: " + e.getMessage()));
		}
	}

	// (DELETE)Eliminar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<?> eliminarUsuario(@PathVariable Long id) {
		try {
			usuarioService.eliminarUsuario(id);
			return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
		} catch (ErrorAlEliminarUsuarioException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error",
							"Error al eliminar usuario: " + e.getMessage()));
		} catch (UsuarioNoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(HttpStatus.NOT_FOUND.value(),
					"Not Found", "Usuario no encontrado: " + e.getMessage()));
		}
	}

}
