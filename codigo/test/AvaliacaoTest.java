package test;

import org.junit.Test;

import business.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;

public class AvaliacaoTest {

    Avaliacao a;
    Serie s;
    Cliente c;

    @BeforeEach
    public void setUp() {
        s = new Serie("9999", "Breaking Bad", "Drama", "Português", new Date(), 25);
        c = new Cliente("xX_TesterCraft_Xx", "10", "teste123");
    }

    @Test
    public void testarAvaliacao() {
        Avaliacao a = new Avaliacao(c, 4, "Comentario teste");
        assertEquals(4, a.getNota());
    }

    @Test
    public void testarAvaliarMaisQue5eMenorQue1() {
        Avaliacao a = new Avaliacao(c, 3, "Comentario teste");
        assertThrows(IllegalArgumentException.class, () -> a.setNota(6)); // Testa nota maior que 6
        assertThrows(IllegalArgumentException.class, () -> a.setNota(-1)); // Testa notar menor que 1
    }
}
