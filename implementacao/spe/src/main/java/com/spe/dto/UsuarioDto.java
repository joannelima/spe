package com.spe.dto;

import org.modelmapper.ModelMapper;

import com.spe.model.Usuario;

import lombok.Data;

@Data
public class UsuarioDto {

	private Integer id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String cpf;
	
	public static UsuarioDto create(Usuario usuario) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(usuario, UsuarioDto.class);
	}
}
