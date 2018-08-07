package com.example.ams.api.resource;


import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.example.ams.api.exceptionhandler.AmsExceptionHandler.Erro;
import com.example.ams.api.model.Solicitacao;
import com.example.ams.api.repository.SolicitacaoRepository;
import com.example.ams.api.repository.filter.SolicitacaoFilter;
import com.example.ams.api.repository.projection.ResumoSolicitacao;
import com.example.ams.api.service.SolicitacaoService;
import com.example.ams.api.service.exception.AgendaInexistenteOuInativaException;
import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;
import com.example.ams.api.storage.S3;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoResource {

	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private SolicitacaoService solicitacaoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private S3 s3;


	@PostMapping("/anexo")
	@PreAuthorize("hasAuthority('ROLE_USUARIO') and #oauth2.hasScope('write')")
	public Anexo uploadAnexo(@RequestParam MultipartFile anexo) throws IOException {
		String nome = s3.salvarTemporariamente(anexo);
		return new Anexo(nome, s3.configurarUrl(nome));

	}


	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<Solicitacao> pesquisar(SolicitacaoFilter solicitacaoFilter, Pageable pageable) {
		return solicitacaoRepository.filtrar(solicitacaoFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('read')")
	public Page<ResumoSolicitacao> resumir(SolicitacaoFilter solicitacoFilter, Pageable pageable) {
		return solicitacaoRepository.resumir(solicitacoFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_USUARIO') and #oauth2.hasScope('read')")
	public ResponseEntity<Solicitacao> buscarPeloCodigo(@PathVariable Long codigo) {
		Solicitacao solicitacao = solicitacaoRepository.findOne(codigo);
		return solicitacao != null ? ResponseEntity.ok(solicitacao) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_USUARIO') and #oauth2.hasScope('write')")
	public ResponseEntity<Solicitacao> criar(@Valid @RequestBody Solicitacao solicitacao, HttpServletResponse response) {
		Solicitacao solicitacaoSalva = solicitacaoService.salvar(solicitacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, solicitacaoSalva.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(solicitacaoSalva);
	}

	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}
	
	@ExceptionHandler({ AgendaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handleAgendaInexistenteOuInativaException(AgendaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("agenda.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		solicitacaoRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_FUNCIONARIO')")
	public ResponseEntity<Solicitacao> atualizar(@PathVariable Long codigo, @Valid @RequestBody Solicitacao solicitacao) {
		try {
			Solicitacao solicitacaoSalva = solicitacaoService.atualizar(codigo, solicitacao);
			return ResponseEntity.ok(solicitacaoSalva);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
