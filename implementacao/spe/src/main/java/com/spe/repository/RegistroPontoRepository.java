package com.spe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Integer>{

	long countByFolhaPonto(FolhaPonto folhaPonto);
	
	Optional<List<RegistroPonto>> findByFolhaPonto(FolhaPonto folhaPonto);
	
}
