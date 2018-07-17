package com.example.ams.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ams.api.model.Exame;
import com.example.ams.api.repository.exame.ExameRepositoryQuery;

public interface ExameRepository extends JpaRepository<Exame, Long>, ExameRepositoryQuery {

}