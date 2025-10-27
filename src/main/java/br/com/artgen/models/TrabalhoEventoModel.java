package br.com.artgen.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrabalhoEventoModel {
    private Long id;
    private String titulo;
    private PessoaModel autor;
    private PessoaModel coautor;
    private PessoaModel orientador;
    private String anoPublicacao;
    private String idioma;
    private String doi;
    private String meioDivulgacao;
    private String url;

    private String nomeEvento;
    private String nomeEventoIngles;
    private String cidadeEvento;
    private String paisEvento;
    private String anoEvento;
    private String tituloAnais;
    private String issnIsbn;
    private String volume;
    private String paginaInicial;
    private String paginaFinal;


    private List<String> palavrasChave = new ArrayList<>();
    private List<AreaCNPqModel> areasCNPq = new ArrayList<>();
    private List<String> setoresAtividade = new ArrayList<>();
    private String observacoes;
}
