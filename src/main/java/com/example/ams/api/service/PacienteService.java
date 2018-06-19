package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Paciente;
import com.example.ams.api.repository.PacienteRepository;
import com.example.ams.api.model.Pessoa;
import com.example.ams.api.repository.PessoaRepository;
import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class PacienteService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	public Paciente salvar(Paciente paciente) {
		validarPessoa(paciente);

		return pacienteRepository.save(paciente);
	}

	public Paciente atualizar(Long codigo, Paciente paciente) {
		Paciente pacienteSalvo = buscarPacienteExistente(codigo);
		if (!paciente.getPessoa().equals(pacienteSalvo.getPessoa())) {
			validarPessoa(paciente);
		}

		BeanUtils.copyProperties(paciente, pacienteSalvo, "codigo");

		return pacienteRepository.save(pacienteSalvo);
	}

	private void validarPessoa(Paciente paciente) {
		Pessoa pessoa = null;
		if (paciente.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findOne(paciente.getPessoa().getCodigo());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private Paciente buscarPacienteExistente(Long codigo) {
		Paciente pacienteSalvo = pacienteRepository.findOne(codigo);
		if (pacienteSalvo == null) {
			throw new IllegalArgumentException();
		}
		return pacienteSalvo;
	}

}

