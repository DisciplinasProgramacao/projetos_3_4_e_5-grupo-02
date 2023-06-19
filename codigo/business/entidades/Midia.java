package business.entidades;
import business.entidades.fracas.ClienteProfissional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public abstract class Midia {

    // ATRIBUTOS
    public static final String[] GENEROS = new String[]{"Comédia", "Ação", "Terror", "Drama", "Romance", "Aventura", "Animação", "Suspense"};
    public static final String[] IDIOMAS = new String[]{"Português", "Inglês", "Esperanto", "Romeno"};
    private final String id;
    private String nome;
    private String genero;
    private String idioma;
    private Date lancamento;
    private int audiencia;
    private final List<Avaliacao> avaliacoes;
    private boolean ehLancamento;

    // CONSTRUTORES
    public Midia(String id, String nome, String genero, String idioma, Date lancamento) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.lancamento = lancamento;
        this.audiencia = 0;
        this.avaliacoes = new ArrayList<>();
        this.ehLancamento = false;
    }

    // GETTERS E SETTERS
    private void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
        }
    }

    private void setEhLancamento(boolean ehLancamento) {
    	this.ehLancamento = ehLancamento;
    }

    public int getAudiencia() {
        return this.audiencia;
    }

    public String getIdioma() {
        return idioma;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return this.nome;
    }

    public String getGenero() {
        return genero;
    }

    public Date getLancamento() {
        return lancamento;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public int qtdAvaliacoes() {
        return this.avaliacoes.size();
    }

    // MÉTODOS

    /**
     * Aumenta um ponto de audiência da mídia
     */
    public void registrarAudiencia() {
        this.audiencia++;
    }

    /**
     * Verifica se, na lista de avaliações, já existe uma valiação feita pelo mesmo cliente anteriormente. Caso não
     * exista, instancia uma nova avaliação contendo cliente avaliador, nota e comentário recebidos pela função que a
     * chamou. Em seguida, a adiciona à lista de avaliações da mídia.
     *
     * @param cliente    Cliente que está avaliando
     * @param nota       Nota a ser atribuída à mídia avaliada
     * @param comentario Comentário a ser atribuído na mídia avaliada
     * @throws IllegalStateException Caso este cliente já tenha avaliado a mesma mídia anteriormente
     */
    public void criarAvaliacao(Cliente cliente, int nota, String comentario) throws IllegalStateException {
        Avaliacao avaliacao = new Avaliacao(cliente, nota, comentario);
        if (avaliacoes.contains(avaliacao))
            throw new IllegalStateException("Cliente não pode avaliar a mídia mais de uma vez.");

        avaliacoes.add(avaliacao);
    }

    /**
     * Calcula a média de notas recebidas como avaliação.
     *
     * @return Média de notas
     */
    public double mediaAvaliacoes() {
        return avaliacoes.stream().mapToInt(Avaliacao::getNota).average().orElse(0.0);
    }

    /**
     * Verifica se um cliente é profissional e permite que ele assista a mídia.
     *
     * @param cliente O cliente que deseja assistir a mídia
     * @return true se o cliente é profissional e pode assistir à mídia, false caso contrário
     */
    public boolean permitirAcesso(Cliente cliente) {
        return (cliente instanceof ClienteProfissional);
    }

    /**
     * Sobrepoe o método toString() da classe Java Object a fim de modificar o resultado da impressão tela ao se passar
     * um objeto da classe Midia como parâmetro do método print().
     *
     * @return string contendo nome, id, gênero, idioma, média da nota e avaliaçoes da mídia
     */
    @Override
    public String toString() {
        return ("Nome: " + this.nome +
                "\nId: " + this.id +
                "\nGênero: " + this.genero +
                "\nIdioma: " + this.idioma +
                "\nNota média: " + this.mediaAvaliacoes() +
                "\nAvaliações: " + this.avaliacoes);
    }
}
