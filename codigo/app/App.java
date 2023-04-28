package app;

import business.Cliente;
import business.Serie;
import business.Filme;
import business.PlataformaStreaming;

import java.io.FileNotFoundException;
import java.util.Date;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        PlataformaStreaming plataforma = new PlataformaStreaming("Xam OBH", new Cliente("Lott", "Lott123"));

        plataforma.carregarSeries();
        plataforma.carregarFilmes();
        plataforma.carregarClientes();
        plataforma.carregarAudiencia();
    }
}
