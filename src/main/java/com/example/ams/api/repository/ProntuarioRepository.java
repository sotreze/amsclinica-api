package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Prontuario;
import com.example.ams.api.repository.prontuario.ProntuarioRepositoryQuery;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long>, ProntuarioRepositoryQuery {

}