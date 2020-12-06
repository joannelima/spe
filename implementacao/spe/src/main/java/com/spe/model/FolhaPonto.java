package com.spe.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class FolhaPonto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern="dd/MM/yyyy",locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "dd/MM/yyyy") 
	@Temporal(TemporalType.TIMESTAMP)
	private Date dia;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm",locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horasExtras;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm",locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date horasDebito;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm",locale = "pt-BR", timezone = "Brazil/East")
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIMESTAMP)
	private Date saldo;
	
	@ManyToOne
	@JoinColumn(name="fk_usuario")
	private Usuario usuario;

	public FolhaPonto(Date date, Usuario usuario) {
		super();
		this.dia = date;
		this.usuario = usuario;
	}

	public FolhaPonto() {}
	
	
	

}
