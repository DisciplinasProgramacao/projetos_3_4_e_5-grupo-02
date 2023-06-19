# Prioridades e pendências

## **Importante e muito grave:** Estão criando código à toa porque não estão usando modularidade, por exemplo, um método para assistirSerie e outro para assistirFilme. Isso quebra o OCP. Procurem por isso nas classes. Também estão com gets em excesso. O app, por exemplo, não tem que dar get de nada. Ele só pede para a plataforma.

| Requisito                                                                                               |     Tipo      | Implementado |           Responsavel           | Tester              | Passou no Teste? | Funciona no App? |
| :------------------------------------------------------------------------------------------------------ | :-----------: | :----------: | :-----------------------------: | ------------------- | :--------------: | :--------------: |
| Garantir a robustez do sistema principal.                                                               | Implementação |     Não      |             Andre               |                     |                  |                  |
| Testar tudo no app e completar menus.                                                                   | Implementação |     Não      |             Andre               |                     |                  |                  |
| Adicionar série na lista para assistir (pelo nome)                                                      | Implementação |     Não      |             Andre               |                     |                  |                  |
| Adicionar série nas lista de assistidas (pelo nome)                                                     | Implementação |     Não      |             Andre               |                     |                  |                  |
| Procurar e tentar melhorar problemas de modularidade/polimorfismo:<br> - situações com get de get de get (getCliente().getAssistidas().get(x))<br> - situações que quebram LSP ou OCP (tipicamente, instanceof ou métodos com nomes diferentes entre classe mãe e filhas. <br> - construtores sem validação.                                  | Implementação |     Não      |             Yan                 |                     |                  |                  |
| Pause no main antes de chamar o menu novamente                                                          | Implementação |     Sim      |             Lott                |                     |                  |       Sim        |
| Buscar mídia por nome na lista geral                                                                    | Implementação |     Sim      |             Lott                |                     |                  |       Sim        |
| Cliente profissional e lançamentos                                                                      | Implementação |     Não      |           Gustavo               |                     |                  |                  |
| Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente;        | Implementação |     Não      |           Gustavo               |                     |                  |                  |
| Quais são as 10 mídias com mais visualizações, em ordem decrescente;                                    | Implementação |     Não      |           Gustavo               |                     |                  |                  |
| Estes mesmos dois últimos relatórios, porém com as mídias separadas por gênero.                         | Implementação |     Não      |           Gustavo               |                     |                  |                  |
| Conferir registro de audiência                                                                          | Implementação |     Não      |           Carlos                |                     |                  |                  |
| Conferir média de notas da mídia                                                                        | Implementação |     Não      |           Carlos                |                     |                  |                  |
! Qual cliente assistiu mais mídias, e quantas mídias;                                                    | Implementação |     Não      |           Carlos                |                     |                  |                  |
| Qual a porcentagem dos clientes com pelo menos 15 avaliações;                                           | Implementação |     Não      |           Carlos                |                     |                  |                  |

## Backlog antigo

| Requisito                                                                                               |     Tipo      | Implementado |           Responsavel           | Tester              | Passou no Teste? | Funciona no App? |
| :------------------------------------------------------------------------------------------------------ | :-----------: | :----------: | :-----------------------------: | ------------------- | :--------------: | :--------------: |
| Classe Cliente                                                                                          | Implementação |     Sim      |     Carlos, Leticia, Andre      | Leticia, Andre, Yan |       Sim        |                  |
| Classe Serie                                                                                            | Implementação |     Sim      |         Andre, Leticia          | Andre, Leticia      |       Sim        |                  |
| Classe PlataformaStreaming                                                                              | Implementação |     Sim      |         Andre, Leticia          |                     |                  |                  |
| Métodos para carga de dados de séries, clientes e audiência                                             | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Lógica de carga de dados do catálogo de filmes;                                                         | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Cadastro de séries                                                                                      | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Cadastro de cliente                                                                                     | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Cadastro de filme                                                                                       | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Sistema de avaliação de mídias: uma mídia tem sua avaliação média;                                      | Implementação |     Sim      |               Yan               |                     |                  |                  |
| Um cliente não poder avaliar a mesma mídia duas vezes;                                                  | Implementação |     Sim      |               Yan               |                     |                  |                  |
| Protótipo do sistema cobrindo os requisitos;                                                            | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Salvar dados de clientes, filmes e séries em arquivo;                                                   | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Clientes podem ser regulares ou especialistas, e estes últimos podem adicionar comentários à avaliação; | Implementação |     Sim      |             Leticia             |                     |                  |                  |
| Qual cliente assistiu mais mídias, e quantas mídias;                                                    | Implementação |     Não      |                                 |                     |                  |                  |
| Qual cliente tem mais avaliações, e quantas avaliações;                                                 | Implementação |     Sim      |              Andre              |                     |                  |                  |
| Qual a porcentagem dos clientes com pelo menos 15 avaliações;                                           | Implementação |     Não      |                                 |                     |                  |                  |
| Quais são as 10 mídias de melhor avaliação, com pelo menos 100 avaliações, em ordem decrescente;        | Implementação |     Não      |                                 |                     |                  |                  |
| Quais são as 10 mídias com mais visualizações, em ordem decrescente;                                    | Implementação |     Não      |                                 |                     |                  |                  |
| Estes mesmos dois últimos relatórios, porém com as mídias separadas por gênero.                         | Implementação |     Não      |                                 |                     |                  |                  |
| Criação e tratamento de exceções                                                                        | Implementação |     Sim      |               Yan               |                     |                  |                  |
| Diagrama original contempla o requisito de carga de dados;                                              |  Atualização  |     Sim      |         Carlos, Gustavo         |                     |                  |                  |
| Diagrama abrigando o novo tipo de mídia: Filme;                                                         |  Atualização  |     Sim      |         Carlos, Gustavo         |                     |                  |                  |
| Atualização em diagramas                                                                                |  Atualização  |     Sim      |         Carlos, Gustavo         |                     |                  |                  |
| Javadoc                                                                                                 |  Atualização  |     Sim      | Carlos, Gustavo, Andre, Leticia |                     |                  |                  |

    


