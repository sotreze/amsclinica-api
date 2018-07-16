package com.example.ams.api.repository.prescricao;

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

import com.example.ams.api.model.Exame_;
//import com.example.ams.api.dto.ReceitaGerar;
import com.example.ams.api.model.Medicacao_;
import com.example.ams.api.model.Medico_;
import com.example.ams.api.model.Paciente_;
import com.example.ams.api.model.Prescricao;
import com.example.ams.api.model.Prescricao_;
import com.example.ams.api.repository.filter.PrescricaoFilter;
import com.example.ams.api.repository.projection.ResumoPrescricao;

public class PrescricaoRepositoryImpl implements PrescricaoRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	

	@Override
	public Page<Prescricao> filtrar(PrescricaoFilter prescricaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Prescricao> criteria = builder.createQuery(Prescricao.class);
		Root<Prescricao> root = criteria.from(Prescricao.class);

		Predicate[] predicates = criarRestricoes(prescricaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Prescricao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(prescricaoFilter));
	}


	@Override
	public Page<ResumoPrescricao> resumir(PrescricaoFilter prescricaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoPrescricao> criteria = builder.createQuery(ResumoPrescricao.class);
		Root<Prescricao> root = criteria.from(Prescricao.class);

		criteria.select(builder.construct(ResumoPrescricao.class
				, root.get(Prescricao_.codigo) 
				, root.get(Prescricao_.medico).get(Medico_.nome)
				, root.get(Prescricao_.paciente).get(Paciente_.nome)
				, root.get(Prescricao_.medicacao).get(Medicacao_.descricao)
				, root.get(Prescricao_.descricao)
				, root.get(Prescricao_.tipo)
				, root.get(Prescricao_.exame).get(Exame_.nome)
				));

		Predicate[] predicates = criarRestricoes(prescricaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoPrescricao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(prescricaoFilter));
	}

	private Predicate[] criarRestricoes(PrescricaoFilter prescricaoFilter, CriteriaBuilder builder,
			Root<Prescricao> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(prescricaoFilter.getDescricao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Prescricao_.descricao)), "%" + prescricaoFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(prescricaoFilter.getMedico())) {
			predicates.add(builder.like(
					builder.lower(root.get(Prescricao_.medico).get(Medico_.nome)), "%" + prescricaoFilter.getMedico().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(prescricaoFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Prescricao_.paciente).get(Paciente_.nome)), "%" + prescricaoFilter.getPaciente().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(prescricaoFilter.getMedicacao())) {
			predicates.add(builder.like(
					builder.lower(root.get(Prescricao_.medicacao).get(Medicacao_.descricao)), "%" + prescricaoFilter.getMedicacao().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(prescricaoFilter.getExame())) {
			predicates.add(builder.like(
					builder.lower(root.get(Prescricao_.exame).get(Exame_.nome)), "%" + prescricaoFilter.getExame().toLowerCase() + "%"));
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

	private Long total(PrescricaoFilter prescricaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Prescricao> root = criteria.from(Prescricao.class);

		Predicate[] predicates = criarRestricoes(prescricaoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
