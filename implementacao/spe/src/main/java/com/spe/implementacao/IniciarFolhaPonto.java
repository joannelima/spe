package com.spe.implementacao;

import java.time.LocalDateTime;

import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;
import com.spe.repository.FolhaPontoRepository;

public class IniciarFolhaPonto implements AcaoFolhaPonto<Usuario, FolhaPontoRepository>{

	@Override
	public FolhaPonto acao(Usuario usuario, FolhaPontoRepository folhaPontoRepository) {
		FolhaPonto folhaPonto = new FolhaPonto(LocalDateTime.now(), usuario);
		folhaPontoRepository.saveAndFlush(folhaPonto);
		return folhaPonto;
	}

}
