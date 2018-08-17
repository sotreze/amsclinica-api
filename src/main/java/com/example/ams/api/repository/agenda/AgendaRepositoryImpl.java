package com.example.ams.api.repository.agenda;

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

import com.example.ams.api.dto.AgendaEstatisticaMedico;
import com.example.ams.api.model.Agenda;
import com.example.ams.api.model.Agenda_;
import com.example.ams.api.model.Medico_;
import com.example.ams.api.model.Paciente_;
import com.example.ams.api.repository.filter.AgendaFilter;
import com.example.ams.api.repository.projection.ResumoAgenda;

public class AgendaRepositoryImpl implements AgendaRepositoryQuery {

	@PersistenceContext
	private EntityManager manager;
	
	
	
	@Override
	public List<AgendaEstatisticaMedico> porMedico(LocalDate inicio, LocalDate fim) {
		CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();

		CriteriaQuery<AgendaEstatisticaMedico> criteriaQuery = criteriaBuilder.
				createQuery(AgendaEstatisticaMedico.class);

		Root<Agenda> root = criteriaQuery.from(Agenda.class);

		criteriaQuery.select(criteriaBuilder.construct(AgendaEstatisticaMedico.class,
				root.get(Agenda_.medico),
				root.get(Agenda_.paciente)));

		criteriaQuery.where(
				criteriaBuilder.greaterThanOrEqualTo(root.get(Agenda_.data), 
						inicio),
				criteriaBuilder.lessThanOrEqualTo(root.get(Agenda_.data), 
						fim));

		criteriaQuery.groupBy(root.get(Agenda_.medico),
							root.get(Agenda_.paciente));

		TypedQuery<AgendaEstatisticaMedico> typedQuery = manager
				.createQuery(criteriaQuery);

		return typedQuery.getResultList();
	}
	

	@Override
	public Page<Agenda> filtrar(AgendaFilter agendaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Agenda> criteria = builder.createQuery(Agenda.class);
		Root<Agenda> root = criteria.from(Agenda.class);

		Predicate[] predicates = criarRestricoes(agendaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<Agenda> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(agendaFilter));
	}


	@Override
	public Page<ResumoAgenda> resumir(AgendaFilter agendaFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoAgenda> criteria = builder.createQuery(ResumoAgenda.class);
		Root<Agenda> root = criteria.from(Agenda.class);

		criteria.select(builder.construct(ResumoAgenda.class
				, root.get(Agenda_.codigo)
				, root.get(Agenda_.ativo)
				, root.get(Agenda_.email)
				, root.get(Agenda_.data)
				, root.get(Agenda_.hora)
				, root.get(Agenda_.paciente).get(Paciente_.nome)
				, root.get(Agenda_.medico).get(Medico_.nome)));


		Predicate[] predicates = criarRestricoes(agendaFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoAgenda> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(agendaFilter));
	}

	private Predicate[] criarRestricoes(AgendaFilter agendaFilter, CriteriaBuilder builder,
			Root<Agenda> root) {
		List<Predicate> predicates = new ArrayList<>();

		/*if (agendaFilter.getDataDe() != null) {
			predicates.add(
					builder.greaterThanOrEqualTo(root.get(Agenda_.data), agendaFilter.getDataDe()));
		}

		if (agendaFilter.getDataAte() != null) {
			predicates.add(
					builder.lessThanOrEqualTo(root.get(Agenda_.data), agendaFilter.getDataAte()));
		}*/
		
		if (agendaFilter.getCodigo() != null) {
			predicates.add(
				builder.equal(root.get(Agenda_.codigo), agendaFilter.getCodigo()));
		}
		
		if (!StringUtils.isEmpty(agendaFilter.getEmail())) {
			predicates.add(builder.like(
					builder.lower(root.get(Agenda_.email)), "%" + agendaFilter.getEmail().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(agendaFilter.getPaciente())) {
			predicates.add(builder.like(
					builder.lower(root.get(Agenda_.paciente).get(Paciente_.nome)), "%" + agendaFilter.getPaciente().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(agendaFilter.getMedico())) {
			predicates.add(builder.like(
					builder.lower(root.get(Agenda_.medico).get(Medico_.nome)), "%" + agendaFilter.getMedico().toLowerCase() + "%"));
		}
		
		if (agendaFilter.getData() != null) {
			predicates.add(
				builder.equal(root.get(Agenda_.data), agendaFilter.getData()));
		}
		
		if (agendaFilter.getHora() != null) {
			predicates.add(
				builder.equal(root.get(Agenda_.hora), agendaFilter.getHora()));
		}
		
		/*if (!StringUtils.isEmpty(agendaFilter.getHora())) {
			predicates.add(builder.like(
					builder.lower(root.get(Agenda_.hora)), "%" + agendaFilter.getHora().toLowerCase() + "%"));
		}*/
		
		/*if (agendaFilter.getHorario() != null) {
		predicates.add(
			builder.equal(root.get(Agenda_.horario).get(Horario_.hora), agendaFilter.getHorario()));
		}*/
		

		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

	private void adicionarRestricoesDePaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistrosPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistrosPorPagina);
	}

	private Long total(AgendaFilter agendaFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Agenda> root = criteria.from(Agenda.class);

		Predicate[] predicates = criarRestricoes(agendaFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
