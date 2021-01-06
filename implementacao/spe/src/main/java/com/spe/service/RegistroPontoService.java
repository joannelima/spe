package com.spe.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.interfaces.SalvarPonto;
import com.spe.mapper.RegistroPontoMapper;
import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;
import com.spe.model.Usuario;

@Service
@AllArgsConstructor
public class RegistroPontoService {

	@Autowired
	private FolhaPontoService folhaPontoService;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private RegistroPontoService registroPontoService;
	
	private RegistroPontoMapper registroPontoMapper;
	
	public void baterPonto(Usuario usuario) {
		LocalDateTime hora = LocalDateTime.now();
		Optional<FolhaPonto> folha = folhaPontoService.retornarFolhaPontoDoUsuarioPorDia(usuario, hora);
		if(folha.isPresent()) {
			salvarPorDiaDaSemana(hora, folha);
		}
		
		Optional<FolhaPonto> novaFolha = folhaPontoService.iniciarFolhaPonto(usuario);
		salvarPorDiaDaSemana(hora, novaFolha);
		
	}

	private void salvarPorDiaDaSemana(LocalDateTime dia, Optional<FolhaPonto> folha) {
		TipoMarcacaoSemana diaDaSemana = dataService.retornaEnumDiaDaSemana(dia);
		SalvarPonto salvarPonto = diaDaSemana.obter();
		salvarPonto.salvar(folha.get(), dia, registroPontoService, folhaPontoService, folhaPontoRepository);
	}
	
	public void salvarRegistroPonto(LocalDateTime hora, FolhaPonto folha, TipoMarcacaoSemana tpm) {
		RegistroPonto ponto =  RegistroPonto.builder().
									tipoMarcacaoSemana(tpm).
									folhaPonto(folha).
									horasMarcacao(hora).build(); 
		registroPontoMapper.inserirNovo(ponto);
		
	}
	
	public Long retornaQuantidadePontos(FolhaPonto folha) {
		return registroPontoRepository.countByFolhaPonto(folha);
    }
	
	 public Optional<List<RegistroPonto>> listaDePontos(FolhaPonto folha){
			Optional<List<RegistroPonto>> pontos = registroPontoRepository.findByFolhaPonto(folha);
			return pontos;
	  }

}
