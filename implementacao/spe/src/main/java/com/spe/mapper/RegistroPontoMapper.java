package com.spe.mapper;

import com.spe.model.RegistroPonto;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface RegistroPontoMapper{

    RegistroPonto inserirNovo(@Param("ponto") RegistroPonto ponto);

}