package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.Funcionario;
import com.example.ams.api.repository.FuncionarioRepository;
import com.example.ams.api.model.Pessoa;
import com.example.ams.api.repository.PessoaRepository;
import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class FuncionarioService {

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public Funcionario salvar(Funcionario funcionario) {
		validarPessoa(funcionario);

		return funcionarioRepository.save(funcionario);
	}

	public Funcionario atualizar(Long codigo, Funcionario funcionario) {
		Funcionario funcionarioSalvo = buscarFuncionarioExistente(codigo);
		if (!funcionario.getPessoa().equals(funcionarioSalvo.getPessoa())) {
			validarPessoa(funcionario);
		}

		BeanUtils.copyProperties(funcionario, funcionarioSalvo, "codigo");

		return funcionarioRepository.save(funcionarioSalvo);
	}

	private void validarPessoa(Funcionario funcionario) {
		Pessoa pessoa = null;
		if (funcionario.getPessoa().getCodigo() != null) {
			pessoa = pessoaRepository.findOne(funcionario.getPessoa().getCodigo());
		}

		if (pessoa == null || pessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private Funcionario buscarFuncionarioExistente(Long codigo) {
		Funcionario funcionarioSalvo = funcionarioRepository.findOne(codigo);
		if (funcionarioSalvo == null) {
			throw new IllegalArgumentException();
		}
		return funcionarioSalvo;
	}

}

