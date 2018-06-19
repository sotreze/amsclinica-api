package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Prontuario;
import com.example.ams.api.repository.ProntuarioRepository;

@Service
public class ProntuarioService {

	@Autowired
	private ProntuarioRepository prontuarioRepository;

	public Prontuario salvar(Prontuario prontuario) {
		return prontuarioRepository.save(prontuario);
	}

	public Prontuario atualizar(Long codigo, Prontuario prontuario) {
		Prontuario prontuarioSalvo = buscarProntuarioExistente(codigo);

		BeanUtils.copyProperties(prontuario, prontuarioSalvo, "codigo");

		return prontuarioRepository.save(prontuarioSalvo);
	}


	private Prontuario buscarProntuarioExistente(Long codigo) {
		Prontuario prontuarioSalvo = prontuarioRepository.findOne(codigo);
		if (prontuarioSalvo == null) {
			throw new IllegalArgumentException();
		}
		return prontuarioSalvo;
	}

}


