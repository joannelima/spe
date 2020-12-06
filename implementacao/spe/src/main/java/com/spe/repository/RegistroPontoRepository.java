package com.spe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spe.model.FolhaPonto;
import com.spe.model.RegistroPonto;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Integer>{

	long countByFolhaPonto(FolhaPonto folhaPonto);
}
