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
import com.example.ams.api.model.UsuarioPermissao;
import com.example.ams.api.repository.UsuarioPermissaoRepository;
import com.example.ams.api.repository.filter.UsuarioPermissaoFilter;
import com.example.ams.api.repository.projection.ResumoUsuarioPermissao;
import com.example.ams.api.service.UsuarioPermissaoService;


@RestController
@RequestMapping("/perfis")
public class UsuarioPermissaoResource {


@Autowired
private UsuarioPermissaoRepository usuarioPermissaoRepository;

@Autowired
private UsuarioPermissaoService usuarioPermissaoService;

@Autowired
private ApplicationEventPublisher publisher;

//@Autowired
//private MessageSource messageSource;

@GetMapping
@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('read')")
public Page<UsuarioPermissao> pesquisar(UsuarioPermissaoFilter usuarioPermissaoFilter, Pageable pageable) {
	return usuarioPermissaoRepository.filtrar(usuarioPermissaoFilter, pageable);
}


@GetMapping(params = "resumo")
@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('read')")
public Page<ResumoUsuarioPermissao> resumir(UsuarioPermissaoFilter usuarioPermissaoFilter, Pageable pageable) {
	return usuarioPermissaoRepository.resumir(usuarioPermissaoFilter, pageable);
}

@GetMapping("/{codigo}")
@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('read')")
public ResponseEntity<UsuarioPermissao> buscarPeloCodigo(@PathVariable Long codigo) {
	UsuarioPermissao usuarioPermissao = usuarioPermissaoRepository.findOne(codigo);
	 return usuarioPermissao != null ? ResponseEntity.ok(usuarioPermissao) : ResponseEntity.notFound().build();
}

@PostMapping
@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('write')")
public ResponseEntity<UsuarioPermissao> criar(@Valid @RequestBody UsuarioPermissao usuarioPermissao, HttpServletResponse response) {
	UsuarioPermissao usuarioPermissaoSalvo = usuarioPermissaoRepository.save(usuarioPermissao);
	publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioPermissaoSalvo.getCodigo()));
	return ResponseEntity.status(HttpStatus.CREATED).body(usuarioPermissaoSalvo);
}

@DeleteMapping("/{codigo}")
@ResponseStatus(HttpStatus.NO_CONTENT)
@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('write')")
public void remover(@PathVariable Long codigo) {
	usuarioPermissaoRepository.delete(codigo);
}

@PutMapping("/{codigo}")
@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
public ResponseEntity<UsuarioPermissao> atualizar(@PathVariable Long codigo, @Valid @RequestBody UsuarioPermissao usuarioPermissao) {
	try {
		UsuarioPermissao usuarioPermissaoSalvo = usuarioPermissaoService.atualizar(codigo, usuarioPermissao);
		return ResponseEntity.ok(usuarioPermissaoSalvo);
	} catch (IllegalArgumentException e) {
		return ResponseEntity.notFound().build();
	}
}

}