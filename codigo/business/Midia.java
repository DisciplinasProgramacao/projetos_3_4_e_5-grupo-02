package business;

import java.util.Date;

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
    private int somaNotas;
    private int totalNotas;

    // CONSTRUTORES
    public Midia(String nome, String genero, String idioma, Date lancamento){
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.lancamento = lancamento;
        this.audiencia = 0;
        this.somaNotas = 0;
        this.totalNotas = 0;
    }

    // GETTERS E SETTERS
    public void setNome(String nome){
        if (nome.length() > 0){
            this.nome = nome;
        }
    }
    
    private void addNota(int nota) {
    	if (nota >= 1 && nota <= 5) {
    		this.somaNotas += nota;
    		this.totalNotas++;
    	}
    }
    
    public double getMediaNota() {
    	return this.somaNotas / this.totalNotas;
    }

    public String getNome(){
        return this.nome;
    }

    public String getGenero() {
        return genero;
    }

     public Date getLancamento() { 
    	return this.lancamento;
    }

    // MÉTODOS
    public void registrarAudiencia(){
        this.audiencia++;
    }
    
    public void avaliar(int nota) {
    	addNota(nota);
    }

    @Override
    public String toString(){
        return ("Nome: " + this.nome +
                "\nGênero: " + this.genero +
                "\nIdioma: " + this.idioma);
    } 
}
