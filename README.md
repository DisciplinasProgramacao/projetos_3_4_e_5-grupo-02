[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10825181)
# Xam HBO 
Projeto apresentado à disciplina de Laboratório de Programação Modular. O objetivo desse projeto é implementar uma plataforma de streaming e suas respectivas funcionalidades.

# Comentários - Projeto 4

## Nota base: 12,3

### Comentários

- **GRAVE**: instanceOf para especialistas
- Para ser efetivo, o equals devia ser cliente e midia. O melhor seria um comparador.
- Cliente com lista de séries, não de mídias
- Midia e comentarios(): quebra de encapsulamento. a mídia não tem que saber da outra classe.
- Por algum motivo, havia código comentado em cliente impedindo de mudar de categoria
- Percebam que uma documentação como abaixo não diz nada. Todo mundo sabe que um método pode chamar outro. O importante é porque ele faz e como ele faz.

```
* Chama o método criarAvaliacao(Cliente, int, String) de mídia.
```

----
	
- Aderência às classes do diagrama: 1/2 pontos
	- Diagrama não contém avaliações nem especialistas

- Requisitos de corretamente implementados: 8,5/14 pontos
    - só pode avaliar o que tiver visto		0/2 pontos
    - avaliar, calcular e exibir media 		2/2 pontos
    - cliente não pode avaliar 2x			2/3 pontos
    - especialistas podem comentar			2/4 pontos
    - verificação de especialistas			2,5/3 pontos
	
- Documentação de código: 1,2/2 pontos
    - Avaliacao, Filme, Serie mal documentados

- Implementação na aula inicial: 1,6/2 pontos (02/05)
    - arquivos JavaDoc ❌
    - diagrama atualizado ✔️
    - backlog de pendências ✔️

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
