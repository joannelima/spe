package com.spe.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spe.dto.FolhaDto;
import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.implementacao.CalculaHoraDebito;
import com.spe.implementacao.CalculaHoraExtra;
import com.spe.interfaces.CalcularHora;
import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;

@Service
public class DataService {

	public TipoMarcacaoSemana retornaEnumDiaDaSemana(Date data) { 
		Integer sabado = 7;
		Integer domingo = 1;
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);  
		int dia = cal.get(Calendar.DAY_OF_WEEK);
		if(dia == domingo || dia == sabado) {
			return TipoMarcacaoSemana.FimDeSemana;
		}else {
			return TipoMarcacaoSemana.Normal;
		}
	}
	
	
	public FolhaDto calculoHorasNormal(FolhaPonto folha, Date dia) throws ParseException {
		List<RegistroPonto> listaPontosList = folha.getPontos();
		Date entrada = listaPontosList.get(0).getHorasMarcacao();
		Date saidaAlmoco = listaPontosList.get(1).getHorasMarcacao();
		Date entradaAlmoco = listaPontosList.get(2).getHorasMarcacao();
		Date saida = listaPontosList.get(3).getHorasMarcacao();
		Integer horasPorDia = 8;
		Date resultadoPrimeiroIntevalo = calculaIntervaloDeDatas(entrada, saidaAlmoco);
		Date resultadoSegundoIntervalo = calculaIntervaloDeDatas(entradaAlmoco, saida);
		Date horaExtra = null;
		Date horaDebito = null;
		Date saldo = null;
		
		Calendar c = Calendar.getInstance();
		c.setTime(resultadoPrimeiroIntevalo);
		int horaEntrada = c.get(Calendar.HOUR_OF_DAY);
		calculaHorasRestantes(c, horaEntrada);
		Date resultadoSomaHoras = somaDatas(resultadoPrimeiroIntevalo, resultadoSegundoIntervalo);
		
		Calendar d = Calendar.getInstance();
		d.setTime(resultadoSomaHoras);
		int horaRestante = d.get(Calendar.HOUR_OF_DAY);
		int minutoRestante = d.get(Calendar.MINUTE);
		
		
		if(horaRestante > horasPorDia) {
			horaExtra = calculoHoraExtra(horasPorDia, horaExtra, horaRestante, minutoRestante);
		}else if(horaRestante < horasPorDia){
			horaDebito = calculoHoraDebito(horasPorDia, horaExtra, horaRestante, minutoRestante);
		}
		
		 return controiDtoHoras(folha, horaExtra, horaDebito, saldo);
	}


	private FolhaDto controiDtoHoras(FolhaPonto folha, Date horaExtra, Date horaDebito, Date saldo) {
		FolhaDto updateFolha = new FolhaDto(folha, horaExtra, horaDebito, saldo);
		 return updateFolha;
	}


	private Date calculoHoraDebito(Integer horasPorDia, Date horaExtra, int horaRestante, int minutoRestante)
			throws ParseException {
		Date horaDebito;
		CalcularHora calculoHoraExtra = new CalculaHoraDebito();
		horaDebito = calculoHoraExtra.calcular(horaRestante, minutoRestante, horaExtra, horasPorDia);
		return horaDebito;
	}


	private Date calculoHoraExtra(Integer horasPorDia, Date horaExtra, int horaRestante, int minutoRestante)
			throws ParseException {
		CalcularHora calculoHoraExtra = new CalculaHoraExtra();
		horaExtra = calculoHoraExtra.calcular(horaRestante, minutoRestante, horaExtra, horasPorDia);
		return horaExtra;
	}


	private void calculaHorasRestantes(Calendar c, int horaSaida) {
		Date resultadoSegundoIntervalo = new Date();
		c.add(horaSaida, -4);
		resultadoSegundoIntervalo = c.getTime();
	}


	private Date calculaIntervaloDeDatas(Date entrada, Date saida) throws ParseException {
		Date total;
		SimpleDateFormat format = new SimpleDateFormat("H:m");
        long diff = saida.getTime() - entrada.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        String resultado = diffHours + ":" + diffMinutes;
        total = format.parse(resultado);
		return total;
	}
	
	
	private Date somaDatas(Date entrada, Date saida) throws ParseException {
		Date total;
		SimpleDateFormat format = new SimpleDateFormat("H:m");
        long diff = saida.getTime() + entrada.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        String resultado = diffHours + ":" + diffMinutes;
        total = format.parse(resultado);
		return total;
	}
	
	
}
