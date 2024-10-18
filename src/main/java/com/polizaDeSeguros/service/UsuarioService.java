package com.polizaDeSeguros.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.polizaDeSeguros.enums.Roles;
import com.polizaDeSeguros.model.dto.UsuarioDto;
import com.polizaDeSeguros.model.entity.Usuario;
import com.polizaDeSeguros.repository.UsuarioRepository;

import exceptions.exception.ContraseñaInvalidaException;
import exceptions.exception.ErrorAlCrearUsuarioException;
import exceptions.exception.ErrorAlEliminarUsuarioException;
import exceptions.exception.ErrorAlModificarUsuarioException;
import exceptions.exception.UsuarioNoEncontradoException;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	// Creo un nuevo Usuario (cliente)

	public Usuario crearUsuario(Usuario usuario) throws ErrorAlCrearUsuarioException, ContraseñaInvalidaException {

		if (usuario == null) {
			throw new ErrorAlCrearUsuarioException("Error al crear usuario: el usuario no puede ser null.");
		}

		if (usuario.getPassword().length() < 8) {
			throw new ContraseñaInvalidaException("La contraseña debe tener al menos 8 caracteres.");
		}

		usuario.setNombre(usuario.getNombre());
		usuario.setApellido(usuario.getApellido());
		usuario.setDni(usuario.getDni());
		usuario.setTelefono(usuario.getTelefono());
		usuario.setDireccion(usuario.getDireccion());
		usuario.setEmail(usuario.getEmail());
		usuario.setPassword(usuario.getPassword());
		// asigno el rol como Cliente por defecto
		usuario.setRol(Roles.CLIENTE);

		return usuarioRepository.save(usuario);

	}

	// Obtener usuario por id(cliente)
	public UsuarioDto usuarioDtoPorId(Long id) throws UsuarioNoEncontradoException {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
		return convertirAUsuarioDto(usuario);
	}

	// Obtener todos los usuario (Admin)
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioRepository.findAll();
	}

	// Buscar un usuario por id (Admin)
	public Usuario obtenerUsuarioPorId(Long id) throws UsuarioNoEncontradoException {
		return usuarioRepository.findById(id)
				.orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
	}

	// Modificar un usuario (Admin)
	public Usuario modificarUsuario(Long id, Usuario usuarioModificado) throws ErrorAlModificarUsuarioException {
		Usuario usuario = usuarioRepository.findById(id)
				.orElseThrow(() -> new ErrorAlModificarUsuarioException("Error al modificar usuario"));

		usuario.setNombre(usuarioModificado.getNombre());
		usuario.setApellido(usuarioModificado.getApellido());
		usuario.setDni(usuarioModificado.getDni());
		usuario.setTelefono(usuarioModificado.getTelefono());
		usuario.setDireccion(usuarioModificado.getDireccion());
		usuario.setEmail(usuarioModificado.getEmail());
		usuario.setPassword(usuarioModificado.getPassword());
		usuario.setRol(usuarioModificado.getRol());
		return usuarioRepository.save(usuario);

	}

	// Eliminar usuario (Admin)
	public void eliminarUsuario(Long id) throws ErrorAlEliminarUsuarioException {
		usuarioRepository.findById(id)
				.orElseThrow(() -> new ErrorAlEliminarUsuarioException("Error al eliminar: Usuario no encontrado"));

		usuarioRepository.deleteById(id);
	}

	// conversion de Usuario a UsuarioDto(cliente)

	private UsuarioDto convertirAUsuarioDto(Usuario usuario) {
		return new UsuarioDto(usuario.getNombre(), usuario.getApellido(), usuario.getDni(), usuario.getTelefono(),
				usuario.getDireccion(), usuario.getEmail(), usuario.getRol().toString());

	}

}
