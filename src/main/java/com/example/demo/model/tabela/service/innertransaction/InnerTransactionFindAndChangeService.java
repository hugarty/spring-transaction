package com.example.demo.model.tabela.service.innertransaction;

import org.springframework.stereotype.Service;

import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/** 
 * Essa classe usa os próprios métodos, que também declaram transações, para acessar o banco
 * mas por conta do OAP de proxy do Spring vemos que a primeira transação chamada é a que vale para a transação.
 * 
 * Nesta situação a quantidade de métodos anotados com Transaction não tem efeito nenhum.
 * https://docs.spring.io/spring-framework/docs/current/reference/html/data-access.html#:~:text=In%20proxy%20mode,a%20%40PostConstruct%20method.
 */
@Service
public class InnerTransactionFindAndChangeService {
    
    private static final String INNER = "INNER_TRANSACTIONAL";

    @Autowired
    private TabelaRepository repository;

    /**
     * A alteração é feita no banco. Apenas o primeiro transactional que conta.
     */
    @Transactional
    public void updateFindReadOnly () {
        String methodName = "updateFindReadOnly";
        printMethodName(methodName);
        Tabela tabela = this.findReadOnly(); 
        tabela.setNome(getNewName(methodName));
        System.out.println(tabela);
    }

    /**
     * A alteração é feita no banco. Apenas o primeiro transactional que conta.
     */
    @Transactional
    public void updateFindPropagationNotSupported () { 
        String methodName = "updateFindPropagationNotSupported";
        printMethodName(methodName);
        Tabela tabela = this.findPropagationNotSupported ();
        tabela.setNome(getNewName(methodName));
        System.out.println(tabela);
    }

    /**
     * A alteração é feita no banco. Apenas o primeiro transactional que conta.
     */
    @Transactional
    public void updateFindPropagationNever () { 
        String methodName = "updateFindPropagationNever";
        printMethodName(methodName);
        Tabela tabela = this.findPropagationNever(); 
        tabela.setNome(getNewName(methodName));
        System.out.println(tabela);
    }


    @Transactional(readOnly = true)
    public Tabela findReadOnly () {
        return repository.findTopByOrderByIdAsc();
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Tabela findPropagationNotSupported () {
        return repository.findTopByOrderByIdAsc();
    }

    @Transactional(propagation = Propagation.NEVER)
    public Tabela findPropagationNever () {
        return repository.findTopByOrderByIdAsc();
    }

    private String getNewName(String methodName) {
        return INNER + methodName;
    }

    private void printMethodName(String methodName) {
        System.out.println("\n - - - "+ methodName +" - - -  \n");
    }

}
