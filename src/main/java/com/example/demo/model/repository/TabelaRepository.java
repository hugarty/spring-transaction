package com.example.demo.model.repository;

import com.example.demo.model.Tabela;

import org.springframework.data.repository.CrudRepository;

public interface TabelaRepository extends CrudRepository<Tabela, Integer>{
    
    Tabela findTopByOrderByIdDesc();
}
