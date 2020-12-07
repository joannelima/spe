package com.spe.service;

import java.text.ParseException;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spe.dto.FolhaDto;
import com.spe.implementacao.CalculaHoraNormal;
import com.spe.implementacao.CalculaHoraSabado;
import com.spe.implementacao.IniciarFolhaPonto;
import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.interfaces.CalculaHora;
import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;
import com.spe.repository.FolhaPontoRepository;

@Service
public class FolhaPontoService {

	  @Autowired
	  private FolhaPontoRepository folhaPontoRepository;
	  
	  @Autowired
	  private DataService dataService;
	  
	  public Optional<FolhaPonto> retornarFolhaPontoDoUsuarioPorDia(Usuario usuario, Date dia) {
		  return folhaPontoRepository.findByUsuarioAndDia(usuario, dia);
	  }
	  
	  public Optional<FolhaPonto> iniciarFolhaPonto(Usuario usuario) {
		  AcaoFolhaPonto<Usuario, FolhaPontoRepository> salvar = new IniciarFolhaPonto();
		  return Optional.of(salvar.acao(usuario, folhaPontoRepository));
	  }
	  
	  public FolhaDto controiDtoSabado(FolhaPonto folhaPonto, Date dia) throws ParseException {
			CalculaHora sabado = new CalculaHoraSabado();
			return sabado.calcular(folhaPonto, dataService);
	  }
	  
	  public FolhaDto constroiDtoNormal(FolhaPonto folhaPonto, Date dia) throws ParseException {
			CalculaHora normal = new CalculaHoraNormal();
			return normal.calcular(folhaPonto, dataService);
	  }
	  

}
