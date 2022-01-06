package com.example.demo.model.service;

import java.time.LocalDateTime;
import java.util.Random;

import com.example.demo.model.Tabela;
import com.example.demo.model.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateService { 
        
    @Autowired
    private TabelaRepository repository;

    /**
     * Atualiza.
     */
    public void updateDefault () {
		update("updateDefault");
    }

    /**
     * Atualiza.
     */
    @Transactional
    public void updateTransactional () {
		update("updateTransactional");
    }

    /**
     * NÃO atualiza e não lança exceção.como 
     */
    @Transactional(readOnly = true)
    public void updateReadOnly () {
		update("updateReadOnly");
    }

    /**
     * Atualiza.
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void updatePropagationNotSupported () {
		update("updatePropagationNotSupported");
    }

    /**
     * Atualiza.
     */
    @Transactional(propagation = Propagation.NEVER)
    public void updatePropagationNever () {
		update("updatePropagationNever");
    }
    

    private void update(String methodName) {
        System.out.println("\n - - - " + methodName + " - - -  \n");
        Tabela tabela = repository.findTopByOrderByIdAsc();
        tabela.setNome(methodName);
        tabela.setData(LocalDateTime.now());
        repository.save(tabela);
    }

}