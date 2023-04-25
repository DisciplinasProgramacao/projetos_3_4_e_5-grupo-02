package test;

import business.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class ClienteTest {

    @Test
    public void demo() {
        assertTrue(1 + 1 == 2);
    }

    Serie s;
    Cliente c;

    @BeforeEach
    public void setUp() {
    s  = new Serie("Breaking Bad", "Drama", "Portuguese", new Date(), 25);
    c  = new Cliente("xX_TesterCraft_Xx", "teste123");
    }

    @Test
    public void filtrarPorGeneroTest() {
        Lista<Serie> l = new Lista<>();

        c.adicionarNaLista(s); // Adiciona a série na lista de series do cliente

        l = c.filtrarPorGenero("Drama"); // A lista de séries recebe a serie filtrada pelo genero

        Serie vetorSeries[] = new Serie[10]; // Cria um vetor de séries
        l.allElements(vetorSeries); // Adiciona no vetorSeries todas as séries contidas na lista 'l'

        assertEquals("Breaking Bad", vetorSeries[0]);
    }

    @Test
    public void filtrarPorEpisodiosTest() {
        Lista<Serie> l = new Lista<>();

        c.adicionarNaLista(s); // Adiciona a série na lista de series do cliente

        l = c.filtrarPorQtdEpisodios(25); // A lista de séries recebe a serie filtrada por qtd de episodios

        Serie vetorSeries[] = new Serie[10]; // Cria um vetor de séries
        l.allElements(vetorSeries); // Adiciona no vetorSeries todas as séries contidas na lista 'l'

        assertEquals("Breaking Bad", vetorSeries[0]);
    }

    @Test
    public void filtrarPorIdiomaTest() {
        Lista<Serie> l = new Lista<>();

        c.adicionarNaLista(s); // Adiciona a série na lista de series do cliente

        l = c.filtrarPorIdioma("Portuguese"); // A lista de séries recebe a serie filtrada pelo idioma

        Serie vetorSeries[] = new Serie[10]; // Cria um vetor de séries
        l.allElements(vetorSeries); // Adiciona no vetorSeries todas as séries contidas na lista 'l'

        assertEquals("Breaking Bad", vetorSeries[0]);
    }

}
