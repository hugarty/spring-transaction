package com.example.demo.model.service.outertransaction;

import com.example.demo.model.service.UpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Service00Default {
    
    @Autowired
    private UpdateService tabelaService;

    public void noUpdateReadOnly () {
        // Tabela tabela = tabelaService.findReadOnly(); 
        // tabela.setNome("Alberto UserService.readOnly");
        // System.out.println(tabela);
    }

    public void noUpdatePropagationNotSupported () { 
        // Tabela tabela = tabelaService.findPropagationNotSupported();
        // tabela.setNome("Alberto UserService.updatePropagationNotSupported");
        // System.out.println(tabela);
    }

    // Lança exceção org.springframework.transaction.IllegalTransactionStateException: 
    // Existing transaction found for transaction marked with propagation 'never'
    public void noUpdatePropagationNever () { 
        // Tabela tabela = tabelaService.findPropagationNever(); // É pra gerar exceção! ! !
        // tabela.setNome("Alberto UserService.updatePropagationNever");
        // System.out.println(tabela);
    }
}
