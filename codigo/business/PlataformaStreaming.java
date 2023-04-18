package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class PlataformaStreaming {

	private String nome;
	private Cliente clienteAtual;
	private HashMap<Integer, Serie> series;
	private HashMap<String, Cliente> clientes;

	public PlataformaStreaming(String nome, Cliente clienteAtual) {
		this.nome = nome;
		this.clienteAtual = clienteAtual;
		this.series = new HashMap<Integer, Serie>();
		this.clientes = new HashMap<String, Cliente>();
	}
	
	public void carregarClientes() throws FileNotFoundException {
		File file = new File("docs/database/Espectadores.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do csv

		while (filereader.hasNextLine()) {
			String[] temp = filereader.nextLine().split(" ");
			
			String[] dados = temp[3].split(";");

			Cliente novoCliente = new Cliente(dados[0], dados[2]);

			this.clientes.put(dados[1], novoCliente);
		}

		this.clientes.forEach((key, value) -> System.out.println("\n" + this.clientes.get(key)));
	}

	public void carregarSeries() throws FileNotFoundException {
		File file = new File("docs/database/Series.csv");
		Scanner filereader = new Scanner(file);

		filereader.nextLine(); // Artifício para ignorar primeira linha do csv

		while (filereader.hasNextLine()) {
			String[] dados = filereader.nextLine().split(";");

			Serie novaSerie = new Serie(dados[1], "Ação", "Inglês", 100); // ?? DÚVIDA

			this.series.put(Integer.valueOf(dados[0]), novaSerie);
		}

		// Imprimir lista
		this.series.forEach((key, value) -> System.out.println("\n" + this.series.get(key)));
	}

	public Cliente login(String nomeUsuario, String senha) {

	}

	public void adicionarCliente(Cliente cliente) {
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
