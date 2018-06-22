package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Agenda;
import com.example.ams.api.repository.AgendaRepository;
//import com.example.ams.api.model.Medico;
//import com.example.ams.api.repository.MedicoRepository;
//import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class AgendaService {

	//@Autowired
	//private MedicoRepository medicoRepository;

	@Autowired
	private AgendaRepository agendaRepository;

	public Agenda salvar(Agenda agenda) {
		//validarMedico(agenda);

		return agendaRepository.save(agenda);
	}

	public Agenda atualizar(Long codigo, Agenda agenda) {
		Agenda agendaSalva = buscarAgendaExistente(codigo);
		/*if (!agenda.getMedico().equals(agendaSalva.getMedico())) {
			validarMedico(agenda);
		}*/

		BeanUtils.copyProperties(agenda, agendaSalva, "codigo");

		return agendaRepository.save(agendaSalva);
	}

	/*private void validarMedico(Agenda agenda) {
		Medico medico = null;
		if (agenda.getMedico().getCodigo() != null) {
			medico = medicoRepository.findOne(agenda.getMedico().getCodigo());
		}

		if (medico == null || medico.getPessoa().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}*/

	private Agenda buscarAgendaExistente(Long codigo) {
		Agenda agendaSalva = agendaRepository.findOne(codigo);
		if (agendaSalva == null) {
			throw new IllegalArgumentException();
		}
		return agendaSalva;
	}

}