package business;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import business.entidades.Avaliacao;
import business.entidades.Cliente;
import business.entidades.Midia;
import business.entidades.fracas.ClienteEspecialista;
import business.entidades.fracas.Filme;
import business.entidades.fracas.Serie;
import business.exceptions.ClienteJaExisteException;
import business.exceptions.LoginInvalidoException;
import business.exceptions.MidiaJaExisteException;
import business.exceptions.MidiaNaoEncontradaException;
import utils.Lista;

public class PlataformaStreaming {

    // ATRIBUTOS
    private final String nome;
    private Cliente clienteAtual;
    private final Map<Integer, Midia> midias;
    private final HashMap<String, Cliente> clientes;

    public enum MidiaType {
        FILMES, SERIES
    }

    // CONSTRUTORES
    public PlataformaStreaming(String nome) {
        this.nome = nome;
        this.midias = new HashMap<>();
        this.clientes = new HashMap<String, Cliente>();

        /* Carregar dados */
        try {
            this.carregarClientes();
            this.carregarMidias();
            this.carregarAudiencia();
            this.carregarAvaliacoes();
        } catch (FileNotFoundException | MidiaNaoEncontradaException e) {
            System.err.print("Erro ao carregar dados da plataforma! " + e.getMessage());
        }
    }

    // GETTERS E SETTERS
    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public Midia findMidiaById(int id) {
        return this.midias.get(id);
    }

    public Map<Integer, Midia> getMidiasMap() {
        return this.midias;
    }

    public List<Midia> getSeries() {
        return midias.values().stream().filter(midia -> midia instanceof Serie).collect(Collectors.toList());
    }

    public List<Midia> getFilmes() {
        return midias.values().stream().filter(midia -> midia instanceof Filme).collect(Collectors.toList());
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    // MÉTODOS

    /**
     * Realiza operação de login a partir do nome de usuário e senha informados. Se um usuário correspondente for
     * encontrado, o cliente atual será definido como o cliente conectado. Caso contrário, lança
     * LoginInvalidoException.
     *
     * @param nomeUsuario Nome de usuário do cliente que tenta logar.
     * @param senha       Senha do cliente que tenta logar.
     * @throws LoginInvalidoException Se o nome de usuário e a senha fornecidos não corresponderem a nenhum cliente na
     *                                lista.
     */
    public void login(String nomeUsuario, String senha) throws LoginInvalidoException {
        for (Cliente cliente : this.clientes.values()) {
            if (cliente.getId().equals(nomeUsuario) && cliente.getSenha().equals(senha)) {
                this.clienteAtual = cliente;
                System.out.println("Login realizado com sucesso!");
                return;
            }
        }

        throw new LoginInvalidoException(nomeUsuario, senha);
    }

    /**
     * Adiciona um novo cliente à lista de clientes. Caso o cliente a ser adicionado já esteja previamente presente na
     * lista, a operação não é executada
     *
     * @param userId       Id do novo cliente
     * @param userName     Nome do novo cliente
     * @param userPassword Senha do novo cliente
     */
    public void adicionarCliente(String userName, String userId, String userPassword) throws ClienteJaExisteException {
        if (clientes.containsKey(userId)) throw new ClienteJaExisteException(userId);
        else {
            Cliente novoCliente = new Cliente(userName, userId, userPassword);
            clientes.put(userId, novoCliente);
        }
    }

    /**
     * Adiciona uma nova mídia ao map mídias, usando o id fornecido como chave.
     * Lança uma exceção se a mídia já existe.
     *
     * @param id        o ID da mídia a ser adicionada
     * @param novaMidia a mídia a ser adicionada ao mapa
     * @throws MidiaJaExisteException se a mídia já existe no mapa
     * @throws NullPointerException  se a novaMidia for nula
     */
    public void adicionarMidia(Integer id, Midia novaMidia) throws MidiaJaExisteException {
        if (novaMidia == null) {
            throw new NullPointerException();
        }

        if (this.midias.containsKey(id)) {
            throw new MidiaJaExisteException(novaMidia.getId());
        }

        this.midias.put(id, novaMidia);
    }

    /**
     * Lê o conteúdo do HashMap clientes e os escreve em um arquvivo .csv, sobrepondo o arquivo já existente de nome
     * Espectadores.csv. O salvamento deve ser realizado após execução do programa a fim de registrar em arquivo todas
     * as mudanças realizadas nos dados em memória
     *
     * @throws MidiaNaoEncontradaException
     */
    public void registrarAudiencia(Midia midia) throws NullPointerException, MidiaNaoEncontradaException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        clienteAtual.registrarAudiencia(midia);
    }

