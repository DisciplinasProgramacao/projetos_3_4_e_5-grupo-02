package app;

import business.*;
import business.exceptions.*;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class App {
    /*System.in implementa a interface Closable. Ver javadoc java.util.scanner -> Scanner.close*/
    public static Scanner read = new Scanner(System.in);

    /*Método estatico para fazer login */
    public static void fazerLogin(PlataformaStreaming plat) {
        String userName, password;

        System.out.print("---------- Fazer Login ----------\n");

        System.out.print("Insira o ID do usuário: ");
        userName = read.nextLine();

        System.out.print("Insira a SENHA do usuário: ");
        password = read.nextLine();

        try {
            plat.login(userName, password);
        } catch (LoginInvalidoException e) {
            System.out.print(e.getMessage());
        }
    }

    /*Método estatico para cadastrar cliente */
    public static void cadastrarCliente(PlataformaStreaming plat) {
        String userName, userId, userPassword;

        System.out.print("---------- Cadastrar Cliente ----------\n");

        System.out.print("Nome do usuário: ");
        userName = read.nextLine();

        System.out.print("ID do usuário: ");
        userId = read.nextLine();

        System.out.print("Senha do usuário: ");
        userPassword = read.nextLine();

        try {
            plat.adicionarCliente(userName, userId, userPassword);
            System.out.print("Usuário cadastrado com sucesso.\n");
        } catch (ElementoJaExisteException e) {
            System.out.print(e.getMessage());
        }
    }

    /*Método estatico para cadastrar nova midia */
    public static void cadastrarMidia(PlataformaStreaming plat) {
        String midiaId, midiaName, midiaGenre, midiaIdiom, midiaRelease, midiaType;

        System.out.print("---------- Cadastrar Midia ----------\n");

        System.out.print("Nome: ");
        midiaName = read.nextLine();

        System.out.print("ID: ");
        midiaId = read.nextLine();

        System.out.print("Gêneros disponíveis: Comédia, Ação, Terror, Drama, Romance, Aventura, Animação, Suspense\n");
        System.out.print("Gênero: ");
        midiaGenre = read.nextLine();

        System.out.print("Idiomas disponíveis: Português, Inglês, Esperanto, Romeno\n");
        System.out.print("Idioma: ");
        midiaIdiom = read.nextLine();

        System.out.print("Data de lançamento (dd-mm-aaaa): ");
        midiaRelease = read.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Date midiaReleaseDate = null;

        try {
            midiaReleaseDate = dateFormat.parse(midiaRelease);
        } catch (ParseException e) {
            System.out.print("Formato de data invalido! Utilize o formato dd-MM-yyyy\n");
        }

        System.out.print("""
                Selecione o tipo de mídia:\s
                    a. Série.
                    b. Filme.
                """);

        midiaType = read.nextLine();

        switch (midiaType) {
            case "a" -> {
                System.out.print("Quantidade de episodios: ");
                int serieQtdEp = read.nextInt();
                read.nextLine();

                try {
                    plat.adicionarSerie(Integer.valueOf(midiaId), new Serie(midiaId, midiaName, midiaGenre, midiaIdiom, midiaReleaseDate, serieQtdEp));
                    System.out.println("Série cadastrada com sucesso!\n");
                } catch (ElementoJaExisteException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }

            case "b" -> {
                System.out.print("Duração do filme (min): ");
                int filmLength = read.nextInt();
                read.nextLine();

                try {
                    plat.adicionarFilme(Integer.valueOf(midiaId), new Filme(midiaId, midiaName, midiaGenre, midiaIdiom, midiaReleaseDate, filmLength));
                    System.out.print("Filme cadastrado com sucesso!\n");
                } catch (ElementoJaExisteException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /*Método estatico para assistir midia */
    public static void assistirMidia(PlataformaStreaming plat) {
        String midiaId;
        int midiaOption;

        System.out.print("---------- Assistir uma séire ----------\n");

        System.out.print("Escolha o que assistir:\n1.Filme\n2.Série\n");
        midiaOption = read.nextInt();
        read.nextLine();

        switch (midiaOption) {
            case 1 -> {
                System.out.print("ID do filme: ");
                midiaId = read.nextLine();

                if (plat.getFilmes().containsKey((Object) midiaId)) {
                    plat.getFilmes().get((Object) midiaId).registrarAudiencia();
                    System.out.print("Filme assistido\n");
                } else {
                    System.out.printf("O filme de id: %s não foi encontrado\n", midiaId);
                }
            }

            case 2 -> {
                System.out.print("ID da série: ");
                midiaId = read.nextLine();

                if (plat.getSeries().containsKey((Object) midiaId)) {
                    plat.getSeries().get((Object) midiaId).registrarAudiencia();
                    System.out.print("Série assistida\n");
                } else {
                    System.out.printf("A série de id: %s não foi encontrada\n", midiaId);
                }
            }
        }
    }

    /*Método estatico para ver audiencia de uma mídia */
    public static void verAudiencia(PlataformaStreaming plat) {
    }

    /*Método estatico para exibir midias para ver */
    public static void midiasParaAssistir(PlataformaStreaming plat) {
        Lista<Serie> listaParaVer = plat.getClienteAtual().getListaParaVer();

        Serie[] listaAssistidos = new Serie[listaParaVer.size()];
        listaAssistidos = listaParaVer.allElements(listaAssistidos);

        for (Serie serie : listaAssistidos) {
            System.out.printf("%s\n", serie.getNome());
        }
    }

    /*Método estatico para exibir todas as midias assistidas pelo cliente atual*/
    public static void midiasAssistidas(PlataformaStreaming plat) {
        Lista<Serie> listaParaVer = plat.getClienteAtual().getListaJaVistas();

        Serie[] listaAssistidos = new Serie[listaParaVer.size()];
        listaAssistidos = listaParaVer.allElements(listaAssistidos);

        for (Serie serie : listaAssistidos) {
            System.out.printf("%s\n", serie.getNome());
        }
    }

    public static void main(String[] args) {
        /*Variaveis */
        Cliente clientePadrao = new Cliente("John Doe", "Jd123", "psswd456");
        PlataformaStreaming plataform = new PlataformaStreaming("Xam OBH", clientePadrao);
        int option = 0;

        /*Carregar dados */
        try {
            plataform.carregarAudiencia();
            plataform.carregarClientes();
            plataform.carregarFilmes();
            plataform.carregarSeries();

        } catch (FileNotFoundException e) {
            System.out.print("Erro ao carregar dados da plataforma!");
            read.close();
        }

        while (option != 99) {
            System.out.print("-------------- MENU --------------\n");
            System.out.printf("Usuário logado: %s\n", plataform.getClienteAtual().getNomeUsuario());
            System.out.print("""
                    Escolha uma operação:
                    \n--- Gerenciar Clientes ---
                    1. Fazer login com outro usuário
                    2. Cadastrar novo cliente
                    \n--- Gerenciar Mídias ---
                    3. Cadastrar nova mídia
                    4. Assistir mídia
                    5. Ver audiência de uma midia
                    \n--- Outros ---
                    6. Ver lista para assistir
                    7. Ver lista já visto
                    8. Fazer busca a partir de um filtro
                    99. Salvar e sair
                    \n""");
            System.out.print("Opção: ");
            option = read.nextInt();
            read.nextLine(); // Lê o \n que o nextInt não lê

            // Escolher opção do menu
            switch (option) {
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
                case 5:
                    System.out.print("Opção 05");
                    break;
                case 6:
                    midiasParaAssistir(plataform);
                    break;
                case 7:
                    midiasAssistidas(plataform);
                    break;
                case 8:
                    System.out.print("Opção 08");
                    break;
                case 98:
                    System.out.print("Opção 98 selecionada\n");
                    break;
                case 99:
                    plataform.salvarFilmes();
                    plataform.salvarSeries();
                    plataform.salvarClientes();
                    plataform.salvarAudiencia();
                    // plataforma.salvarAvaliacoes();
                    break;
                default:
                    System.out.printf("A opção %d é inválida\n", option);
                    break;
            }
        }

        read.close();
    }
}
