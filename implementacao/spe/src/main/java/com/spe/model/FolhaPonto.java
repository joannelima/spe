package com.spe.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Temporal(TemporalType.DATE)
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
	
	
	@JsonIgnore
	@OneToMany(mappedBy = "folhaPonto", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<RegistroPonto> pontos;

	public FolhaPonto(Date date, Usuario usuario) {
		super();
		this.dia = date;
		this.usuario = usuario;
	}

	public FolhaPonto() {}
	

}
