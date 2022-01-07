package com.example.demo.model.relacionamentoone.dto;

import com.example.demo.model.relacionamentoone.RelacionamentoOne;

public class OneDto {
    
    public Integer id;
    public String titulo;
    public Integer tabelaId;

    public static OneDto toDto (RelacionamentoOne one) {
        if (one == null) {
            return null;
        }
        OneDto dto = new OneDto();
        dto.id = one.getId();
        dto.titulo = one.getTitulo();
        if (one.getTabela() != null) {
            dto.tabelaId = one.getTabela().getId();
        }
        return dto;
    }

}
