package com.spe.interfaces;

import java.text.ParseException;

import com.spe.dto.FolhaDto;
import com.spe.model.FolhaPonto;
import com.spe.service.DataService;

public interface CalculaHora {
	public FolhaDto calcular(FolhaPonto folha, DataService dataService) throws ParseException ;
}
