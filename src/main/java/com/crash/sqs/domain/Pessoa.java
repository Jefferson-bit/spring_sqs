package com.crash.sqs.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private  String nome ;
    @Column(nullable = false)
    private  String sobreNome ;
    @Column(nullable = false)
    private  Integer idade ;
    @Column(nullable = false)
    private  LocalDate dataDeNascimento;

    @Deprecated
    public Pessoa(){
    }

    public Pessoa(String nome, String sobreNome, Integer idade, LocalDate dataDeNascimento) {
        this.nome = nome;
        this.sobreNome = sobreNome;
        this.idade = idade;
        this.dataDeNascimento = dataDeNascimento;
    }

    public String getNome() {
        return nome;
    }

    public String getSobreNome() {
        return sobreNome;
    }

    public Integer getIdade() {
        return idade;
    }

    public LocalDate getDataDeNascimento() {
        return dataDeNascimento;
    }

    @Override
    public String toString() {
        return "Pesssoa{" +
                "nome='" + nome + '\'' +
                ", sobreNome='" + sobreNome + '\'' +
                ", idade=" + idade +
                ", dataDeNascimento=" + dataDeNascimento +
                '}';
    }
}
