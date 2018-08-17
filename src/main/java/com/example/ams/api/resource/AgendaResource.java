package com.example.ams.api.resource;


import java.time.LocalDate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ams.api.event.RecursoCriadoEvent;
import com.example.ams.api.model.Agenda;
import com.example.ams.api.repository.AgendaRepository;
import com.example.ams.api.repository.filter.AgendaFilter;
import com.example.ams.api.repository.projection.ResumoAgenda;
import com.example.ams.api.service.AgendaService;


@RestController
@RequestMapping("/agendas")
public class AgendaResource {

	@Autowired
	private AgendaRepository agendaRepository;

	@Autowired
	private AgendaService agendaService;

	@Autowired
	private ApplicationEventPublisher publisher;

	/*@Autowired
	private MessageSource messageSource;*/
	

	@GetMapping("/relatorios/por-medico")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<byte[]> relatorioPorMedico(
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate inicio,
			@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate fim) throws Exception {
		byte[] relatorio = agendaService.relatorioPorMedico(inicio, fim);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_PDF_VALUE)
				.body(relatorio);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_USUARIO') and #oauth2.hasScope('read')")
	public Page<Agenda> pesquisar(AgendaFilter agendaFilter, Pageable pageable) {
		return agendaRepository.filtrar(agendaFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_USUARIO') and #oauth2.hasScope('read')")
	public Page<ResumoAgenda> resumir(AgendaFilter agendaFilter, Pageable pageable) {
		return agendaRepository.resumir(agendaFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Agenda> buscarPeloCodigo(@PathVariable Long codigo) {
		Agenda agenda = agendaRepository.findOne(codigo);
		 return agenda != null ? ResponseEntity.ok(agenda) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Agenda> criar(@Valid @RequestBody Agenda agenda, HttpServletResponse response) {
		Agenda agendaSalva = agendaRepository.save(agenda);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, agendaSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(agendaSalva);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		agendaRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO')")
	public ResponseEntity<Agenda> atualizar(@PathVariable Long codigo, @Valid @RequestBody Agenda agenda) {
		try {
			Agenda agendaSalva = agendaService.atualizar(codigo, agenda);
			return ResponseEntity.ok(agendaSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
}
