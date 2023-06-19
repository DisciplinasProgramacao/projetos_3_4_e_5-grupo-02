package business.entidades;

import business.entidades.fracas.ClienteEspecialista;
import business.entidades.fracas.Serie;
import business.exceptions.MidiaNaoEncontradaException;
import business.interfaces.*;

import utils.Lista;

public class Cliente {
    // ATRIBUTOS
    private String nomeDeUsuario;
    private String id;
    private String senha;
    private Lista<Midia> listaParaVer;
    private Lista<Midia> listaJaVistas;
    public ICliente modoAvaliacao;

    // CONSTRUTORES
    public Cliente(String nomeDeUsuario, String id, String senha) {
        this.nomeDeUsuario = nomeDeUsuario;
        this.id = id;
        this.senha = senha;
        this.listaParaVer = new Lista<Midia>();
        this.listaJaVistas = new Lista<Midia>();
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

    public Lista<Midia> getListaParaVer() {
        return listaParaVer;
    }

    public Lista<Midia> getListaJaVistas() {
        return listaJaVistas;
    }

    public ICliente getModoAvaliacao() {
        return modoAvaliacao;
    }

    // MÉTODOS

    /**
     * Adiciona uma série à lista de séries para ver. Caso a serie a ser adicionada
     * já exista na lista, a operação não é
     * realizada
     *
     * @param nomeSerie Nome da série a ser adicionada
     */
    public void adicionarNaListaParaVer(Midia nomeSerie) {
        Midia[] listaJaVistasArray = new Midia[listaParaVer.size()];
        listaJaVistasArray = this.listaParaVer.allElements(listaJaVistasArray);

        for (Midia serieDaLista : listaJaVistasArray) {
            if (serieDaLista.equals(nomeSerie)) {
                return;
            }
        }

        this.listaParaVer.add(nomeSerie);
    }

    public void adicionarNaListaJaVistos(Midia nomeSerie) {
        Midia[] listaJaVistasArray = new Midia[listaJaVistas.size()];
        listaJaVistasArray = this.listaJaVistas.allElements(listaJaVistasArray);

        for (Midia serieDaLista : listaJaVistasArray) {
            if (serieDaLista.equals(nomeSerie)) {
                return;
            }
        }

        this.listaJaVistas.add(nomeSerie);
    }

    /**
     * Retira uma série da lista de séries para ver, contanto que a série
     * selecionada esteja presente na lista
     *
     * @param nomeMidia Nome da série a ser removida
     */

    // TODO: PRECISA DE CORREÇÃO
    public void retirarDaListaParaVer(Midia midia) {
        Midia[] midias = new Midia[listaParaVer.size()];
        midias = listaParaVer.allElements(midias);

        for (int i = 0; i < midias.length; i++) {
            if (midias[i].getNome().equals(midia.getNome()))
                listaParaVer.remove(i);
        }
    }

    /**
     * Filtra, dentre as listas de séries para ver e de séries já vistas, aquelas
     * que correspondem ao gênero
     * selecionado
     *
     * @param genero gênero selecionado
     * @return lista filtrada por gênero das séries encontradas
     */
    public Lista<Midia> filtrarPorGenero(String genero) {
        Lista<Midia> midiasFiltradas = new Lista<Midia>();

        Midia[] midias = new Midia[listaParaVer.size()];
        midias = listaParaVer.allElements(midias);

        for (Midia midia : midias) {
            if (midia.getGenero().equals(genero)) {
                midiasFiltradas.add(midia);
            }
        }

        return midiasFiltradas;
    }

    /**
     * Filtra, dentre as listas de séries para ver e de séries já vistas, aquelas
     * que correspondem ao idioma
     * selecionado
     *
     * @param idioma idioma selecionado.
     * @return lista filtrada por idioma das séries encontradas
     */
    public Lista<Midia> filtrarPorIdioma(String idioma) {
        Lista<Midia> midiasFiltradas = new Lista<Midia>();

        Midia[] midias = new Midia[listaParaVer.size()];
        midias = listaParaVer.allElements(midias);

        for (Midia midia : midias) {
            if (midia.getIdioma().equals(idioma)) {
                midiasFiltradas.add(midia);
            }
        }

        return midiasFiltradas;
    }

    /**
     * Filtra, dentre as listas de séries para ver e de séries já vistas, aquelas
     * que possuem quantidade de episódios
     * igual à selecionada
     *
     * @param quantEpisodios quantidade de episódios selecionada
     * @return lista filtrada por qtd. de episódios das séries encontradas
     */
    public Lista<Midia> filtrarPorQtdEpisodios(int quantEpisodios) {
        Lista<Midia> filtrada = new Lista<Midia>();

        Midia[] midias = new Midia[listaParaVer.size()];
        midias = listaParaVer.allElements(midias);

        for (Midia serie : midias) {
            if (serie instanceof Serie) {
                if (((Serie) serie).getQuantidadeEpisodios() == quantEpisodios) {
                    filtrada.add((Serie) serie);
                }
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

    public int tamanhoListaJaVistos() {
        return this.listaJaVistas.size();
    }

    /**
     * Imprime na tela as séries existentes na lista de séries para ver
     */
    public void imprimirListaParaVer() {
        Midia[] listaImprimir = new Midia[listaParaVer.size()];
        listaImprimir = listaParaVer.allElements(listaImprimir);

        for (Midia midia : listaImprimir) {
            System.out.println(midia);
        }
    }

    public void imprimirListaJaVisto() {
        Midia[] listaAssistidos = new Midia[listaJaVistas.size()];
        listaAssistidos = listaJaVistas.allElements(listaAssistidos);

        for (Midia midia : listaAssistidos) {
            System.out.printf("%s\n", midia.getNome());
        }
    }

    /**
     * Contabiliza audiência de uma série. Caso a série selecionada já esteja
     * presente na lista de séries para ver, a
     * operação não é realizada.
     *
     * @param midia série selecionada
     */
    public void registrarAudiencia(Midia midia) throws MidiaNaoEncontradaException {
        this.modoAvaliacao = categorizarCliente();

        Midia[] buscaJaVistas = new Midia[listaJaVistas.size()];
        buscaJaVistas = listaJaVistas.allElements(buscaJaVistas);

        if (midia == null) {
            throw new MidiaNaoEncontradaException();
        }

        // Se está na lista de "jaVistas" é porque o cliente ja viu e não pode
        // contabilizar mais
        // uma audiencia
        if (listaJaVistas.contains(midia)) {
            return;
        }

        // for (Serie buscada : buscaJaVistas) {
        // if (buscada.equals(serie))
        // return;
        // }

        listaJaVistas.add(midia);

        midia.registrarAudiencia();
    }

    /**
     * Categoriza o cliente com base no número de itens já vistos.
     *
     * @return Uma instância de ICliente representando a categoria do cliente, ou
     *         null se o cliente tiver atingido
     *         critério para receber nova categoria.
     */
    private ICliente categorizarCliente() {
        if (listaJaVistas.size() >= 5)
            return new ClienteEspecialista();
        else
            return null;
    }

    /**
     * Chama o método criarAvaliacao(Cliente, int, String) de Midia, passando-o como
     * parâmetro o cliente atual que está
     * avaliando a mídia em questão, bem como a nota a ser inserida na avaliação.
     * Como parâmetro de comentário, passa o
     * valor null, uma vez que clientes regulares não podem avaliar com comentários.
     *
     * @param midia Mídia a ser avaliada
     * @param nota  Nota a ser atribuída à mídia avaliada
     * @throws IllegalStateException Caso este cliente já tenha avaliado a mesma
     *                               mídia previamente.
     */
    public void avaliarMidia(Midia midia, int nota) throws IllegalStateException {
        midia.criarAvaliacao(this, nota, null);
    }

    /**
     * Verifica se o cliente que está avaliando possui modoAvaliador especialista.
     * Se positivo, prossegue em chamar o
     * método criarAvaliacao(Cliente, int, String) de Midia, passando-o como
     * parâmetro o cliente atual que está
     * avaliando a mídia, bem como a nota a ser inserida na avaliação.
     *
     * @param midia      Mídia a ser avaliada
     * @param nota       Nota a ser atribuída à mídia avaliada
     * @param comentario Comentário a ser atribuído na mídia avaliada
     * @throws IllegalStateException Caso o cliente que está tentando avaliar não
     *                               seja ClienteEspecialista
     */
    public void avaliarMidia(Midia midia, int nota, String comentario) throws IllegalStateException {
        if (modoAvaliacao instanceof ClienteEspecialista)
            modoAvaliacao.avaliarMidia(midia, this, nota, comentario);
        else
            throw new IllegalStateException("O cliente não possui modo avaliador de especialista.");
    }

    /**
     * Sobrepoe o método toString() da classe Java Object a fim de modificar o
     * resultado da impressão tela ao se passar
     * um objeto da classe Cliente como parâmetro do método print().
     *
     * @return string contendo nome de usuário, senha e modo de avaliação do objeto
     *         Cliente
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
     * Sobrepõe o método equals() da classe Java Object a fim de modificar o
     * resultado da comparação entre dois objetos.
     * Para isso, realiza downcast para Cliente, possibilitando comparar os ids de
     * Cliente de ambos objetos. Caso os ids
     * sejam iguais, o método assegura que os dois objetos se tratam de um mesmo
     * cliente.
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