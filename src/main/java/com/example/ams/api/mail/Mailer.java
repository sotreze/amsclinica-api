package com.example.ams.api.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.example.ams.api.model.Lancamento;
import com.example.ams.api.model.Usuario;

@Component
public class Mailer {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private TemplateEngine thymeleaf;

	/*@Autowired
	private LancamentoRepository repo;
	@EventListener
	private void teste(ApplicationReadyEvent event) {
		String template = "mail/aviso-lancamentos-limites";

		List<Lancamento> lista = repo.findAll();

		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("lancamentos", lista);

		this.enviarEmail("amsproconsultoria@gmail.com",
				Arrays.asList("sotreze@yahoo.com.br"),
				"Testando", template, variaveis);
		System.out.println("Terminado o envio de e-mail...");
	}*/

	// início refatorar
	public void avisarSobreLancamentosConsultas(
			List<Lancamento> proximas, List<Usuario> destinatarios) {
		Map<String, Object> variaveis = new HashMap<>();
		variaveis.put("lancamentos", proximas);

		List<String> emails = destinatarios.stream()
				.map(u -> u.getEmail())
				.collect(Collectors.toList());

		this.enviarEmail("sistemaamsclinica@gmail.com",
				emails,
				"Lançamentos de consultas",
				"mail/aviso-lancamentos-consultas",
				variaveis);
	}
	// fim refatorar

	public void enviarEmail(String remetente,
			List<String> destinatarios, String assunto, String template,
			Map<String, Object> variaveis) {
		Context context = new Context(new Locale("pt", "BR"));

		variaveis.entrySet().forEach(
				e -> context.setVariable(e.getKey(), e.getValue()));

		String mensagem = thymeleaf.process(template, context);

		this.enviarEmail(remetente, destinatarios, assunto, mensagem);
	}

	public void enviarEmail(String remetente,
			List<String> destinatarios, String assunto, String mensagem) {
		try {
			MimeMessage mimeMessage = mailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
			helper.setFrom(remetente);
			helper.setTo(destinatarios.toArray(new String[destinatarios.size()]));
			helper.setSubject(assunto);
			helper.setText(mensagem, true);

			mailSender.send(mimeMessage);
		} catch (MessagingException e) {
			throw new RuntimeException("Problemas com o envio de e-mail!", e);
		}
	}
}