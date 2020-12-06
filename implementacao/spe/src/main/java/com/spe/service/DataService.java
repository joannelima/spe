package com.spe.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.spe.enumeration.TipoMarcacaoSemana;

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
	
	
}
