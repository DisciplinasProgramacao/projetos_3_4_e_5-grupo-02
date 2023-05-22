package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Midia {

    // ATRIBUTOS
    public static final String[] GENEROS = new String[]{"Comédia", "Ação", "Terror", "Drama", "Romance", "Aventura", "Animação", "Suspense"};
    public static final String[] IDIOMAS = new String[]{"Português", "Inglês", "Esperanto", "Romeno"};
    private int id;
    private String nome;
    private String genero;
    private String idioma;
    private Date lancamento;
    private int audiencia;
    private List<Avaliacao> avaliacoes;

    // CONSTRUTORES
    public Midia(String nome, String genero, String idioma, Date lancamento){
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.lancamento = lancamento;
        this.audiencia = 0;
        this.avaliacoes = new ArrayList<>();
    }

    // GETTERS E SETTERS
    public void setNome(String nome){
        if (nome.length() > 0){
            this.nome = nome;
        }
    }
    
    public String getNome() {
        return this.nome;
    }

    public String getGenero() {
        return genero;
    }

     public Date getLancamento() { 
    	return this.lancamento;
    }

    // MÉTODOS

    /**
     * Aumenta um ponto de audiência da mídia
     */
    public void registrarAudiencia(){
        this.audiencia++;
    }

    public void avaliar(Cliente cliente, int nota) throws Exception {
        Avaliacao avaliacao = new Avaliacao(cliente, nota);
        for(Avaliacao a: avaliacoes) {
            if(a.getCliente() == cliente) {
                throw new Exception("Um cliente não pode avaliar a midia 2 vezes");
            }
        }
        avaliacoes.add(avaliacao);
    }


    public void avaliar(Cliente cliente, int nota, String comentario) throws Exception {
        Avaliacao avaliacao = new Avaliacao(cliente, nota, comentario);
        for(Avaliacao a: avaliacoes) {
            if(a.getCliente() == cliente) {
                throw new Exception("Um cliente não pode avaliar a midia 2 vezes");
            }
        }
        avaliacoes.add(avaliacao);
    }

    public int mediaAvaliacoes() {
        int total = 0;
        for(Avaliacao a: avaliacoes) {
            total += a.getNota();
        }

        return total / avaliacoes.size();
    }
    
    @Override
    public String toString(){
        return ("Nome: " + this.nome +
                "\nGênero: " + this.genero +
                "\nIdioma: " + this.idioma);
    } 
}
