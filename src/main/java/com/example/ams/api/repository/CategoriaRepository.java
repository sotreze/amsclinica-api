package com.example.ams.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}