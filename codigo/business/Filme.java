package business;

import java.util.Date;

public class Filme extends Midia {
    // ATRIBUTOS
    private int duracao;

     // CONSTRUTORES
     public Filme(String nome, String genero, String idioma, Date lancamento, int duracao){
        super(nome, genero, idioma, lancamento);
        this.duracao = duracao;
    }

    // GETTERS E SETTERS
    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    // MÉTODOS
    @Override
    public String toString(){
        return (super.toString() + "\nDuração:" + this.duracao);
    }   
}
