package com.spe.enumeration;

import com.spe.implementacao.SalvarPontoNormal;
import com.spe.implementacao.SalvarPontoSabado;
import com.spe.interfaces.SalvarPonto;

public enum TipoMarcacaoSemana {
	Normal {
		@Override
		public SalvarPonto obter() {
			return new SalvarPontoNormal();
		}
	},
	FimDeSemana {
		@Override
		public SalvarPonto obter() {
			return new SalvarPontoSabado();
		}
	};
	
	public abstract SalvarPonto obter();
	
}
