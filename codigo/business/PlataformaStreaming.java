package business;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import business.exceptions.ElementoJaExisteException;
import business.exceptions.LoginInvalidoException;

public class PlataformaStreaming {

    // ATRIBUTOS
    private final String nome;
    private Cliente clienteAtual;
    private final HashMap<Integer, Serie> series;
    private final HashMap<Integer, Filme> filmes;
    private final HashMap<String, Cliente> clientes;

    // CONSTRUTORES
    public PlataformaStreaming(String nome, Cliente clienteAtual) {
        this.nome = nome;
        this.clienteAtual = clienteAtual;
        this.series = new HashMap<Integer, Serie>();
        this.filmes = new HashMap<Integer, Filme>();
        this.clientes = new HashMap<String, Cliente>();
    }

    // GETTERS E SETTERS
    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }

    public HashMap<Integer, Serie> getSeries() {
        return series;
    }

    public HashMap<Integer, Filme> getFilmes() {
        return filmes;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    // MÉTODOS

    /**
     * Realiza operação de login a partir do nome de usuário e senha informados. Se
     * um usuário correspondente for
     * encontrado, o cliente atual será definido como o cliente conectado. Caso
     * contrário, lança
     * LoginInvalidoException.
     *
     * @param nomeUsuario Nome de usuário do cliente que tenta logar.
     * @param senha       Senha do cliente que tenta logar.
     * @throws LoginInvalidoException Se o nome de usuário e a senha fornecidos não
     *                                corresponderem a nenhum cliente na lista.
     */
    public void login(String nomeUsuario, String senha) throws LoginInvalidoException {
        for (Cliente cliente : this.clientes.values()) {
            if (cliente.getId().equals(nomeUsuario)
                    && cliente.getSenha().equals(senha)) {
                this.clienteAtual = cliente;
                System.out.println("Login realizado com sucesso!");
                return;
            }
        }

        throw new LoginInvalidoException(nomeUsuario, senha);
    }

    /**
     * Lê o arquivo "Espectadores.csv", ignorando a primeira linha do arquivo,
     * instancia clientes a partir das
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
            } catch (ElementoJaExisteException e) {
                System.out.println(e.getMessage());
            }

            linha++;
        }

        // this.clientes.forEach((key, value) -> System.out.println(
        // this.clientes.get(key) + "\nID: " + key + " \n"));

        filereader.close();
    }

    /**
     * Adiciona um novo cliente à lista de clientes. Caso o cliente a ser adicionado
     * já esteja previamente presente na lista, a operação não é executada
     *
     * @param userId       Id do novo cliente
     * @param userName     Nome do novo cliente
     * @param userPassword Senha do novo cliente
     */
    public void adicionarCliente(String userName, String userId, String userPassword) throws ElementoJaExisteException {
        if (clientes.containsKey(userId))
            throw new ElementoJaExisteException(userId);
        else {
            Cliente novoCliente = new Cliente(userName, userId, userPassword);
            clientes.put(userId, novoCliente);
        }
    }

    /**
     * Lê o hashmap clientes e registra no arquivo Filmes.csv os atributos de cada
     * cliente presente.
     */
    public void salvarClientes() {
        String csvFilename = "docs/database/Espectadores.csv";

        try (FileWriter writer = new FileWriter(csvFilename)) {
            writer.append("nomeDeUsuario; id; senha\n");

            this.clientes.forEach((key, value) -> {
                try {
                    writer.append(value.getNomeUsuario())
                            .append(";")
                            .append(value.getId())
                            .append(";")
                            .append(value.getSenha())
                            .append("\n");
                } catch (IOException e) {
                    System.out.println("Erro: não foi escrever no arquivo para salvar dados do cliente.");
                }
            });

            System.out.println("Clientes salvos com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro: não foi possível gerar arquivo para salvar dados do cliente.");
        }
    }

    /**
     * Adiciona uma nova série ao catálogo.
     *
     * @param id        O identificador único da série
     * @param novaSerie O objeto Série a ser adicionado
     * @throws NullPointerException      Se o novoFilme for nulo
     * @throws ElementoJaExisteException Se o filme com o mesmo id já existir na
     *                                   plataforma
     */
    public void adicionarSerie(Integer id, Serie novaSerie) throws NullPointerException, ElementoJaExisteException {
        if (novaSerie == null) {
            throw new NullPointerException();
        }

        if (this.series.containsKey(id)) {
            throw new ElementoJaExisteException(id.toString(), "series");
        }

        this.series.put(id, novaSerie);
    }

    /**
     * Lê o arquivo "Series.csv", ignorando a primeira linha do arquivo, instancia
     * séries a partir das informações lidas
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
            Serie novaSerie = new Serie(dados[0], dados[1], novoGenero, novoIdioma, novaData,
                    (int) (Math.random() * 100));

            try {
                adicionarSerie(Integer.valueOf(dados[0]), novaSerie);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(linha + ":" + dados);
            } catch (ElementoJaExisteException e) {
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
     * Le o HashMap series e registra um arquivo contendo o nome, genero e
     * quantidade de episodios de uma serie
     */
    public void salvarSeries() {
        String arquivo = "docs/database/Series.csv";

        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.append("id; nome; lancamento\n");

            this.series.forEach((key, value) -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String lancamentoFormatted = dateFormat.format(value.getLancamento());

                try {
                    writer.append(key.toString())
                            .append(";")
                            .append(value.getNome())
                            .append(";")
                            .append(lancamentoFormatted)
                            // .append(value.getLancamento().toString())
                            .append("\n");
                } catch (IOException e) {
                    System.out.println("Erro: não foi possivel escrever no arquivo para salvar dados da serie.");
                }
            });

            System.out.println("Série salvas com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro: não foi possível gerar arquivo para salvar dados da serie.");
        }
    }

    /**
     * Adiciona um novo filme ao catálogo.
     *
     * @param id        O identificador único do filme
     * @param novoFilme O objeto Filme a ser adicionado
     * @throws NullPointerException      Se o novoFilme for nulo
     * @throws ElementoJaExisteException Se o filme com o mesmo id já existir na
     *                                   plataforma
     */
    public void adicionarFilme(Integer id, Filme novoFilme) throws NullPointerException, ElementoJaExisteException {
        if (novoFilme == null) {
            throw new NullPointerException();
        }

        if (this.filmes.containsKey(id)) {
            throw new ElementoJaExisteException(id.toString(), "filmes");
        }

        this.filmes.put(id, novoFilme);
    }

    /**
     * Lê o arquivo "Filmes.csv", ignorando a primeira linha do arquivo, instancia
     * filmes a partir das informações lidas
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
            Filme novoFilme = new Filme(dados[0], dados[1], novoGenero, novoIdioma, novaData,
                    Integer.parseInt(dados[3]) * 60);

            try {
                adicionarFilme(Integer.valueOf(dados[0]), novoFilme);
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(linha + ":" + dados);
            } catch (ElementoJaExisteException e) {
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
     * Lê o hashmap filmes e registra no arquivo Filmes.csv os atributos de cada
     * filme presente.
     */
    public void salvarFilmes() {
        String arquivo = "docs/database/Filmes.csv";

        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.append("id; nome; lancamento; duracao;\n");

            this.filmes.forEach((key, value) -> {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                String lancamentoFormatted = dateFormat.format(value.getLancamento());

                try {
                    writer.append(key.toString())
                            .append(";")
                            .append(value.getNome())
                            .append(";")
                            .append(lancamentoFormatted)
                            .append(";")
                            .append(String.valueOf(value.getDuracao() / 3600))
                            .append("\n");
                } catch (IOException e) {
                    System.out.println("Erro: não foi possivel escrever no arquivo para salvar dados do filme.");
                }
            });

            System.out.println("Filmes salvos com sucesso!");

        } catch (IOException e) {
            System.out.println("Erro: não foi possível gerar arquivo para salvar dados do filme.");
        }
    }

    /**
     * Lê o arquivo "Audiencia.csv" e, conforme lido no arquivo, adiciona série à
     * lista para assistir (F) ou registra audiência de série já assistida pelo
     * cliente (A).
     *
     * @throws FileNotFoundException se o arquivo não for encontrado.
     */
    public void carregarAudiencia() throws FileNotFoundException {
        String arquivo = "docs/database/Audiencia.csv";

        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String line;
            while ((line = br.readLine()) != null) {
                StringTokenizer tokenizer = new StringTokenizer(line, ";");

                String userId = tokenizer.nextToken();
                String status = tokenizer.nextToken();
                int mediaId = Integer.parseInt(tokenizer.nextToken());

                if (clientes.containsKey(userId) && series.containsKey(mediaId)) {
                    if (status.equals("F")) {
                        clientes.get(userId).adicionarNaLista(series.get(mediaId));
                    } else if (status.equals("A")) {
                        clientes.get(userId).registrarAudiencia(series.get(mediaId));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Erro: não foi possível ler arquivo de dados de audiência");
        }
    }

    /**
     * Lê as listas de séries assistidas e para ver e registra no arquivo
     * Audiencia.csv a audiência de cada
     * uma delas no formato idDoUsuario;F/A;idDaSerie, sendo que "A" significa que a
     * série já foi assistida,
     * e "F" significa que a série está na lista para ver.
     */
    public void salvarAudiencia() {
        String arquivo = "docs/database/Audiencia.csv";

        try (FileWriter writer = new FileWriter(arquivo)) {
            this.getClientes().forEach((key, value) -> {

                // Séries assistidas
                Lista<Serie> listaJaVistas = value.getListaJaVistas();

                Serie[] arrayAssistidas = new Serie[listaJaVistas.size()];
                arrayAssistidas = listaJaVistas.allElements(arrayAssistidas);

                for (Serie serieAssistida : arrayAssistidas) {
                    try {
                        writer.append(value.getId());
                        writer.append(";");
                        writer.append("A");
                        writer.append(";");
                        writer.append(serieAssistida.getId());
                        writer.append("\n");
                    } catch (IOException e) {
                        System.out
                                .println("Erro: não foi possivel escrever no arquivo para salvar dados de audiência.");
                    }
                }

                // Séries para ver
                Serie[] listaParaVer = new Serie[value.getListaParaVer().size()];
                listaParaVer = value.getListaParaVer().allElements(listaParaVer);

                for (Serie serie : listaParaVer) {
                    try {
                        writer.append(value.getId());
                        writer.append(";");
                        writer.append("F");
                        writer.append(";");
                        writer.append(serie.getId());
                        writer.append("\n");
                    } catch (IOException e) {
                        System.out
                                .println("Erro: não foi possivel escrever no arquivo para salvar dados de audiência.");
                    }
                }
            });

        } catch (IOException e) {
            System.out.println("Erro: não foi possível gerar arquivo para salvar dados de audiência.");
        }

        System.out.println("Audiência salva com sucesso!");
    }

    /**
     * Lê o conteúdo do HashMap clientes e os escreve em um arquvivo .csv,
     * sobrepondo o arquivo já existente de nome
     * Espectadores.csv. O salvamento deve ser realizado após execução do programa a
     * fim de registrar em arquivo todas
     * as mudanças realizadas nos dados em memória
     */
    public void registrarAudiencia(Serie serie) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        clienteAtual.registrarAudiencia(serie);
    }

    /**
     * Lê o arquivo "Avaliacoes.csv" e, conforme lido no arquivo, adiciona às midias
     * as avaliações presentes,
     * com nota e comentário. No entanto, caso o cliente avaliador não seja do tipo
     * cliente especialista, se
     * adiciona à avaliação apenas nota.
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
            if (this.series.containsKey(Integer.parseInt(dados[0])))
                midiaAvaliada = this.series.get(Integer.parseInt(dados[0]));
            else if (this.filmes.containsKey(Integer.parseInt(dados[0])))
                midiaAvaliada = this.filmes.get(Integer.parseInt(dados[0]));
            else
                throw new NullPointerException("Erro: A mídia a ser avaliada não existe");

            Cliente avaliador = this.clientes.get(dados[1]);

            if (avaliador.getModoAvaliacao() instanceof ClienteEspecialista) {
                avaliador.avaliarMidia(midiaAvaliada, Integer.parseInt(dados[2]), dados[3]);
            } else {
                avaliador.avaliarMidia(midiaAvaliada, Integer.parseInt(dados[2]));
            }
        }
    }

    // Se não houver comentário de avaliação, salva string " "
    public void salvarAvaliacoes() {
        String arquivo = "docs/database/Avaliacoes.csv";

        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.append("id_midia;id_cliente;nota;comentario\n");

            // Avaliações de séries
            this.series.forEach((key, value) -> {
                value.getAvaliacoes().forEach(avaliacao -> {
                    try {
                        writer.append(value.getId());
                        writer.append(";");
                        writer.append(avaliacao.getCliente().getId());
                        writer.append(";");
                        writer.append(String.valueOf(avaliacao.getNota()));
                        writer.append(";");
                        if (avaliacao.getTexto() != null)
                            writer.append(avaliacao.getTexto());
                        else
                            writer.append(" ");
                        writer.append("\n");
                    } catch (IOException e) {
                        System.out.println("Erro: não foi possivel escrever no arquivo de avaliações.");
                    }
                });
            });

            // Avaliações de filmes
            this.filmes.forEach((key, value) -> {
                value.getAvaliacoes().forEach(avaliacao -> {
                    try {
                        writer.append(value.getId());
                        writer.append(";");
                        writer.append(avaliacao.getCliente().getId());
                        writer.append(";");
                        if (avaliacao.getTexto() != null)
                            writer.append(avaliacao.getTexto());
                        else
                            writer.append(" ");
                        writer.append("\n");
                    } catch (IOException e) {
                        System.out.println("Erro: não foi possivel escrever no arquivo de avaliações.");
                    }
                });
            });

        } catch (IOException e) {
            System.out.println("Erro: não foi possível gerar arquivo para salvar avaliações.");
        }

        System.out.println("Avaliações salvas com sucesso!");
    }

    /**
     * Invoca o método "filtrarPorGenero" do cliente atual, obtendo, assim, as
     * séries das listas que correspondem ao
     * gênero selecionado.
     *
     * @param genero Gênero selecionado
     * @return Lista filtrada por gênero das séries encontradas
     * @throws NullPointerException Se não houver cliente atual na plataforma
     */
    public Lista<Serie> filtrarPorGenero(String genero) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        return clienteAtual.filtrarPorGenero(genero);
    }

    /**
     * Invoca o método "filtrarPorIdioma" do cliente atual, obtendo, assim, as
     * séries das listas que correspondem ao
     * idioma selecionado.
     *
     * @param idioma Idioma selecionado
     * @return Lista filtrada por idioma das séries encontradas
     * @throws NullPointerException Se não houver cliente atual na plataforma
     */
    public Lista<Serie> filtrarPorIdioma(String idioma) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        return clienteAtual.filtrarPorIdioma(idioma);
    }

    /**
     * Invoca o método "filtrarPorQtdEpisodios" do cliente atual, obtendo, assim, as
     * séries das listas que possuem a
     * quantidade de episódios selecionada.
     *
     * @param quantEpisodios Quantidade de episódios selecionada
     * @return Lista filtrada por idioma das séries encontradas
     * @throws NullPointerException Se não houver cliente atual na plataforma
     */
    public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
    }

    /**
     * Filta pela lista de clientes da plataforma de streaming e checa qual cliente
     * tem
     * a maior lista de séries ja vistas
     * 
     * @return Cliente que mais assistiu midia
     */
    public Cliente qualClienteAssistiuMaisMidias() {

        Cliente c = this.clientes.values().stream()
                .max(Comparator.comparing(Cliente::tamanhoListaJaVistos)).get();

        System.out.println(c);
        int quantidadeDeMidiasAssistidas = c.tamanhoListaJaVistos();
        System.out.println(quantidadeDeMidiasAssistidas);
        return c;

    }

    public Cliente qualClienteTemMaisAvaliações() {

        List<Midia> listaSeriesFilmes = new LinkedList<>();

        for (Serie s : series.values()) {
            listaSeriesFilmes.add(s);
        }

        for (Filme f : filmes.values()) {
            listaSeriesFilmes.add(f);
        }

        List<Integer> listaDeAvaliacoes = new LinkedList<>();

        for (Midia m : listaSeriesFilmes) {
            listaDeAvaliacoes.add(m.qtdAvaliacoes());
        }

        Integer valorMaximo = Collections.max(listaDeAvaliacoes);

        Cliente clienteQueMaisAvaliou = null;

        for (Midia m : listaSeriesFilmes) {
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
}
