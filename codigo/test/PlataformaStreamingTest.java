package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;

public class PlataformaStreamingTest {

    @Test
    public void carregarClientesTest() {
        
        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("MinhaPlataforma", new Cliente("usuario", "senha"));
        try {
            plataformaStreaming.carregarClientes();
        } catch (FileNotFoundException e) {
            Assertions.fail("Arquivo de clientes não encontrado");
        }
        
        Assertions.assertFalse(plataformaStreaming.getClientes().isEmpty(), "Mapa de clientes vazio");
    }

    @Test
    public void carregarSeriesTest() {
        
        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("MinhaPlataforma", new Cliente("usuario", "senha"));
        try {
            plataformaStreaming.carregarSeries();
        } catch (FileNotFoundException e) {
            Assertions.fail("Arquivo de séries não encontrado");
        }
        
        Assertions.assertFalse(plataformaStreaming.getSeries().isEmpty(), "Mapa de séries vazio");
    }

//    @Test
//    public void carregarFilmesTest() {
//        
//        PlataformaStreaming plataformaStreaming = new PlataformaStreaming("MinhaPlataforma", new Cliente("usuario", "senha"));
//        try {
//            plataformaStreaming.carregarFilmes();
//        } catch (FileNotFoundException e) {
//            Assertions.fail("Arquivo de filmes não encontrado");
//        }
//        
//        Assertions.assertFalse(plataformaStreaming.getFilmes().isEmpty(), "Mapa de filmes vazio");
//    }

}







