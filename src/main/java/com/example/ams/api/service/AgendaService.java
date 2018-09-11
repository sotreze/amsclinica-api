package com.example.ams.api.service;


import java.io.InputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.ams.api.dto.AgendaEstatisticaMedico;
import com.example.ams.api.model.Agenda;
import com.example.ams.api.model.Medico;
import com.example.ams.api.repository.AgendaRepository;
import com.example.ams.api.repository.MedicoRepository;
import com.example.ams.api.service.exception.MedicoInexistenteOuInativoException;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@Service
public class AgendaService {
	
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private AgendaRepository agendaRepository;
	
	
	public byte[] relatorioPorMedico(LocalDate inicio, LocalDate fim) throws Exception {
		List<AgendaEstatisticaMedico> dados = agendaRepository.porMedico(inicio, fim);

		Map<String, Object> parametros = new HashMap<>();
		parametros.put("DT_INICIO", Date.valueOf(inicio));
		parametros.put("DT_FIM", Date.valueOf(fim));
		parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

		InputStream inputStream = this.getClass().getResourceAsStream(
				"/relatorios/agendas-por-medico.jasper");

		JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros,
				new JRBeanCollectionDataSource(dados));

		return JasperExportManager.exportReportToPdf(jasperPrint);
	}

	public Agenda salvar(Agenda agenda) {
		
		validarMedico(agenda);	
		validarHora(agenda);
		
		return agendaRepository.save(agenda);		
	}

	public Agenda atualizar(Long codigo, Agenda agenda) {
		Agenda agendaSalva = buscarAgendaPeloCodigo(codigo);
		if (!agenda.getMedico().equals(agendaSalva.getMedico())) {
			validarMedico(agenda);
			validarHora(agenda);
		}

		BeanUtils.copyProperties(agenda, agendaSalva, "codigo");
		return agendaRepository.save(agendaSalva);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Agenda agendaSalva = buscarAgendaPeloCodigo(codigo);
		agendaSalva.setAtivo(ativo);
		agendaRepository.save(agendaSalva);
	}

	private void validarMedico(Agenda agenda) {
		Medico medico = null;
		
		if (agenda.getMedico().getCodigo() != null) {
			medico = medicoRepository.findOne(agenda.getMedico().getCodigo());
		}

		if (medico == null || medico.isInativo()) {
			throw new MedicoInexistenteOuInativoException();
		}
	}

	private Agenda buscarAgendaPeloCodigo(Long codigo) {
		Agenda agendaSalva = agendaRepository.findOne(codigo);
		if (agendaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return agendaSalva;
	}
	
	private void validarHora(Agenda agenda) {
		
	      if ( agenda.getHora().isBefore(agenda.getHoraAgendamento())) {
	    	 if ( agenda.getData().compareTo(agenda.getDataAgendamento()) == 0) { 
	    	  //throw new IllegalArgumentException("Agenda não disponível");
	    		 throw new MedicoInexistenteOuInativoException();
	    	 }
	       }
	
	}
}