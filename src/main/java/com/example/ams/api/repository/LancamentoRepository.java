package com.example.ams.api.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Lancamento;
import com.example.ams.api.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>, LancamentoRepositoryQuery {

	// início refatorar
	List<Lancamento> findByDataConsultaLessThanEqualAndDataConsultaIsNull(LocalDate data);
	// fim refatorar
}
