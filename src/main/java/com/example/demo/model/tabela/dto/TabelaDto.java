package com.example.demo.model.tabela.dto;

import java.time.LocalDateTime;
import java.util.Set;

import com.example.demo.model.relacionamentomany.dto.ManyDto;
import com.example.demo.model.relacionamentoone.dto.OneDto;
import com.example.demo.model.tabela.Tabela;

public class TabelaDto {
    
    public Integer id;
    public String nome;
    public LocalDateTime data;
    public OneDto one;
    public Set<ManyDto> many;

    public static TabelaDto toDto(Tabela tabela) {
        TabelaDto dto = new TabelaDto();
        dto.id = tabela.getId();
        dto.nome = tabela.getNome();
        dto.data = tabela.getData();
        dto.one = OneDto.toDto(tabela.getOne());
        dto.many = (Set<ManyDto>) ManyDto.toDto(tabela.getMany());
        return dto;
    }

}
