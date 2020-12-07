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

public class CalculaHoraNormal implements CalculaHora{
	
	public FolhaDto calcular(FolhaPonto folha, DataService dataService) throws ParseException {
	
		List<RegistroPonto> listaPontosList = folha.getPontos();
		Date entrada = listaPontosList.get(0).getHorasMarcacao();
		Date saidaAlmoco = listaPontosList.get(1).getHorasMarcacao();
		Date entradaAlmoco = listaPontosList.get(2).getHorasMarcacao();
		Date saida = listaPontosList.get(3).getHorasMarcacao();
		Integer horasPorDia = 8;
		Date resultadoPrimeiroIntevalo = dataService.calculaIntervaloDeDatas(entrada, saidaAlmoco);
		Date resultadoSegundoIntervalo = dataService.calculaIntervaloDeDatas(entradaAlmoco, saida);
		Date horaExtra = null;
		Date horaDebito = null;
		Date saldo = null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(resultadoPrimeiroIntevalo);
		int horaEntrada = c.get(Calendar.HOUR_OF_DAY);
		dataService.calculaHorasRestantes(c, horaEntrada);
		Date resultadoSomaHoras = dataService.somaDatas(resultadoPrimeiroIntevalo, resultadoSegundoIntervalo);
		
		Calendar d = Calendar.getInstance();
		d.setTime(resultadoSomaHoras);
		int horaRestante = d.get(Calendar.HOUR_OF_DAY);
		int minutoRestante = d.get(Calendar.MINUTE);
		dataService.retornaExtraOuDebito(horasPorDia, horaRestante, minutoRestante);
		
		 return dataService.controiDtoHoras(folha, horaExtra, horaDebito, saldo);

	}
}
