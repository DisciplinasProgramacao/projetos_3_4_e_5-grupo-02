package app;

import business.Cliente;
import business.PlataformaStreaming;
import business.exceptions.ElementoJaExisteException;

import java.io.FileNotFoundException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        PlataformaStreaming plataforma = new PlataformaStreaming("Xam OBH", new Cliente("Lott", "Lott123", "senha"));

        plataforma.carregarSeries();
        plataforma.carregarFilmes();
        plataforma.carregarClientes();
        plataforma.carregarAudiencia();

        Cliente c = new Cliente("jorgin", "jorg12", "da12");
        
		try {
			plataforma.adicionarCliente(c);
		} catch (NullPointerException e) {
			e.printStackTrace();
		} catch (ElementoJaExisteException e) {
			System.out.println(e.getMessage());
		}

        plataforma.salvarClientes();
    }
}
