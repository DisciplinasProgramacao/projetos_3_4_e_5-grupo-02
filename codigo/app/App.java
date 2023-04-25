package app;

import business.Cliente;
import business.Midia;
import business.PlataformaStreaming;
import java.io.FileNotFoundException;
import java.text.ParseException;

public class App {
    public static void main(String[] args) throws FileNotFoundException {
        PlataformaStreaming plataforma = new PlataformaStreaming("Flin TeX", new Cliente("Lott", "Lott123"));

//        plataforma.carregarSeries();    // ok
//        plataforma.carregarFilmes();    // ok
        plataforma.carregarClientes();  // verificar
//        plataforma.carregarAudiencia(); // erro em função de carregarClientes()
    }
}
