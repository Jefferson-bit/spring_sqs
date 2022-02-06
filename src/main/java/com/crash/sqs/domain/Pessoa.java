package com.crash.sqs.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

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
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @JsonDeserialize(using = LocalDateDeserializer.class)
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
