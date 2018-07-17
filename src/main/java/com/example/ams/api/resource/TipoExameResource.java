package com.example.ams.api.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ams.api.model.TipoExame;
import com.example.ams.api.repository.TipoExameRepository;

@RestController
@RequestMapping("/tiposexames")
public class TipoExameResource {
	
	@Autowired
	private TipoExameRepository tipoExameRepository;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public List<TipoExame> listar() {
		return tipoExameRepository.findAll();
	}

}