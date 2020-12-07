package com.spe.interfaces;

import com.spe.dto.FolhaDto;
import com.spe.model.FolhaPonto;
import com.spe.service.DataService;
import com.spe.service.RegistroPontoService;

public interface CalculaHora {
	public FolhaDto calcular(FolhaPonto folha, RegistroPontoService folhaPontoService, DataService dataService);
}
