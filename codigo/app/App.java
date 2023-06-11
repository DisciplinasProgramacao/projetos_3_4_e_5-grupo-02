package app;

import business.*;
import business.exceptions.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class App {
    /*System.in implementa a interface Closable. Ver javadoc java.util.scanner -> Scanner.close*/
    public static Scanner read = new Scanner(System.in); 

    /*Método estatico para fazer login */
    public static void fazerLogin(PlataformaStreaming plat) {
        String userName, password;

        System.out.printf("---------- Fazer Login ----------\n");
        
        System.out.printf("Insira o ID do usuário: ");
        userName = read.nextLine();

        System.out.printf("Insira a SENHA do usuário: ");
        password = read.nextLine();

        try{
            plat.login(userName, password);
        } catch(LoginInvalidoException e) {
            System.out.printf(e.getMessage());
        } 
    }

    /*Método estatico para cadastrar cliente */
    public static void cadastrarCliente(PlataformaStreaming plat) {
        String userName, userId, userPassword;

        System.out.printf("---------- Cadastrar Cliente ----------\n");
        
        System.out.printf("Nome do usuário: ");
        userName = read.nextLine();

        System.out.printf("ID do usuário: ");
        userId = read.nextLine();

        System.out.printf("Senha do usuário: ");
        userPassword = read.nextLine();

        try{
            plat.adicionarCliente(userName, userId, userPassword);
            System.out.printf("Usuário cadastrado com sucesso.");
        } catch(ElementoJaExisteException e) {
            System.out.printf(e.getMessage());
        }
    }
    public static void main(String[] args) {
        /*Variaveis */
        PlataformaStreaming plataform = new PlataformaStreaming("Xam OBH", new Cliente("John Doe", "Jd123", "psswd456"));
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
            /*Criar menu */
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
            read.nextLine(); /*Lê o \n que o nextInt não lê */

            //Escolher opção
            switch(option) {
                case 1: 
                    fazerLogin(plataform);
                    break;
                case 2:
                    cadastrarCliente(plataform);
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
