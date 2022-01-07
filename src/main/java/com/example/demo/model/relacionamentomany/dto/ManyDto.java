package com.example.demo.model.relacionamentomany.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.model.relacionamentomany.RelacionamentoMany;

public class ManyDto {
    
    public Integer id;
    public String paragrafo;
    public Integer tabelaId;

    public static ManyDto toDto (RelacionamentoMany many) {
        if (many == null) {
            return null;
        }
        ManyDto dto = new ManyDto();
        dto.id = many.getId();
        dto.paragrafo = many.getParagrafo();
        if (many.getTabela() != null) {
            dto.tabelaId = many.getTabela().getId();
        }
        return dto;
    }

    public static Collection<ManyDto> toDto (Set<RelacionamentoMany> collection) {
        Collection<ManyDto> collectionDto = new HashSet<>();
        for(RelacionamentoMany many : collection) {
            ManyDto dto = toDto(many);
            collectionDto.add(dto);
        }
        return collectionDto;
    }

}
