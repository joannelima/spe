package com.spe.interfaces;

import com.spe.model.FolhaPonto;

public interface AcaoFolhaPonto<T, R> {
	public FolhaPonto acao(T entidade, R repositorio);
}
