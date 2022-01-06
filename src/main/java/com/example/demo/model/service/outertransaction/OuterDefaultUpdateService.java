package com.example.demo.model.service.outertransaction;

import com.example.demo.model.Tabela;
import com.example.demo.model.service.FindService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/** 
 * Este service não declara transações mas usa um outro service que declara transações.
 * 
 * Não é o caso aqui quando temos uma transanção chamando outra transação podemos ter comportamentos diferentes.
 */
@Service
public class OuterDefaultUpdateService {
    
    private static final String OUTER = "OUTER_DEFAULT";

    @Autowired
    private FindService findService;
    
    /**
     * O valor no banco não é alterado
     */
    public void updateReadOnly () {
        Tabela tabela = findService.findReadOnly(); 
        tabela.setNome(getNewName("updateReadOnly"));
        System.out.println(tabela);
    }

    /**
     * O valor no banco não é alterado
     */
    public void updatePropagationNotSupported () { 
        Tabela tabela = findService.findPropagationNotSupported();
        tabela.setNome(getNewName("updatePropagationNotSupported"));
        System.out.println(tabela);
    }

    /**
     * O valor no banco não é alterado
     */
    public void updatePropagationNever () { 
        Tabela tabela = findService.findPropagationNever();
        tabela.setNome(getNewName("updatePropagationNever"));
        System.out.println(tabela);
    }

    private String getNewName(String methodName) {
        return OUTER + methodName;
    }
}
