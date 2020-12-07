package com.spe.implementacao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.spe.dto.FolhaDto;
import com.spe.interfaces.CalculaHora;
import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;
import com.spe.service.DataService;

public class CalculaHoraSabado implements CalculaHora{
	
	public FolhaDto calcular(FolhaPonto folha, DataService dataService) throws ParseException {
	
		List<RegistroPonto> listaPontosList = folha.getPontos();
		Date entrada = listaPontosList.get(0).getHorasMarcacao();
		Date saida = listaPontosList.get(1).getHorasMarcacao();
		Integer horasPorDia = 4;
		Date resultadoPrimeiroIntevalo = dataService.calculaIntervaloDeDatas(entrada, saida);
		Date horaExtra = null;
		Date horaDebito = null;
		Date saldo = null;
		
		Calendar d = Calendar.getInstance();
		d.setTime(resultadoPrimeiroIntevalo);
		int horaRestante = d.get(Calendar.HOUR_OF_DAY);
		int minutoRestante = d.get(Calendar.MINUTE);
		
		
		if(horaRestante > horasPorDia) {
			horaExtra = dataService.calculoHoraExtra(horasPorDia, horaExtra, horaRestante, minutoRestante);
		}else if(horaRestante < horasPorDia){
			horaDebito = dataService.calculoHoraDebito(horasPorDia, horaExtra, horaRestante, minutoRestante);
		}
		
		 return dataService.controiDtoHoras(folha, horaExtra, horaDebito, saldo);

	}
}
