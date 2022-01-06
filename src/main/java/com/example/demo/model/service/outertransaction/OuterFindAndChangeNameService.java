package com.example.demo.model.service.outertransaction;

import com.example.demo.model.Tabela;
import com.example.demo.model.service.FindService;
import com.example.demo.model.service.UpdateService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OuterFindAndChangeNameService {
    
    @Autowired
    private FindService findService;
    
    @Transactional
    public void updateReadOnly () {
        Tabela tabela = findService.findReadOnly(); 
        tabela.setNome("Jose UserService.readOnly");
        System.out.println(tabela);
    }

    @Transactional
    public void updatePropagationNotSupported () { 
        Tabela tabela = findService.findPropagationNotSupported();
        tabela.setNome("Jose UserService.updatePropagationNotSupported");
        System.out.println(tabela);
    }

    @Transactional
    // Lança exceção org.springframework.transaction.IllegalTransactionStateException: 
    // Existing transaction found for transaction marked with propagation 'never'
    public void updatePropagationNever () { 
        Tabela tabela = findService.findPropagationNever(); // É pra gerar exceção! ! !
        tabela.setNome("Jose UserService.updatePropagationNever");
        System.out.println(tabela);
    }


    private void findAndChangeTableName(String name) {
		System.out.println("\n - - - "+name+" - - -  \n");
        Tabela tabela = repository.findById(1).get();
        System.out.println(tabela);
        tabela.setNome(name + new Random().nextInt(50));
        System.out.println(tabela);
    }
}
