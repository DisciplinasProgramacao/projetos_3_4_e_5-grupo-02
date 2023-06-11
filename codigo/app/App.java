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

        } catch(FileNotFoundException e) {
            System.out.printf("Erro ao entrar na plataforma");
            read.close();
        }

        while(option != 99) {
            System.out.printf("------- DEMO -------\n");
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

            switch(option) {
                case 1: 
                    System.out.printf("Opção 01 selecionada\n");
                    break;
                case 2:
                    System.out.printf("Opção 02 selecionada\n");
                    break;
                case 3:
                    System.out.printf("Opção 03 selecionada\n");
                    break;
                case 4:
                    System.out.printf("Opção 04 selecionada\n");
                    break;
                case 98: 
                    System.out.printf("Opção 98 selecionada\n");
                    break;
                case 99:
                    System.out.printf("Opção 99 selecionada\n");
                    break;
                default:
                    System.out.printf("A opção %d é inválida\n", option);

            }
        }

        read.close();
    }     
}
