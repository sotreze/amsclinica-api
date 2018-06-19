package com.example.ams.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	// analisar
	public Optional<Usuario> findByEmail(String email);


	// analisar
	public Page<Usuario> findByPrimeiroNomeContaining(String primeiroNome, Pageable pageable);

	public List<Usuario> findByPermissoesDescricao(String permissaoDescricao);


}
