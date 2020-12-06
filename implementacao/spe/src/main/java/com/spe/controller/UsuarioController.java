package com.spe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spe.dto.UsuarioDto;
import com.spe.model.Usuario;
import com.spe.service.UsuarioService;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;

	@PostMapping("/novo")
	public ResponseEntity<UsuarioDto> salvar(@RequestBody Usuario usuario){
		UsuarioDto usuarioNovo = usuarioService.salvarUsuario(usuario);
		return ResponseEntity.ok(usuarioNovo);
	}
}
