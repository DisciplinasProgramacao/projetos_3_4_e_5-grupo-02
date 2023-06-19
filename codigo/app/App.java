package app;

import business.*;
import business.entidades.*;
import business.entidades.fracas.*;
import business.exceptions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

import utils.Lista;

public class App {
    /*
     * System.in implementa a interface Closable. Ver javadoc java.util.scanner -> Scanner.close
     */
    public static Scanner read = new Scanner(System.in);
    
    public static void main(String[] args) {
        /* Variaveis */
        Cliente clientePadrao = new Cliente("Lei108163", "Leia D V Brum", "LAus15911");
        PlataformaStreaming ps = new PlataformaStreaming("Xam OBH");
        
        try {
			ps.login(clientePadrao.getNomeUsuario(), clientePadrao.getSenha());
		} catch (LoginInvalidoException e) {
			e.printStackTrace();
		}
        
        int option = 0;

        while (option != 99) {
            System.out.print("\n-------------- MENU --------------\n");
            System.out.printf("Usuário logado: %s\n", ps.getClienteAtual().getNomeUsuario());
            System.out.print("""
                    \nEscolha uma operação:
                    \n--- Gerenciar Clientes ---
                    1. Fazer login com outro usuário
                    2. Cadastrar novo cliente
                    \n--- Gerenciar Mídias ---
                    3. Cadastrar nova mídia
                    4. Assistir midia
                    5. Ver audiência de uma midia
                    6. Ver minha lista de midias para assistir
                    7. Ver minha lista de midias já vistas
                    8. Filtrar minhas midias
                    9. Buscar mídia por nome
                    10. Ver catálogo completo
                    \n--- Gerenciar avaliações ---
                    11. Avaliar mídia
                    12. Ver minhas avaliações
                    \n--- Relatórios ---
                    \n--- Outros ---
                    99. Salvar e sair
                    ----------------------------------
                    \n""");
            System.out.print("Opção: ");
            option = read.nextInt();
            read.nextLine(); // Lê o \n que o nextInt não lê

            // Escolher opção do menu
            switch (option) {
                case 1:
                    fazerLogin(ps);
                    break;
                case 2:
                    cadastrarCliente(ps);
                    break;
                case 3:
                    cadastrarMidia(ps);
                    break;
                case 4:
                    assistirMidia(ps);
                    break;
                case 5:
                    verAudiencia(ps);
                    break;
                case 6:
                    midiasParaAssistir(ps);
                    break;
                case 7:
                    midiasAssistidas(ps);
                    break;
                case 8:
                    filtrarMidias(ps);
                    break;
                case 9:
                    buscarMidia(ps);
                    break;
                case 10:
                    imprimirMidias(ps);
                    break;
                case 11:
                    avaliarMidia(ps);
                    break;
                case 12:
                    midiasAvaliadas(ps);
                    break;
                case 98:
                    System.out.print("Opção 98 selecionada\n");
                    break;
                case 99:
                	ps.salvar();
                    break;
                default:
                    System.out.printf("A opção %d é inválida\n", option);
                    break;
            }

            try {
                Thread.sleep(1500); // Pausa antes de mostrar menu novamente
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        read.close();
    }

    /**
     * Método estático para fazer login com um usuário existente na plataforma
     * @param plat Plataforma streaming
     */
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

    /**
     * Método estático para cadastrar novo cliente à plataforma
     * @param plat Plataforma streaming
     */
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
        } catch (ClienteJaExisteException e) {
            System.out.print(e.getMessage());
        }
    }

    /**
     * Método estático para cadastrar uma nova mídia ao catálogo
     * @param plat Plataforma streaming
     */
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

        Date midiaReleaseDate = null;

        try {
        	midiaReleaseDate = new SimpleDateFormat("dd-MM-yyyy").parse(midiaRelease);
        } catch (ParseException e) {
            System.out.print("Formato de data invalido! Utilize o formato dd-MM-yyyy\n");
        }

        System.out.print("""
                Tipo de mídia: \s
                    Digite (A) para Série
                    Digite (B) para Filme
                """);

        midiaType = read.nextLine();

