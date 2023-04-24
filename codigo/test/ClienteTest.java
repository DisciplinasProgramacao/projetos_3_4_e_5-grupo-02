package test;

import business.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {

    @Test
    public void demo() {
        assertTrue(1 + 1 == 2);
    }

    // @BeforeEach
    // public void setUp() {
    // Serie s = new Serie("Breaking Bad", "Drama", "Portuguese", 25);
    // Cliente c = new Cliente("xX_TesterCraft_Xx", "teste123");
    // }

    @Test
    public void test() {
        Lista<Serie> l = new Lista<>();

        Serie s = new Serie("Breaking Bad", "Drama", "Portuguese", 25);
        Cliente c = new Cliente("xX_TesterCraft_Xx", "teste123");

        c.adicionarNaLista(s); // Adiciona a série na lista de series do cliente

        l = c.filtrarPorGenero("Drama"); // A lista de séries recebe a serie filtrada pelo genero

        Serie vetorSeries[] = new Serie[10]; // Cria um vetor de séries
        l.allElements(vetorSeries); // Adiciona no vetorSeries todas as séries contidas na lista 'l'

        assertEquals("Breaking Bad", vetorSeries[0]);
    }

}
