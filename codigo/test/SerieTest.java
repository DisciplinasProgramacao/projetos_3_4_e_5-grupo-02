package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Avaliacao;
import business.Cliente;
import business.Serie;

class SerieTest {

	public static Serie s;
	public static Cliente c;
	public static Cliente c1;
	
	@BeforeEach
	void iniciaTeste() {
		c = new Cliente("Joao", "Joao123", "senha");
		c1 = new Cliente("Jose", "Jose123", "password");
		s = new Serie("9999", "Wandinha", "Comédia", "Português", new Date(), 6000);
	}
	
	@Test
	void testCriarAvaliacao() {
		String comentario = "Serie legal, bem sombria";
		int nota = 4;
		s.criarAvaliacao(c, nota, comentario);
		
		assertEquals(4, s.getAvaliacoes().get(0).getNota());
	}
	
	@Test 
	void testMediaAvaliacoes() {
		String comentario = "Serie legal, bem sombria";
		int nota = 4;
		s.criarAvaliacao(c, nota, comentario);
		
		String comentario1 = "Eita como é ruim";
		int nota1 = 1;
		s.criarAvaliacao(c1, nota1, comentario1);
			
		assertEquals(2.5, s.mediaAvaliacoes());
	}

}
