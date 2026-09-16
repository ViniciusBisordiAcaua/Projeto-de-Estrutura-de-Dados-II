import arvore.ArvoreTernaria;
import indexacao.Indexador;
import indexacao.LeitorArquivos;
import indexacao.Normalizador;

import java.util.List;

public class TesteIndexacao {

    public static void main(String[] args) {

        System.out.println("=== TESTE DE NORMALIZACAO ===");

        testarNormalizacao("Venda");
        testarNormalizacao("VENDA");
        testarNormalizacao("venda,");
        testarNormalizacao("venda.");
        testarNormalizacao("venda;");
        testarNormalizacao("CONTRATO");
        testarNormalizacao("contrato.");

        System.out.println("\n=== TESTE DE LEITURA ===");

        List<String> arquivos =
                LeitorArquivos.obterCaminhosArquivos();

        System.out.println(
                "Arquivos .txt encontrados: " + arquivos.size()
        );

        for (String arquivo : arquivos) {
            System.out.println("- " + arquivo);
        }

        System.out.println("\n=== TESTE DE INDEXACAO ===");

        ArvoreTernaria arvore =
                Indexador.construirIndice();

        testarBusca(arvore, "contrato");
        testarBusca(arvore, "compra");
        testarBusca(arvore, "venda");

        // Palavra que pode nao existir
        testarBusca(arvore, "palavrainexistente");

        System.out.println("\n=== FIM DO TESTE ===");
    }

    private static void testarNormalizacao(String palavra) {

        String resultado =
                Normalizador.normalizar(palavra);

        System.out.println(
                palavra + " -> " + resultado
        );
    }

    private static void testarBusca(
            ArvoreTernaria arvore,
            String palavra) {

        System.out.println(
                "\nBusca no indice: " + palavra
        );

        List<String> resultado =
                arvore.buscar(
                        Normalizador.normalizar(palavra)
                );

        if (resultado.isEmpty()) {
            System.out.println("Nenhum arquivo encontrado.");
        } else {
            for (String arquivo : resultado) {
                System.out.println("- " + arquivo);
            }
        }
    }
}