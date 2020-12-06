package com.spe.service;

import java.util.Date;

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
	
	
	public void monitoramentoPontoDiasNormais(Usuario usuario){
		Date dia = new Date();
		FolhaPonto folha = folhaPontoService.retornarFolhaPontoDoUsuarioPorDia(usuario, dia);
		if(folha != null) {
			salvarPonto(dia, folha);
			if(retornaQuantidadePontos(folha) == 4) {
				folhaPontoService.finalizarFolhaPonto(folha);
			}
		}else {
			folhaPontoService.iniciarFolhaPonto(usuario);
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
