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
        PlataformaStreaming plataform = new PlataformaStreaming("Xam OBH", new Cliente("John Doe", "Jd123", "psswd789"));
        int option = 0;

        /*Carregar dados */
        try{
            plataform.carregarAudiencia();
            plataform.carregarClientes();
            plataform.carregarFilmes();
            plataform.carregarSeries();

            System.out.printf("Olá, " + plataform.getClienteAtual().getNomeUsuario() + "! Bem-vindo(a) a demonstração do Xam OBH!\n");
            
        } catch(FileNotFoundException e) {
            System.out.printf("Erro ao entrar na plataforma");
            read.close();
        }

        while(option != 99) {
            System.out.printf("Usuário: %s\n", plataform.getClienteAtual().getNomeUsuario());
            System.out.printf("Menu: \n");
            System.out.printf("""
                                Escolha uma operação:
                                --- Gerenciar Clientes ---
                                1. Fazer login com outro usuário.
                                2. Cadastrar novo cliente.
                                --- Gerenciar Mídias ---
                                3. Cadastrar nova série.
                                4. Assistir série.
                                --- Outros ---
                                98. Demonstrar outras funções.
                                99. Salvar e sair.
                                """);
            System.out.printf("Opção: ");
            option = read.nextInt();
        }
    }     
}
