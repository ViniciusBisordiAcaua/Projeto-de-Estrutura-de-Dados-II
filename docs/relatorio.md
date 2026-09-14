# Relatório - Índice Invertido com Árvore Digital Ternária

## 1. Objetivo

O projeto tem como objetivo desenvolver um sistema de busca
de palavras em arquivos de texto utilizando uma Árvore Digital Ternária.

## 2. Estrutura do projeto

O projeto foi dividido em três módulos:

- Leitura e indexação
- Árvore Digital Ternária
- Consultas booleanas

## 3. Árvore Digital Ternária

Cada nó possui:

- caractere
- ponteiro esquerda
- ponteiro meio
- ponteiro direita
- indicador de fim da palavra
- lista de arquivos associados

## 4. Inserção

A inserção compara o caractere atual da palavra com o caractere
armazenado no nó.

- menor: esquerda
- maior: direita
- igual: meio

## 5. Busca

A busca utiliza o mesmo princípio da inserção.

## 6. Complexidade

### Inserção

O caso médio de Inserção é de O(M + L), na qual 'M' é o tamanho da palavra, e 'L' é o custo de redimensionamento da lista de arquivos associados. - O tempo total para inserir uma ocorrência de uma palavra na árvore é a soma dessas duas etapas: 'M' + 'L'. Como essas duas operações acontecem de forma sequencial para cada inserção, a complexidade total resulta em O(M + L).

### Busca

O caso médio de Busca é O(M) - Assim como na inserção, o processo de busca navega exatamente pelos caracteres da palavra consultada.

### Indexação

O caso médio de Indexação é O(T * M), na qual 'T' é o número total de palavras brutas extraídas de todos os documentos. - Durante a indexação de todo o corpus de documentos, o sistema processa um total de 'T' palavras brutas extraídas dos arquivos. Cada uma dessas palavras passa pelo normalizador e é inserida individualmente na árvore com custo proporcional ao seu tamanho 'M'

## 7. Consultas booleanas

Foram implementados os operadores:

- E
- OU
- NAO

## 8. Conclusão

...