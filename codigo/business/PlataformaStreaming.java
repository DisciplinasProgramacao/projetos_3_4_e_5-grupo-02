package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

public class PlataformaStreaming {

	private String nome;
	private Cliente clienteAtual;
	private HashMap<Integer, Serie> series;
	private HashMap<Integer, Filme> filmes;
	private HashMap<String, Cliente> clientes;

	public PlataformaStreaming(String nome, Cliente clienteAtual) {
		this.nome = nome;
		this.clienteAtual = clienteAtual;
		this.series = new HashMap<Integer, Serie>();
		this.filmes = new HashMap<Integer, Filme>();
		this.clientes = new HashMap<String, Cliente>();
	}

	public void carregarClientes() throws FileNotFoundException {
		File file = new File("docs/database/Espectadores.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do csv

		while (filereader.hasNextLine()) {
			String[] temp = filereader.nextLine().split(" ");

			String[] dados = temp[3].split(";");
			
			// dados[0] = nomeUsuario, dados[2] = senha
			Cliente novoCliente = new Cliente(dados[0], dados[2]);

			// dados[1] = id
			this.clientes.put(dados[1], novoCliente);
		}

		this.clientes.forEach((key, value) -> System.out.println("\n" + this.clientes.get(key)));
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
			this.series.put(Integer.valueOf(dados[0]), novaSerie);
		}

		// Imprimir lista
		this.series.forEach((key, value) -> System.out.println("\n" + this.series.get(key)));
		// DÚVIDA: ESTÁ PRINTANDO APENAS 111 SÉRIES
	}

	public void carregarFilmes() throws FileNotFoundException{
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


			// Imprimir lista
			this.filmes.forEach((key, value) -> System.out.println("\n" + this.filmes.get(key)));
		}
	}

	public Cliente login(String nomeUsuario, String senha) {
		for (Cliente cliente : this.clientes.values())
		{
			if (cliente.getNomeUsuario() == nomeUsuario && cliente.getSenha() == senha)
			{
				adicionarCliente(cliente);
				return cliente;
			}
		}
		return null;
	}

	public void adicionarCliente(Cliente cliente) {
		this.clienteAtual = cliente;
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

	public void registrarAudiencia(Serie serie) {
		if (clienteAtual != null)
			clienteAtual.registrarAudiencia(serie);
	}
}
