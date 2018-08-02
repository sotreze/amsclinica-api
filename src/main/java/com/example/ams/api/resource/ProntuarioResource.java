package com.example.ams.api.resource;

import java.io.IOException;


//import java.util.Arrays;
//import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.context.MessageSource;
//import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.ams.api.dto.Anexo;
import com.example.ams.api.event.RecursoCriadoEvent;
//import com.example.ams.api.exceptionhandler.AmsExceptionHandler.Erro;
import com.example.ams.api.model.Prontuario;
import com.example.ams.api.repository.ProntuarioRepository;
import com.example.ams.api.repository.filter.ProntuarioFilter;
import com.example.ams.api.repository.projection.ResumoProntuario;
import com.example.ams.api.service.ProntuarioService;
//import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

import com.example.ams.api.storage.S3;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioResource {

	@Autowired
	private ProntuarioRepository prontuarioRepository;

	@Autowired
	private ProntuarioService prontuarioService;

	@Autowired
	private ApplicationEventPublisher publisher;

	//@Autowired
	//private MessageSource messageSource;
	
	@Autowired
	private S3 s3;
	
	@PostMapping("/anexo")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public Anexo uploadAnexo(@RequestParam MultipartFile anexo) throws IOException {
		String nome = s3.salvarTemporariamente(anexo);
		return new Anexo(nome, s3.configurarUrl(nome));
	}

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<Prontuario> pesquisar(ProntuarioFilter prontuarioFilter, Pageable pageable) {
		return prontuarioRepository.filtrar(prontuarioFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<ResumoProntuario> resumir(ProntuarioFilter prontuarioFilter, Pageable pageable) {
		return prontuarioRepository.resumir(prontuarioFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Prontuario> buscarPeloCodigo(@PathVariable Long codigo) {
		Prontuario prontuario = prontuarioRepository.findOne(codigo);
		 return prontuario != null ? ResponseEntity.ok(prontuario) : ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Prontuario> criar(@Valid @RequestBody Prontuario prontuario, HttpServletResponse response) {
		Prontuario prontuarioSalvo = prontuarioService.salvar(prontuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, prontuarioSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(prontuarioSalvo);
	}


	/*@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}*/

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_ADMINISTRADOR') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		prontuarioRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO')")
	public ResponseEntity<Prontuario> atualizar(@PathVariable Long codigo, @Valid @RequestBody Prontuario prontuario) {
		try {
			Prontuario prontuarioSalvo = prontuarioService.atualizar(codigo, prontuario);
			return ResponseEntity.ok(prontuarioSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
