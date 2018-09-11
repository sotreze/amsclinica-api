package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Medico;
import com.example.ams.api.repository.MedicoRepository;
import com.example.ams.api.model.Pessoa;
import com.example.ams.api.repository.PessoaRepository;
import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class MedicoService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private MedicoRepository medicoRepository;
	
	/*public Medico salvar(Medico medico) {
		//medico.getAgendas().forEach(c -> c.setMedico(medico));
		//validarPessoa(medico);
		return medicoRepository.save(medico);
	}*/

	public Medico salvar(Medico medico) {
		validarPessoa(medico);

		return medicoRepository.save(medico);
	}

	public Medico atualizar(Long codigo, Medico medico) {
		Medico medicoSalvo = buscarMedicoPeloCodigo(codigo);
		if (!medico.getPessoa().equals(medicoSalvo.getPessoa())) {
			validarPessoa(medico);
		}
		
		BeanUtils.copyProperties(medico, medicoSalvo, "codigo");

		return medicoRepository.save(medicoSalvo);
	}
	
	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		Medico medicoSalvo = buscarMedicoPeloCodigo(codigo);
		medicoSalvo.setAtivo(ativo);
		medicoRepository.save(medicoSalvo);
	}

	private void validarPessoa(Medico medico) {
		Pessoa pessoa = null;
		if (medico.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findOne(medico.getPessoa().getCodigo());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	public Medico buscarMedicoExistente(Long codigo) {
		Medico medicoSalvo = medicoRepository.findOne(codigo);
		if (medicoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return medicoSalvo;
	}
	
	public Medico buscarMedicoPeloCodigo(Long codigo) {
		Medico medicoSalvo = medicoRepository.findOne(codigo);
		if (medicoSalvo == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return medicoSalvo;
	}

}