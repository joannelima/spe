package com.spe.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;

@Repository
public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Integer>{

	FolhaPonto findByUsuarioAndDia(Usuario usuario, Date dia);

}
