package com.spe.interfaces;

import java.text.ParseException;
import java.util.Date;

import com.spe.model.FolhaPonto;
import com.spe.repository.FolhaPontoRepository;
import com.spe.service.FolhaPontoService;
import com.spe.service.RegistroPontoService;

public interface SalvarPonto {
	public void salvar(FolhaPonto folhaPonto, Date dia, RegistroPontoService pontoService, FolhaPontoService folhaService, FolhaPontoRepository folhaRepository) throws ParseException;
}
