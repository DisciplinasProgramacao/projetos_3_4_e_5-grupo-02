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
            System.out.println("\nOlá, " + plataforma.getClienteAtual().getNomeUsuario() + "! Bem-vindo(a) ao Xam OBH!\n");

            System.out.println("""
                Escolha a operação que deseja realizar:
                 --- Gerenciamento de clientes ---
                 \t1 - Fazer login com outro usuário
                 \t2 - Cadastrar novo cliente
                 --- Gerenciamento de mídias ---
                 \t3 - Cadastrar nova série ou filme
                 \t4 - Assistir série
                 --- Outros ---
                 \t98 - Demonstração de outras funções
                 \t99 - Salvar alterações e sair
                 """);

            int op = scanner.nextInt();

            switch (op) {
                case 1:
                    fazerLogin(plataforma);
                    System.out.println("Usuário logado: " + plataforma.getClienteAtual().getNomeUsuario());
                    break;
                case 2:
                    cadastrarCliente(plataforma);
                    break;
                case 3:
                    cadastrarMidia(plataforma);
                    break;
                case 4:
                    assistirSerie(plataforma);
                    break;
                case 98:
                    demo(plataforma);
                    break;
                case 99:
                    System.out.println("Salvando alterações...");
                    plataforma.salvarClientes();
                    plataforma.salvarFilmes();
                    plataforma.salvarSeries();
                    exit = true;
                    break;
            }
        }

        scanner.close();
    }

    public static void fazerLogin(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("ID: ");
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
        System.out.println("ID: ");
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

    public static void cadastrarMidia(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nome: ");
        String nome = scanner.nextLine();

        System.out.println("ID: ");
        int id = Integer.parseInt(scanner.nextLine());

        System.out.println("Gênero: ");
        System.out.println("Gêneros disponíveis: Comédia, Ação, Terror, Drama, Romance, Aventura, Animação, Suspense");
        String genero = scanner.nextLine();

        System.out.println("Idioma: ");
        System.out.println("Idiomas disponíveis: Português, Inglês, Esperanto, Romeno");
        String idioma = scanner.nextLine();

        System.out.println("Data de lançamento (formato aaaa-mm-dd): ");
        String lancamento = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Date dataLancamento = null;

        try {
            dataLancamento = dateFormat.parse(lancamento);
        } catch (ParseException e) {
            System.out.println("Formato inválido de data.");
        }

        System.out.println("""
                Selecione o tipo de mídia
                 a - Série
                 b - Filme""");

        String tipoMidia = scanner.nextLine();

        switch (tipoMidia) {
            case "a" -> {
                System.out.println("Insira a quantidade de episodios da série: ");
                String quantidadeEpisodios = scanner.nextLine();
                try {
                    plataforma.adicionarSerie(id, new Serie(nome, genero, idioma, dataLancamento, Integer.parseInt(quantidadeEpisodios)));
                    System.out.println("Série adicionada com sucesso!");
                } catch (ElementoJaExisteException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                scanner.close();
            }
            case "b" -> {
                System.out.println("Insira a duração do filme: ");
                String duracao = scanner.nextLine();
                try {
                    plataforma.adicionarFilme(id, new Filme(nome, genero, idioma, dataLancamento, Integer.parseInt(duracao)));
                    System.out.println("Filme adicionado com sucesso!");
                } catch (ElementoJaExisteException | NullPointerException e) {
                    System.out.println(e.getMessage());
                }
                scanner.close();
            }
        }

        scanner.close();
    }

    public static void assistirSerie(PlataformaStreaming plataforma) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Insira o id da série que você deseja assistir: ");
        String id = scanner.nextLine();

        if (plataforma.getSeries().containsKey(id)){
            plataforma.getSeries().get(id).registrarAudiencia();
            System.out.println("Série assistida e adicionada à lista.");
        }

        else
            System.out.println("A série de id " + id + " não foi encontrada.");
    }

    public static void demo(PlataformaStreaming plataforma) {
        /* Demonstração de adição de dados */
        Cliente novoCliente = new Cliente("Cirilo", "maria_joaquina_stan", "cirilo12");

        try {
            plataforma.adicionarCliente(novoCliente);
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (ElementoJaExisteException e) {
            System.out.println(e.getMessage());
        }

        /* Demonstração de salvamento de dados em arquivo .csv */
        plataforma.salvarClientes();

        System.out.println();

        /* Demonstração de avaliação para clientes regulares */
        Cliente avaliadorRegular = new Cliente("Ednaldo Pereira", "ed.pereira", "123");

        Filme filme = new Filme("Minions", "Terror", "Aramaico", new Date(), 120);

        avaliadorRegular.avaliarMidia(filme,3);

        /* Demonstração de avaliação para cliente especialista*/
        Cliente clienteEsp = new Cliente("Givanildo", "hulk13", "13");

        clienteEsp.modoAvaliacao = new ClienteEspecialista();
        clienteEsp.avaliarMidia(filme, 4, "Muito ruim");

        System.out.println(filme);
    }
}
