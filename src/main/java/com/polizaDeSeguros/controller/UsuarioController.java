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

import exceptions.exception.ContraseñaInvalidaException;
import exceptions.exception.ErrorAlCrearUsuarioException;
import exceptions.exception.ErrorAlEliminarUsuarioException;
import exceptions.exception.ErrorAlModificarUsuarioException;
import exceptions.exception.UsuarioNoEncontradoException;
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
	public ResponseEntity<Usuario> crearUsuario(@Valid @RequestBody Usuario usuario)
			throws ErrorAlCrearUsuarioException, ContraseñaInvalidaException {
		Usuario nuevoUsuario = usuarioService.crearUsuario(usuario);
		return new ResponseEntity<>(nuevoUsuario, HttpStatus.CREATED);
	}

	// (GET)Ver perfil de usuario (cliente DTO)
	@GetMapping("/perfil/{id}")
	public ResponseEntity<UsuarioDto> verPerfil(@PathVariable Long id) throws UsuarioNoEncontradoException {
		UsuarioDto usuarioDto = usuarioService.usuarioDtoPorId(id);
		return new ResponseEntity<>(usuarioDto, HttpStatus.OK);
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
	public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) throws UsuarioNoEncontradoException {
		Usuario usuario = usuarioService.obtenerUsuarioPorId(id);
		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	// (PUT)Modificar un usuario(admin)
	@PutMapping("/{id}")
	public ResponseEntity<Usuario> modificarUsuario(@PathVariable Long id, @RequestBody Usuario usuario)
			throws ErrorAlModificarUsuarioException {
		Usuario usuarioActulizado = usuarioService.modificarUsuario(id, usuario);
		return new ResponseEntity<>(usuarioActulizado, HttpStatus.OK);
	}

	// (DELETE)Eliminar un usuario
	@DeleteMapping("/{id}")
	public ResponseEntity<String> eliminarUsuario(@PathVariable Long id) throws ErrorAlEliminarUsuarioException {
		usuarioService.eliminarUsuario(id);
		return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
	}

}
