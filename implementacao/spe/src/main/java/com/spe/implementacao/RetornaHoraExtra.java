package com.spe.implementacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.spe.interfaces.RetornaHora;

public class RetornaHoraExtra implements RetornaHora{
	
	public Date calcular(Integer horaRestante, Integer minutoRestante, Date horaExtra, Integer horaPorDia) throws ParseException {
	
		int diferenca = horaRestante - horaPorDia;
		SimpleDateFormat format = new SimpleDateFormat("H:m"); 
		String resultado = diferenca + ":" + minutoRestante;
		return horaExtra = format.parse(resultado);

	}
}
