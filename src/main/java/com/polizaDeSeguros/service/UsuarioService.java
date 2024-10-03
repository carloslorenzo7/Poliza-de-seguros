package com.polizaDeSeguros.service;


import org.springframework.stereotype.Service;


import com.polizaDeSeguros.model.dto.UsuarioDto;
import com.polizaDeSeguros.model.entity.Usuario;
import com.polizaDeSeguros.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
    
	
	
	public UsuarioService(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}
	
	// creo un nuevo Usuario
	
	public Usuario crearUsuario(UsuarioDto usuarioDto) {
		 Usuario usuario = new Usuario();
	        usuario.setNombre(usuarioDto.getNombre());
	        usuario.setApellido(usuarioDto.getApellido());
	        usuario.setEmail(usuarioDto.getEmail());
	        usuario.setPassword("defaultPassword");
	        return usuarioRepository.save(usuario);
		
	}
	
}
