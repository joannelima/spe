package com.spe.interfaces;

import com.spe.model.FolhaPonto;

public interface AcaoFolhaPonto<E, R> {
	public FolhaPonto acao(E entidade, R repository);
}
