package com.example.ams.api.repository.usuariopermissao;

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

import com.example.ams.api.model.Usuario_;
import com.example.ams.api.model.UsuarioPermissao;
import com.example.ams.api.model.UsuarioPermissao_;
import com.example.ams.api.repository.filter.UsuarioPermissaoFilter;
import com.example.ams.api.repository.projection.ResumoUsuarioPermissao;

public class UsuarioPermissaoRepositoryImpl implements UsuarioPermissaoRepositoryQuery {


	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<UsuarioPermissao> filtrar(UsuarioPermissaoFilter usuarioPermissaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<UsuarioPermissao> criteria = builder.createQuery(UsuarioPermissao.class);
		Root<UsuarioPermissao> root = criteria.from(UsuarioPermissao.class);

		Predicate[] predicates = criarRestricoes(usuarioPermissaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<UsuarioPermissao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(usuarioPermissaoFilter));
	}


	@Override
	public Page<ResumoUsuarioPermissao> resumir(UsuarioPermissaoFilter usuarioPermissaoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoUsuarioPermissao> criteria = builder.createQuery(ResumoUsuarioPermissao.class);
		Root<UsuarioPermissao> root = criteria.from(UsuarioPermissao.class);

		criteria.select(builder.construct(ResumoUsuarioPermissao.class
				//, root.get(UsuarioPermissao_.codigo) 
				, root.get(UsuarioPermissao_.usuario)
				, root.get(UsuarioPermissao_.permissao)));

		Predicate[] predicates = criarRestricoes(usuarioPermissaoFilter, builder, root);
		criteria.where(predicates);

		TypedQuery<ResumoUsuarioPermissao> query = manager.createQuery(criteria);
		adicionarRestricoesDePaginacao(query, pageable);

		return new PageImpl<>(query.getResultList(), pageable, total(usuarioPermissaoFilter));
	}

	private Predicate[] criarRestricoes(UsuarioPermissaoFilter usuarioPermissaoFilter, CriteriaBuilder builder,
			Root<UsuarioPermissao> root) {
		List<Predicate> predicates = new ArrayList<>();

		if (!StringUtils.isEmpty(usuarioPermissaoFilter.getUsuario())) {
			predicates.add(builder.like(
					builder.lower(root.get(UsuarioPermissao_.usuario).get(Usuario_.email)), "%" + usuarioPermissaoFilter.getUsuario().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(usuarioPermissaoFilter.getUsuario())) {
			predicates.add(builder.like(
					builder.lower(root.get(UsuarioPermissao_.usuario).get(Usuario_.primeiroNome)), "%" + usuarioPermissaoFilter.getUsuario().toLowerCase() + "%"));
		}
		
		if (!StringUtils.isEmpty(usuarioPermissaoFilter.getUsuario())) {
			predicates.add(builder.like(
					builder.lower(root.get(UsuarioPermissao_.usuario).get(Usuario_.sobrenome)), "%" + usuarioPermissaoFilter.getUsuario().toLowerCase() + "%"));
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

	private Long total(UsuarioPermissaoFilter usuarioPermissaoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<UsuarioPermissao> root = criteria.from(UsuarioPermissao.class);

		Predicate[] predicates = criarRestricoes(usuarioPermissaoFilter, builder, root);
		criteria.where(predicates);

		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}

}
