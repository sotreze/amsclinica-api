package com.example.ams.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ams.api.model.UsuarioPermissao;
import com.example.ams.api.repository.UsuarioRepository;
import com.example.ams.api.repository.UsuarioPermissaoRepository;
import com.example.ams.api.model.Usuario;
import com.example.ams.api.service.exception.PessoaInexistenteOuInativaException;

@Service
public class UsuarioPermissaoService {

	@Autowired
	private UsuarioPermissaoRepository usuarioPermissaoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;
	

	public UsuarioPermissao salvar(UsuarioPermissao usuarioPermissao) {
		validarUsuario(usuarioPermissao);

		return usuarioPermissaoRepository.save(usuarioPermissao);
	}

	public UsuarioPermissao atualizar(Long codigo, UsuarioPermissao usuarioPermissao) {
		UsuarioPermissao usuarioPermissaoSalvo = buscarUsuarioExistente(codigo);
		if (!usuarioPermissao.getUsuario().equals(usuarioPermissaoSalvo.getUsuario())) {
			validarUsuario(usuarioPermissao);
		}

		BeanUtils.copyProperties(usuarioPermissao, usuarioPermissaoSalvo, "codigo_usuario");

		return usuarioPermissaoRepository.save(usuarioPermissaoSalvo);
	}

	private void validarUsuario(UsuarioPermissao usuarioPermissao) {
		Usuario usuario = null;
		if (usuarioPermissao.getUsuario().getCodigo() != null) {
			usuario = usuarioRepository.findOne(usuarioPermissao.getUsuario().getCodigo());
		}

		if (usuario == null || usuario.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}

	private UsuarioPermissao buscarUsuarioExistente(Long codigo) {
		UsuarioPermissao usuarioPermissaoSalvo = usuarioPermissaoRepository.findOne(codigo);
		if (usuarioPermissaoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return usuarioPermissaoSalvo;
	}

}

