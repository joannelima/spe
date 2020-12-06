package com.spe.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;
import com.spe.model.Usuario;
import com.spe.repository.RegistroPontoRepository;

@Service
public class RegistroPontoService {

	@Autowired
	private RegistroPontoRepository registroPontoRepository;
	
	@Autowired
	private FolhaPontoService folhaPontoService;
	
	@Autowired
	private DataService dataService;
	
	
	public void monitoramentoPontoDiasNormais(Usuario usuario) throws ParseException{
		Date dia = new Date();
		Date dataFormatada = dataService.formatarData(dia);
		Optional<FolhaPonto> folha = folhaPontoService.retornarFolhaPontoDoUsuarioPorDia(usuario, dataFormatada);
		if(folha.isPresent()) {
			salvarPonto(dia, folha.get());
			if(retornaQuantidadePontos(folha.get()) == 4) {
				folhaPontoService.finalizarFolhaPonto(folha.get());
			}
		}else {
			Optional<FolhaPonto> novaFolha = folhaPontoService.iniciarFolhaPonto(usuario);
			salvarPonto(dia, novaFolha.get());
		}
	}


	private void salvarPonto(Date dia, FolhaPonto folha) {
		RegistroPonto ponto = new RegistroPonto(dia, folha, TipoMarcacaoSemana.Normal); 
		registroPontoRepository.save(ponto);
	}
	
	 public Long retornaQuantidadePontos(FolhaPonto folha) {
		 return registroPontoRepository.countByFolhaPonto(folha);
	  }


	
}
