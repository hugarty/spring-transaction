package com.example.demo.model.service;

import java.time.LocalDateTime;

import com.example.demo.model.Tabela;
import com.example.demo.model.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SaveService {
    
    @Autowired
    private TabelaRepository repository;

    /**
     * Salva normalmente.
     */
    public void saveDefault () {
		saveNew("saveDefault");
    }

    /**
     * Salva normalmente.
     */
    @Transactional
    public void saveTransactional () {
		saveNew("saveTransactional");
    }

    /**
     * Lança uma exceção 
     * org.postgresql.util.PSQLException: ERROR: cannot execute INSERT in a read-only transaction
     */
    @Transactional(readOnly = true)
    public void saveReadOnly () { 
		saveNew("saveReadOnly");
    }

    /**
     * Salva normalmente.
     */
    @Transactional(propagation = Propagation.NEVER)
    public void savePropagationNever () {
		saveNew("savePropagationNever");
    }

    /**
     * Salva normalmente.
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void savePropagationNotSupported () {
		saveNew("savePropagationNotSupported");
    }

    private void saveNew(String methodName) {
        System.out.println("\n - - - "+ methodName +" - - -  \n");
        Tabela tabela = new Tabela();
        tabela.setNome(methodName);
        tabela.setData(LocalDateTime.now());
        repository.save(tabela);
    }

}
