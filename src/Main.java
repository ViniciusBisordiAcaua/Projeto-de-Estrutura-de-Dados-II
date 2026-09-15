import arvore.ArvoreTernaria;
import consulta.Consulta;
import indexacao.Indexador;
import indexacao.LeitorArquivos;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // 1. Constrói o índice chamando o método do colega (sem alterar nada lá)
        ArvoreTernaria tst = Indexador.construirIndice();

        // 2. Obtém os caminhos usando LeitorArquivos e extrai apenas os nomes dos arquivos
        List<String> caminhos = LeitorArquivos.obterCaminhosArquivos();
        List<String> todosArquivos = new ArrayList<>();
        
        if (caminhos != null) {
            for (String caminho : caminhos) {
                String nomeArquivo = Paths.get(caminho).getFileName().toString();
                if (!todosArquivos.contains(nomeArquivo)) {
                    todosArquivos.add(nomeArquivo);
                }
            }
        }

        // 3. Instancia a sua classe de consulta passando a TST e o universo de arquivos
        Consulta consulta = new Consulta(tst, todosArquivos);

        // 4. Loop interativo no terminal conforme a Seção 9 do enunciado
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Digite uma consulta:");
            System.out.print("> ");

            String entrada = scanner.nextLine();

            // Condição de saída
            if (entrada != null && entrada.trim().equalsIgnoreCase("sair")) {
                System.out.println("Programa encerrado.");
                break;
            }

            // Ignora entradas em branco
            if (entrada == null || entrada.trim().isEmpty()) {
                continue;
            }

            // Executa a busca através das operações booleanas
            List<String> resultados = consulta.executar(entrada);

            // Exibição dos resultados encontrados
            System.out.println("Arquivos encontrados:");
            if (resultados.isEmpty()) {
                System.out.println("Nenhum arquivo encontrado para a consulta.");
            } else {
                for (String nomeArquivo : resultados) {
                    System.out.println(nomeArquivo);
                }
            }
            System.out.println(); // Linha em branco separadora
        }

        scanner.close();
    }
}