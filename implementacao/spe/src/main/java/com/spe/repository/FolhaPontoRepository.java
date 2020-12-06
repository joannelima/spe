package com.spe.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spe.model.FolhaPonto;
import com.spe.model.Usuario;

@Repository
public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Integer>{

	@Query(value = "select * from folha_ponto where fk_usuario = ?1 and dia=?2 ", nativeQuery = true)
	Optional<FolhaPonto> folhaPontoPorDia(Usuario usuario, Date dia);

}
