package com.example.ams.api.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ams.api.event.RecursoCriadoEvent;
import com.example.ams.api.model.Exame;
import com.example.ams.api.repository.ExameRepository;

@RestController
@RequestMapping("/exames")
public class ExameResource {

	@Autowired
	private ExameRepository exameRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EXAME') and #oauth2.hasScope('read')")
	public List<Exame> listar() {
		return exameRepository.findAll();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_EXAME') and #oauth2.hasScope('write')")
	public ResponseEntity<Exame> criar(@Valid @RequestBody Exame exame, HttpServletResponse response) {
		Exame exameSalvo = exameRepository.save(exame);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, exameSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(exameSalvo);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_EXAME') and #oauth2.hasScope('read')")
	public ResponseEntity<Exame> buscarPeloCodigo(@PathVariable Long codigo) {
		Exame exame = exameRepository.findOne(codigo);
		 return exame != null ? ResponseEntity.ok(exame) : ResponseEntity.notFound().build();
	}

}
