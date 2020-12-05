package com.spe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spe.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer>{

}
