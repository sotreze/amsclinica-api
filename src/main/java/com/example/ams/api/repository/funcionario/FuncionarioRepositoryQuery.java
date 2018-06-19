package com.example.ams.api.repository.funcionario;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.ams.api.model.Funcionario;
import com.example.ams.api.repository.filter.FuncionarioFilter;
import com.example.ams.api.repository.projection.ResumoFuncionario;

public interface FuncionarioRepositoryQuery {

	public Page<Funcionario> filtrar(FuncionarioFilter funcionarioFilter, Pageable pageable);
	public Page<ResumoFuncionario> resumir(FuncionarioFilter funcionarioFilter, Pageable pageable);

}