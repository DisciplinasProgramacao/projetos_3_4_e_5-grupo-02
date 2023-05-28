[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10825181)
# Xam HBO 
Projeto apresentado à disciplina de Laboratório de Programação Modular. O objetivo desse projeto é implementar uma plataforma de streaming e suas respectivas funcionalidades.

----

## Correção Projeto 3 (branch de 02/05)

### Nota base: 13,4
### Comentários

- Midia tem vários sets desnecessários e, pior, sem validação. A avaliação (nota) de uma mídia só pode ser feita por método específico. Acontece em outras classes. Revisar.
- Erro na carga: procura campos 3 e 4 em carregar séries (só vai até 2)
- Em adicionarCliente, veja que a linha 100 já lança um NullPointer se for nulo.
- App sem cadastros
- Métodos de salvamento: código repetido. Inserir polimorfismo.
- Cliente precisa ter lista de mídias, não de séries (e assim, os métodos das listas)
- Em nenhum momento adiciona na lista de vistas (olha a falta de teste, pessoal...)
- Filtros da plataforma precisam ser por mídia, não por série

1. Aderência às classes do diagrama: 2/2 pontos


2. Requisitos de corretamente implementados: 7,6/12 pontos
    - Carga de dados					1/2 pontos
    - Cadastro + salvar dados			0,6/2 pontos
    - Robustez básica					1/1 ponto
    - Clientes							1,5/2 pontos
	      - Listas, audiência sem repet
    - Séries							1/1 ponto
    - Filme/Herança de mídia			1/1 ponto
    - Buscas 							2,5/3 pontos
        - nome, gênero, idioma

3. Documentação de código: 2,8/4 pontos
    - PlataformaStreaming mal documentada

4. Implementação na aula inicial: 1/2 pontos (cliente e série testados)
    - Cliente ok, teste não
    - Série ok, teste não

----
## Alunos integrantes da equipe

* André Rodrigues de Freitas Faria
* Carlos Emanuel Silva e Melo Oliveira
* Gustavo Andrade Alves
* Letícia Teixeira Lott Carvalho
* Yan Rodrigues Nalon

## Professores responsáveis

* João Caram Santos de Oliveira

## Documentação

- Atualização do diagrama UML ✔
- Implementação e teste da classe PlataformaStreaming ✔
- Método(s) para carregamento dos dados ✔
- Criação de um pequeno aplicativo para chamar o carregamento dos dados ✔
- Classe filme e carga do catálogo de filmes ✔
- Primeira versão do protótipo do aplicativo ✔
- Cadastro de novas entidades ✔
- Salvar dados das entidades ✔
- Robustez no sistema principal ✔
- Implementação e teste da classe avaliação ✔
- Finalizar documentação ✔
- Gerar javadoc ✔
