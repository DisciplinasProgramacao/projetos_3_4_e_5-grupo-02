package business;

public class Filme extends Midia {
    // ATRIBUTOS
    private int duracao;

     // CONSTRUTORES
     public Filme(String nome, String genero, String idioma, int quantidadeEpisodios){
        super(nome, genero, idioma);
        this.duracao = duracao;
    }

    // GETTERS E SETTERS
    public int getDuracao() {
        return this.duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
}
