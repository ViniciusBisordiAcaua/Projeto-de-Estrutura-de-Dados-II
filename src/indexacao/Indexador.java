package indexacao;

import arvore.ArvoreTernaria; // Importa a TST
import java.util.List;

public class Indexador {

    /**
     * Realiza a leitura de todos os arquivos da pasta 'entradas', 
     * normaliza as palavras e as insere na Árvore Digital Ternária.
     * 
     * @return A Árvore Ternária já indexada e pronta para consultas.
     */
    
    public static ArvoreTernaria construirIndice() {
        System.out.println("INDICE INVERTIDO");
        
        // pega a lista com o caminho de todos os arquivos .txt na pasta 'entradas'
        List<String> caminhosArquivos = LeitorArquivos.obterCaminhosArquivos();
        
        System.out.println("Arquivos encontrados: " + caminhosArquivos.size());
        System.out.println("Construindo indice...");
        ArvoreTernaria tst = new ArvoreTernaria();

        // passa por cada arquivo encontrado
        for (String caminho : caminhosArquivos) {
            // pega apenas o nome do arquivo (ex: "processo_001.txt")
            String nomeArquivo = extrairNomeArquivo(caminho);
            // lê todo o conteúdo do arquivo
            String conteudo = LeitorArquivos.lerConteudoArquivo(caminho);
            if (!conteudo.isEmpty()) {
                // quebra o texto em palavras brutas usando espaços ou quebras de linha
                String[] palavrasBrutas = conteudo.split("\\s+");
                // passa cada palavra pelo Normalizador e insere na TST
                for (String palavraBruta : palavrasBrutas) {
                    String palavraLimpa = Normalizador.normalizar(palavraBruta);
                    // só insere se a palavra limpa não estiver vazia
                    if (!palavraLimpa.isEmpty()) {
                        // chama o método de inserção da TST
                        tst.inserir(palavraLimpa, nomeArquivo);
                    }
                }
            }
        }

        System.out.println("Indice construido com sucesso.");
        return tst; // retorna a árvore pronta para usar nas consultas
    }

    /**
     * Extra: para isolar apenas o nome do arquivo a partir do caminho completo.
     * Ex: "entradas/processo_001.txt" vira "processo_001.txt"
     */
    private static String extrairNomeArquivo(String caminhoCompleto) {
        java.nio.file.Path p = java.nio.file.Paths.get(caminhoCompleto);
        return p.getFileName().toString();
    }
}