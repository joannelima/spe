package com.spe.service;

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
}
