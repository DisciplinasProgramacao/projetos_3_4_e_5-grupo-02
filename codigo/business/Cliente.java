package business;

import business.interfaces.*;

public class Cliente {
    // ATRIBUTOS
    private String nomeDeUsuario;
    private String id;
    private String senha;
    private Lista<Serie> listaParaVer;
    private Lista<Serie> listaJaVistas;
    public ICliente modoAvaliacao;

    // CONSTRUTORES
    public Cliente(String nomeDeUsuario, String id, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.id = id;
        this.senha = senha;
        this.listaParaVer = new Lista<Serie>();
        this.listaJaVistas = new Lista<Serie>();
        this.modoAvaliacao = null;
    }

    // GETTERS E SETTERS
    public String getNomeUsuario() {
        return this.nomeDeUsuario;
    }

    public String getId() {
        return id;
    }

    public String getSenha() {
        return this.senha;
    }

    // MÉTODOS

    /**
     * Adiciona uma série à lista de séries para ver. Caso a serie a ser adicionada já exista na lista, a operação não é
     * realizada
     *
     * @param nomeSerie Nome da série a ser adicionada
     */
    public void adicionarNaLista(Serie nomeSerie) {
        Serie[] listaJaVistasArray = new Serie[listaParaVer.size()];
        listaJaVistasArray = this.listaParaVer.allElements(listaJaVistasArray);

        for (Serie serieDaLista : listaJaVistasArray) {
            if (serieDaLista.equals(nomeSerie)) {
                return;
            }
        }

        this.listaParaVer.add(nomeSerie);
    }

    /**
     * Retira uma série da lista de séries para ver, contanto que a série selecionada esteja presente na lista
     *
     * @param nomeSerie Nome da série a ser removida
     */
    public void retirarDaLista(String nomeSerie) {
        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);

