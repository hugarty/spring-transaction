package com.example.demo.model.tabela.service;

import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.repository.TabelaRepository;

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
        findLastAndDelete("deleteDefault");
    }
    /**
     * Deleta normal
     */
    @Transactional
    public void deleteTransactional () {
        findLastAndDelete("deleteTransactional");
    }

    /**
     * Não deleta e não lança exceção
     */
    @Transactional(readOnly = true)
    public void deleteReadOnly () {
        findLastAndDelete("deleteReadOnly");
    }

    /** 
     * Deleta e não lança exceção
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void deleteNotSupported () {
        findLastAndDelete("deleteNotSupported");
    }

    /** 
     * Deleta e não lança exceção
     */
    @Transactional(propagation = Propagation.NEVER)
    public void deleteNever () {
        findLastAndDelete("deleteNever");
    }

    private void findLastAndDelete(String methodName) {
        System.out.print("\n\n - - - "+methodName+" - - - \n\n");
        Tabela tabela = repository.findTopByOrderByIdDesc();
        System.out.println(tabela);
        repository.delete(tabela);
    }
}
