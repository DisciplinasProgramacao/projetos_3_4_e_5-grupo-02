package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

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
     * Realiza operação de login a partir do nome de usuário e senha informados. Se um usuário correspondente for
     * encontrado, o cliente atual será definido como o cliente conectado. Caso contrário, lança
     * LoginInvalidoException.
     *
     * @param nomeUsuario Nome de usuário do cliente que tenta logar.
     * @param senha       Senha do cliente que tenta logar.
     * @throws LoginInvalidoException Se o nome de usuário e a senha fornecidos não corresponderem a nenhum cliente na lista.
     */
    public void login(String nomeUsuario, String senha) throws LoginInvalidoException {
        for (Cliente cliente : this.clientes.values()) {
            if (cliente.getId().equals(nomeUsuario)
                    && cliente.getSenha().equals(senha)) {
                this.clienteAtual = cliente;
                System.out.println("Login realizado com sucesso!");
            }
        }

        throw new LoginInvalidoException(nomeUsuario, senha);
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

            Cliente novoCliente = new Cliente(split[0], split[1], split[2]);

            try {
                adicionarCliente(novoCliente);
            } catch (NullPointerException e) {
                System.out.println(linha + ":" + split);

            } catch (ElementoJaExisteException e) {
                System.out.println(e.getMessage());
            }

            linha++;
        }

        // this.clientes.forEach((key, value) -> System.out.println(
        // 		this.clientes.get(key) + "\nID: " + key + " \n"));

        filereader.close();
    }

    public void adicionarCliente(String userName, String userId, String userPassword) throws ElementoJaExisteException {
        if (clientes.containsKey(userId))
            throw new ElementoJaExisteException(userId);
        else {
            Cliente novoCliente = new Cliente(userName, userId, userPassword);
            clientes.put(userId, novoCliente);
        }
    }

    /**
     * Adiciona um novo cliente à lista de clientes. Caso o cliente a ser adicionado já esteja previamente presente na
     * lista, a operação não é executada
     *
     * @param novoCliente cliente a ser adicionado
     */
    public void adicionarCliente(Cliente novoCliente) throws NullPointerException, ElementoJaExisteException {
        if (novoCliente == null) {
            throw new NullPointerException();
        }

        if (this.clientes.containsKey(novoCliente.getId())) {
            throw new ElementoJaExisteException(novoCliente.getNomeUsuario(), "clientes");
        }

        this.clientes.put(novoCliente.getId(), novoCliente);
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
     * Lê o conteúdo do HashMap clientes e os escreve em um arquvivo .csv, sobrepondo o arquivo já existente de nome
     * Espectadores.csv. O salvamento deve ser realizado após execução do programa a fim de registrar em arquivo todas
     * as mudanças realizadas nos dados em memória
     */
    public void registrarAudiencia(Serie serie) throws NullPointerException {
        if (clienteAtual == null) {
            throw new NullPointerException();
        }

        clienteAtual.registrarAudiencia(serie);
    }

    public void adicionarFilme(Integer id, Filme novoFilme) throws NullPointerException, ElementoJaExisteException {
        if (novoFilme == null) {
            throw new NullPointerException();
        }

        if (this.filmes.containsKey(id)) {
            throw new ElementoJaExisteException(id.toString(), "filmes");
        }

        this.filmes.put(id, novoFilme);
    }

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
     * Le o HashMap series e registra um arquivo contendo o nome, genero e quantidade de episodios de uma serie
     */
    public void salvarSeries() {
        String arquivo = "docs/database/Series.csv";

        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.append("id; nome; lancamento; somaNotas; totalNotas\n");

            this.series.forEach((key, value) -> {
                try {
                    writer.append(key.toString())
                            .append(";")
                            .append(value.getNome())
                            .append(";")
                            .append(value.getLancamento().toString())
                            .append(";")
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
     * Lê o hashmap filmes e registra no arquivo Filmes.csv os atributos de cada filme presente.
     */
    public void salvarFilmes() {
        String arquivo = "docs/database/Filmes.csv";

        try (FileWriter writer = new FileWriter(arquivo)) {
            writer.append("id; nome; lancamento; duracao; somaNotas; totalNotas\n");

            this.filmes.forEach((key, value) -> {
                try {
                    writer.append(key.toString())
                            .append(";")
                            .append(value.getNome())
                            .append(";")
                            .append(value.getLancamento().toString())
                            .append(";")
                            .append(String.valueOf(value.getDuracao()))
                            .append(";")
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
                System.out.println("Erro: Formato inválido na leitura da data de lançamento de série");
            }

            // Passa-se como parâmetros o nome conforme lido no arquivo (dados[1]), gênero,
            // idioma, data de lançamento e duracao (dados[3]) em segundos. Em seguida,
            // insere-se o novo filme no hashmap
            Filme novoFilme = new Filme(dados[1], novoGenero, novoIdioma, novaData, Integer.parseInt(dados[3]) * 60);

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
//        this.filmes.forEach((key, value) -> System.out.println("\n" + this.filmes.get(key)));

        filereader.close();
    }

    /**
     * Lê o arquivo "Audiencia.csv" e, conforme lido no arquivo, adiciona série à lista para assistir ou registra
     * audiência de série já assistida pelo cliente.
     *
     * @throws FileNotFoundException se o arquivo não for encontrado.
     */
    public void carregarAudiencia() throws FileNotFoundException {
        File file = new File("docs/database/Audiencia.csv");
        Scanner filereader = new Scanner(file);

        while (filereader.hasNextLine()) {
            String[] dados = filereader.nextLine().split(";");

            if (clientes.containsKey(dados[0]) && series.containsKey(Integer.valueOf(dados[2]))) {

                if (dados[1].equals("F")) {
                    clientes.get(dados[0]).adicionarNaLista(series.get(Integer.valueOf(dados[2]))); // Adiciona série à lista
                } else if (dados[1].equals("A")) {
                    clientes.get(dados[0]).registrarAudiencia(series.get(Integer.valueOf(dados[2]))); // Registra +1 ponto de audiência na série
                }
            }
        }

        filereader.close();
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
            Serie novaSerie = new Serie(dados[1], novoGenero, novoIdioma, novaData, (int) (Math.random() * 100));

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
//        this.series.forEach((key, value) -> System.out.println("\n" + this.series.get(key)));

        filereader.close();
    }

    /**
     * Invoca o método "filtrarPorGenero" do cliente atual, obtendo, assim, as séries das listas que correspondem ao
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
     * Invoca o método "filtrarPorIdioma" do cliente atual, obtendo, assim, as séries das listas que correspondem ao
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
     * Invoca o método "filtrarPorQtdEpisodios" do cliente atual, obtendo, assim, as séries das listas que possuem a
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
}
