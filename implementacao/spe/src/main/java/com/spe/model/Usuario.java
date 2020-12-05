package com.spe.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
	private String cpf;
	
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuario_perfil", 
			joinColumns = @JoinColumn(name = "fk_usuario", referencedColumnName = "id"), 
			inverseJoinColumns = @JoinColumn(name="fk_perfil", referencedColumnName = "id"))
	private List<Perfil> perfis;
	
	@OneToMany(mappedBy = "usuario", cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private List<FolhaPonto> listPontos;

}
