package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Cliente;
import business.Filme;
import business.PlataformaStreaming;
import business.Serie;
import business.exceptions.ElementoJaExisteException;
import business.exceptions.LoginInvalidoException;

class PlataformaStreamingTest {

	public static PlataformaStreaming plataforma;
	public static Cliente cliente;
	public static Serie serie;
	public static Serie serie1;
	public static Serie serie2;
	public static Serie serie3;
	public static Filme filme;
	
	@BeforeEach
	void iniciaTeste() {
		plataforma = new PlataformaStreaming("Xam OBH", cliente);
		try {
			plataforma.adicionarCliente("Joao", "Joao123", "senha");
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ElementoJaExisteException e) {
			System.out.println(e.getMessage());
		}
		serie = new Serie("11111", "Infinity Train", "Animação", "Inglês", new Date(), 10);
		serie1 = new Serie("22222", "One Piece", "Animação", "Português", new Date(), 1060);
		serie2 = new Serie("33333", "The Last of Us", "Suspense", "Português", new Date(), 9);
		serie3 = new Serie("44444", "Brooklyn Nine-Nine", "Comédia", "Português", new Date(), 10);
		filme = new Filme("55555", "Gato de Botas 2", "Animação", "Portugûes", new Date(), 6000);
	}
	
	@Test
	void testLoginCliente() throws LoginInvalidoException {
		plataforma.login("Joao", "Joao123");
		assertEquals ("Usuário: Joao\n" + "Senha: senha", plataforma.getClienteAtual().toString());
	}
	
	@Test
	void testLoginInvalido() throws LoginInvalidoException {
		Exception exception = assertThrows(LoginInvalidoException.class, () -> {
	        plataforma.login("Maria", "123");
	    });

	    String expectedMessage = "Nao foi encontrado nenhum usuario com as credenciais: Maria | 123";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testAdicionarClienteJaExistente() throws NullPointerException, ElementoJaExisteException {
		Exception exception = assertThrows(ElementoJaExisteException.class, () -> {
			plataforma.adicionarCliente("Joao", "Joao123", "senha");
	    });
		
		String expectedMessage = "O objeto 'Joao' recebido ja existe na lista 'clientes'";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testAdicionarSerie() throws NullPointerException, ElementoJaExisteException {
		plataforma.adicionarSerie(1, serie);
		assertEquals("{1=Nome: Infinity Train\n"
				+ "Gênero: Animação\n"
				+ "Idioma: Inglês\n"
				+ "Qtd de eps.: 10}", plataforma.getSeries().toString());
	} 
	
	@Test
	void testAdicionarFilme() throws NullPointerException, ElementoJaExisteException {
		plataforma.adicionarFilme(1, filme);
		assertEquals("{1=Nome: Gato de Botas 2\n"
				+ "Gênero: Animação\n"
				+ "Idioma: Português\n"
				+ "Duração: 6000 segundos}", plataforma.getFilmes().toString());
	} 
	
	@Test
	void testAdicionarSerieJaExistente() throws NullPointerException, ElementoJaExisteException {
		Exception exception = assertThrows(ElementoJaExisteException.class, () -> {
			plataforma.adicionarSerie(1, serie);
			plataforma.adicionarSerie(1, serie);
	    });
		
		String expectedMessage = "O objeto '1' recebido ja existe na lista 'series'";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
	@Test
	void testAdicionarFilmeJaExistente() throws NullPointerException, ElementoJaExisteException {
		Exception exception = assertThrows(ElementoJaExisteException.class, () -> {
			plataforma.adicionarFilme(1, filme);
			plataforma.adicionarFilme(1, filme);
	    });
		
		String expectedMessage = "O objeto '1' recebido ja existe na lista 'filmes'";
	    String actualMessage = exception.getMessage();

	    assertTrue(actualMessage.contains(expectedMessage));
	}
	
}
