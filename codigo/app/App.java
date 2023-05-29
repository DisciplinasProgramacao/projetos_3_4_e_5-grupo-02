package app;

import business.Cliente;
import business.ClienteEspecialista;
import business.Filme;
import business.PlataformaStreaming;
import business.exceptions.*;
import java.util.Date;

public class App {
    public static void main(String[] args) throws Exception {
        PlataformaStreaming plataforma = new PlataformaStreaming("Xam OBH", new Cliente("Lott", "Lott123", "senha"));

        /* Demonstração de carregamento de dados */
        plataforma.carregarSeries();
        plataforma.carregarFilmes();
        plataforma.carregarClientes();
        plataforma.carregarAudiencia();

        /* Demonstração de adição de dados */
        Cliente novoCliente = new Cliente("Cirilo", "maria_joaquina_stan", "cirilo12");

        try {
            plataforma.adicionarCliente(novoCliente);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ElementoJaExisteException e) {
            System.out.println(e.getMessage());
        }

        /* Demonstração de salvamento de dados em arquivo .csv */
        plataforma.salvarClientes();

        /* Demonstração de avaliação para clientes regulares */
        Cliente avaliadorRegular = new Cliente("Clientinho", "client12", "cli");
        Filme filme = new Filme("Nome", "Genero", "Idioma", new Date(), 120);

        avaliadorRegular.avaliarMidia(filme,3);

        /* Demonstração de avaliação para cliente especialista*/
        Cliente clienteEsp = new Cliente("Givanildo", "hulk13", "13");

        clienteEsp.modoAvaliacao = new ClienteEspecialista();
        clienteEsp.avaliarMidia(filme, "Muito ruim, nota 9");

        System.out.println(filme);


        // javadoc -d doc -encoding "utf-8" -subpackages business app business.exceptions
    }
}
