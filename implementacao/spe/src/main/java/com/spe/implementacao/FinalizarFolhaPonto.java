package com.spe.implementacao;

import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.model.FolhaPonto;
import com.spe.repository.FolhaPontoRepository;

public class FinalizarFolhaPonto implements AcaoFolhaPonto<FolhaPonto, FolhaPontoRepository>{

	@Override
	public FolhaPonto acao(FolhaPonto folhaPonto, FolhaPontoRepository folhaPontoRepository) {
		 folhaPontoRepository.saveAndFlush(folhaPonto);
		 return folhaPonto;
	}

}
