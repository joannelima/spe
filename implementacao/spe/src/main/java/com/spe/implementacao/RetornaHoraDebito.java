package com.spe.implementacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.spe.interfaces.RetornaHora;

public class RetornaHoraDebito implements RetornaHora{
	
	public Date calcular(Integer horaRestante, Integer minutoRestante, Integer horaPorDia) throws ParseException {
	
		int diferenca = horaPorDia - horaRestante;
		SimpleDateFormat format = new SimpleDateFormat("H:m"); 
		String resultado = diferenca + ":" + minutoRestante;
		Date horaDebito = format.parse(resultado);
		return horaDebito; 
				

	}
}
