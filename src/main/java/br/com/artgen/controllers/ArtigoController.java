package br.com.artgen.controllers;

import br.com.artgen.models.ArtigoModel;
import br.com.artgen.services.ArtigoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value = "/artigo")
@CrossOrigin(origins = "http://localhost:4200")
@RequiredArgsConstructor
public class ArtigoController {

    @Autowired
    private final ArtigoService service;

    @PostMapping
    public ResponseEntity<?> criarArtigo(@RequestBody ArtigoModel artigo) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(artigo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao salvar artigo: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        ArtigoModel artigo = service.buscarPorId(id);

        if (artigo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Artigo não encontrado: " + id);
        }

        return ResponseEntity.ok(artigo);
    }
}
