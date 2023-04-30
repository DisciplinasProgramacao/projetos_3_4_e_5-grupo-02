package app;

import business.Cliente;
import business.PlataformaStreaming;
import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        PlataformaStreaming plataforma = new PlataformaStreaming("Xam OBH", new Cliente("Lott", "Lott123", "senha"));

        plataforma.carregarSeries();
        plataforma.carregarFilmes();
        plataforma.carregarClientes();
        plataforma.carregarAudiencia();

        Cliente c = new Cliente("jorgin", "jorg12", "da12");
        plataforma.adicionarCliente(c);

        plataforma.salvarClientes();
    }
}
