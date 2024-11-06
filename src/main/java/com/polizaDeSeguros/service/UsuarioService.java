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
import exceptions.exception.ValidacionUsuarioException;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	// Creo un nuevo Usuario (cliente)

	public Usuario crearUsuario(Usuario usuario) throws ErrorAlCrearUsuarioException, ValidacionUsuarioException {

		if (usuario == null) {
			throw new ValidacionUsuarioException("Error al crear usuario: el usuario no puede ser null.");
		}

		if (usuario.getNombre() == null || usuario.getNombre().length() < 5) {
			 System.out.println("Lanzando ValidacionUsuarioException para el nombre");
			throw new ValidacionUsuarioException("El nombre debe tener al menos 5 caracteres.");
		}
		if (usuario.getApellido() == null || usuario.getApellido().length() < 5) {
			throw new ValidacionUsuarioException("El apellido debe tener al menos 5 caracteres.");
		}

		if (usuario.getDni() == null || !usuario.getDni().matches("\\d{7,8}")) {
			throw new ValidacionUsuarioException("El DNI debe tener entre 7 y 8 dígitos.");
		}

		if (usuario.getTelefono() == null || !usuario.getTelefono().matches("\\d{8,15}")) {
			throw new ValidacionUsuarioException("El teléfono debe tener entre 8 y 15 dígitos.");
		}

		if (usuario.getDireccion() == null || usuario.getDireccion().length() < 5) {
			throw new ValidacionUsuarioException("La dirección debe tener al menos 5 caracteres.");
		}
		if (usuario.getEmail() == null || !usuario.getEmail().contains("@")) {
			throw new ValidacionUsuarioException("El email debe contener un '@' válido.");
		}

		if (usuario.getPassword() == null || usuario.getPassword().length() < 8) {
			throw new ValidacionUsuarioException("La contraseña debe tener al menos 8 caracteres.");
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
	public Usuario modificarUsuario(Long id, Usuario usuarioModificado) throws ErrorAlModificarUsuarioException, UsuarioNoEncontradoException {
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
	public void eliminarUsuario(Long id) throws ErrorAlEliminarUsuarioException, UsuarioNoEncontradoException {
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