        for (int i = 0; i < series.length; i++) {
            if (series[i].getNome().equals(nomeSerie))
                listaParaVer.remove(i);
        }
    }

    /**
     * Filtra, dentre as listas de séries para ver e de séries já vistas, aquelas que correspondem ao gênero
     * selecionado
     *
     * @param genero gênero selecionado.
     * @return lista filtrada por gênero das séries encontradas
     */
    public Lista<Serie> filtrarPorGenero(String genero) {
        Lista<Serie> seriesFiltradas = new Lista<Serie>();

        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);

        for (Serie serie : series) {
            if (serie.getGenero().equals(genero)) {
                seriesFiltradas.add(serie);
            }
        }

        series = listaJaVistas.allElements(series);

        for (Serie serie : series) {
            if (serie.getGenero().equals(genero)) {
                seriesFiltradas.add(serie);
            }
        }

        return seriesFiltradas;
    }

    /**
     * Filtra, dentre as listas de séries para ver e de séries já vistas, aquelas que correspondem ao idioma
     * selecionado
     *
     * @param idioma idioma selecionado.
     * @return lista filtrada por idioma das séries encontradas
     */
    public Lista<Serie> filtrarPorIdioma(String idioma) {
        Lista<Serie> seriesFiltradas = new Lista<Serie>();

        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);

        for (Serie serie : series) {
            if (serie.getGenero().equals(idioma)) {
                seriesFiltradas.add(serie);
            }
        }

        series = listaJaVistas.allElements(series);

        for (Serie serie : series) {
            if (serie != null && serie.getGenero().equals(idioma)) {
                seriesFiltradas.add(serie);
            }
        }

        return seriesFiltradas;
    }

    /**
     * Filtra, dentre as listas de séries para ver e de séries já vistas, aquelas que possuem quantidade de episódios
     * igual à selecionada
     *
     * @param quantEpisodios quantidade de episódios selecionada
     * @return lista filtrada por qtd. de episódios das séries encontradas
     */
    public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
        Lista<Serie> filtrada = new Lista<Serie>();

        Serie[] series = new Serie[listaParaVer.size()];
        series = listaParaVer.allElements(series);

        for (Serie serie : series) {
            if (serie.getQuantidadeEpisodios() == quantEpisodios) {
                filtrada.add(serie);
            }
        }

        series = listaJaVistas.allElements(series);

        for (Serie serie : series) {
            if (serie.getQuantidadeEpisodios() == quantEpisodios) {
                filtrada.add(serie);
            }
        }
        return filtrada;
    }

    /**
     * Retorna o tamanho da lista de séries para ver
     *
     * @return o tamanho de listaParaVer
     */
    public int tamanhoListaParaVer() {
        return this.listaParaVer.size();
    }

    public int tamanhoListaJaVistos(){
        return this.listaJaVistas.size();
    }



    /**
     * Imprime na tela as séries existentes na lista de séries para ver
     */
    public void imprimirListaParaVer() {
        Serie[] listaImprimir = new Serie[listaParaVer.size()];
        listaImprimir = listaParaVer.allElements(listaImprimir);

        for (Serie serie : listaImprimir) {
            System.out.println(serie);
        }
    }

    public void imprimirListaJaVisto() {
        Serie[] listaAssistidos = new Serie[listaJaVistas.size()];
        listaAssistidos = listaJaVistas.allElements(listaAssistidos);

        for(Serie serie : listaAssistidos) {
            System.out.printf("%s\n", serie.getNome());
        }
    }

    /**
     * Contabiliza audiência de uma série. Caso a série selecionada já esteja presente na lista de séries para ver, a
     * operação não é realizada.
     *
     * @param serie série selecionada
     */
    public void registrarAudiencia(Serie serie) {
        this.modoAvaliacao = categorizarCliente();

        Serie[] buscaJaVistas = new Serie[listaJaVistas.size()];
        buscaJaVistas = listaJaVistas.allElements(buscaJaVistas);

        // Chamar a função de categorizar cliente
        // meuTipo = meuTipo.veificarCategoria(listajaVistas);
        // buscaJaVistas = listaJaVistas.allElements(buscaJaVistas);
        // if(this.viu5NoMes())
        // this.modoAvaliacao = new especialista

        for (Serie buscada : buscaJaVistas) {
            if (buscada.equals(serie))
                return;
        }

        listaJaVistas.add(serie);
        serie.registrarAudiencia();
    }

    /**
     * Categoriza o cliente com base no número de itens já vistos.
     *
     * @return Uma instância de ICliente representando a categoria do cliente, ou null se o cliente tiver atingido
     * critério para receber nova categoria.
     */
    private ICliente categorizarCliente() {
        if (listaJaVistas.size() >= 5)
            return new ClienteEspecialista();
        else
            return null;
    }
    /**
     * Chama o método criarAvaliacao(Cliente, int, String) de Midia, passando-o como parâmetro o cliente atual que está
     * avaliando a mídia em questão, bem como a nota a ser inserida na avaliação. Como parâmetro de comentário, passa o
     * valor null, uma vez que clientes regulares não podem avaliar com comentários.
     *
     * @param midia Mídia a ser avaliada
     * @param nota  Nota a ser atribuída à mídia avaliada
     * @throws IllegalStateException Caso este cliente já tenha avaliado a mesma mídia previamente.
     */
    public void avaliarMidia(Midia midia, int nota) throws IllegalStateException {
        midia.criarAvaliacao(this, nota, null);
    }

    /**
     * Verifica se o cliente que está avaliando possui modoAvaliador especialista. Se positivo, prossegue em chamar o
     * método criarAvaliacao(Cliente, int, String) de Midia, passando-o como parâmetro o cliente atual que está
     * avaliando a mídia, bem como a nota a ser inserida na avaliação.
     *
     * @param midia      Mídia a ser avaliada
     * @param nota       Nota a ser atribuída à mídia avaliada
     * @param comentario Comentário a ser atribuído na mídia avaliada
     * @throws IllegalStateException Caso o cliente que está tentando avaliar não seja ClienteEspecialista
     */
    public void avaliarMidia(Midia midia, int nota, String comentario) throws IllegalStateException {
        if (modoAvaliacao instanceof ClienteEspecialista)
            modoAvaliacao.avaliarMidia(midia, this, nota, comentario);
        else
            throw new IllegalStateException("O cliente não possui modo avaliador de especialista.");
    }

    /**
     * Sobrepoe o método toString() da classe Java Object a fim de modificar o resultado da impressão tela ao se passar
     * um objeto da classe Cliente como parâmetro do método print().
     *
     * @return string contendo nome de usuário, senha e modo de avaliação do objeto Cliente
     */
    @Override
    public String toString() {

        String printModoAvaliacao;

        if (modoAvaliacao instanceof ClienteEspecialista)
            printModoAvaliacao = "Cliente Especialista";
        else
            printModoAvaliacao = "Cliente Regular";

        return "Usuário: " + nomeDeUsuario
                + "\nSenha: " + senha
                + "\nModo de avaliação: " + printModoAvaliacao;
    }

    /**
     * Sobrepõe o método equals() da classe Java Object a fim de modificar o resultado da comparação entre dois objetos.
     * Para isso, realiza downcast para Cliente, possibilitando comparar os ids de Cliente de ambos objetos. Caso os ids
     * sejam iguais, o método assegura que os dois objetos se tratam de um mesmo cliente.
     *
     * @param o Objeto a ser comparado com this.
     * @return Se os ids de cliente são iguais.
     */
    @Override
    public boolean equals(Object o) {
        Cliente outro = (Cliente) o;
        return this.id.equals(outro.id);
    }
}