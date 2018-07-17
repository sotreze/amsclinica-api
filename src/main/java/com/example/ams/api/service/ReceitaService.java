package com.example.ams.api.service;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Receita;
import com.example.ams.api.repository.ReceitaRepository;


@Service
public class ReceitaService {

	@Autowired
	private ReceitaRepository receitaRepository;

	public Receita atualizar(Long codigo, Receita receita) {
		Receita receitaSalva = buscarReceitaExistente(codigo);

		BeanUtils.copyProperties(receita, receitaSalva, "codigo");

		return receitaRepository.save(receitaSalva);
	}


	private Receita buscarReceitaExistente(Long codigo) {
		Receita receitaSalva = receitaRepository.findOne(codigo);
		if (receitaSalva == null) {
			throw new IllegalArgumentException();
		}
		return receitaSalva;
	}

}


