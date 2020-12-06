package com.spe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.spe.enumeration.TipoMarcacaoSemana;

import lombok.Data;

@Entity
@Data
public class RegistroPonto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm",locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horasMarcacao;
	
	@ManyToOne
	@JoinColumn(name="fk_folha_ponto")
	private FolhaPonto folhaPonto;
	
	@Enumerated(EnumType.STRING)
	private TipoMarcacaoSemana tipoMarcacaoSemana;

	public RegistroPonto(Date horasMarcacao, FolhaPonto folhaPonto, TipoMarcacaoSemana tipoMarcacaoSemana) {
		super();
		this.horasMarcacao = horasMarcacao;
		this.folhaPonto = folhaPonto;
		this.tipoMarcacaoSemana = tipoMarcacaoSemana;
	}

	public RegistroPonto() {}
	
	
}