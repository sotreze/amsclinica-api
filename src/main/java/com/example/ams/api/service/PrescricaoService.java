package com.example.ams.api.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Prescricao;
import com.example.ams.api.repository.PrescricaoRepository;


@Service
public class PrescricaoService {

	@Autowired
	private PrescricaoRepository prescricaoRepository;

	public Prescricao atualizar(Long codigo, Prescricao prescricao) {
		Prescricao prescricaoSalva = buscarPrescricaoExistente(codigo);

		BeanUtils.copyProperties(prescricao, prescricaoSalva, "codigo");

		return prescricaoRepository.save(prescricaoSalva);
	}


	private Prescricao buscarPrescricaoExistente(Long codigo) {
		Prescricao prescricaoSalva = prescricaoRepository.findOne(codigo);
		if (prescricaoSalva == null) {
			throw new IllegalArgumentException();
		}
		return prescricaoSalva;
	}

}


