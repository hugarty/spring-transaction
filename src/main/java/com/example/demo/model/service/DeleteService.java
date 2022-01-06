package com.example.demo.model.service;

import com.example.demo.model.Tabela;
import com.example.demo.model.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteService {
    
    @Autowired
    private TabelaRepository repository;

    /**
     * Deleta normal
     */
    public void deleteDefault () {
        findLastAndDelete();
    }
    /**
     * Deleta normal
     */
    @Transactional
    public void deleteTransactional () {
        findLastAndDelete();
    }

    /**
     * Não deleta e não lança exceção
     */
    @Transactional(readOnly = true)
    public void deleteReadOnly () {
        findLastAndDelete();
    }

    /** 
     * Deleta e não lança exceção
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void deleteNotSupported () {
        findLastAndDelete();
    }

    /** 
     * Deleta e não lança exceção
     */
    @Transactional(propagation = Propagation.NEVER)
    public void deleteNever () {
        findLastAndDelete();
    }

    private void findLastAndDelete() {
        Tabela tabela = repository.findTopByOrderByIdDesc();
        System.out.println(tabela);
        repository.delete(tabela);
    }
}
