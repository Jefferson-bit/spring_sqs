package com.crash.sqs.controller;

import com.crash.sqs.domain.Pessoa;
import com.crash.sqs.repository.PessoaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaController {

    private final PessoaRepository pessoaRepository;

    public PessoaController(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }


    @GetMapping
    public ResponseEntity<Pessoa> buscaPessoaPorId() {
        Pessoa pessoa = new Pessoa("Rodrigo", "Silva", 15, LocalDate.of(2000, Month.JANUARY, 12));

        return ResponseEntity.ok().body(pessoa);
    }
}
