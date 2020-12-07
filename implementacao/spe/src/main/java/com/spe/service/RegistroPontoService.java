package com.spe.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.interfaces.SalvarPonto;
import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;
import com.spe.model.Usuario;
import com.spe.repository.FolhaPontoRepository;
import com.spe.repository.RegistroPontoRepository;

@Service
public class RegistroPontoService {

	@Autowired
	private RegistroPontoRepository registroPontoRepository;
	
	@Autowired
	private FolhaPontoService folhaPontoService;
	
	@Autowired
	private FolhaPontoRepository folhaPontoRepository;
	
	@Autowired
	private DataService dataService;
	
	@Autowired
	private RegistroPontoService registroPontoService;
	
	
	public void baterPonto(Usuario usuario) throws ParseException{
		LocalDateTime hora = LocalDateTime.now();
		Optional<FolhaPonto> folha = folhaPontoService.retornarFolhaPontoDoUsuarioPorDia(usuario, hora);
		if(folha.isPresent()) {
			salvarPorDiaDaSemana(hora, folha);
		}else {
			Optional<FolhaPonto> novaFolha = folhaPontoService.iniciarFolhaPonto(usuario);
			salvarPorDiaDaSemana(hora, novaFolha);
		}
	}

	private void salvarPorDiaDaSemana(LocalDateTime dia, Optional<FolhaPonto> folha) throws ParseException {
		TipoMarcacaoSemana diaDaSemana = dataService.retornaEnumDiaDaSemana(dia);
		SalvarPonto salvarPonto = diaDaSemana.obter();
		salvarPonto.salvar(folha.get(), dia, registroPontoService, folhaPontoService, folhaPontoRepository);
	}
	
	public void salvarRegistroPonto(LocalDateTime hora, FolhaPonto folha, TipoMarcacaoSemana tpm) {
		RegistroPonto ponto = new RegistroPonto(hora, folha, tpm); 
		registroPontoRepository.save(ponto);
	}
	
	public Long retornaQuantidadePontos(FolhaPonto folha) {
		return registroPontoRepository.countByFolhaPonto(folha);
    }
	
	 public Optional<List<RegistroPonto>> listaDePontos(FolhaPonto folha){
			Optional<List<RegistroPonto>> pontos = registroPontoRepository.findByFolhaPonto(folha);
			return pontos;
	  }

}
