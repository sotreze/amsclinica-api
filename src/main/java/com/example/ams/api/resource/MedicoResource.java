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
import com.example.ams.api.model.Medico;
import com.example.ams.api.repository.MedicoRepository;
import com.example.ams.api.repository.filter.MedicoFilter;
import com.example.ams.api.repository.projection.ResumoMedico;
import com.example.ams.api.service.MedicoService;
import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("/medicos")
public class MedicoResource {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private MedicoService medicoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private MessageSource messageSource;

	@GetMapping
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICO') and #oauth2.hasScope('read')")
	public Page<Medico> pesquisar(MedicoFilter medicoFilter, Pageable pageable) {
		return medicoRepository.filtrar(medicoFilter, pageable);
	}

	@GetMapping(params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICO') and #oauth2.hasScope('read')")
	public Page<ResumoMedico> resumir(MedicoFilter medicoFilter, Pageable pageable) {
		return medicoRepository.resumir(medicoFilter, pageable);
	}

	@GetMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_MEDICO') and #oauth2.hasScope('read')")
	public ResponseEntity<Medico> buscarPeloCodigo(@PathVariable Long codigo) {
		Medico medico = medicoRepository.findOne(codigo);
		 return medico != null ? ResponseEntity.ok(medico) : ResponseEntity.notFound().build();
	}

	@PostMapping
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICO') and #oauth2.hasScope('write')")
	public ResponseEntity<Medico> criar(@Valid @RequestBody Medico medico, HttpServletResponse response) {
		//Medico medicoSalvo = medicoRepository.save(medico);
		Medico medicoSalvo = medicoService.salvar(medico);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, medicoSalvo.getCodigo()));
		return ResponseEntity.status(HttpStatus.CREATED).body(medicoSalvo);
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
	@PreAuthorize("hasAuthority('ROLE_REMOVER_MEDICO') and #oauth2.hasScope('write')")
	public void remover(@PathVariable Long codigo) {
		medicoRepository.delete(codigo);
	}

	@PutMapping("/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_MEDICO')")
	public ResponseEntity<Medico> atualizar(@PathVariable Long codigo, @Valid @RequestBody Medico medico) {
		try {
			Medico medicoSalvo = medicoService.atualizar(codigo, medico);
			return ResponseEntity.ok(medicoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}

}
