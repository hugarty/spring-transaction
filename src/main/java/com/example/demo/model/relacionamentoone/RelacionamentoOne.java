package com.example.demo.model.relacionamentoone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.example.demo.model.tabela.Tabela;

import static com.example.demo.model.tabela.Tabela.random;

@Entity
public class RelacionamentoOne {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer id;

    @Column
    private String titulo;

    @OneToOne
    @JoinColumn(name="tabela_id")
    private Tabela tabela;


    public RelacionamentoOne () {
    }

    public RelacionamentoOne (String titulo, Tabela tabela) {
        this.titulo = titulo + random.nextInt(100);
        this.tabela = tabela;
    }

    public Integer getId() {
        return id;
    }
    public String getTitulo() {
        return titulo;
    }

    public Tabela getTabela() {
        return tabela;
    }
    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }
}
