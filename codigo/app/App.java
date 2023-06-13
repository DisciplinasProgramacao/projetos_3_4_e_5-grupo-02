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

    /*Método estático para fazer login */
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

    /*Método estático para cadastrar cliente */
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

    /*Método estático para cadastrar nova mídia */
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

    /*Método estático para assistir midia */
    public static void assistirMidia(PlataformaStreaming plat) {
        String midiaId;
        int midiaOption;

        System.out.print("---------- Assistir uma série ----------\n");

        System.out.print("Escolha o que assistir:\n1. Filme\n2. Série\n");
        midiaOption = read.nextInt();
        read.nextLine();

        switch (midiaOption) {
            case 1 -> {
                System.out.print("Id do filme: ");
                midiaId = read.nextLine();

                if (plat.getFilmes().containsKey((Object) midiaId)) {
                    plat.getFilmes().get((Object) midiaId).registrarAudiencia();
                    System.out.print("Filme assistido\n");
                } else {
                    System.out.printf("O filme de id: %s não foi encontrado\n", midiaId);
                }
            }

            case 2 -> {
                System.out.print("Id da série: ");
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

    /*Método estático para ver audiencia de uma mídia */
    public static void verAudiencia(PlataformaStreaming plat) {
    }

    /*Método estático para exibir midias para ver */
    public static void midiasParaAssistir(PlataformaStreaming plat) {
        Lista<Serie> listaParaVer = plat.getClienteAtual().getListaParaVer();

        Serie[] listaImprimir = new Serie[listaParaVer.size()];
        listaImprimir = listaParaVer.allElements(listaImprimir);

        for (Serie serie : listaImprimir) {
            System.out.printf("%s\n", serie.getNome());
        }
    }

    /*Método estático para exibir todas as midias assistidas pelo cliente atual*/
    public static void midiasAssistidas(PlataformaStreaming plat) {
        Lista<Serie> listaJaVistas = plat.getClienteAtual().getListaJaVistas();

        Serie[] listaImprimir = new Serie[listaJaVistas.size()];
        listaImprimir = listaJaVistas.allElements(listaImprimir);

        for (Serie serie : listaImprimir) {
            System.out.printf("%s\n", serie.getNome());
        }
    }

    public static void imprimirMidias(PlataformaStreaming plat) {
        System.out.println("\n---------- Filmes disponíveis ----------");
        plat.getFilmes().forEach((key, value) -> System.out.println(value + "\n"));
        System.out.println("\n---------- Séries disponíveis ----------");
        plat.getSeries().forEach((key, value) -> System.out.println(value + "\n"));
    }

    public static void filtrarMidias(PlataformaStreaming plat) {
        Scanner scan = new Scanner(System.in);
        Serie[] arrayFiltradas = new Serie[plat.getSeries().size()];

        System.out.println("---------- Filtar mídias ----------\n");

        System.out.println("""
                Selecione o critério de busca:
                    1. Gênero
                    2. Idioma
                    3. Quantidade de episódios
                """);
        String op = scan.nextLine();

        switch (Integer.parseInt(op)) {
            case 1:
                System.out.print("Gêneros disponíveis: Comédia, Ação, Terror, Drama, Romance, Aventura, Animação, Suspense\n");
                System.out.println("Digite um gênero para buscar: ");
                String genero = scan.nextLine();
                arrayFiltradas = plat.filtrarPorGenero(genero).allElements(arrayFiltradas);
                break;
            case 2:
                System.out.print("Idiomas disponíveis: Português, Inglês, Esperanto, Romeno\n");
                System.out.println("Digite um idioma para buscar: ");
                String idioma = scan.nextLine();
                arrayFiltradas = plat.filtrarPorIdioma(idioma).allElements(arrayFiltradas);
                break;
            case 3:
                System.out.println("Digite uma quantidade de episódios para buscar: ");
                String qtdEpisodios = scan.nextLine();
                arrayFiltradas = plat.filtrarPorQtdEpisodios(Integer.parseInt(qtdEpisodios)).allElements(arrayFiltradas);
                break;
            default:
                System.out.println("A opção escolhida é inválida");
                break;
        }

        System.out.println("\"---------- Resultados encontrados ----------\\n\"");
        for (int i = 0; i < arrayFiltradas.length; i++) {
            if (arrayFiltradas[i] != null)
                System.out.println(arrayFiltradas[i]);
        }

    }

    public static void main(String[] args) {
        /*Variaveis */
        Cliente clientePadrao = new Cliente("John Doe", "Jd123", "psswd456");
        PlataformaStreaming plataform = new PlataformaStreaming("Xam OBH", clientePadrao);
        int option = 0;

        /*Carregar dados */
        try {
            plataform.carregarClientes();
            plataform.carregarFilmes();
            plataform.carregarSeries();
            plataform.carregarAudiencia();
            plataform.carregarAvaliacoes();
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
                    6. Ver lista de séries para assistir
                    7. Ver lista de séries já vistas
                    8. Fazer busca a partir de um filtro
                    9. Exibir todas as mídias
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
                    filtrarMidias(plataform);
                    break;
                case 9:
                    imprimirMidias(plataform);
                    break;
                case 98:
                    System.out.print("Opção 98 selecionada\n");
                    break;
                case 99:
                    plataform.salvarFilmes();
                    plataform.salvarSeries();
                    plataform.salvarClientes();
                    plataform.salvarAudiencia();
                    plataform.salvarAvaliacoes();
                    break;
                default:
                    System.out.printf("A opção %d é inválida\n", option);
                    break;
            }
        }

        read.close();
    }
}
