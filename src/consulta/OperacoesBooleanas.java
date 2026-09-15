package consulta;

import java.util.ArrayList;
import java.util.List;

public class OperacoesBooleanas {

    // Operador E: Interseção (apenas os arquivos que estão em ambas as listas)
    public static List<String> e(List<String> a, List<String> b) {
        List<String> resultado = new ArrayList<>();

        // Se alguma das listas for nula ou vazia, a interseção fica vazia
        if (a == null || b == null || a.isEmpty() || b.isEmpty()) {
            return resultado;
        }

        // Passa pela lista A e só adiciona se estiver em B e não for duplicado
        for (String arq : a) {
            if (b.contains(arq) && !resultado.contains(arq)) {
                resultado.add(arq);
            }
        }

        return resultado;
    }

    // Operador OU: União (junta todos os arquivos sem repetir)
    public static List<String> ou(List<String> a, List<String> b) {
        List<String> resultado = new ArrayList<>();

        // Adiciona os arquivos da lista A sem duplicatas
        if (a != null) {
            for (String arq : a) {
                if (arq != null && !resultado.contains(arq)) {
                    resultado.add(arq);
                }
            }
        }

        // Adiciona os arquivos da lista B sem duplicar o que já entrou
        if (b != null) {
            for (String arq : b) {
                if (arq != null && !resultado.contains(arq)) {
                    resultado.add(arq);
                }
            }
        }

        return resultado;
    }

    // Operador NAO: Diferença (pega a base e remove o que tem no segundo termo)
    public static List<String> nao(List<String> base, List<String> remover) {
        List<String> resultado = new ArrayList<>();

        // Se a lista base for vazia, não tem o que retornar
        if (base == null || base.isEmpty()) {
            return resultado;
        }

        // Se não tem nada para remover, copia a base inteira
        if (remover == null || remover.isEmpty()) {
            for (String arq : base) {
                if (arq != null && !resultado.contains(arq)) {
                    resultado.add(arq);
                }
            }
            return resultado;
        }

        // Percorre a base e só adiciona se NÃO estiver na lista de remoção
        for (String arq : base) {
            if (arq != null && !remover.contains(arq) && !resultado.contains(arq)) {
                resultado.add(arq);
            }
        }

        return resultado;
    }
}