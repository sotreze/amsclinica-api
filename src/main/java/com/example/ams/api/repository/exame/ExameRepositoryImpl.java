package com.example.ams.api.repository.exame;


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

import com.example.ams.api.model.Medico_;
import com.example.ams.api.model.Paciente_;
import com.example.ams.api.model.TipoExame_;
import com.example.ams.api.model.Exame;
import com.example.ams.api.model.Exame_;
import com.example.ams.api.dto.ExameEstatisticaDia;
import com.example.ams.api.dto.ExameEstatisticaTipoExame;
import com.example.ams.api.repository.filter.ExameFilter;
import com.example.ams.api.repository.projection.ResumoExame;

public class ExameRepositoryImpl implements ExameRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	@Override
	public List<ExameEstatisticaDia> porDia(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
		
		CriteriaQuery<ExameEstatisticaDia> criteriaQuery = criteriaBuilder.
				createQuery(ExameEstatisticaDia.class);
		
		Root<Exame> root = criteriaQuery.from(Exame.class);
		
		criteriaQuery.select(criteriaBuilder.construct(ExameEstatisticaDia.class, 
				root.get(Exame_.tipoExame),
				root.get(Exame_.dataAgendamento)));
		
		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());
		
		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Exame_.dataAgendamento), 
						primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(Exame_.dataAgendamento), 
						ultimoDia));
		
		criteriaQuery.groupBy(root.get(Exame_.tipoExame), 
				root.get(Exame_.dataAgendamento));
		
		TypedQuery<ExameEstatisticaDia> typedQuery = manager
				.createQuery(criteriaQuery);
		
		return typedQuery.getResultList();
	}	
	
	@Override
	public List<ExameEstatisticaTipoExame> porExame(LocalDate mesReferencia) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<ExameEstatisticaTipoExame> criteriaQuery = criteriaBuilder.
				createQuery(ExameEstatisticaTipoExame.class);

		Root<Exame> root = criteriaQuery.from(Exame.class);

		criteriaQuery.select(criteriaBuilder.construct(ExameEstatisticaTipoExame.class,
				root.get(Exame_.tipoExame),
				criteriaBuilder.sum(root.get(Exame_.quantidade))));

		LocalDate primeiroDia = mesReferencia.withDayOfMonth(1);
		LocalDate ultimoDia = mesReferencia.withDayOfMonth(mesReferencia.lengthOfMonth());

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Exame_.dataAgendamento),
						primeiroDia),
				criteriaBuilder.lessThanOrEqualTo(root.get(Exame_.dataAgendamento),
						ultimoDia));

		criteriaQuery.groupBy(root.get(Exame_.tipoExame));

		TypedQuery<ExameEstatisticaTipoExame> typedQuery = manager
				.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}
	

	@Override
	public Page<Exame> filtrar(ExameFilter exameFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Exame> criteria = builder.createQuery(Exame.class);
		Root<Exame> root = criteria.from(Exame.class);

		Predicate[] predicates = criarRestricoes(exameFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Exame> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(exameFilter));
	}


	@Override
	public Page<ResumoExame> resumir(ExameFilter exameFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoExame> criteria = builder.createQuery(ResumoExame.class);
		Root<Exame> root = criteria.from(Exame.class);

		criteria.select(builder.construct(ResumoExame.class
				, root.get(Exame_.codigo) 
				, root.get(Exame_.medico).get(Medico_.nome)
				, root.get(Exame_.paciente).get(Paciente_.nome)
				, root.get(Exame_.tipoExame).get(TipoExame_.nome)
				, root.get(Exame_.descricao)
				, root.get(Exame_.dataAgendamento)
				));

		Predicate[] predicates = criarRestricoes(exameFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoExame> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(exameFilter));
	}

	private Predicate[] criarRestricoes(ExameFilter exameFilter, CriteriaBuilder builder,
			Root<Exame> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(exameFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.descricao)), "%" + exameFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(exameFilter.getMedico())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.medico).get(Medico_.nome)), "%" + exameFilter.getMedico().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(exameFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.paciente).get(Paciente_.nome)), "%" + exameFilter.getPaciente().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(exameFilter.getTipoExame())) {
			predicates.add(builder.like(
					builder.lower(root.get(Exame_.tipoExame).get(TipoExame_.nome)), "%" + exameFilter.getTipoExame().toLowerCase() + "%"));
		}
		
		if (exameFilter.getDataExameDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Exame_.dataAgendamento), exameFilter.getDataExameDe()));
		}
		
		if (exameFilter.getDataExameAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Exame_.dataAgendamento), exameFilter.getDataExameAte()));
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

	private Long total(ExameFilter exameFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Exame> root = criteria.from(Exame.class);

		Predicate[] predicates = criarRestricoes(exameFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
