package com.spe.interfaces;

import java.util.Date;

import com.spe.model.FolhaPonto;
import com.spe.repository.FolhaPontoRepository;
import com.spe.service.RegistroPontoService;

public interface SalvarPonto {
	public void salvar(FolhaPonto folhaPonto, Date dia, RegistroPontoService pontoService, FolhaPontoRepository folhaRepository);
}
