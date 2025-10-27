package br.com.artgen.controllers;

import br.com.artgen.models.PessoaModel;
import br.com.artgen.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<PessoaModel> criarPessoa(@RequestBody PessoaModel pessoa) {
        try {
            PessoaModel novaPessoa = pessoaService.salvarPessoa(pessoa);
            return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<PessoaModel>> listarPessoas() {
        try {
            List<PessoaModel> pessoas = pessoaService.listarPessoas();
            return new ResponseEntity<>(pessoas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> buscarPessoa(@PathVariable Long id) {
        Optional<PessoaModel> pessoa = pessoaService.buscarPessoaPorId(id);
        return pessoa.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> atualizarPessoa(@PathVariable Long id, @RequestBody PessoaModel pessoa) {
        Optional<PessoaModel> atualizada = pessoaService.atualizarPessoa(id, pessoa);
        return atualizada.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        boolean removida = pessoaService.deletarPessoa(id);
        return removida ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
