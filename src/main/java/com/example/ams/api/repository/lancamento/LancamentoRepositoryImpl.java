package com.example.ams.api.repository.lancamento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import com.example.ams.api.dto.LancamentoEstatisticaExame;
import com.example.ams.api.dto.LancamentoEstatisticaDia;
import com.example.ams.api.dto.LancamentoEstatisticaPessoa;
import com.example.ams.api.model.Exame_;
import com.example.ams.api.model.Lancamento;
import com.example.ams.api.model.Lancamento_;
import com.example.ams.api.model.Paciente_;
import com.example.ams.api.repository.filter.LancamentoFilter;
import com.example.ams.api.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;


	@Override
	public List<LancamentoEstatisticaPessoa> porPessoa(LocalDate inicio, LocalDate fim) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<LancamentoEstatisticaPessoa> criteriaQuery = criteriaBuilder.
				createQuery(LancamentoEstatisticaPessoa.class);

		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

		criteriaQuery.select(criteriaBuilder.construct(LancamentoEstatisticaPessoa.class,
				root.get(Lancamento_.tipo),
				root.get(Lancamento_.paciente),
				criteriaBuilder.sum(root.get(Lancamento_.valor))));

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Lancamento_.dataConsulta),
						inicio),
				criteriaBuilder.lessThanOrEqualTo(root.get(Lancamento_.dataConsulta),
						fim));

		criteriaQuery.groupBy(root.get(Lancamento_.tipo),
				root.get(Lancamento_.paciente));

		TypedQuery<LancamentoEstatisticaPessoa> typedQuery = manager
				.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}


	@Override
	public List<LancamentoEstatisticaDia> porDia(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<LancamentoEstatisticaDia> criteriaQuery = criteriaBuilder.
				createQuery(LancamentoEstatisticaDia.class);

		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

		criteriaQuery.select(criteriaBuilder.construct(LancamentoEstatisticaDia.class,
				root.get(Lancamento_.tipo),
				root.get(Lancamento_.dataConsulta),
				criteriaBuilder.sum(root.get(Lancamento_.valor))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Lancamento_.dataConsulta),
						primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(Lancamento_.dataConsulta),
						ultimoDia));

		criteriaQuery.groupBy(root.get(Lancamento_.tipo),
				root.get(Lancamento_.dataConsulta));

		TypedQuery<LancamentoEstatisticaDia> typedQuery = manager
				.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}


	@Override
	public List<LancamentoEstatisticaExame> porExame(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<LancamentoEstatisticaExame> criteriaQuery = criteriaBuilder.
				createQuery(LancamentoEstatisticaExame.class);

		Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

		criteriaQuery.select(criteriaBuilder.construct(LancamentoEstatisticaExame.class,
				root.get(Lancamento_.exame),
				criteriaBuilder.sum(root.get(Lancamento_.valor))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Lancamento_.dataExame),
						primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(Lancamento_.dataExame),
						ultimoDia));

		criteriaQuery.groupBy(root.get(Lancamento_.exame));

		TypedQuery<LancamentoEstatisticaExame> typedQuery = manager
				.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}

	@Override
	public Page<Lancamento> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Lancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}


	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		criteria.select(builder.construct(ResumoLancamento.class
				, root.get(Lancamento_.codigo), root.get(Lancamento_.descricao)
				, root.get(Lancamento_.dataConsulta), root.get(Lancamento_.dataExame)
				, root.get(Lancamento_.valor), root.get(Lancamento_.tipo)
				, root.get(Lancamento_.exame).get(Exame_.nome)
				, root.get(Lancamento_.observacao)
				, root.get(Lancamento_.paciente).get(Paciente_.nome)));

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder,
			Root<Lancamento> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Lancamento_.descricao)), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}

		if (lancamentoFilter.getDataConsultaDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Lancamento_.dataConsulta), lancamentoFilter.getDataConsultaDe()));
		}

		if (lancamentoFilter.getDataConsultaAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Lancamento_.dataConsulta), lancamentoFilter.getDataConsultaAte()));
		}
		
		if (!StringUtils.isEmpty(lancamentoFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Lancamento_.paciente).get(Paciente_.nome)), "%" + lancamentoFilter.getPaciente().toLowerCase() + "%"));
		}

		return predicates.toArray(new Predicate[predicates.size()]);
	}

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);

		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
