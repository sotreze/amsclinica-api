package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Medico;
import com.example.ams.api.repository.medico.MedicoRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MedicoRepository extends JpaRepository<Medico, Long>, MedicoRepositoryQuery {
	
	public Page<Medico> findByNomeContaining(String nome, Pageable pageable);

}