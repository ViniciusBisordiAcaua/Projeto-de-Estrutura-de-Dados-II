# Relatório - Índice Invertido com Árvore Digital Ternária

## 1. Objetivo

O projeto tem como objetivo desenvolver um sistema de busca de palavras
em arquivos de texto utilizando um índice invertido implementado por meio
de uma Árvore Digital Ternária (TST).

O sistema permite identificar em quais arquivos uma determinada palavra
está presente e realizar consultas utilizando os operadores booleanos
E, OU e NAO.

## 2. Estrutura do projeto

O projeto foi dividido em três módulos principais:

- Leitura, normalização e indexação dos arquivos;
- Implementação da Árvore Digital Ternária;
- Processamento de consultas booleanas.

Os arquivos de entrada são lidos e suas palavras são normalizadas antes
de serem inseridas na árvore. Cada palavra armazenada na TST mantém uma
lista dos arquivos nos quais ela aparece.

## 3. Árvore Digital Ternária

A Árvore Digital Ternária é utilizada como estrutura principal para
armazenar as palavras do índice invertido.

Cada nó possui:

- caractere;
- ponteiro para a esquerda;
- ponteiro para o meio;
- ponteiro para a direita;
- indicador de fim da palavra;
- lista de arquivos associados.

A navegação pela árvore é realizada comparando cada caractere da palavra
com o caractere armazenado no nó atual.

## 4. Inserção

A inserção percorre a palavra caractere por caractere.

Durante o processo:

- Se o caractere for menor que o caractere do nó atual, o algoritmo
  segue para a esquerda;
- Se for maior, segue para a direita;
- Se for igual, avança para o próximo caractere através do ponteiro
  do meio.

Quando o último caractere é alcançado, o nó é marcado como fim de palavra
e o arquivo correspondente é associado àquela palavra.

Antes de adicionar o arquivo, é realizada uma verificação para evitar
arquivos duplicados na lista.

## 5. Busca

A busca utiliza o mesmo princípio de navegação da inserção.

Os caracteres da palavra consultada são comparados com os caracteres
armazenados nos nós da árvore.

Quando o último caractere é encontrado e o nó está marcado como fim de
palavra, o sistema retorna a lista de arquivos associados.

Caso a palavra não exista, é retornada uma lista vazia.

## 6. Complexidade

### Inserção

Considerando `M` como o tamanho da palavra, o percurso principal da
inserção depende da quantidade de caracteres processados e dos desvios
realizados na TST.

Além disso, ao chegar ao fim da palavra, o código verifica a lista de
arquivos associados para evitar duplicações. Se `L` representar o número
de arquivos associados à palavra, essa verificação pode custar `O(L)`.

Dessa forma, de maneira simplificada, o custo pode ser descrito em função
do percurso da palavra e da verificação da lista de arquivos.

### Busca

A busca percorre a TST comparando os caracteres da palavra pesquisada.

Considerando `M` como o tamanho da palavra, em uma situação favorável o
custo está relacionado ao número de caracteres processados. Entretanto,
o custo real também depende da organização da TST e da quantidade de
desvios para esquerda e direita realizados durante a busca.

### Indexação

Considerando `T` como o número total de palavras processadas nos
documentos, cada palavra passa pelo processo de normalização e depois é
inserida na TST.

Portanto, o custo total da indexação depende da quantidade de palavras,
do tamanho das palavras e do custo das respectivas inserções na árvore.

## 7. Consultas booleanas

O sistema permite consultas utilizando os operadores:

- `E`: realiza a interseção entre os arquivos encontrados para dois termos;
- `OU`: realiza a união dos arquivos encontrados, sem duplicações;
- `NAO`: remove do conjunto considerado os arquivos que contêm o termo negado.

A precedência utilizada é:

1. `NAO`
2. `E`
3. `OU`

Dessa forma, uma consulta como:

`contrato OU venda E casa`

é processada respeitando primeiro a operação `E` e posteriormente a
operação `OU`.

## 8. Testes

Foram criados três arquivos de teste:

- `TesteArvore.java`;
- `TesteIndexacao.java`;
- `TesteConsulta.java`.

Os testes verificam separadamente o funcionamento da Árvore Digital
Ternária, da leitura e indexação dos arquivos e das consultas booleanas.

Também foi realizada a execução do programa completo através da classe
`Main`, verificando a integração entre todos os módulos.

## 9. Conclusão

O projeto permitiu aplicar conceitos de estruturas de dados na construção
de um sistema de índice invertido baseado em uma Árvore Digital Ternária.

A implementação possibilitou armazenar palavras associadas aos arquivos
em que aparecem, realizar buscas e processar consultas utilizando os
operadores booleanos E, OU e NAO.

Além da implementação da TST, o projeto envolveu leitura e normalização
de arquivos, manipulação de listas, integração entre diferentes módulos
e análise de complexidade dos principais algoritmos utilizados.

Os testes realizados permitiram verificar individualmente os módulos e
também o funcionamento integrado do sistema.