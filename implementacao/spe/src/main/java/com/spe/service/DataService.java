package com.spe.service;

import java.text.ParseException;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;

import org.springframework.stereotype.Service;

import com.spe.dto.FolhaDto;
import com.spe.enumeration.TipoMarcacaoSemana;
import com.spe.implementacao.RetornaHoraDebito;
import com.spe.implementacao.RetornaHoraExtra;
import com.spe.interfaces.RetornaHora;
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
