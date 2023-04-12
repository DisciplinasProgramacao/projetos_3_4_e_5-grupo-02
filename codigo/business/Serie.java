package business;

import java.util.Arrays;

public class Serie{

    // ATRIBUTOS
    private static final String[] GENEROS = new String[]{"Comédia","Ação","Terror"}; 
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    // CONSTRUTORES
    public Serie(String nome, String genero, String idioma, int quantidadeEpisodios){
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios;
        this.audiencia = 0;
    }

    // GETTERS E SETTERS

    public String getNome(){
        return this.nome;
    }

    public String getGenero() {
        return genero;
    }

    public int getQuantidadeEpisodios() {
        return quantidadeEpisodios;
    }

    // MÉTODOS
    public void registrarAudiencia(){
        this.audiencia++;
    }

}