package com.example.ams.api.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ams.api.model.Medicacao;
import com.example.ams.api.repository.MedicacaoRepository;

@RestController
@RequestMapping("/medicacoes")
public class MedicacaoResource {
	
	@Autowired
	private MedicacaoRepository medicacaoRepository;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public List<Medicacao> listar() {
		return medicacaoRepository.findAll();
	}

}