package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Medico;
import com.example.ams.api.repository.medico.MedicoRepositoryQuery;

public interface MedicoRepository extends JpaRepository<Medico, Long>, MedicoRepositoryQuery {

}