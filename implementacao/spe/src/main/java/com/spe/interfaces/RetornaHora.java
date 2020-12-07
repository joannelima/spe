package com.spe.interfaces;

import java.text.ParseException;
import java.util.Date;

public interface RetornaHora {
	public Date calcular(Integer hora, Integer minuto, Date horaFinal, Integer horaPorDia) throws ParseException ;
}
