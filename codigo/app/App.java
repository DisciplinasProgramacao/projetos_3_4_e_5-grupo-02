package app;

import business.*;
import business.entidades.*;
import business.entidades.fracas.*;
import business.exceptions.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import utils.Lista;

public class App {
    /*
     * System.in implementa a interface Closable. Ver javadoc java.util.scanner ->
     * Scanner.close
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
                    6. Ver lista de midias para assistir
                    7. Ver lista de midias já vistas
                    8. Filtrar minhas midias
                    9. Exibir todas as mídias
                    10. Avaliar mídia
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
                    imprimirMidias(ps);
                    break;
                case 10:
                    avaliarMidia(ps);
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

    // OK!!!
    /* Método estático para fazer login */
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

    // OK!!!
    /* Método estático para cadastrar cliente */
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

    // OK!!!
    /* Método estático para cadastrar nova mídia */
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

    // OK!!!
    /* Método estático para assistir midia */
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

    /* Método estático para ver audiencia de uma mídia */
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

    // OK!!!
    /* Método estático para exibir midias para ver */
    public static void midiasParaAssistir(PlataformaStreaming plat) {
        Lista<Midia> listaParaVer = plat.getClienteAtual().getListaParaVer();

        Midia[] listaImprimir = new Midia[listaParaVer.size()];
        listaImprimir = listaParaVer.allElements(listaImprimir);

        System.out.println("\n---------- Séries para assistir ----------");
        for (Midia midia : listaImprimir) {
            System.out.printf("%s\n", midia.getNome());
        }
    }

    // OK!!!
    /* Método estático para exibir todas as midias assistidas pelo cliente atual */
    public static void midiasAssistidas(PlataformaStreaming plat) {
        Lista<Midia> listaJaVistas = plat.getClienteAtual().getListaJaVistas();

        Midia[] listaImprimir = new Midia[listaJaVistas.size()];
        listaImprimir = listaJaVistas.allElements(listaImprimir);

        System.out.println("\n---------- Séries já vistas ----------");
        for (Midia midia : listaImprimir) {
            System.out.printf(midia.getId() + " - " + midia.getNome() + "\n");
        }
    }

    // OK!!!
    public static void imprimirMidias(PlataformaStreaming plat) {
        System.out.println("\n---------- Filmes disponíveis ----------");
        plat.getFilmes().forEach(filme -> System.out.println(filme.getNome() + "\n"));
        System.out.println("\n---------- Séries disponíveis ----------");
        plat.getSeries().forEach(serie -> System.out.println(serie.getNome() + "\n"));
    }

    // OK!!!
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

    // OK!!!
    public static void avaliarMidia(PlataformaStreaming plat) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\n---------- Avaliar mídia ----------");

        System.out.println("Midias assistidas:");
        midiasAssistidas(plat);

        System.out.println("\nDigite o id da mídia que você deseja avaliar:");
        String idMidia = scan.nextLine();
        
        if (plat.getMidiasMap().containsKey(Integer.parseInt(idMidia))) {

            if (plat.getClienteAtual().getModoAvaliacao() instanceof ClienteEspecialista) {
                System.out.println("Insira uma nota de 1 a 5: ");
                String nota = scan.nextLine();
                System.out.println("Insira um comentário: ");
                String comentario = scan.nextLine();

                plat.getClienteAtual().avaliarMidia(plat.findMidiaById(Integer.parseInt(idMidia)), Integer.parseInt(nota), comentario);
                System.out.println("Mídia avaliada com sucesso!");
            } else {
                System.out.println("Insira uma nota de 1 a 5: ");
                String nota = scan.nextLine();

                plat.getClienteAtual().avaliarMidia(plat.findMidiaById(Integer.parseInt(idMidia)), Integer.parseInt(nota));
                System.out.println("Mídia avaliada com sucesso!");
            }
        } else {
            System.out.println("Não foi possível encontrar mídia de id " + idMidia);
        }
    }
}
