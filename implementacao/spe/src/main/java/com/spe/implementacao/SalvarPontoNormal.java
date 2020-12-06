package com.spe.implementacao;

import java.util.Date;
import java.util.HashMap;

import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.interfaces.SalvarPonto;
import com.spe.model.FolhaPonto;
import com.spe.repository.FolhaPontoRepository;
import com.spe.service.RegistroPontoService;

public class SalvarPontoNormal implements SalvarPonto {

	@Override
	public void salvar(FolhaPonto folhaPonto, Date dia, RegistroPontoService pontoService, FolhaPontoRepository folhaRepository) {
			Long qtdPontos = pontoService.retornaQuantidadePontos(folhaPonto);
			HashMap<Long, AcaoFolhaPonto> acao = new HashMap<>();
			acao.put(4L, new FinalizarFolhaPonto());
			if(acao.containsKey(qtdPontos)) {
				acao.get(qtdPontos).acao(folhaPonto.getUsuario(), folhaRepository);
			}else {
				pontoService.salvarRegistroPonto(dia, folhaPonto, TipoMarcacaoSemana.Normal);
			}


		
	}

}
