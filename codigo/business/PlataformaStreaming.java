package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

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
	public Cliente login(String nomeUsuario, String senha) {
		for (Cliente cliente : this.clientes.values()) {
			if (cliente.getNomeUsuario() == nomeUsuario && cliente.getSenha() == senha) {
				this.clienteAtual = cliente;
				return cliente;
			}
		}
		return null;
	}

	public void carregarClientes() throws FileNotFoundException {
		File file = new File("docs/database/Espectadores.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do csv

		while (filereader.hasNextLine()) {
			String[] split = filereader.nextLine().split(";");

			Cliente novoCliente = new Cliente(split[0], split[1], split[2]);

			// dados[1] = id
			this.clientes.put(split[1], novoCliente);
		}

		this.clientes.forEach((key, value) -> System.out.println(
				this.clientes.get(key) + "\nID: " + key + " \n"));
		
		filereader.close();
	}

	/**
	 * Adiciona um novo cliente à lista de clientes. Caso o cliente a ser adicionado já esteja previamente
	 * presente na lista, a operação não é executada
	 *
	 * @param novoCliente cliente a ser adicionado
	 */
	public void adicionarCliente(Cliente novoCliente) {
		if (! this.clientes.containsKey(novoCliente.getId()) )
			this.clientes.put(novoCliente.getId(), novoCliente);
	}

	/**
	 * Lê o conteúdo do HashMap clientes e os escreve em um arquvivo .csv, sobrepondo o arquivo já existente
	 * de nome Espectadores.csv
	 * O salvamento deve ser realizado após execução do programa a fim de registrar em arquivo todas as mudanças
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

	public void registrarAudiencia(Serie serie) {
		if (clienteAtual != null)
			clienteAtual.registrarAudiencia(serie);
	}
	
	public void carregarSeries() throws FileNotFoundException {
		File file = new File("docs/database/Series.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do arquivo .csv

		while (filereader.hasNextLine()) {
			String[] dados = filereader.nextLine().split(";");

			// Gera um número aleatório, limitado pelo tamanho do vetor de gêneros/idiomas, como índice a fim de selecionar algum dos gêneros/idiomas disponíveis
			String novoGenero = Midia.GENEROS[(int) (Math.random() * (Midia.GENEROS.length))];
			String novoIdioma = Midia.IDIOMAS[(int) (Math.random() * (Midia.IDIOMAS.length))];

			// Atribui a uma data os valores de dia/mes/ano lidos no arquivo. Caso a string não apresente formato válido, é lançada uma exceção
			Date novaData = null;
			try {
				novaData = new SimpleDateFormat("dd/MM/yyyy").parse(dados[2]);
			} catch (Exception e) {
				System.out.println("Erro: Formato inválido na leitura da data de lançamento de série");
			}

			// Passa-se como parâmetros o nome conforme lido no arquivo (dados[1]), gênero e idioma gerados aleatóriamente, novaData e uma qtd aleatória de episódios.Em seguida, insere-se a nova série no hashmap
			Serie novaSerie = new Serie(dados[1], novoGenero, novoIdioma, novaData, (int) (Math.random() * 100));

			Serie antiga = this.series.put(Integer.valueOf(dados[0]), novaSerie);
		}

		// Imprimir lista
		this.series.forEach((key, value) -> System.out.println("\n" + this.series.get(key)));

		filereader.close();
	}

	public void carregarFilmes() throws FileNotFoundException {
		File file = new File("docs/database/Filmes.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do arquivo .csv

		while (filereader.hasNextLine()){
			String[] dados = filereader.nextLine().split(";");

			// Gera um número aleatório, limitado pelo tamanho do vetor de gêneros/idiomas, como índice a fim de selecionar algum dos gêneros/idiomas disponíveis
			String novoGenero = Midia.GENEROS[(int) (Math.random() * (Midia.GENEROS.length))];
			String novoIdioma = Midia.IDIOMAS[(int) (Math.random() * (Midia.IDIOMAS.length))];

			// Atribui a uma data os valores de dia/mes/ano lidos no arquivo. Caso a string não apresente formato válido, é lançada uma exceção
			Date novaData = null;
			try {
				novaData = new SimpleDateFormat("dd/MM/yyyy").parse(dados[2]);
			} catch (Exception e) {
				System.out.println("Erro: Formato inválido na leitura da data de lançamento de série");
			}

			// Passa-se como parâmetros o nome conforme lido no arquivo (dados[1]), gênero, idioma, data de lançamento e duracao (dados[3]) em segundos. Em seguida, insere-se o novo filme no hashmap
			Filme novoFilme = new Filme(dados[1], novoGenero, novoIdioma, novaData, Integer.parseInt(dados[3]) * 60);
			this.filmes.put(Integer.valueOf(dados[0]), novoFilme);
		}

		// Imprimir lista
		this.filmes.forEach((key, value) -> System.out.println("\n" + this.filmes.get(key)));

		filereader.close();
	}

	public void carregarAudiencia() throws FileNotFoundException {
		File file = new File("docs/database/Audiencia.csv");
		Scanner filereader = new Scanner(file);

		while (filereader.hasNextLine()){
			String[] dados = filereader.nextLine().split(";");

			if (clientes.containsKey(dados[0]) && series.containsKey(dados[2])){

				if (dados[1].equals("F")){
					clientes.get(dados[0]).adicionarNaLista(series.get(dados[2])); // Adiciona série à lista
				} else if (dados[1].equals("A")) {
					clientes.get(dados[0]).registrarAudiencia(series.get(dados[2])); // Registra +1 ponto de audiência na série
				}
			}
		}

		filereader.close();
	}
	
	public Lista<Serie> filtrarPorGenero(String genero) {
		return clienteAtual != null ? clienteAtual.filtrarPorGenero(genero) : null;
	}

	public Lista<Serie> filtrarPorIdioma(String idioma) {
		return clienteAtual != null ? clienteAtual.filtrarPorIdioma(idioma) : null;
	}

	public Lista<Serie> filtrarPorQtdEpisodios(int quantEpisodios) {
		return clienteAtual != null ? clienteAtual.filtrarPorQtdEpisodios(quantEpisodios) : null;
	}
}