    /**
     * Invoca o método "filtrarPorGenero" do cliente atual, obtendo, assim, as séries das listas que correspondem ao
     * gênero selecionado.
     *
     * @param genero Gênero selecionado
     * @return Lista filtrada por gênero das séries encontradas
     * @throws NullPointerException Se não houver cliente atual na plataforma
     */
    public Lista<Midia> filtrarPorGenero(String genero) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        return clienteAtual.filtrarPorGenero(genero);
    }

    /**
     * Invoca o método "filtrarPorIdioma" do cliente atual, obtendo, assim, as séries das listas que correspondem ao
     * idioma selecionado.
     *
     * @param idioma Idioma selecionado
     * @return Lista filtrada por idioma das séries encontradas
     * @throws NullPointerException Se não houver cliente atual na plataforma
     */
    public Lista<Midia> filtrarPorIdioma(String idioma) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        return clienteAtual.filtrarPorIdioma(idioma);
    }

    /**
     * Invoca o método "filtrarPorQtdEpisodios" do cliente atual, obtendo, assim, as séries das listas que possuem a
     * quantidade de episódios selecionada.
     *
     * @param quantEpisodios Quantidade de episódios selecionada
     * @return Lista filtrada por idioma das séries encontradas
     * @throws NullPointerException Se não houver cliente atual na plataforma
     */
    public Lista<Midia> filtrarPorQtdEpisodios(int quantEpisodios) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    }

    /**
     * Lê o arquivo "Espectadores.csv", ignorando a primeira linha do arquivo, instancia clientes a partir das
     * informações lidas e os adiciona à lista de clientes.
     *
     * @throws FileNotFoundException se o arquivo não for encontrado.
     */
    public void carregarClientes() throws FileNotFoundException {
        File file = new File("docs/database/Espectadores.csv");
        Scanner filereader = new Scanner(file);

        filereader.nextLine(); // Artifício para ignorar primeira linha do csv
        int linha = 0;

        while (filereader.hasNextLine()) {
            String[] split = filereader.nextLine().split(";");

            try {
                adicionarCliente(split[0], split[1], split[2]);
            } catch (NullPointerException e) {
                System.out.println(linha + ":" + split);
            } catch (ClienteJaExisteException e) {
                System.out.println(e.getMessage());
            }

            linha++;
        }

        filereader.close();

    }

    public void carregarMidias() throws FileNotFoundException {

        for (MidiaType mt : MidiaType.values()) {

            File file = new File("docs/database/" + mt.name().substring(0, 1).toUpperCase() + mt.name().substring(1).toLowerCase() + ".csv");
            Scanner sc = new Scanner(file);

            sc.nextLine();

            int linha = 0;

            while (sc.hasNextLine()) {
                String[] dados = sc.nextLine().split(";");

                // Gera um número aleatório, limitado pelo tamanho do vetor de gêneros/idiomas,
                // como índice a fim de selecionar algum dos gêneros/idiomas disponíveis
                String novoGenero = Midia.GENEROS[(int) (Math.random() * (Midia.GENEROS.length))];
                String novoIdioma = Midia.IDIOMAS[(int) (Math.random() * (Midia.IDIOMAS.length))];

                // Atribui a uma data os valores de dia/mes/ano lidos no arquivo. Caso a string
                // não apresente formato válido, é lançada uma exceção
                Date novaData = null;
                try {
                    novaData = new SimpleDateFormat("dd/MM/yyyy").parse(dados[2]);
                } catch (Exception e) {
                    System.out.println("Erro: Formato inválido na leitura da data de lançamento de série");
                }

                Midia midia = null;

                switch (mt) {
                    case FILMES:
                        // Passa-se como parâmetros o nome conforme lido no arquivo (dados[1]), gênero,
                        // idioma, data de lançamento e duracao (dados[3]) em segundos. Em seguida,
                        // insere-se o novo filme no hashmap
                        midia = new Filme(dados[0], dados[1], novoGenero, novoIdioma, novaData, Integer.parseInt(dados[3]) * 60);
                        break;

                    case SERIES:
                        // Passa-se como parâmetros o nome conforme lido no arquivo (dados[1]), gênero e
                        // idioma gerados aleatóriamente, novaData e uma qtd aleatória de episódios. Em
                        // seguida, insere-se a nova série no hashmap
                        midia = new Serie(dados[0], dados[1], novoGenero, novoIdioma, novaData, (int) (Math.random() * 100));
                        break;
                }

                try {
                    adicionarMidia(Integer.valueOf(dados[0]), midia);
                } catch (NumberFormatException | NullPointerException e) {
                    System.out.println(linha + ":" + dados);
                } catch (MidiaJaExisteException e) {
                    System.out.println(e.getMessage());
                }

                linha++;
            }

            sc.close();
        }
    }

    /**
     * Lê o arquivo "Series.csv", ignorando a primeira linha do arquivo, instancia séries a partir das informações lidas
     * e as adiciona à lista de séries.
     *
     * @throws FileNotFoundException se o arquivo não for encontrado.
     */
    public void carregarSeries() throws FileNotFoundException {
        File file = new File("docs/database/Series.csv");
        Scanner filereader = new Scanner(file);

        filereader.nextLine(); // Artifício para ignorar primeira linha do arquivo .csv

        int linha = 0;

        while (filereader.hasNextLine()) {
            String[] dados = filereader.nextLine().split(";");

            // Gera um número aleatório, limitado pelo tamanho do vetor de gêneros/idiomas,
            // como índice a fim de selecionar algum dos gêneros/idiomas disponíveis
            String novoGenero = Midia.GENEROS[(int) (Math.random() * (Midia.GENEROS.length))];
            String novoIdioma = Midia.IDIOMAS[(int) (Math.random() * (Midia.IDIOMAS.length))];

            // Atribui a uma data os valores de dia/mes/ano lidos no arquivo. Caso a string
            // não apresente formato válido, é lançada uma exceção
            Date novaData = null;
            try {
                novaData = new SimpleDateFormat("dd/MM/yyyy").parse(dados[2]);
            } catch (Exception e) {
                System.out.println("Erro: Formato inválido na leitura da data de lançamento de série");
            }

            // Passa-se como parâmetros o nome conforme lido no arquivo (dados[1]), gênero e
            // idioma gerados aleatóriamente, novaData e uma qtd aleatória de episódios. Em
            // seguida, insere-se a nova série no hashmap
            Serie novaSerie = new Serie(dados[0], dados[1], novoGenero, novoIdioma, novaData, (int) (Math.random() * 100));

            try {
                adicionarMidia(Integer.valueOf(dados[0]), novaSerie);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(linha + ":" + dados);
            } catch (MidiaJaExisteException e) {
                System.out.println(e.getMessage());
            }

            linha++;
        }

        // Imprimir lista
        // this.series.forEach((key, value) -> System.out.println("\n" +
        // this.series.get(key)));

        filereader.close();
    }

    /**
     * Lê o arquivo "Filmes.csv", ignorando a primeira linha do arquivo, instancia filmes a partir das informações lidas
     * e os adiciona à lista de filmes.
     *
     * @throws FileNotFoundException se o arquivo não for encontrado.
     */
    public void carregarFilmes() throws FileNotFoundException {
        File file = new File("docs/database/Filmes.csv");
        Scanner filereader = new Scanner(file);

        filereader.nextLine(); // Artifício para ignorar primeira linha do arquivo .csv

        int linha = 0;
        while (filereader.hasNextLine()) {
            String[] dados = filereader.nextLine().split(";");

            // Gera um número aleatório, limitado pelo tamanho do vetor de gêneros/idiomas,
            // como índice a fim de selecionar algum dos gêneros/idiomas disponíveis
            String novoGenero = Midia.GENEROS[(int) (Math.random() * (Midia.GENEROS.length))];
            String novoIdioma = Midia.IDIOMAS[(int) (Math.random() * (Midia.IDIOMAS.length))];

            // Atribui a uma data os valores de dia/mes/ano lidos no arquivo. Caso a string
            // não apresente formato válido, é lançada uma exceção
            Date novaData = null;
            try {
                novaData = new SimpleDateFormat("dd/MM/yyyy").parse(dados[2]);
            } catch (Exception e) {
                System.out.println("Erro: Formato inválido na leitura da data de lançamento de filme");
            }

            // Passa-se como parâmetros o nome conforme lido no arquivo (dados[1]), gênero,
            // idioma, data de lançamento e duracao (dados[3]) em segundos. Em seguida,
            // insere-se o novo filme no hashmap
            Filme novoFilme = new Filme(dados[0], dados[1], novoGenero, novoIdioma, novaData, Integer.parseInt(dados[3]) * 60);

            try {
                adicionarMidia(Integer.valueOf(dados[0]), novoFilme);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(linha + ":" + dados);
            } catch (MidiaJaExisteException e) {
                System.out.println(e.getMessage());
            }

            linha++;
        }

        // Imprimir lista
        // this.filmes.forEach((key, value) -> System.out.println("\n" +
        // this.filmes.get(key)));

        filereader.close();
    }

    /**
     * Lê o arquivo "Audiencia.csv" e, conforme lido no arquivo, adiciona série à lista para assistir (F) ou registra
     * audiência de série já assistida pelo cliente (A).
     *
     * @throws FileNotFoundException       se o arquivo não for encontrado.
     * @throws MidiaNaoEncontradaException
     */
    public void carregarAudiencia() throws FileNotFoundException, MidiaNaoEncontradaException {
        String arquivo = "docs/database/Audiencia.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");

                String userId = tokenizer.nextToken();
                String status = tokenizer.nextToken();
                int mediaId = Integer.parseInt(tokenizer.nextToken());

                if (clientes.containsKey(userId) && midias.containsKey(mediaId)) {
                    if (status.equals("F")) {
                        clientes.get(userId).adicionarNaListaParaVer(midias.get(mediaId));
                    } else if (status.equals("A")) {
                        clientes.get(userId).registrarAudiencia(midias.get(mediaId));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro: não foi possível ler arquivo de dados de audiência");
        }
    }

    /**
     * Lê o arquivo "Avaliacoes.csv" e, conforme lido no arquivo, adiciona às midias as avaliações presentes, com nota e
     * comentário. No entanto, caso o cliente avaliador não seja do tipo cliente especialista, se adiciona à avaliação
     * apenas nota.
     *
     * @throws FileNotFoundException se o arquivo não for encontrado.
     */
    public void carregarAvaliacoes() throws FileNotFoundException, NullPointerException {
        File file = new File("docs/database/Avaliacoes.csv");
        Scanner filereader = new Scanner(file);

        filereader.nextLine(); // Pular primeira linha do arquivo

        while (filereader.hasNextLine()) {
            String[] dados = filereader.nextLine().split(";");

            Midia midiaAvaliada = null;

            // Verificar se mídia é filme, série ou se não existe na plataforma
            if (this.midias.containsKey(Integer.parseInt(dados[0])))
                midiaAvaliada = this.midias.get(Integer.parseInt(dados[0]));
            else throw new NullPointerException("Erro: A mídia a ser avaliada não existe");

            Cliente avaliador = this.clientes.get(dados[1]);

            if (avaliador.getModoAvaliacao() instanceof ClienteEspecialista) {
                avaliador.avaliarMidia(midiaAvaliada, Integer.parseInt(dados[2]), dados[3]);
            } else {
                avaliador.avaliarMidia(midiaAvaliada, Integer.parseInt(dados[2]));
            }
        }
    }

    /**
     * Lê o hashmap clientes e registra no arquivo Filmes.csv os atributos de cada cliente presente.
     */
    public void salvarClientes() {
        String csvFilename = "docs/database/Espectadores.csv";

        try (FileWriter writer = new FileWriter(csvFilename)) {
            writer.append("nomeDeUsuario; id; senha\n");

            this.clientes.forEach((key, value) -> {
                try {
                    writer.append(value.getNomeUsuario()).append(";").append(value.getId()).append(";").append(value.getSenha()).append("\n");
                } catch (IOException e) {
                    System.err.println("Erro: não foi escrever no arquivo para salvar dados do cliente.");
                }
            });

            System.out.println("Clientes salvos com sucesso!");

        } catch (IOException e) {
            System.err.println("Erro: não foi possível gerar arquivo para salvar dados do cliente.");
        }
    }

    /**
     * Lê o hashmap midias e, conforme tipo de mídia presente, grava os atributos de filme ou série, respectivamente,
     * nos arquivos Filmes.csv ou Series.csv.
     */
    public void salvarMidia() {
        for (MidiaType mt : MidiaType.values()) {
            try (FileWriter writer = new FileWriter("docs/database/" + mt.name().substring(0, 1).toUpperCase() + mt.name().substring(1).toLowerCase() + ".csv")) {

                writer.append("id; nome; lancamento;");

                switch (mt) {
                    case FILMES:
                        writer.append("duracao;");

                        for (Midia filme : getFilmes()) {
                            String lancamentoFormatted = new SimpleDateFormat("dd/MM/yyyy").format(filme.getLancamento());

                            try {
                                writer.append(filme.getId()).append(";").append(filme.getNome()).append(";").append(lancamentoFormatted).append(";").append(String.valueOf(((Filme) filme).getDuracao() / 3600));
                            } catch (IOException e) {
                                System.out.println("Erro: não foi possivel escrever no arquivo para salvar dados do filme.");
                            }

                            writer.append("\n");
                        }
                        break;

                    case SERIES:
                        for (Midia serie : getSeries()) {
                            String lancamentoFormatted = new SimpleDateFormat("dd/MM/yyyy").format(serie.getLancamento());

                            try {
                                writer.append(serie.getId()).append(";").append(serie.getNome()).append(";").append(lancamentoFormatted);
                            } catch (IOException e) {
                                System.out.println("Erro: não foi possivel escrever no arquivo para salvar dados do filme.");
                            }

                            writer.append("\n");
                        }
                        break;
                }

            } catch (IOException e) {
                System.out.println("Erro: não foi possível gerar arquivo para salvar dados da serie." + e.getMessage());
            }
        }

        System.out.println("Mídias salvas com sucesso!");
    }

    /**
     * Lê as listas de séries assistidas e para ver e registra no arquivo Audiencia.csv a audiência de cada uma delas no
     * formato idDoUsuario;F/A;idDaSerie, sendo que "A" significa que a série já foi assistida, e "F" significa que a
     * série está na lista para ver.
     */
    public void salvarAudiencia() {
        String arquivo = "docs/database/Audiencia.csv";

        try (FileWriter writer = new FileWriter(arquivo)) {
            this.getClientes().forEach((key, value) -> {

                // Séries assistidas
                Lista<Midia> listaJaVistas = value.getListaJaVistas();

                Midia[] arrayAssistidas = new Midia[listaJaVistas.size()];
                arrayAssistidas = listaJaVistas.allElements(arrayAssistidas);

                for (Midia serieAssistida : arrayAssistidas) {
                    try {
                        writer.append(value.getId());
                        writer.append(";");
                        writer.append("A");
                        writer.append(";");
                        writer.append(serieAssistida.getId());
                        writer.append("\n");
                    } catch (IOException e) {
                        System.out.println("Erro: não foi possivel escrever no arquivo para salvar dados de audiência.");
                    }
                }

                // Séries para ver
                Midia[] listaParaVer = new Midia[value.getListaParaVer().size()];
                listaParaVer = value.getListaParaVer().allElements(listaParaVer);

                for (Midia serie : listaParaVer) {
                    try {
                        writer.append(value.getId());
                        writer.append(";");
                        writer.append("F");
                        writer.append(";");
                        writer.append(serie.getId());
                        writer.append("\n");
                    } catch (IOException e) {
                        System.out.println("Erro: não foi possivel escrever no arquivo para salvar dados de audiência.");
                    }
                }
            });

        } catch (IOException e) {
            System.out.println("Erro: não foi possível gerar arquivo para salvar dados de audiência.");
        }

        System.out.println("Audiência salva com sucesso!");
    }

    /**
     * Lê a lista de mídias e acessa as avaliações de cada uma delas. Em seguida, para cada avaliação, salva no arquivo
     * Avaliacoes.csv o id da mídia, id do cliente, nota e comentário da avaliação. Caso não haja comentário de
     * avaliação, salva-se uma string vazia.
     */
    public void salvarAvaliacoes() {
        try (FileWriter writer = new FileWriter("docs/database/Avaliacoes.csv")) {
            writer.append("id_midia;id_cliente;nota;comentario\n");

            for (Map.Entry<Integer, Midia> entry : midias.entrySet()) {
                for (Avaliacao avaliacao : entry.getValue().getAvaliacoes()) {
                    try {
                        writer.append(entry.getValue().getId());
                        writer.append(";");
                        writer.append(avaliacao.getCliente().getId());
                        writer.append(";");
                        writer.append(String.valueOf(avaliacao.getNota()));
                        writer.append(";");
                        if (avaliacao.getTexto() != null) writer.append(avaliacao.getTexto());
                        else writer.append(" ");
                        writer.append("\n");
                    } catch (IOException e) {
                        System.out.println("Erro: não foi possivel escrever no arquivo de avaliações.");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro: não foi possível gerar arquivo para salvar avaliações.");
        }
        System.out.println("Avaliações salvas com sucesso!");
    }

    /**
     * Promove o salvamento de todas as entidades da plataforma em seus respectivos arquivos designados ao chamar os
     * métodos de salvamento de avaliações, audiência, mídia e clientes
     */
    public void salvar() {
        salvarAvaliacoes();
        salvarAudiencia();
        salvarMidia();
        salvarClientes();
    }

    /**
     * Filta pela lista de clientes da plataforma de streaming e checa qual cliente tem a maior lista de séries ja
     * vistas
     *
     * @return Cliente que mais assistiu midia
     */
    public Cliente qualClienteAssistiuMaisMidias() {

        Cliente c = this.clientes.values().stream().max(Comparator.comparing(Cliente::tamanhoListaJaVistos)).get();

        return c;

    }

    public Cliente qualClienteTemMaisAvaliacoes() {

        List<Integer> listaDeAvaliacoes = new LinkedList<>();

        for (Midia m : this.midias.values()) {
            listaDeAvaliacoes.add(m.qtdAvaliacoes());
        }

        Integer valorMaximo = Collections.max(listaDeAvaliacoes);

        Cliente clienteQueMaisAvaliou = null;

        for (Midia m : this.midias.values()) {
            if (m.qtdAvaliacoes() == valorMaximo) {
                List<Avaliacao> lista = null;
                lista = m.getAvaliacoes();
                for (Avaliacao a : lista) {
                    clienteQueMaisAvaliou = a.getCliente();
                }

            }
        }

        return clienteQueMaisAvaliou;
    }

    public int clientesComQuinzeAvaliacoes () {
        int qtdAvaliacoes;
        int clientesQuinze = 0;

        Collection<Cliente> listaClientes = this.clientes.values();

        for(Cliente cliente : listaClientes) {
            qtdAvaliacoes = 0;
            Lista<Midia> listaJaVistas = cliente.getListaJaVistas();

            Midia[] midiasVistas = new Midia[listaJaVistas.size()];
            midiasVistas = listaJaVistas.allElements(midiasVistas);

            for(Midia midia : midiasVistas) {
                List<Avaliacao> avaliacoes = midia.getAvaliacoes();

                for(Avaliacao avaliacao : avaliacoes) {
                    if(avaliacao.getCliente().equals(cliente)) {
                        qtdAvaliacoes++;
                    }
                }
            }

            if(qtdAvaliacoes >= 15) {
                clientesQuinze++;
            }
        }

        return (clientesQuinze / listaClientes.size()) * 100;
    }
}