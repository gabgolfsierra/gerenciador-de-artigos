package br.com.artgen.controllers;

import br.com.artgen.models.ArtigoModel;
import br.com.artgen.services.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping(value = "/artigo")
public class ArtigoController {

    @Autowired
    private ArtigoService artigoService;

    @PostMapping
    public ResponseEntity<ArtigoModel> criarArtigo(@RequestBody ArtigoModel artigo){
        try {
            ArtigoModel novoArtigo = artigoService.salvarArtigo(artigo);
            return new ResponseEntity<>(novoArtigo, HttpStatus.CREATED);
        } catch (Exception e) {
            // Tratar a exceção aqui
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<ArtigoModel>> listarArtigos() {
        List<ArtigoModel> artigos = artigoService.listarArtigos();
        return new ResponseEntity<>(artigos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArtigoModel> buscarArtigo(@PathVariable Long id) {
        Optional<ArtigoModel> artigo = artigoService.buscarArtigoPorId(id);
        return artigo.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArtigoModel> atualizarArtigo(@PathVariable Long id, @RequestBody ArtigoModel artigo) {
        Optional<ArtigoModel> atualizado = artigoService.atualizarArtigo(id, artigo);
        return atualizado.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarArtigo(@PathVariable Long id) {
        boolean removido = artigoService.deletarArtigo(id);
        return removido ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
