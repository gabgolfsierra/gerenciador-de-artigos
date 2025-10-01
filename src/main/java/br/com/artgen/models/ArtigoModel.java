package br.com.artgen.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter @AllArgsConstructor
public class ArtigoModel {

    private Long id;
    private String titulo;
    private String ano;
    private AutorModel autor;
}
