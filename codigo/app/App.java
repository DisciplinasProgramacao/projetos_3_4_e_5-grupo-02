package app;

import business.Cliente;
import business.PlataformaStreaming;
import business.Serie;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException{
        PlataformaStreaming plataforma = new PlataformaStreaming("CatFlix", new Cliente("Lott", "Lott123"));

        plataforma.carregarSeries();
    }
}
