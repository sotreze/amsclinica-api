package com.example.ams.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;


import com.example.ams.api.model.Agenda;
import com.example.ams.api.repository.agenda.AgendaRepositoryQuery;

public interface AgendaRepository extends JpaRepository<Agenda, Long>, AgendaRepositoryQuery {

}
