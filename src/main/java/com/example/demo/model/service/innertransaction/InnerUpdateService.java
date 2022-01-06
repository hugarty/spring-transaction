package com.example.demo.model.service.innertransaction;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

import com.example.demo.model.Tabela;
import com.example.demo.model.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InnerUpdateService {
    
    @Autowired
    private TabelaRepository repository;

    @Transactional
    public void updateaaa () {
        // Não tem efeito nenhum na transação por que o modo de configuração é PROXY, 
        // neste modo não é possível invocar um método da mesma classe e ele receber uma nova transação, 
        // mesmo que ele esteja anotado com @Transactional
        Tabela tabela = findReadOnly(); 
        tabela.setNome("Jose asdfaaqa");
        System.out.println(tabela);
    }


    @Transactional(readOnly = true)
    public Tabela findReadOnly () {
        return repository.findById(1).get();
    }
}
