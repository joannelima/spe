package com.spe.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spe.dto.FolhaDto;
import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;

@Service
public class DataService {

	public TipoMarcacaoSemana retornaEnumDiaDaSemana(Date data) { 
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);  
		int dia = cal.get(Calendar.DAY_OF_WEEK);
		if(dia == 1 || dia == 7) {
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
		Date resultadoPrimeiroIntevalo = calculaIntervaloDeDatas(entrada, saidaAlmoco);
		Date resultadoSegundoIntervalo = calculaIntervaloDeDatas(entradaAlmoco, saida);
		Date horaExtra = new Date();
		Date horaDebito = new Date();
		Date saldo = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(resultadoPrimeiroIntevalo);
		int horaEntrada = c.get(Calendar.HOUR_OF_DAY);
		calculaHorasRestantes(c, horaEntrada);
		
		
		
		Date resultadoSomaHoras = somaDatas(resultadoPrimeiroIntevalo, resultadoSegundoIntervalo);
		
		Calendar d = Calendar.getInstance();
		d.setTime(resultadoSomaHoras);
		int horaRestante = d.get(Calendar.HOUR_OF_DAY);
		int minutoRestante = d.get(Calendar.MINUTE);
		
		
		if(horaRestante > 8) {
			int diferenca = horaRestante - 8;
			SimpleDateFormat format = new SimpleDateFormat("H:m"); 
			String resultado = diferenca + ":" + minutoRestante;
			horaExtra = format.parse(resultado);
			horaDebito = null;
			saldo = horaExtra;
		}else if(horaRestante < 8){
			int diferenca = 8 - horaRestante;
			SimpleDateFormat format = new SimpleDateFormat("H:m"); 
			String resultado = diferenca + ":" + minutoRestante;
			horaExtra = null;
			horaDebito = format.parse(resultado);
			saldo = horaDebito;
		}else {
			SimpleDateFormat format = new SimpleDateFormat("H:m"); 
			String resultado = horaRestante + ":" + minutoRestante;
			horaDebito = null;
			horaExtra = null;
			saldo = format.parse(resultado);
		}
		
		 FolhaDto updateFolha = new FolhaDto(folha, horaExtra, horaDebito, saldo);
		 return updateFolha;
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
