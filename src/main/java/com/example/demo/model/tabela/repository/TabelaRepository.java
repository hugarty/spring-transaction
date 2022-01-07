package com.example.demo.model.tabela.repository;

import com.example.demo.model.tabela.Tabela;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TabelaRepository extends CrudRepository<Tabela, Integer>{
    
    Tabela findTopByOrderByIdDesc(); // Faz duas requisições para o banco por conta do One to One
    
    Tabela findTopByOrderByIdAsc(); // Faz duas requisições para o banco por conta do One to One

    // @Query("SELECT t FROM Tabela t WHERE :id")
    // Tabela recuperarLazy(Integer id); // recupera várias tabelas

    @Query("SELECT t FROM Tabela t JOIN FETCH t.many")
    Tabela recuperarEager(); // Este também faz duas requisições para o banco por conta do mapeamento One to One

}
