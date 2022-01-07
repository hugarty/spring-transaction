package com.example.demo.model.relacionamentoone.service;

import com.example.demo.model.relacionamentoone.RelacionamentoOne;
import com.example.demo.model.relacionamentoone.repository.OneRepository;
import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OneService {
    
    @Autowired
    private OneRepository oneRepository;
    @Autowired
    private TabelaRepository tabelaRepository;

    public void saveNewRelacionamento() {
        System.out.print("\n\n - - - saveNewRelacionamento - - - \n\n");
        Tabela tabelaFirst = tabelaRepository.findTopByOrderByIdAsc();
        RelacionamentoOne one = new RelacionamentoOne("OneSaveNewRelacionamento", tabelaFirst);
        oneRepository.save(one);
    }

    public void deleteLast(){
        RelacionamentoOne one = oneRepository.findTopByOrderByIdDesc();
        oneRepository.delete(one);
    }


}
