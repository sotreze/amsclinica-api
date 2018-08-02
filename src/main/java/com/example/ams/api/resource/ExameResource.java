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
import com.example.ams.api.model.Exame;
import com.example.ams.api.repository.ExameRepository;
import com.example.ams.api.repository.filter.ExameFilter;
import com.example.ams.api.repository.projection.ResumoExame;
import com.example.ams.api.service.ExameService;

@RestController
@RequestMapping("/exames")
public class ExameResource {

	@Autowired
	private ExameRepository exameRepository;

	@Autowired
	private ExameService exameService;

	@Autowired
	private ApplicationEventPublisher publisher;
	

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<Exame> pesquisar(ExameFilter exameFilter, Pageable pageable) {
		return exameRepository.filtrar(exameFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<ResumoExame> resumir(ExameFilter exameFilter, Pageable pageable) {
		return exameRepository.resumir(exameFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Exame> buscarPeloCodigo(@PathVariable Long codigo) {
		Exame exame = exameRepository.findOne(codigo);
		 return exame != null ? ResponseEntity.ok(exame) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Exame> criar(@Valid @RequestBody Exame exame, HttpServletResponse response) {
		Exame exameSalvo = exameRepository.save(exame);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, exameSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(exameSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		exameRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO')")
	public ResponseEntity<Exame> atualizar(@PathVariable Long codigo, @Valid @RequestBody Exame exame) {
		try {
			Exame exameSalvo = exameService.atualizar(codigo, exame);
			return ResponseEntity.ok(exameSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}

