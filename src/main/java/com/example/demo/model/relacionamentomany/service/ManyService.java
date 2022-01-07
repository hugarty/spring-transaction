package com.example.demo.model.relacionamentomany.service;

import com.example.demo.model.relacionamentomany.RelacionamentoMany;
import com.example.demo.model.relacionamentomany.repository.ManyRepository;
import com.example.demo.model.tabela.Tabela;
import com.example.demo.model.tabela.repository.TabelaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManyService {
    
    @Autowired
    private ManyRepository oneRepository;
    @Autowired
    private TabelaRepository tabelaRepository;

    public void saveNewRelacionamento() {
        System.out.print("\n\n - - - saveNewRelacionamento - - - \n\n");
        Tabela tabelaFirst = tabelaRepository.findTopByOrderByIdAsc();
        RelacionamentoMany one = new RelacionamentoMany("ManySaveNewRelacionamento", tabelaFirst);
        oneRepository.save(one);
    }

    public void deleteLast(){
        RelacionamentoMany one = oneRepository.findTopByOrderByIdDesc();
        oneRepository.delete(one);
    }


}
