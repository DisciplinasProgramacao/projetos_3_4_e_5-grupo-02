package app;

import business.*;
import business.exceptions.*;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;


public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        PlataformaStreaming plataforma = new PlataformaStreaming("Xam OBH", new Cliente("Lott", "Lott123", "senha"));

        System.out.println("\nOlá, " + plataforma.getClienteAtual().getNomeUsuario() + "! Bem-vindo(a) ao Xam OBH!\n");

        /* Carregamento de dados */
        try {
            plataforma.carregarSeries();
            plataforma.carregarFilmes();
            plataforma.carregarClientes();
            plataforma.carregarAudiencia();

            System.out.println("Dados carregados com sucesso!");
        } catch (FileNotFoundException e) {
            System.out.println("Erro: arquivos não encontrados");
        }

        while (!exit) {
            System.out.println("\nEscolha a operação que deseja realizar:\n");
            System.out.println("--- Gerenciamento de clientes ---");
            System.out.println("\t 1 - Fazer login com outro usuário");
            System.out.println("\t 2 - Cadastrar novo cliente");
            System.out.println("--- Outros ---");
            System.out.println("\t 99 - Fechar plataforma");


            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    fazerLogin(plataforma);
                    System.out.println("Usuário logado: " + plataforma.getClienteAtual().getNomeUsuario());
                    break;
                case 2:
                    cadastrarCliente(plataforma);
                    break;
                case 99:
                    System.out.println("Fechando plataforma...");
                    exit = true;
            }
        }

        scanner.close();

        plataforma.salvarClientes();
        plataforma.salvarFilmes();
        plataforma.salvarSeries();

    }

    public static void fazerLogin(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome de usuário: ");
        String loginUserName = scanner.nextLine();
        System.out.println("Senha: ");
        String loginPassword = scanner.nextLine();

        try {
            plataforma.login(loginUserName, loginPassword);
            System.out.println("Login realizado com sucesso!");
        } catch (LoginInvalidoException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void cadastrarCliente(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-- Cadastrando um novo cliente --");
        System.out.println("Nome de usuário: ");
        String userName = scanner.nextLine();
        System.out.println("Id: ");
        String userId = scanner.nextLine();
        System.out.println("Senha: ");
        String userPassword = scanner.nextLine();

        try {
            plataforma.adicionarCliente(userName, userId, userPassword);
            System.out.println("Cliente adicionado com sucesso!");
        } catch (ElementoJaExisteException e) {
            System.out.println(e.getMessage());
        }
    }
}
