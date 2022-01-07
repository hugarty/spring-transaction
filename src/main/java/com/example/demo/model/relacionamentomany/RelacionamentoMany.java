package com.example.demo.model.relacionamentomany;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.example.demo.model.tabela.Tabela;

import static com.example.demo.model.tabela.Tabela.random;

@Entity
public class RelacionamentoMany {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
    private Integer id;

    @Column
    private String paragrafo;

    @ManyToOne
    @JoinColumn(name="tabela_id")
    private Tabela tabela;


    public RelacionamentoMany () {
    }

    public RelacionamentoMany (String paragrafo, Tabela tabela) {
        this.paragrafo = paragrafo + random.nextInt(100);
        this.tabela = tabela;
    }

    public Integer getId() {
        return id;
    }
    public String getParagrafo() {
        return paragrafo;
    }

    public Tabela getTabela() {
        return tabela;
    }
    public void setTabela(Tabela tabela) {
        this.tabela = tabela;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof RelacionamentoMany)){
            return false;
        }
        return this.id == ((RelacionamentoMany) obj).id;
    }

    @Override
    public int hashCode() {
        int result = (id ^ (id >>> 32));
        result = 31 * result + paragrafo.hashCode();
        return result;
    }
}
