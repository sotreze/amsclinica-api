package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.UsuarioPermissao;
import com.example.ams.api.repository.usuariopermissao.UsuarioPermissaoRepositoryQuery;

public interface UsuarioPermissaoRepository extends JpaRepository<UsuarioPermissao, Long>, UsuarioPermissaoRepositoryQuery {

}
