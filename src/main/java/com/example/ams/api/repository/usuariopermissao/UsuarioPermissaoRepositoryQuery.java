package com.example.ams.api.repository.usuariopermissao;

import com.example.ams.api.model.UsuarioPermissao;
import com.example.ams.api.repository.filter.UsuarioPermissaoFilter;
import com.example.ams.api.repository.projection.ResumoUsuarioPermissao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UsuarioPermissaoRepositoryQuery {
	
	public Page<UsuarioPermissao> filtrar(UsuarioPermissaoFilter usuarioPermissaoFilter, Pageable pageable);
	public Page<ResumoUsuarioPermissao> resumir(UsuarioPermissaoFilter usuarioPermissaoFilter, Pageable pageable);

}
