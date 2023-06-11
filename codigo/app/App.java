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
            System.out.printf("Usuário cadastrado com sucesso.\n");
        } catch(ElementoJaExisteException e) {
            System.out.printf(e.getMessage());
        }
    }
    
    /*Método estatico para cadastrar nova midia */
    public static void cadastrarMidia(PlataformaStreaming plat) {
        String midiaName, midiaGenre, midiaIdiom, midiaRelease, midiaType;
        int midiaId;

        System.out.printf("---------- Cadastrar Midia ----------\n");

        System.out.printf("Nome: ");
        midiaName = read.nextLine();

        System.out.printf("ID: ");
        midiaId = read.nextInt();
        read.nextLine();

        System.out.printf("Gêneros disponíveis: Comédia, Ação, Terror, Drama, Romance, Aventura, Animação, Suspense\n");
        System.out.printf("Gênero: ");
        midiaGenre = read.nextLine();

        System.out.printf("Idiomos disponíveis: Português, Inglês, Esperanto, Romeno\n");
        System.out.printf("Idioma: ");
        midiaIdiom = read.nextLine();

        System.out.printf("Data de lançamento(aaaa-mm-dd): ");
        midiaRelease = read.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date midiaReleaseDate = null;

        try{
            midiaReleaseDate = dateFormat.parse(midiaRelease);
        } catch(ParseException e) {
            System.out.printf("Formato de data invalido\n");
        }

        System.out.printf("""
                            Selecione o tipo de mídia: 
                            a. Série.
                            b. Filme.
                            """);
        
        midiaType = read.nextLine();

        switch(midiaType) {
            case "a" -> {
                System.out.printf("Quantidade de episodios: ");
                int serieQtdEp = read.nextInt();
                read.nextLine();

                try{
                    plat.adicionarSerie(midiaId, new Serie(midiaName, midiaGenre, midiaIdiom, midiaReleaseDate, serieQtdEp));
                    System.out.printf("Série cadastrada com sucesso!\n");
                }catch(ElementoJaExisteException | NullPointerException e){
                    System.out.println(e.getMessage());
                }
            } 

            case "b" -> {
                System.out.printf("Duração do filme (min): ");
                int filmLength = read.nextInt();
                read.nextLine();

                try{
                    plat.adicionarFilme(midiaId, new Filme(midiaName, midiaGenre, midiaIdiom, midiaReleaseDate, filmLength));
                    System.out.printf("Filme cadastrado com sucesso!\n");
                }catch(ElementoJaExisteException | NullPointerException e){
                    System.out.println(e.getMessage());
                }
            } 
        }
    }

    /*Método estatico para assistir midia */
    public static void assistirMidia(PlataformaStreaming plat) {
        String midiaId;
        int midiaOption;

        System.out.printf("---------- Assistir uma séire ----------\n");
        
        System.out.printf("Escolha o que assistir:\n1.Filme\n2.Série\n");
        midiaOption = read.nextInt();
        read.nextLine();

        switch(midiaOption) {
            case 1 -> {
                System.out.printf("ID do filme: ");
                midiaId = read.nextLine();

                if(plat.getFilmes().containsKey((Object)midiaId)) {
                    plat.getFilmes().get((Object)midiaId).registrarAudiencia();
                    System.out.printf("Filme assistido\n");
                } else {
                    System.out.printf("O filme de id: %s não foi encontrado\n", midiaId);
                }
            }
            
            case 2 -> {
                System.out.printf("ID da série: ");
                midiaId = read.nextLine();

                if(plat.getSeries().containsKey((Object)midiaId)) {
                    plat.getSeries().get((Object)midiaId).registrarAudiencia();
                    System.out.printf("Série assistida\n");
                } else {
                    System.out.printf("A séirie de id: %s não foi encontrada\n", midiaId);
                }
            }
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
                                3. Cadastrar nova mídia.
                                4. Assistir mídia.
                                5. Ver audiencia de uma midia.
                                --- Outros ---
                                6. Ver lista para assistir.
                                7. Ver lista já visto.
                                8. Fazer buscar a partir de um filtro.
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
                    cadastrarMidia(plataform);
                    break;
                case 4:
                    assistirMidia(plataform);
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
