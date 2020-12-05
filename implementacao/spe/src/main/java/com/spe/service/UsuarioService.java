package com.spe.service;

import org.springframework.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spe.dto.UsuarioDto;
import com.spe.model.Usuario;
import com.spe.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	public UsuarioDto salvarUsuario(Usuario usuario){
		Assert.isNull(usuario.getId(), "Não foi possível inserir");
		return UsuarioDto.create(usuarioRepository.save(usuario));
	}
	
}
