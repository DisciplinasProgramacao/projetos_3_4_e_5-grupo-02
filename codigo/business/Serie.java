package business;

import java.util.Date;

public class Serie extends Midia {

    // ATRIBUTOS
    private int quantidadeEpisodios;

    // CONSTRUTORES
    public Serie(String id, String nome, String genero, String idioma, Date lancamento, int quantidadeEpisodios){
        super(id, nome, genero, idioma, lancamento);
        this.quantidadeEpisodios = quantidadeEpisodios;
    }

    // GETTERS E SETTERS
    public int getQuantidadeEpisodios() {
        return this.quantidadeEpisodios;
    }

    public void setQuantidadeEpisodios(int quantidadeEpisodios){
        if(quantidadeEpisodios > 0){
            this.quantidadeEpisodios = quantidadeEpisodios;
        }
    }

    // MÉTODOS
    @Override
    public String toString(){
        return (super.toString() + "\nQtd de eps.: " + this.quantidadeEpisodios);
    }

}

