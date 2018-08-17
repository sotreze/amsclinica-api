package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.ams.api.model.Prontuario;
import com.example.ams.api.repository.ProntuarioRepository;
import com.example.ams.api.storage.S3;

@Service
public class ProntuarioService {
	

	@Autowired
	private ProntuarioRepository prontuarioRepository;
	
	
	@Autowired
	private S3 s3;

	public Prontuario salvar(Prontuario prontuario) {
		if (StringUtils.hasText(prontuario.getAnexo())) {
			s3.salvar(prontuario.getAnexo());
		}
		
		return prontuarioRepository.save(prontuario);
	}

	public Prontuario atualizar(Long codigo, Prontuario prontuario) {
		Prontuario prontuarioSalvo = buscarProntuarioExistente(codigo);
		
		if (StringUtils.isEmpty(prontuario.getAnexo())
				&& StringUtils.hasText(prontuarioSalvo.getAnexo())) {
			s3.remover(prontuarioSalvo.getAnexo());
		} else if (StringUtils.hasText(prontuario.getAnexo())
				&& !prontuario.getAnexo().equals(prontuarioSalvo.getAnexo())) {
			s3.substituir(prontuarioSalvo.getAnexo(), prontuario.getAnexo());
		}

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


