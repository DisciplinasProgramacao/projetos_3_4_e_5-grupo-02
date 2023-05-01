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
    Lista<Serie> l;

    @BeforeEach
    public void setUp() {
        s = new Serie("Breaking Bad", "Drama", "Portuguese", new Date(), 25);
        c = new Cliente("xX_TesterCraft_Xx", "10", "teste123");

        Lista<Serie> l = new Lista<>(); // Cria uma lista secundária de Séries

        c.adicionarNaLista(s); // Adiciona a série na lista de series do cliente

    }

    @Test
    public void filtrarPorGeneroTest() {
        Serie s1 = new Serie("Breaking Good", "Suspense", "Portuguese", new Date(), 25);
        c.adicionarNaLista(s1);
        l = c.filtrarPorGenero("Drama"); // A lista de séries recebe a serie filtrada pelo genero

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
    public void adicionarSerieRepetida(){
        c.adicionarNaLista(s);
        c.adicionarNaLista(s);

        assertEquals(1,c.tamanhoListaParaVer());
    }

}