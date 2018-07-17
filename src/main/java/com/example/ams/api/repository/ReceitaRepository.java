package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Receita;
import com.example.ams.api.repository.receita.ReceitaRepositoryQuery;

public interface ReceitaRepository extends JpaRepository<Receita, Long>, ReceitaRepositoryQuery {

}
