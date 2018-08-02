package com.example.ams.api.resource;


import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.ams.api.event.RecursoCriadoEvent;
import com.example.ams.api.model.Receita;
import com.example.ams.api.repository.ReceitaRepository;
import com.example.ams.api.repository.filter.ReceitaFilter;
import com.example.ams.api.repository.projection.ResumoReceita;
import com.example.ams.api.service.ReceitaService;

@RestController
@RequestMapping("/receitas")
public class ReceitaResource {

	@Autowired
	private ReceitaRepository receitaRepository;

	@Autowired
	private ReceitaService receitaService;

	@Autowired
	private ApplicationEventPublisher publisher;
	

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<Receita> pesquisar(ReceitaFilter receitaFilter, Pageable pageable) {
		return receitaRepository.filtrar(receitaFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<ResumoReceita> resumir(ReceitaFilter receitaFilter, Pageable pageable) {
		return receitaRepository.resumir(receitaFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Receita> buscarPeloCodigo(@PathVariable Long codigo) {
		Receita receita = receitaRepository.findOne(codigo);
		 return receita != null ? ResponseEntity.ok(receita) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Receita> criar(@Valid @RequestBody Receita receita, HttpServletResponse response) {
		Receita receitaSalva = receitaRepository.save(receita);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, receitaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(receitaSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		receitaRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO')")
	public ResponseEntity<Receita> atualizar(@PathVariable Long codigo, @Valid @RequestBody Receita receita) {
		try {
			Receita receitaSalva = receitaService.atualizar(codigo, receita);
			return ResponseEntity.ok(receitaSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}

