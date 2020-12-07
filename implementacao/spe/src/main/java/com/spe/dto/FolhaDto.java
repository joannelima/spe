package com.spe.dto;

import java.util.Date;

import com.spe.model.FolhaPonto;

import lombok.Data;

@Data
public class FolhaDto {

	private FolhaPonto folha;
	private Date horaExtras;
	private Date horaDebitos;
	private Date saldo;
	
	public FolhaDto(FolhaPonto folha, Date horaExtras, Date horaDebitos, Date saldo) {
		super();
		this.folha = folha;
		this.horaExtras = horaExtras;
		this.horaDebitos = horaDebitos;
		this.saldo = saldo;
	}
	
}
