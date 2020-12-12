package com.spe.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spe.implementacao.IniciarFolhaPonto;
import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;
import com.spe.repository.FolhaPontoRepository;

@Service
public class FolhaPontoService {

	  @Autowired
	  private FolhaPontoRepository folhaPontoRepository;
	  
	  public Optional<FolhaPonto> retornarFolhaPontoDoUsuarioPorDia(Usuario usuario, LocalDateTime dia) {
		  return folhaPontoRepository.findByUsuarioAndDia(usuario, dia);
	  }
	  
	  public Optional<FolhaPonto> iniciarFolhaPonto(Usuario usuario) {
		  AcaoFolhaPonto<Usuario, FolhaPontoRepository> salvar = new IniciarFolhaPonto();
		  return Optional.of(salvar.acao(usuario, folhaPontoRepository));
	  }
	  
	
	  
	 

}
