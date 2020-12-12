package com.spe.service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.spe.dto.FolhaDto;
import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.model.FolhaPonto;

@Service
public class DataService {

	public TipoMarcacaoSemana retornaEnumDiaDaSemana(LocalDateTime data) { 
		DayOfWeek diaDaSemana = data.getDayOfWeek();
		HashMap<DayOfWeek, TipoMarcacaoSemana> retorno = new HashMap<>();
		retorno.get(DayOfWeek.SATURDAY);
		retorno.get(DayOfWeek.SUNDAY);
		
		if(retorno.containsKey(diaDaSemana)) {
			return TipoMarcacaoSemana.FimDeSemana;
		}
			
		return TipoMarcacaoSemana.Normal;
	}

	public FolhaDto controiDtoHoras(FolhaPonto folha, Date horaExtra, Date horaDebito, Date saldo) {
		FolhaDto updateFolha = new FolhaDto(folha, horaExtra, horaDebito, saldo);
		 return updateFolha;
	}


}
