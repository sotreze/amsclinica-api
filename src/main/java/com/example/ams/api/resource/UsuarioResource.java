//cadastro de usuario

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.ams.api.event.RecursoCriadoEvent;
import com.example.ams.api.model.Usuario;
import com.example.ams.api.repository.UsuarioRepository;
import com.example.ams.api.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private ApplicationEventPublisher publisher;


	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Usuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Usuario usuario = usuarioRepository.findOne(codigo);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('write')")
	public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario, HttpServletResponse response) {
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuarioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		usuarioRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR')")
	public ResponseEntity<Usuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Usuario usuario) {
		try {
			Usuario usuarioSalvo = usuarioService.atualizar(codigo, usuario);
			return ResponseEntity.ok(usuarioSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@PutMapping("/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		usuarioService.atualizarPropriedadeAtivo(codigo, ativo);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO')")
	public Page<Usuario> pesquisar(@RequestParam(required = false, defaultValue = "%") String primeiroNome, Pageable pageable) {
		return usuarioRepository.findByPrimeiroNomeContaining(primeiroNome, pageable);
	}

}

