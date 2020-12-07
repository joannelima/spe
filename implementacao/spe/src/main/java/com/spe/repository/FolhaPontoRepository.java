package com.spe.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;

@Repository
public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Integer>{

	Optional<FolhaPonto> findByUsuarioAndDia(Usuario usuario, LocalDateTime dia);

}
