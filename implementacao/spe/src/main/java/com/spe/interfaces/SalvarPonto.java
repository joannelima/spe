package com.spe.interfaces;

import java.time.LocalDateTime;

import com.spe.model.FolhaPonto;
import com.spe.repository.FolhaPontoRepository;
import com.spe.service.FolhaPontoService;
import com.spe.service.RegistroPontoService;

public interface SalvarPonto {
	public void salvar(FolhaPonto folhaPonto, LocalDateTime hora, RegistroPontoService pontoService, FolhaPontoService folhaService, FolhaPontoRepository folhaRepository);
}
