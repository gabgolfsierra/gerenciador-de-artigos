package br.com.artgen.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArtigoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Dados gerais
    private String titulo;
    private String anoPublicacao;
    private String idioma;
    private String meioDivulgacao;
    private String homepageUrl;

    private String doi;

    private PessoaModel autor;
    private PessoaModel coautor;
    private List<PessoaModel> outrosAutores;

    private String tituloPeriodico;
    private String issn;
    private String volume;
    private String numero;
    private String serie;
    private String paginaInicial;
    private String paginaFinal;
    private String artigoEletronico;

    private List<String> palavrasChave;
    private List<AreaCNPqModel> areasCNPq;
    private List<String> setoresAtividade;

    private String outrasInformacoes;

    private String tituloIngles;
    private String outrasInformacoesIngles;

    // Instituição e orientador
    private String instituicao;
    private PessoaModel orientador;
}


