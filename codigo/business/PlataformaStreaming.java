package business;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class PlataformaStreaming {

    private String nome;
    private Cliente clienteAtual;
    private HashMap<Integer, Serie> series;
    private HashMap<Integer, Cliente> clientes;

    public PlataformaStreaming(String nome, Cliente clienteAtual) {
        this.nome = nome;
        this.clienteAtual = clienteAtual;
        this.series = new HashMap<Integer, Serie>();
        this.clientes = new HashMap<Integer, Cliente>();
    }

    public void carregarSeries() throws FileNotFoundException {
        File file = new File("docs/database/Series.csv");
        Scanner filereader = new Scanner(file);

        filereader.nextLine();  // Artifício para ignorar primeira linha do csv

        while (filereader.hasNextLine()){
            String[] dados = filereader.nextLine().split(";");

            Serie novaSerie = new Serie(dados[1], "Ação", "Inglês", 100);   // ?? DÚVIDA

            this.series.put(Integer.valueOf(dados[0]), novaSerie);
        }

        // Imprimir lista
        this.series.forEach(
                (key, value) -> System.out.println("\n" + this.series.get(key))
        );
    }
}
