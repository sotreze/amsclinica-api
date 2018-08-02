package com.example.ams.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

}