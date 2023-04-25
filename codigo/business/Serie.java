package business;

public class Serie extends Midia {

    // ATRIBUTOS
    private int quantidadeEpisodios;

    // CONSTRUTORES
    public Serie(String nome, String genero, String idioma, int quantidadeEpisodios){
        super(nome, genero, idioma);
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
        return (super.toString() + this.quantidadeEpisodios);
    }   

}

