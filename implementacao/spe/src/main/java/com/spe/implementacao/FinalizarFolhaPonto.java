package com.spe.implementacao;

import com.spe.dto.FolhaDto;
import com.spe.interfaces.AcaoFolhaPonto;
import com.spe.model.FolhaPonto;
import com.spe.repository.FolhaPontoRepository;

public class FinalizarFolhaPonto implements AcaoFolhaPonto<FolhaDto, FolhaPontoRepository>{
	
	@Override
	public FolhaPonto acao(FolhaDto folhaDto, FolhaPontoRepository folhaPontoRepository) {
		FolhaPonto folha = folhaDto.getFolha();
		folha.setHorasDebito(folhaDto.getHoraDebitos());
		folha.setHorasExtras(folhaDto.getHoraExtras());
		folha.setSaldo(folhaDto.getSaldo());
		return folhaPontoRepository.saveAndFlush(folha);
	}



}
