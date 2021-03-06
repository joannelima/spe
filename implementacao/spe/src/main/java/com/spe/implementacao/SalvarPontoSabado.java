package com.spe.implementacao;

import java.time.LocalDateTime;
import java.util.HashMap;

import com.spe.dto.FolhaDto;
import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.interfaces.SalvarPonto;
import com.spe.model.FolhaPonto;
import com.spe.repository.FolhaPontoRepository;
import com.spe.service.FolhaPontoService;
import com.spe.service.RegistroPontoService;

public class SalvarPontoSabado implements SalvarPonto {

	@Override
	public void salvar(FolhaPonto folhaPonto, LocalDateTime hora, RegistroPontoService pontoService, FolhaPontoService folhaService, FolhaPontoRepository folhaRepository) {
			Long qtdPontos = pontoService.retornaQuantidadePontos(folhaPonto);
			HashMap<Long, AcaoFolhaPonto<FolhaDto, FolhaPontoRepository>> acao = new HashMap<>();
			//acao.put(2L, new FinalizarFolhaPonto());
			if(acao.containsKey(qtdPontos)) {
//				acao.get(qtdPontos).acao(folhaDto, folhaRepository);
			}else {
				pontoService.salvarRegistroPonto(hora, folhaPonto, TipoMarcacaoSemana.FimDeSemana);
			}
		
	}

}
