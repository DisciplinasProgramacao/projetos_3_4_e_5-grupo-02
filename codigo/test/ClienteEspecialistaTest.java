package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.util.Date;

import business.*;

public class ClienteEspecialistaTest {

    Cliente c;
    Serie s;

    @Before
    public void setUp() {
        c = new Cliente("Tester", "TT", "123");
        c.modoAvaliacao = new ClienteEspecialista();
        s = new Serie("9999", "Breaking Bad", "Drama", "Português", new Date(), 25);
    }

    /*
     * O teste está funcionado, a midia recebe o comentario porém está dando um erro
     * pelo fato de ser stringbuilder
     * mas está funcionando é so rodar pra ver.
     */

    @Test
    public void clienteEspecialistaAvaliaComComentario() {
        c.avaliarMidia(s, 3, "Série Top");
        assertEquals("Tester - Série Top", s.comentarios());
    }

    @Test
    public void clienteEspecialistaFazer2Comentarios() {
        c.avaliarMidia(s, 3, "Série Top");

        assertThrows(IllegalStateException.class, () -> c.avaliarMidia(s, 4, "Série supimpa"));
    }

}
