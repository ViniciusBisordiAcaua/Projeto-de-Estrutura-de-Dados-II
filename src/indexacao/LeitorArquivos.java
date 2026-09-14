// vai ler os arquivos txt linha por linha

package indexacao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LeitorArquivos {
    // caminho da pasta de entradas em relação à esse arquivo
    private static final String DIRETORIO_ENTRADAS = "entradas";

    public static List<String> obterCaminhosArquivos() {
        List<String> caminhos = new ArrayList<>();
        Path dir = Paths.get(DIRETORIO_ENTRADAS);

        try { // tenta executar a leitura com segurança
            if (Files.exists(dir) && Files.isDirectory(dir)) { // confere se a pasta entradas realmente existe e se é um diretório válido
                Files.list(dir).forEach(path -> { // olha dentro da pasta e pega cada item que está lá dentro
                    String nomeArquivo = path.getFileName().toString(); 
                    if (Files.isRegularFile(path) && nomeArquivo.endsWith(".txt")) { // só vai pegar arquivos que terminam com a extensão .txt
                        caminhos.add(path.toString());
                    }
                });
            } else {
                System.err.println("Aviso: A pasta 'entradas' não foi encontrada na raiz do projeto.");
            }
        } catch (IOException e) { // se der algum problema 
            System.err.println("Erro ao listar os arquivos da pasta entradas: " + e.getMessage());
        }

        return caminhos; // devolve a lista pronta com o caminho dos arquivos
    }

    /**
     * Lê todo o conteúdo de um arquivo específico dado o seu caminho.
     */
    public static String lerConteudoArquivo(String caminhoArquivo) {
        try {
            Path path = Paths.get(caminhoArquivo); 
            List<String> linhas = Files.readAllLines(path); // lê todas as linhas de uma vez de dentro do arquivo e joga numa lista de linhas
            return String.join(" ", linhas); // ao inves de ter linhas, elas viram uma so com espaço separando
        } catch (IOException e) { // se o arquivo não for encontrado ou foi corrompido
            System.err.println("Erro ao ler o arquivo: " + caminhoArquivo);
            return "";
        }
    }
}