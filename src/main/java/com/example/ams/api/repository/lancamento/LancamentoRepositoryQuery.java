package com.example.ams.api.repository.lancamento;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.dto.LancamentoEstatisticaExame;
import com.example.ams.api.dto.LancamentoEstatisticaDia;
import com.example.ams.api.dto.LancamentoEstatisticaPessoa;
import com.example.ams.api.model.Lancamento;
import com.example.ams.api.repository.filter.LancamentoFilter;
import com.example.ams.api.repository.projection.ResumoLancamento;

public interface LancamentoRepositoryQuery {

	public List<LancamentoEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim);
	public List<LancamentoEstatisticaExame> porExame(LocalDate mesReferencia);
	public List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia);

	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable);

}
