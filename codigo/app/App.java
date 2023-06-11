package app;

import business.*;
import business.exceptions.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        /*Variaveis */
        Scanner read = new Scanner(System.in);
        PlataformaStreaming plataforma = new PlataformaStreaming("Xam OBH", new Cliente("John Doe", "Jd123", "psswd789"));
        int op = 0;

        /*Carregar dados */
        try{
            plataforma.carregarAudiencia();
            plataforma.carregarClientes();
            plataforma.carregarFilmes();
            plataforma.carregarSeries();

            System.out.println("Olá, " + plataforma.getClienteAtual().getNomeUsuario() + "! Bem-vindo(a) a Xam OBH!\n");
            
        } catch(FileNotFoundException e) {
            System.out.println("Erro ao entrar na plataforma");
        } finally {
            read.close();
        }
    }     
}
