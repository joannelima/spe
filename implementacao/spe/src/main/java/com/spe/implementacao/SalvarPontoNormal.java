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

public class SalvarPontoNormal implements SalvarPonto {

	@Override
	public void salvar(FolhaPonto folhaPonto, LocalDateTime dia, RegistroPontoService pontoService, FolhaPontoService folhaService, FolhaPontoRepository folhaRepository) {
			Long qtdPontos = pontoService.retornaQuantidadePontos(folhaPonto);
			HashMap<Long, AcaoFolhaPonto<FolhaDto, FolhaPontoRepository>> acao = new HashMap<>();
			if(acao.containsKey(qtdPontos)) {
			//	acao.get(qtdPontos).acao(folhaDto, folhaRepository);
			}
			
			pontoService.salvarRegistroPonto(dia, folhaPonto, TipoMarcacaoSemana.Normal);
	}

}
