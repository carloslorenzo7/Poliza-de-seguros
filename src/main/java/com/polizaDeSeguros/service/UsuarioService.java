package com.polizaDeSeguros.service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.polizaDeSeguros.enums.Roles;
import com.polizaDeSeguros.model.dto.UsuarioDto;
import com.polizaDeSeguros.model.entity.Usuario;
import com.polizaDeSeguros.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
    
	
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	// Creo un nuevo Usuario (cliente)
	
	public Usuario crearUsuario(Usuario usuario) {
		
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
	public UsuarioDto usuarioDtoPorId(Long id) {
		Usuario usuario= usuarioRepository.findById(id)
				  .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		return convertirAUsuarioDto(usuario);
	}

	// Obtener todos los usuario (Admin)
	public List<Usuario>obtenerTodosLosUsuarios(){
		return usuarioRepository.findAll();
	}
	
	// Buscar un usuario por id (Admin)
	public Usuario obtenerUsuarioPorId(Long id) {
		return usuarioRepository.findById(id)
				 .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
	}
	
	// Modificar un usuario (Admin)
	public Usuario modificarUsuario(Long id, Usuario usuarioModificado) {
		Usuario usuario =usuarioRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
		
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
	
	// Eliminr usuario (Admin)
	public void eliminarUsuario(Long id) {
		usuarioRepository.deleteById(id);
	}
	
	// conversion de Usuario a UsuarioDto(cliente)
	
	private UsuarioDto convertirAUsuarioDto(Usuario usuario) {
		return new UsuarioDto(
				usuario.getNombre(),
                usuario.getApellido(),
                usuario.getDni(),
                usuario.getTelefono(),
                usuario.getDireccion(),
                usuario.getEmail(),
                usuario.getRol().toString()
				);
				
	}
	
}
