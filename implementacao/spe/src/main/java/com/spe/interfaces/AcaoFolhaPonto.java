package com.spe.interfaces;

import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;
import com.spe.repository.FolhaPontoRepository;

public interface AcaoFolhaPonto {
	public FolhaPonto acao(Usuario usuario, FolhaPontoRepository repository);
}
