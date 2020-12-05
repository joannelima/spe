package com.spe.enumeration;



public enum TipoMarcacaoSemana {
	Normal("Normal"),
	FDS("Fim de semana");
	
	private String descricao;

	private TipoMarcacaoSemana(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
	
}
