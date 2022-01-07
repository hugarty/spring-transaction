package com.example.demo.model.relacionamentomany.repository;

import com.example.demo.model.relacionamentomany.RelacionamentoMany;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ManyRepository extends CrudRepository<RelacionamentoMany, Integer>{
    
    RelacionamentoMany findTopByOrderByIdDesc(); 
    
}
