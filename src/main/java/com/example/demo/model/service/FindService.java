package com.example.demo.model.service;


import com.example.demo.model.Tabela;
import com.example.demo.model.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FindService {
    
    @Autowired
    private TabelaRepository repository;

    // Todos os métodos conseguem recuperar o elemento sem problema.

    public Tabela findDefault () {
        System.out.print("\n\n - -  - - \n\n");
        return find("findDefault");
    }

    @Transactional
    public Tabela findTransactional () {
        System.out.print("\n\n - -  - - \n\n");
        return find("findTransactional");
    }

    @Transactional(readOnly = true)
    public Tabela findReadOnly () {
        return find("findReadOnly");
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Tabela findPropagationNotSupported () {
        return find("findPropagationNotSupported");
    }

    @Transactional(propagation = Propagation.NEVER)
    public Tabela findPropagationNever () {
        return find("findPropagationNever");
    }

    private Tabela find (String methodName) {
        System.out.print("\n\n - - "+ methodName+" - -\n\n");
        Tabela tabela = repository.findById(1).get();
        System.out.println(tabela);
        return tabela;
    }
}
