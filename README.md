[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10825181)
# Xam HBO 
Projeto apresentado à disciplina de Laboratório de Programação Modular. O objetivo desse projeto é implementar uma plataforma de streaming e suas respectivas funcionalidades.

## Nota base: 15,7

### App 4/6 (5 pontos) = 3,3
	Protótipo de sistema 4 ✔✔✔✔
	Robustez do protótipo 2 ❌
	
### Requisitos principais 19/21 + 4,7/6 (10 pontos) = 8,8
	Implementação das classes Cliente, Serie, Midia, Filme e PlataformaStreaming 2 ✔✔
	Carga de dados 2 ✔✔
	Cadastro e salvamento 2  ✔
	Audiência da mídia 1 ✔
	Implementação do sistema de avaliação de mídias: uma mídia tem sua avaliação média; 2 ✔✔
	Um cliente não pode avaliar a mesma mídia duas vezes; 1 ✔
	Clientes podem ser especialistas, e estes últimos podem adicionar comentários à avaliação; 3 ✔✔✔
	Verificação de especialistas 2  ✔
	Os gêneros de mídias devem ser limitados à esta lista 1 ✔
	Algumas mídias serão marcadas como “Lançamento”. 1 ✔
	Estas mídias só poderão ser assistidas por clientes “profissionais” 2 ✔✔
	Clientes Profissionais também podem escrever comentários para as mídias assistidas. 2 ✔✔
	
	Relatórios 4,7/6 
	
		Qual cliente assistiu mais mídias, e quantas mídias; ✔
		Qual cliente tem mais avaliações, e quantas avaliações; ➗
		Qual a porcentagem dos clientes com pelo menos 15 avaliações; ➗ (cast)
		Quais são as 10 mídias com a melhor média de avaliações, vistas pelo menos 100 vezes, decrescente; ✔
		Quais são as 10 mídias com mais visualizações, em ordem decrescente; ✔
		Estes mesmos dois últimos relatórios, porém com as mídias separadas por gênero. ➗➗ (bagunça)
	
### Documentação 6,3/7 (5 pontos) = 4,1
	Documentação de código 3 ✔✔➗
	Diagrama atualizado    2 ✔➗➗ (relacao cliente)
	Backlog 			   2 ✔✔
	
### SOLID - Descontos: 
	Encapsulamento: get get get (ex, app linha 430)
	Uso de modo de avaliação em lugar de cast/parametro
	
### Apresentação 


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
