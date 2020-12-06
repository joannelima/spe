package com.spe.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.implementacao.IniciarFolhaPonto;
import com.spe.interfaces.AcaoFolhaPonto;
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
		Date dia = new Date();
		Optional<FolhaPonto> folha = folhaPontoService.retornarFolhaPontoDoUsuarioPorDia(usuario, dia);
		if(folha.isPresent()) {
			salvarPorDiaDaSemana(dia, folha);
		}else {
			Optional<FolhaPonto> novaFolha = folhaPontoService.iniciarFolhaPonto(usuario);
			salvarPorDiaDaSemana(dia, novaFolha);
		}
	}

	private void salvarPorDiaDaSemana(Date dia, Optional<FolhaPonto> folha) {
		TipoMarcacaoSemana diaDaSemana = dataService.retornaEnumDiaDaSemana(dia);
		SalvarPonto salvarPonto = diaDaSemana.obter();
		salvarPonto.salvar(folha.get(), dia, registroPontoService, folhaPontoRepository);
	}
	
	public void salvarRegistroPonto(Date dia, FolhaPonto folha, TipoMarcacaoSemana tpm) {
		RegistroPonto ponto = new RegistroPonto(dia, folha, tpm); 
		registroPontoRepository.save(ponto);
	}
	
	public Long retornaQuantidadePontos(FolhaPonto folha) {
		return registroPontoRepository.countByFolhaPonto(folha);
    }


	
}
