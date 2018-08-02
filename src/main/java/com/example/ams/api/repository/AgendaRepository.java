package com.example.ams.api.repository;


import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ams.api.model.Agenda;
import com.example.ams.api.repository.agenda.AgendaRepositoryQuery;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Long>, AgendaRepositoryQuery {

	List<Agenda> findByDataLessThanEqualAndDataIsNull(LocalDate data);
	
}
