package test;

import business.*;
import business.entidades.Cliente;
import business.entidades.fracas.Serie;
import utils.Lista;

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
    Cliente c, c2;
    Lista<Serie> l;

    @BeforeEach
    public void setUp() {
        s = new Serie("9999", "Breaking Bad", "Drama", "Português", new Date(), 25);
        c = new Cliente("xX_TesterCraft_Xx", "10", "teste123");
        c2 = new Cliente("Testerrr", "12", "teste321");
        l = new Lista<>(); // Cria uma lista secundária de Séries

        c.adicionarNaLista(s); // Adiciona a série na lista de series do cliente

    }

    @Test
    public void filtrarPorGeneroTest() {
        c.adicionarNaLista(s);
        l = c.filtrarPorGenero("Drama"); // A lista de séries recebe a serie filtrada pelo genero

        assertEquals(1, l.size());
    }

    @Test
    public void filtrarPorIdiomaTest() {
        c.adicionarNaLista(s);
        l = c.filtrarPorIdioma("Português"); // A lista de séries recebe a serie filtrada pelo genero

        assertEquals(1, l.size());
    }

    @Test
    public void adicionarNaLista() {
        assertEquals(1, c.tamanhoListaParaVer());
    }

    @Test
    public void removerItemDaLista() {
        c.retirarDaLista("Breaking Bad");

        assertEquals(0, c.tamanhoListaParaVer());
    }

    @Test
    public void adicionarSerieRepetida() {
        c.adicionarNaLista(s);
        c.adicionarNaLista(s);

        assertEquals(1, c.tamanhoListaParaVer());
    }

    // @SuppressWarnings("deprecation")
    // @Test
    // public void avaliarMidiaTest() {
    // c.avaliarMidia(s, 3);
    // c2.avaliarMidia(s, 5);

    // assertEquals(4.0,s.getMediaNota());

    // }

}