package com.example.ams.api.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Exame;
import com.example.ams.api.repository.ExameRepository;


@Service
public class ExameService {

	@Autowired
	private ExameRepository exameRepository;

	public Exame atualizar(Long codigo, Exame exame) {
		Exame exameSalvo = buscarExameExistente(codigo);

		BeanUtils.copyProperties(exame, exameSalvo, "codigo");

		return exameRepository.save(exameSalvo);
	}


	private Exame buscarExameExistente(Long codigo) {
		Exame exameSalvo = exameRepository.findOne(codigo);
		if (exameSalvo == null) {
			throw new IllegalArgumentException();
		}
		return exameSalvo;
	}

}



