package com.spe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spe.model.FolhaPonto;

@Repository
public interface FolhaPontoRepository extends JpaRepository<FolhaPonto, Integer>{

}
