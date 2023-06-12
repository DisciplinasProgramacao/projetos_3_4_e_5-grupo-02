package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import business.Avaliacao;
import business.Cliente;
import business.Filme;

class FilmeTest {

	public static Filme f;
	public static Cliente c;
	public static Cliente c1;
	
	@BeforeEach
	void iniciaTeste() {
		c = new Cliente("Joao", "Joao123", "senha");
		c1 = new Cliente("Jose", "Jose123", "password");
		f = new Filme("11111", "Gato de Botas 2", "Animação", "Portugûes", new Date(), 6000);
	}
	
	@Test
	void testCriarAvaliacao() {
		String s = "Excelente filme";
		int nota = 5;
		f.criarAvaliacao(c, nota, s);
		
		assertEquals(5, f.getQtdAva().get(0).getNota());
	}
	
	@Test 
	void testMediaAvaliacoes() {
		String s = "Excelente filme";
		int nota = 5;
		f.criarAvaliacao(c, nota, s);
		
		String s1 = "Filme bom";
		int nota1 = 3;
		f.criarAvaliacao(c1, nota1, s1);
			
		assertEquals(4, f.mediaAvaliacoes());
	}

}
