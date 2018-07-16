package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Prescricao;
import com.example.ams.api.repository.prescricao.PrescricaoRepositoryQuery;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long>, PrescricaoRepositoryQuery {

}
