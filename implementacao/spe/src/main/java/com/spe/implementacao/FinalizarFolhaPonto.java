package com.spe.implementacao;

import java.util.Date;

import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;
import com.spe.repository.FolhaPontoRepository;

public class FinalizarFolhaPonto implements AcaoFolhaPonto{

	@Override
	public FolhaPonto acao(Usuario usuario, FolhaPontoRepository folhaPontoRepository) {
		FolhaPonto folhaPonto = new FolhaPonto(new Date(), usuario);
		folhaPontoRepository.saveAndFlush(folhaPonto);
		return folhaPonto;
		 
	}

}
