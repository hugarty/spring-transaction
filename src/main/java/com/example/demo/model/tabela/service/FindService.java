package com.example.demo.model.tabela.service;


import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *  Todos os métodos dessa classe conseguem recuperar o elemento sem problema.
 */
@Service
public class FindService {
    
    @Autowired
    private TabelaRepository repository;

    public Tabela findDefault () {
        return find("findDefault");
    }

    @Transactional
    public Tabela findTransactional () {
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
        System.out.print("\n\n - - - " + methodName + " - - -\n\n");
        Tabela tabela = repository.findTopByOrderByIdAsc();
        System.out.println(tabela);
        return tabela;
    }

    /**
     * Lança exceção
     */
    // public Tabela findQueryAnnotationLazy (Integer id) { 
    //     System.out.print("\n\n - - - findQueryAnnotationLazy - - -\n\n");
    //     Tabela recuperarLazy = repository.recuperarLazy(id);
    //     return recuperarLazy;
    // }

    public Tabela findQueryAnnotationEager () {
        System.out.print("\n\n - - - findQueryAnnotationEager - - -\n\n");
        Tabela recuperarEager = repository.recuperarEager();
        return recuperarEager;
    }
}
