package app;

import business.Cliente;
import business.Filme;
import business.PlataformaStreaming;
import business.exceptions.ElementoJaExisteException;

import java.io.FileNotFoundException;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
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
        
        Cliente cliente = new Cliente("Clientinho", "client12", "cli");
        Filme filme = new Filme("Nome", "Genero", "Idioma", new Date(), 120);

        cliente.avaliarMidia(filme,3);

        System.out.println(filme.mediaAvaliacoes());

        // javadoc -d doc -encoding "utf-8" -subpackages business app business.exceptions
    }
}
