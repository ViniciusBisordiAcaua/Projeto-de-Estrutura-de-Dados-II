package arvore;

import java.util.ArrayList;
import java.util.List;

public class ArvoreTernaria {

    private No raiz;

    public ArvoreTernaria() {
        raiz = null;
    }

    // ===============================
    // INSERÇÃO
    // ===============================

    public void inserir(String palavra, String arquivo) {

        if (palavra == null || palavra.isEmpty()) {
            return;
        }

        if (arquivo == null || arquivo.isEmpty()) {
            return;
        }

        raiz = inserir(raiz, palavra, 0, arquivo);
    }

    private No inserir(No no, String palavra, int indice, String arquivo) {

        char caractereAtual = palavra.charAt(indice);

        // Se o nó ainda não existe, cria
        if (no == null) {
            no = new No(caractereAtual);
        }

        // Caractere menor -> esquerda
        if (caractereAtual < no.caractere) {

            no.esquerda = inserir(
                    no.esquerda,
                    palavra,
                    indice,
                    arquivo
            );

        }

        // Caractere maior -> direita
        else if (caractereAtual > no.caractere) {

            no.direita = inserir(
                    no.direita,
                    palavra,
                    indice,
                    arquivo
            );

        }

        // Caractere igual
        else {

            // Ainda existem caracteres na palavra
            if (indice < palavra.length() - 1) {

                no.meio = inserir(
                        no.meio,
                        palavra,
                        indice + 1,
                        arquivo
                );

            }

            // Chegamos ao último caractere da palavra
            else {

                no.fimDaPalavra = true;

                no.adicionarArquivo(arquivo);
            }
        }

        return no;
    }


    // ===============================
    // BUSCA
    // ===============================

    public List<String> buscar(String palavra) {

        List<String> resultado = new ArrayList<>();

        if (palavra == null || palavra.isEmpty()) {
            return resultado;
        }

        No noEncontrado = buscarNo(
                raiz,
                palavra,
                0
        );

        // Só é uma palavra válida se estiver marcada
        // como fim da palavra
        if (noEncontrado != null && noEncontrado.fimDaPalavra) {

            resultado.addAll(
                    noEncontrado.arquivosAssociados
            );
        }

        return resultado;
    }

    private No buscarNo(No no, String palavra, int indice) {

        if (no == null) {
            return null;
        }

        char caractereAtual = palavra.charAt(indice);

        // Menor -> esquerda
        if (caractereAtual < no.caractere) {

            return buscarNo(
                    no.esquerda,
                    palavra,
                    indice
            );

        }

        // Maior -> direita
        else if (caractereAtual > no.caractere) {

            return buscarNo(
                    no.direita,
                    palavra,
                    indice
            );

        }

        // Igual
        else {

            // Se chegamos ao último caractere
            if (indice == palavra.length() - 1) {

                return no;
            }

            // Continua pelo meio
            return buscarNo(
                    no.meio,
                    palavra,
                    indice + 1
            );
        }
    }


    // ===============================
    // VERIFICAR SE PALAVRA EXISTE
    // ===============================

    public boolean contemPalavra(String palavra) {

        if (palavra == null || palavra.isEmpty()) {
            return false;
        }

        No noEncontrado = buscarNo(
                raiz,
                palavra,
                0
        );

        return noEncontrado != null
                && noEncontrado.fimDaPalavra;
    }


    // ===============================
    // VERIFICAR SE A ÁRVORE ESTÁ VAZIA
    // ===============================

    public boolean estaVazia() {
        return raiz == null;
    }
}