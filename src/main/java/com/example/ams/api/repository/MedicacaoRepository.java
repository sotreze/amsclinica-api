package com.example.ams.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Medicacao;

public interface MedicacaoRepository extends JpaRepository<Medicacao, Long> {

}