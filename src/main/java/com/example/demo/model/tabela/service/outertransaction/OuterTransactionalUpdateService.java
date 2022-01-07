package com.example.demo.model.tabela.service.outertransaction;

import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.service.FindService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * Essa classe usa um outro service que também declara transações.
 * 
 * Quando temos uma transanção chamando outra transação podemos ter comportamentos diferentes.
 */
@Service
public class OuterTransactionalUpdateService {
    
    private static final String OUTER = "OUTER_TRANSACTIONAL";

    @Autowired
    private FindService findService;
    
    /**
     * Funciona. Altera o nome no banco.
     */
    @Transactional
    public void updateReadOnly () {
        Tabela tabela = findService.findReadOnly(); 
        tabela.setNome(getNewName("updateReadOnly"));
        System.out.println(tabela);
    }

    /**
     * Não alterou mas também não lançou exceção!
     */
    @Transactional
    public void updatePropagationNotSupported () { 
        Tabela tabela = findService.findPropagationNotSupported();
        tabela.setNome(getNewName("updatePropagationNotSupported"));
        System.out.println(tabela);
    }

    /**
     * Lança exceção
     * Caused by: org.springframework.transaction.IllegalTransactionStateException: 
     * Existing transaction found for transaction marked with propagation 'never'
     */
    @Transactional
    public void updatePropagationNever () { 
        Tabela tabela = findService.findPropagationNever(); // É pra gerar exceção! ! !
        tabela.setNome(getNewName("updatePropagationNever"));
        System.out.println(tabela);
    }

    private String getNewName(String methodName) {
        return OUTER + methodName;
    }
}
