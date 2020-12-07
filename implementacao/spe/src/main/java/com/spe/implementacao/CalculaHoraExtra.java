package com.spe.implementacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.spe.interfaces.CalcularHora;

public class CalculaHoraExtra implements CalcularHora{
	
	public Date calcular(Integer horaRestante, Integer minutoRestante, Date horaExtra, Integer horaPorDia) throws ParseException {
	
		int diferenca = horaRestante - 8;
		SimpleDateFormat format = new SimpleDateFormat("H:m"); 
		String resultado = diferenca + ":" + minutoRestante;
		return horaExtra = format.parse(resultado);

	}
}
