package com.spe.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.spe.dto.PontoDto;
import com.spe.dto.UsuarioDto;
import com.spe.exception.UsuarioNotFoundException;
import com.spe.model.Usuario;
import com.spe.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioDto salvarUsuario(Usuario usuario){
		Assert.isNull(usuario.getId(), "Não foi possível inserir");
		return UsuarioDto.create(usuarioRepository.save(usuario));
	}
	
	public Optional<Usuario> buscarUsuarioPorCPF(String cpf) {
		return usuarioRepository.findByCpf(cpf);
	}
	
	public Optional<Usuario> verificaUsuario(PontoDto ponto) {
		
		return Optional.ofNullable(buscarUsuarioPorCPF(ponto.getCpf())
				.orElseThrow(() -> new UsuarioNotFoundException("O usuário de cpf: " + ponto.getCpf() + "não foi encontrado")));
		
	}
	

}
