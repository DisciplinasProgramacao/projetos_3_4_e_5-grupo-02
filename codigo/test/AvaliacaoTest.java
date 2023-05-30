package test;

import org.junit.Test;

import business.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;

public class AvaliacaoTest {

    Serie s;
    Cliente c;

    @BeforeEach
    public void setUp() {
        s = new Serie("Breaking Bad", "Drama", "Português", new Date(), 25);
        c = new Cliente("xX_TesterCraft_Xx", "10", "teste123");
    }

    @Test
    public void testarAvaliacao() {
        Avaliacao a = new Avaliacao(c, 4);
    }

}
