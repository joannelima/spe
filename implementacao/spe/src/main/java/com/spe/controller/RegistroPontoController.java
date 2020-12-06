package com.spe.controller;

import java.text.ParseException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spe.dto.PontoDto;
import com.spe.model.Usuario;
import com.spe.service.RegistroPontoService;
import com.spe.service.UsuarioService;

@RestController
@RequestMapping("/v1/registro")
public class RegistroPontoController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RegistroPontoService registroPontoService;
	

	@PostMapping("/ponto")
	public ResponseEntity<Usuario> baterPonto(@RequestBody PontoDto ponto) throws ParseException{
		Optional<Usuario> usuario = usuarioService.buscarUsuarioPorCPF(ponto.getCpf());
		if(usuario.isPresent()) {
			registroPontoService.monitoramentoPontoDiasNormais(usuario.get());
			return ResponseEntity.ok(usuario.get());
		}else {
			return ResponseEntity.notFound().build();
		}
		
	}
}
