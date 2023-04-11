package business;

import java.util.Arrays;

public class Serie{

    private static final String[] GENEROS = new String[]{"Comédia","Ação","Terror"}; 
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    public Serie(String nome, String genero, String idioma, int quantidadeEpisodios){

        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.quantidadeEpisodios = 0;
        this.audiencia = 0;


    }

    public void registrarAudiencia(){
        
    }

    public String getNome(){
        return this.nome;
    }

    public void setQuantidadeEpisodios(){

    }

}