import arvore.ArvoreTernaria;
import java.util.List;

public class TesteArvore {

    public static void main(String[] args) {

        System.out.println("=== TESTE DA ARVORE TERNARIA ===");

        ArvoreTernaria arvore = new ArvoreTernaria();

        // Insercoes
        arvore.inserir("contrato", "processo_001.txt");
        arvore.inserir("contrato", "processo_002.txt");

        arvore.inserir("compra", "processo_001.txt");
        arvore.inserir("compra", "processo_003.txt");

        arvore.inserir("venda", "processo_003.txt");

        arvore.inserir("aluguel", "processo_004.txt");

        // Teste de duplicacao
        arvore.inserir("contrato", "processo_001.txt");
        arvore.inserir("contrato", "processo_001.txt");

        testarBusca(arvore, "contrato");
        testarBusca(arvore, "compra");
        testarBusca(arvore, "venda");
        testarBusca(arvore, "aluguel");

        // Palavra inexistente
        testarBusca(arvore, "multa");

        System.out.println("\n=== FIM DO TESTE ===");
    }

    private static void testarBusca(
            ArvoreTernaria arvore,
            String palavra) {

        List<String> resultado = arvore.buscar(palavra);

        System.out.println("\nBusca: " + palavra);

        if (resultado.isEmpty()) {
            System.out.println("Nenhum arquivo encontrado.");
        } else {
            for (String arquivo : resultado) {
                System.out.println("- " + arquivo);
            }
        }
    }
}