        switch (midiaType) {
            case "A":
            	System.out.println("Série selecionada!");
                System.out.println("Digite a quantidade de episodios existentes: ");
                int serieQtdEp = read.nextInt();
                read.nextLine();

                try {
                    plat.adicionarMidia(Integer.valueOf(midiaId), new Serie(midiaId, midiaName, midiaGenre, midiaIdiom, midiaReleaseDate, serieQtdEp));
                    System.out.println("Série cadastrada com sucesso!\n");
                } catch (MidiaJaExisteException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            case "B":
            	System.out.println("Filme selecionado!");
                System.out.print("Duração do filme (min): ");
                int filmLength = read.nextInt();
                read.nextLine();

                try {
                    plat.adicionarMidia(Integer.valueOf(midiaId), new Filme(midiaId, midiaName, midiaGenre, midiaIdiom, midiaReleaseDate, filmLength));
                    System.out.print("Filme cadastrado com sucesso!\n");
                } catch (MidiaJaExisteException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                break;

            default:
            	System.out.println("Opção invalida");
            	break;
        }
    }

    /**
     * Método estático para assistir uma mídia disponível no catálogo
     * @param plat Plataforma streaming
     */
    public static void assistirMidia(PlataformaStreaming plat) {
        Scanner scan = new Scanner(System.in);

        System.out.println("\n---------- Assistir mídia ----------");
        System.out.println("Digite o id da mídia que deseja assistir: ");
        String id = scan.nextLine();

        try {
        	plat.getClienteAtual().registrarAudiencia(plat.findMidiaById(Integer.parseInt(id)));
        } catch (MidiaNaoEncontradaException e) {
        	System.out.println(e.getMessage());
        }

        System.out.println("A mídia " + plat.findMidiaById(Integer.parseInt(id)).getNome() + " foi assistida!");
    }

    /**
     * Método estático para imprimir a audiência de uma mídia
     * @param plat Plataforma streaming
     */
    public static void verAudiencia(PlataformaStreaming plat) {
    	Scanner scan = new Scanner(System.in);

        System.out.println("\n---------- Ver Audiência ----------");
        System.out.println("Digite o id da mídia que deseja ver a audiência: ");
        String id = scan.nextLine();

        Midia midia = plat.findMidiaById(Integer.parseInt(id));

        if (midia != null) {
        	System.out.println("Midia: " + midia.getNome() + " \nAudiência: " + midia.getAudiencia());
        } else {
        	System.out.println("Midia não encontrada");
        }
    }

    /**
     * Método estático para imprimir as mídias da lista para ver do cliente logado
     * @param plat Plataforma streaming
     */
    public static void midiasParaAssistir(PlataformaStreaming plat) {
        Lista<Midia> listaParaVer = plat.getClienteAtual().getListaParaVer();

        Midia[] listaImprimir = new Midia[listaParaVer.size()];
        listaImprimir = listaParaVer.allElements(listaImprimir);

        System.out.println("\n---------- Mídias para assistir ----------");
        for (Midia midia : listaImprimir) {
            System.out.printf("%s\n", midia.getNome());
        }
    }

    /**
     * Método estático para exibir todas as mídias já assistidas pelo cliente logado
     * @param plat Plataforma streaming
     */
    public static void midiasAssistidas(PlataformaStreaming plat) {
        Lista<Midia> listaJaVistas = plat.getClienteAtual().getListaJaVistas();

        Midia[] listaImprimir = new Midia[listaJaVistas.size()];
        listaImprimir = listaJaVistas.allElements(listaImprimir);

        System.out.println("\n---------- Mídias já vistas ----------");
        for (Midia midia : listaImprimir) {
            System.out.printf(midia.getId() + " - " + midia.getNome() + "\n");
        }
    }

    /**
     * Método estático para filtrar mídias por gênero, idioma ou quantidade de episódios
     * @param plat Plataforma streaming
     */
    public static void filtrarMidias(PlataformaStreaming plat) {
        Scanner scan = new Scanner(System.in);

        Lista<Midia> arrayFiltradas = new Lista<>();

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
                arrayFiltradas = plat.filtrarPorGenero(genero);
                break;
            case 2:
                System.out.print("Idiomas disponíveis: Português, Inglês, Esperanto, Romeno\n");
                System.out.println("Digite um idioma para buscar: ");
                String idioma = scan.nextLine();
                arrayFiltradas = plat.filtrarPorIdioma(idioma);
                break;
            case 3:
                System.out.println("Digite uma quantidade de episódios para buscar: ");
                String qtdEpisodios = scan.nextLine();
                arrayFiltradas = plat.filtrarPorQtdEpisodios(Integer.parseInt(qtdEpisodios));
                break;
            default:
                System.out.println("A opção escolhida é inválida");
                break;
        }

        System.out.println("\"---------- Resultados encontrados ----------\\n\"");
        for (int i = 0; i < arrayFiltradas.size(); i++) {
            if (arrayFiltradas.get(i) != null)
                System.out.println(arrayFiltradas.get(i).getNome());
        }
    }

