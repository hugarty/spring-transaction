package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Tabela {
    
    static final Random random = new Random();

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nome;

    @Column
    private LocalDateTime data;

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

    @Override
    public String toString() {
        return "Tabela [data=" + data + ", id=" + id + ", nome=" + nome + "]";
    }
}
