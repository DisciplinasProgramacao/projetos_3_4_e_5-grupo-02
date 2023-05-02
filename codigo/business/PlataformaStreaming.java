package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.IntPredicate;

import business.exceptions.ElementoJaExisteException;
import business.exceptions.LoginInvalidoException;

public class PlataformaStreaming {

	// ATRIBUTOS
	private String nome;
	private Cliente clienteAtual;
	private HashMap<Integer, Serie> series;
	private HashMap<Integer, Filme> filmes;
	private HashMap<String, Cliente> clientes;

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

	// MÉTODOS
	public Cliente login(String nomeUsuario, String senha) throws LoginInvalidoException {
		for (Cliente cliente : this.clientes.values()) {
			if (cliente.getNomeUsuario() == nomeUsuario && cliente.getSenha() == senha) {
				this.clienteAtual = cliente;
				return cliente;
			}
		}
		throw new LoginInvalidoException(nomeUsuario, senha);
	}

	public void carregarClientes() throws FileNotFoundException {
		File file = new File("docs/database/Espectadores.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do csv
		int linha=0;
		while (filereader.hasNextLine()) {
			String[] split = filereader.nextLine().split(";");
			
			Cliente novoCliente = new Cliente(split[0], split[1], split[2]);

			// dados[1] = id
			try {
				adicionarCliente(novoCliente);
			} catch (NullPointerException e) {
				System.out.println(linha + ":" + split);
				
			} catch (ElementoJaExisteException e) {
				System.out.println(e.getMessage());
			}
			// this.clientes.put(split[1], novoCliente);
			linha++;
		}

		this.clientes.forEach((key, value) -> System.out.println(
				this.clientes.get(key) + "\nID: " + key + " \n"));

		filereader.close();
	}

	/**
	 * Adiciona um novo cliente à lista de clientes. Caso o cliente a ser adicionado
	 * já esteja previamente
	 * presente na lista, a operação não é executada
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
	 * Lê o conteúdo do HashMap clientes e os escreve em um arquvivo .csv,
	 * sobrepondo o arquivo já existente
	 * de nome Espectadores.csv
	 * O salvamento deve ser realizado após execução do programa a fim de registrar
	 * em arquivo todas as mudanças
	 * realizadas nos dados em memória
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

	public void registrarAudiencia(Serie serie) throws NullPointerException {
		if (clienteAtual == null) {
			throw new NullPointerException();
		}

		clienteAtual.registrarAudiencia(serie);
	}

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
			// idioma gerados aleatóriamente, novaData e uma qtd aleatória de episódios.Em
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
		this.series.forEach((key, value) -> System.out.println("\n" + this.series.get(key)));

		filereader.close();
	}

	 /**
	 * Le o HashMap series e registra um arquivo contendo o nome, genero e quantidade de episodios de uma serie
	 */
	public void salvarSeries() {
		String arquivo = "docs/database/Series.csv";

		
		try (FileWriter writer = new FileWriter(arquivo)) {
			writer.append("nome; genero; quantidadeEpisodios\n");

			this.series.forEach((key, value) -> {
				try {
					writer.append(value.getNome())
						.append(";")
						.append(value.getGenero())
						.append(";")
						.append(value.toString())
						.append("\n");
				} catch (IOException e) {
					System.out.println("Erro: não foi possivel escrever no arquivo para salvar dados da serie.");
				}
			});

			System.out.println("Serie salva com sucesso!");

		} catch (IOException e) {
			System.out.println("Erro: não foi possível gerar arquivo para salvar dados da serie.");
		}
	}

	public void carregarFilmes() throws FileNotFoundException {
		File file = new File("docs/database/Filmes.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do arquivo .csv

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
			} catch (NumberFormatException | NullPointerException | ElementoJaExisteException e) {
				e.printStackTrace();
			}
		}

		// Imprimir lista
		this.filmes.forEach((key, value) -> System.out.println("\n" + this.filmes.get(key)));

		filereader.close();
	}

	public void carregarAudiencia() throws FileNotFoundException {
		File file = new File("docs/database/Audiencia.csv");
		Scanner filereader = new Scanner(file);

		while (filereader.hasNextLine()) {
			String[] dados = filereader.nextLine().split(";");

			if (clientes.containsKey(dados[0]) && series.containsKey(Integer.valueOf(dados[2]))) {

				if (dados[1].equals("F")) {
					clientes.get(dados[0]).adicionarNaLista(series.get(Integer.valueOf(dados[2]))); // Adiciona série à
																									// lista
				} else if (dados[1].equals("A")) {
					clientes.get(dados[0]).registrarAudiencia(series.get(Integer.valueOf(dados[2]))); // Registra +1
																										// ponto de
																										// audiência na
																										// série
				}
			}
		}

		filereader.close();
	}

	public Lista<Serie> filtrarPorGenero(String genero) throws NullPointerException {
		if (clienteAtual == null) {
			throw new NullPointerException();
		}

		return clienteAtual.filtrarPorGenero(genero);
	}

	public Lista<Serie> filtrarPorIdioma(String idioma) throws NullPointerException {
		if (clienteAtual == null) {
			throw new NullPointerException();
		}

		return clienteAtual.filtrarPorIdioma(idioma);
	}

	public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios) throws NullPointerException {
		if (clienteAtual == null) {
			throw new NullPointerException();
		}

		return clienteAtual.filtrarPorQtdEpisodios(quantEpisodios);
	}

	public Cliente cadastraCliente() {

		Scanner sc = new Scanner(System.in);

		String nome;
		String id;
		String senha;

		System.out.println("Por favor digite seu nome de usuário: ");
		nome = sc.nextLine();

		System.out.println("Por favor digite seu id: ");
		id = sc.nextLine();

		System.out.println("Por favor digite sua senha: ");
		senha = sc.nextLine();

		Cliente c = new Cliente(nome, id, senha);

		sc.close();

		return c;
	}

	
	/**
	 * Lê os dados da nova serie registrada e cria um objeto com esses dados
	 * @return Nova série com os dados informados
	 */
	public Serie cadastrarSerie() {
		Scanner read = new Scanner(System.in);

    	 String nome;
    	 String genero;
    	 String idioma;
    	 String lancamento;
		 int quantidadeEpisodios;

		 System.out.println("Insira o nome da série: ");
		 nome = read.nextLine();
		

		 System.out.println("Insira o genero da série: ");
		 genero = read.nextLine();


		 System.out.println("Insira o idioma da série: ");
		 idioma = read.nextLine();


		 System.out.println("Insira a data de lançamento da série: ");
		 lancamento = read.nextLine();

		 System.out.println("Insira a quantidade de episodios da série: ");
		 quantidadeEpisodios = read.nextInt();

		read.close();

		 Date dataLancamento = new Date(lancamento);

		 Serie serie = new Serie(nome, genero, idioma, dataLancamento, quantidadeEpisodios);

		return serie;
	}

}
