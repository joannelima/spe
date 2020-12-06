package com.spe.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class DataService {

	public int diaDaSemana(Date data) { 
		Calendar cal = Calendar.getInstance();
		cal.setTime(data);  
		int day = cal.get(Calendar.DAY_OF_WEEK);
		return day;
	}
	
	 public Date formatarData(Date dataMesAno) throws ParseException {
			
		SimpleDateFormat data = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cData = Calendar.getInstance();
		cData.setTime(dataMesAno);
		String dataString = data.format(dataMesAno); 
		SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd");
		Date dataFormatada = formato.parse(dataString);
		return dataFormatada;
			
	}

	
}
