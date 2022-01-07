package com.example.demo.model.tabela;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.demo.model.relacionamentomany.RelacionamentoMany;
import com.example.demo.model.relacionamentoone.RelacionamentoOne;

@Entity
public class Tabela {
    public static final Random random = new Random();

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private LocalDateTime data;

    @OneToOne(mappedBy="tabela", fetch=FetchType.LAZY)
    private RelacionamentoOne one;

    @OneToMany(mappedBy="tabela", fetch=FetchType.LAZY)
    private Set<RelacionamentoMany> many = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome + random.nextInt(100);
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public RelacionamentoOne getOne() {
        return one;
    }

    public void setOne(RelacionamentoOne one) {
        this.one = one;
    }

    public Set<RelacionamentoMany> getMany() {
        return many;
    }
    public void setMany(Set<RelacionamentoMany> many) {
        this.many = many;
    }
    

    @Override
    public String toString() {
        return "Tabela [data=" + data + ", id=" + id + ", nome=" + nome + "]";
    }
}
