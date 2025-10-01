package br.com.artgen.controllers;

import br.com.artgen.models.ArtigoModel;
import br.com.artgen.services.ArtigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


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
}
