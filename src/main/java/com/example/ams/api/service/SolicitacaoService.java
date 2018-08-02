package com.example.ams.api.service;


import java.time.LocalDate;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.ams.api.mail.Mailer;
import com.example.ams.api.model.Solicitacao;
import com.example.ams.api.model.Usuario;
import com.example.ams.api.repository.SolicitacaoRepository;
import com.example.ams.api.repository.UsuarioRepository;
import com.example.ams.api.storage.S3;



@Service
public class SolicitacaoService {

	private static final String DESTINATARIOS = "ROLE_FUNCIONARIO";

	private static final Logger logger = LoggerFactory.getLogger(SolicitacaoService.class);

	
	@Autowired
	private SolicitacaoRepository solicitacaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Mailer mailer;

	@Autowired
	private S3 s3;

	//inicio refatorar
	//@Scheduled(cron = "0 38 9 * * *")
	@Scheduled(cron = "0 30 23 * * *")
	public void avisarSobreConsultasCanceladas() {

		if (logger.isDebugEnabled()) {
			logger.debug("Preparando envio de "
					+ "e-mails de aviso de solicitacões cadastradas pelos usuários.");
		}

		List<Solicitacao> consultas = solicitacaoRepository
				.findByDataGreaterThanEqual(LocalDate.now());

		if (consultas.isEmpty()) {
			logger.info("Sem solicitacões cadastradas para o aviso.");

			return;
		}

		logger.info("Exitem {} solicitações cadastradas.", consultas.size());

		List<Usuario> destinatarios = usuarioRepository
				.findByPermissoesDescricao(DESTINATARIOS);

		if (destinatarios.isEmpty()) {
			logger.warn("Existem solicitações cadastradas, mas o "
					+ "sistema não encontrou destinatários.");

			return;
		}

		mailer.avisarSobreConsultasCanceladas(consultas, destinatarios);

		logger.info("Envio de e-mail de solicitação concluído.");
	}
	//fim refatorar



	public Solicitacao salvar(Solicitacao solicitacao) {

		if (StringUtils.hasText(solicitacao.getAnexo())) {
			s3.salvar(solicitacao.getAnexo());
		}

		return solicitacaoRepository.save(solicitacao);
	}

	public Solicitacao atualizar(Long codigo, Solicitacao solicitacao) {
		Solicitacao solicitacaoSalva = buscarSolicitacaoExistente(codigo);
		if (StringUtils.isEmpty(solicitacao.getAnexo())
				&& StringUtils.hasText(solicitacaoSalva.getAnexo())) {
			s3.remover(solicitacaoSalva.getAnexo());
		} else if (StringUtils.hasText(solicitacao.getAnexo())
				&& !solicitacao.getAnexo().equals(solicitacaoSalva.getAnexo())) {
			s3.substituir(solicitacaoSalva.getAnexo(), solicitacao.getAnexo());
		}

		BeanUtils.copyProperties(solicitacao, solicitacaoSalva, "codigo");

		return solicitacaoRepository.save(solicitacaoSalva);
	}


	private Solicitacao buscarSolicitacaoExistente(Long codigo) {
		Solicitacao solicitacaoSalva = solicitacaoRepository.findOne(codigo);
		if (solicitacaoSalva == null) {
			throw new IllegalArgumentException();
		}
		return solicitacaoSalva;
	}

}

