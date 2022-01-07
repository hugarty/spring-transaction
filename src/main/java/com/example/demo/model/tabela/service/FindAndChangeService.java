package com.example.demo.model.tabela.service;

import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;

@Service
public class FindAndChangeService {
    
    @Autowired
    private TabelaRepository repository;

    /**
     * Vai recuperar a tabela do banco, alterar o nome do objeto e NÃO vai refletir no banco. Não faz query update
     */
    public void changeNameDefault () {
        findAndChangeTableName("changeNameDefault");
    }

    /**
     * Vai recuperar a tabela no banco, alterar o nome do objeto e refletir a alteração no banco. Faz query de update.
     * Se o nome alterado for igual ao antigo não persiste.
     */
    @Transactional
    public void changeNameTransacional () {
        findAndChangeTableName("changeNameTransacional");
    }

    /**
     * Vai recuperar a tabela do banco, alterar o nome do objeto e NÃO vai refletir no banco. Não faz query update
     */
    @Transactional(readOnly = true)
    public void changeNameReadOnly () {
        findAndChangeTableName("changeNameReadOnly");
    }

    /**
     * Vai recuperar a tabela do banco, alterar o nome do objeto e NÃO vai refletir no banco. Não faz query update
     */
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public void changeNamePropagationNotSupported () {
        findAndChangeTableName("changeNamePropagationNotSupported");
    }

    /**
     * Vai recuperar a tabela do banco, alterar o nome do objeto e NÃO vai refletir no banco. Não faz query update
     */
    @Transactional(propagation = Propagation.NEVER)
    public void changeNamePropagationNever () {
        findAndChangeTableName("changeNamePropagationNever");
    }

    private void findAndChangeTableName(String name) {
		System.out.println("\n - - - "+name+" - - -  \n");
        Tabela tabela = repository.findTopByOrderByIdAsc();
        System.out.println(tabela);
        tabela.setNome(name);
        System.out.println(tabela);
    }

}
