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
import com.example.ams.api.model.Prescricao;
import com.example.ams.api.repository.PrescricaoRepository;
import com.example.ams.api.repository.filter.PrescricaoFilter;
import com.example.ams.api.repository.projection.ResumoPrescricao;
import com.example.ams.api.service.PrescricaoService;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoResource {

	@Autowired
	private PrescricaoRepository prescricaoRepository;

	@Autowired
	private PrescricaoService prescricaoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRESCRICAO') and #oauth2.hasScope('read')")
	public Page<Prescricao> pesquisar(PrescricaoFilter prescricaoFilter, Pageable pageable) {
		return prescricaoRepository.filtrar(prescricaoFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRESCRICAO') and #oauth2.hasScope('read')")
	public Page<ResumoPrescricao> resumir(PrescricaoFilter prescricaoFilter, Pageable pageable) {
		return prescricaoRepository.resumir(prescricaoFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PRESCRICAO') and #oauth2.hasScope('read')")
	public ResponseEntity<Prescricao> buscarPeloCodigo(@PathVariable Long codigo) {
		Prescricao prescricao = prescricaoRepository.findOne(codigo);
		 return prescricao != null ? ResponseEntity.ok(prescricao) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRESCRICAO') and #oauth2.hasScope('write')")
	public ResponseEntity<Prescricao> criar(@Valid @RequestBody Prescricao prescricao, HttpServletResponse response) {
		Prescricao prescricaoSalva = prescricaoRepository.save(prescricao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, prescricaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(prescricaoSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PRESCRICAO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		prescricaoRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PRESCRICAO')")
	public ResponseEntity<Prescricao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Prescricao prescricao) {
		try {
			Prescricao prescricaoSalva = prescricaoService.atualizar(codigo, prescricao);
			return ResponseEntity.ok(prescricaoSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}

