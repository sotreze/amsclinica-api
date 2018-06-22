package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Periodo;
import com.example.ams.api.repository.periodo.PeriodoRepositoryQuery;

public interface PeriodoRepository extends JpaRepository<Periodo, Long>, PeriodoRepositoryQuery {

}
