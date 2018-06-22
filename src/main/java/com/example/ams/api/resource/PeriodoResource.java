package com.example.ams.api.resource;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.ams.api.event.RecursoCriadoEvent;
import com.example.ams.api.exceptionhandler.AmsExceptionHandler.Erro;
import com.example.ams.api.model.Periodo;
import com.example.ams.api.repository.PeriodoRepository;
import com.example.ams.api.repository.filter.PeriodoFilter;
import com.example.ams.api.repository.projection.ResumoPeriodo;
import com.example.ams.api.service.PeriodoService;
import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/periodos")
public class PeriodoResource {

	@Autowired
	private PeriodoRepository periodoRepository;

	@Autowired
	private PeriodoService periodoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERIODO') and #oauth2.hasScope('read')")
	public Page<Periodo> pesquisar(PeriodoFilter periodoFilter, Pageable pageable) {
		return periodoRepository.filtrar(periodoFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERIODO') and #oauth2.hasScope('read')")
	public Page<ResumoPeriodo> resumir(PeriodoFilter periodoFilter, Pageable pageable) {
		return periodoRepository.resumir(periodoFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_PERIODO') and #oauth2.hasScope('read')")
	public ResponseEntity<Periodo> buscarPeloCodigo(@PathVariable Long codigo) {
		Periodo periodo = periodoRepository.findOne(codigo);
		 return periodo != null ? ResponseEntity.ok(periodo) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PERIODO') and #oauth2.hasScope('write')")
	public ResponseEntity<Periodo> criar(@Valid @RequestBody Periodo periodo, HttpServletResponse response) {
		Periodo periodoSalvo = periodoRepository.save(periodo);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, periodoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(periodoSalvo);
	}

	@ExceptionHandler({ PessoaInexistenteOuInativaException.class })
	public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
		String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
		String mensagemDesenvolvedor = ex.toString();
		List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
		return ResponseEntity.badRequest().body(erros);
	}

	@DeleteMapping("/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_PERIODO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		periodoRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_PERIODO')")
	public ResponseEntity<Periodo> atualizar(@PathVariable Long codigo, @Valid @RequestBody Periodo periodo) {
		try {
			Periodo periodoSalvo = periodoService.atualizar(codigo, periodo);
			return ResponseEntity.ok(periodoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
