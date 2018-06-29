package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.ams.api.model.Agenda;
import com.example.ams.api.model.Periodo;
import com.example.ams.api.repository.PeriodoRepository;
//import com.example.ams.api.repository.AgendaRepository;
//import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class PeriodoService {

	//@Autowired
	//private AgendaRepository agendaRepository;

	@Autowired
	private PeriodoRepository periodoRepository;

	public Periodo salvar(Periodo periodo) {
		//validarAgenda(periodo);

		return periodoRepository.save(periodo);
	}

	public Periodo atualizar(Long codigo, Periodo periodo) {
		Periodo periodoSalvo = buscarPeriodoExistente(codigo);
		/*if (!periodo.getAgenda().equals(periodoSalvo.getAgenda())) {
			validarAgenda(periodo);
		}*/

		BeanUtils.copyProperties(periodo, periodoSalvo, "codigo");

		return periodoRepository.save(periodoSalvo);
	}

	/*private void validarAgenda(Periodo periodo) {
		Agenda agenda = null;
		if (periodo.getAgenda().getCodigo() != null) {
			agenda = agendaRepository.findOne(periodo.getAgenda().getCodigo());
		}

		if (agenda == null || agenda.getMedico().getPessoa().isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}*/

	private Periodo buscarPeriodoExistente(Long codigo) {
		Periodo periodoSalvo = periodoRepository.findOne(codigo);
		if (periodoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return periodoSalvo;
	}

}
