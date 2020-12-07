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
	
	
	public FolhaDto calculoHorasSabado(FolhaPonto folha, Date dia) throws ParseException {
		List<RegistroPonto> listaPontosList = folha.getPontos();
		Date dataInicial = listaPontosList.get(0).getHorasMarcacao();
		Date dataFinal = listaPontosList.get(1).getHorasMarcacao();
		Date resultado = calculaIntervaloDeDatas(dataInicial, dataFinal);
		Date horaExtra = new Date();
		Date horaDebito = new Date();
		Date saldo = new Date();
		
		Calendar c = Calendar.getInstance();
		c.setTime(resultado);
		
		int hora = c.get(Calendar.HOUR_OF_DAY);
		c.add(hora, -4);
		resultado = c.getTime();
		
		if(hora > 4) {
			horaExtra = resultado;
			horaDebito = null;
			saldo = resultado;
		}else if(hora < 4){
			horaDebito = resultado;
			horaExtra = null;
			saldo = resultado;
		}
		
		 FolhaDto updateFolha = new FolhaDto(folha, horaExtra, horaDebito, saldo);
		 return updateFolha;
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
	
	
	
}
