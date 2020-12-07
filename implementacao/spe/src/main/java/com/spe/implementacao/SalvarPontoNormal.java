package com.spe.implementacao;

import java.text.ParseException;
import java.util.Date;
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
	public void salvar(FolhaPonto folhaPonto, Date dia, RegistroPontoService pontoService, FolhaPontoService folhaService, FolhaPontoRepository folhaRepository) throws ParseException {
			Long qtdPontos = pontoService.retornaQuantidadePontos(folhaPonto);
			FolhaDto folhaDto = folhaService.constroiDto(folhaPonto, dia);
			HashMap<Long, AcaoFolhaPonto<FolhaDto, FolhaPontoRepository>> acao = new HashMap<>();
			acao.put(4L, new FinalizarFolhaPonto());
			if(acao.containsKey(qtdPontos)) {
				acao.get(qtdPontos).acao(folhaDto, folhaRepository);
			}else {
				pontoService.salvarRegistroPonto(dia, folhaPonto, TipoMarcacaoSemana.Normal);
			}


		
	}

}
