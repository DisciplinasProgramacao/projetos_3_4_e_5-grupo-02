package business;
public class Serie{

    private static final String[] GENEROS = new String[]{"Comédia","Ação","Terror"}; 
    private String nome;
    private String genero;
    private String idioma;
    private int quantidadeEpisodios;
    private int audiencia;

    public Serie(String nome, String genero, String idioma, int quantidadeEpisodios, int audiencia){

        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.quantidadeEpisodios = quantidadeEpisodios;
        this.audiencia = audiencia;
        
    }

    public void registrarAudiencia(){
        this.audiencia++;
    }

    public String getNome(){
        return this.nome;
    }


}