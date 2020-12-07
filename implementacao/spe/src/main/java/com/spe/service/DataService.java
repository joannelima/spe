package com.spe.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.spe.dto.FolhaDto;
import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.implementacao.RetornaHoraDebito;
import com.spe.implementacao.RetornaHoraExtra;
import com.spe.interfaces.RetornaHora;
import com.spe.model.FolhaPonto;

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

	public FolhaDto controiDtoHoras(FolhaPonto folha, Date horaExtra, Date horaDebito, Date saldo) {
		FolhaDto updateFolha = new FolhaDto(folha, horaExtra, horaDebito, saldo);
		 return updateFolha;
	}


	public Date calculoHoraDebito(Integer horasPorDia, int horaRestante, int minutoRestante)
			throws ParseException {
		RetornaHora calculoHoraDebito = new RetornaHoraDebito();
		Date horaDebito = calculoHoraDebito.calcular(horaRestante, minutoRestante, horasPorDia);
		return horaDebito;
	}


	public Date calculoHoraExtra(Integer horasPorDia, int horaRestante, int minutoRestante)
			throws ParseException {
		RetornaHora calculoHoraExtra = new RetornaHoraExtra();
		Date horaExtra = calculoHoraExtra.calcular(horaRestante, minutoRestante, horasPorDia);
		return horaExtra;
	}


	public void calculaHorasRestantes(Calendar c, int horaSaida) {
		Date resultadoSegundoIntervalo = new Date();
		c.add(horaSaida, -4);
		resultadoSegundoIntervalo = c.getTime();
	}


	public Date calculaIntervaloDeDatas(Date entrada, Date saida) throws ParseException {
		Date total;
		SimpleDateFormat format = new SimpleDateFormat("H:m");
        long diff = saida.getTime() - entrada.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        String resultado = diffHours + ":" + diffMinutes;
        total = format.parse(resultado);
		return total;
	}
	
	
	public Date somaDatas(Date entrada, Date saida) throws ParseException {
		Date total;
		SimpleDateFormat format = new SimpleDateFormat("H:m");
        long diff = saida.getTime() + entrada.getTime();
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        String resultado = diffHours + ":" + diffMinutes;
        total = format.parse(resultado);
		return total;
	}

	public Date retornaExtraOuDebito (Integer horasPorDia, Integer horaRestante, Integer minutoRestante) throws ParseException{
		if(horaRestante > horasPorDia) {
			Date horaExtra = calculoHoraExtra(horasPorDia, horaRestante, minutoRestante);
			return horaExtra;
		}else if(horaRestante < horasPorDia){
			Date horaDebito = calculoHoraDebito(horasPorDia, horaRestante, minutoRestante);
			return horaDebito;
		}
		
		return null;
	}

	
}
