package br.com.artgen.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter @AllArgsConstructor
public class AutorModel {


    private Long id;

    private String nome;
    private String sobrenome;

}
