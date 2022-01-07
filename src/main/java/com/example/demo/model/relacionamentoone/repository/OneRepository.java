package com.example.demo.model.relacionamentoone.repository;

import com.example.demo.model.relacionamentoone.RelacionamentoOne;

import org.springframework.data.repository.CrudRepository;

public interface OneRepository extends CrudRepository<RelacionamentoOne, Integer>{
    
    RelacionamentoOne findTopByOrderByIdDesc();
    
}
