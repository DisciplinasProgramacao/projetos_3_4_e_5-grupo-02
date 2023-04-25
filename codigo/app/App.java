package app;

import business.Cliente;
import business.PlataformaStreaming;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException{
        PlataformaStreaming plataforma = new PlataformaStreaming("CatFlix", new Cliente("Lott", "Lott123"));

        plataforma.carregarSeries();
    }
}
