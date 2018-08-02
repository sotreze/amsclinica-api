package com.example.ams.api.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ams.api.model.Horario;
import com.example.ams.api.repository.HorarioRepository;

@RestController
@RequestMapping("/horarios")
public class HorarioResource {

	@Autowired
	private HorarioRepository horarioRepository;
	
	@GetMapping
	@PreAuthorize("isAuthenticated()")
	public List<Horario> listar() {
		return horarioRepository.findAll();
	}	
}
