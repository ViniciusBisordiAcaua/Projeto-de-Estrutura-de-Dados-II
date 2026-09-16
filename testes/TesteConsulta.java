import arvore.ArvoreTernaria;
import consulta.Consulta;

import java.util.ArrayList;
import java.util.List;

public class TesteConsulta {

    public static void main(String[] args) {

        System.out.println("=== TESTE DE CONSULTAS ===");

        ArvoreTernaria arvore =
                new ArvoreTernaria();

        // arquivo1
        arvore.inserir("contrato", "arquivo1.txt");
        arvore.inserir("compra", "arquivo1.txt");
        arvore.inserir("casa", "arquivo1.txt");

        // arquivo2
        arvore.inserir("contrato", "arquivo2.txt");
        arvore.inserir("venda", "arquivo2.txt");
        arvore.inserir("multa", "arquivo2.txt");

        // arquivo3
        arvore.inserir("compra", "arquivo3.txt");
        arvore.inserir("venda", "arquivo3.txt");
        arvore.inserir("casa", "arquivo3.txt");

        // Universo necessario para o operador NAO
        List<String> universo = new ArrayList<>();

        universo.add("arquivo1.txt");
        universo.add("arquivo2.txt");
        universo.add("arquivo3.txt");

        Consulta consulta =
                new Consulta(arvore, universo);

        // Pesquisa simples
        testar(consulta, "contrato");

        // E
        testar(consulta, "contrato E compra");

        // OU
        testar(consulta, "contrato OU compra");

        // NAO
        testar(consulta, "NAO multa");

        // E + NAO
        testar(consulta, "contrato E NAO multa");

        // Teste de precedencia
        testar(consulta, "contrato OU venda E casa");

        // Palavra inexistente
        testar(consulta, "responsabilidade");

        System.out.println("\n=== FIM DO TESTE ===");
    }

    private static void testar(
            Consulta consulta,
            String expressao) {

        System.out.println(
                "\nConsulta: " + expressao
        );

        List<String> resultado =
                consulta.executar(expressao);

        if (resultado.isEmpty()) {
            System.out.println(
                    "Nenhum arquivo encontrado."
            );
        } else {
            for (String arquivo : resultado) {
                System.out.println("- " + arquivo);
            }
        }
    }
}