    /**
     * Método estático para exibir todas as midias disponíveis no catálogo
     * @param plat Plataforma streaming
     */
    public static void imprimirMidias(PlataformaStreaming plat) {
        plat.getMidiasMap().forEach((key, value) -> {
            if (value instanceof Filme)
                System.out.println(value.getId() + " - " + value.getNome() + " - Filme" );
            else if (value instanceof Serie)
                System.out.println(value.getId() + " - " + value.getNome() + " - Série");
        });
    }

    /**
     * Método estático para pesquisar mídia por nome
     * @param plat Plataforma streaming
     */
    private static void buscarMidia(PlataformaStreaming plat) {
        AtomicBoolean midiaEncontrada = new AtomicBoolean(false);
        Scanner scan = new Scanner(System.in);

        System.out.println("Nome da mídia: ");
        String searchedName = scan.nextLine();

        plat.getMidiasMap().forEach((key, value) -> {
            if (value.getNome().equalsIgnoreCase(searchedName)) {
                System.out.println("A seguinte mídia foi encontrada:\n");
                System.out.println(value);
                midiaEncontrada.set(true);
            }
        });

        if (!midiaEncontrada.get())
            System.out.println("Não foi possível encontrar a mídia de nome " + searchedName);
    }

    /**
     * Método estático para avaliar uma mídia
     * @param plat Plataforma streaming
     */
    public static void avaliarMidia(PlataformaStreaming plat) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n---------- Avaliar mídia ----------");

        System.out.println("Midias assistidas:");
        midiasAssistidas(plat);

        System.out.println("\nDigite o id da mídia que você deseja avaliar:");
        String idMidia = scan.nextLine();

        if (plat.getMidiasMap().containsKey(Integer.parseInt(idMidia))) {

            System.out.println("Insira uma nota de 1 a 5: ");
            String nota = scan.nextLine();

            try {
                if (plat.getClienteAtual().getModoAvaliacao() != null) {
                    System.out.println("Insira um comentário: ");
                    String comentario = scan.nextLine();

                    plat.getClienteAtual().avaliarMidia(plat.findMidiaById(Integer.parseInt(idMidia)), Integer.parseInt(nota), comentario);
                } else {
                    plat.getClienteAtual().avaliarMidia(plat.findMidiaById(Integer.parseInt(idMidia)), Integer.parseInt(nota));
                }

                System.out.println("Mídia avaliada com sucesso!");

            } catch (IllegalStateException e) {
                System.out.println("Erro: " + e.getMessage());
            }

        } else {
            System.out.println("Não foi possível encontrar mídia de id " + idMidia);
        }
    }

    /**
     * Método estático para visualizar avaliações do cliente
     * @param plat Plataforma streaming
     */
    public static void midiasAvaliadas(PlataformaStreaming plat) {
        Lista<Midia> listaJaVistas = plat.getClienteAtual().getListaJaVistas();

        Midia[] listaImprimir = new Midia[listaJaVistas.size()];
        listaImprimir = listaJaVistas.allElements(listaImprimir);

        System.out.println("\n---------- Mídias avaliadas ----------");
        for (Midia midia : listaImprimir) {
            if (midia.getAvaliacoes().size() != 0) {
                midia.getAvaliacoes().forEach(avaliacao -> {
                    if (avaliacao.getCliente().equals(plat.getClienteAtual())) {
                        System.out.println(midia.getNome());
                        System.out.println(avaliacao);
                        System.out.println("\n");
                    }
                });
            }
        }
    }
}
