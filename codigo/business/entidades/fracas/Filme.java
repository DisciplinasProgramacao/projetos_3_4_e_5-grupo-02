package business.entidades.fracas;

import java.util.Date;

import business.entidades.Midia;

public class Filme extends Midia {

    // ATRIBUTOS
    private int duracao;

     // CONSTRUTORES
    public Filme(String id, String nome, String genero, String idioma, Date lancamento, int duracao){
        super(id, nome, genero, idioma, lancamento);
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
        return (super.toString() + "\nDuração: " + this.duracao + " segundos");
    }
}
