package business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.OptionalDouble;

public abstract class Midia {

    // ATRIBUTOS
    public static final String[] GENEROS = new String[]{"Comédia", "Ação", "Terror", "Drama", "Romance", "Aventura", "Animação", "Suspense"};
    public static final String[] IDIOMAS = new String[]{"Português", "Inglês", "Esperanto", "Romeno"};
    private String id;
    private String nome;
    private final String genero;
    private final String idioma;
    private final Date lancamento;
    private int audiencia;
    private final List<Avaliacao> avaliacoes;

    // CONSTRUTORES
    public Midia(String id, String nome, String genero, String idioma, Date lancamento) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.idioma = idioma;
        this.lancamento = lancamento;
        this.audiencia = 0;
        this.avaliacoes = new ArrayList<>();
    }

    // GETTERS E SETTERS
    public void setNome(String nome) {
        if (nome.length() > 0) {
            this.nome = nome;
        }
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
        return this.lancamento;
    }

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
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
     * chamou.  Em seguida, a adiciona à lista de avaliações da mídia.
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
        return avaliacoes.stream()
                .mapToInt(Avaliacao::getNota)
                .average()
                .orElse(0.0);
    }

    /**
     * Retorna um objeto StringBuilder contendo os comentários das avaliações.
     *
     * @return Objeto StringBuilder contendo os comentários das avaliações.
     */
    public StringBuilder comentarios() {
        StringBuilder sb = new StringBuilder();

        for (Avaliacao a : avaliacoes) {
            if (a.getTexto() != null) {
                sb.append(a.getCliente().getNomeUsuario());
                sb.append(" - ");
                sb.append("\"").append(a.getTexto()).append("\"").append("; ");
            }
        }

        return sb;
    }

    @Override
    public String toString() {
        return ("Nome: " + this.nome +
                "\nId: " + this.id +
                "\nGênero: " + this.genero +
                "\nIdioma: " + this.idioma +
                "\nAvaliação média: " + this.mediaAvaliacoes() +
                "\nComentários: " + this.comentarios()
        );
    }
}
