package consulta;

import arvore.ArvoreTernaria;
import indexacao.Normalizador;
import java.util.ArrayList;
import java.util.List;

public class Consulta {

    private final ArvoreTernaria arvore; // Instancia com indice invertido
    private final List<String> universoArquivos; // Conjunto completo

    public Consulta(ArvoreTernaria arvore, List<String> universoArquivos) {
        this.arvore = arvore;
        this.universoArquivos = universoArquivos != null ? universoArquivos : new ArrayList<>();
    }

    public List<String> executar(String expressao) {
        if (expressao == null || expressao.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        // Divide a entrada com base em espaços ou tab
        String[] partes = expressao.trim().split("\\s+");
        List<String> tokens = new ArrayList<>();
        for (String p : partes) {
            if (!p.trim().isEmpty()) {
                tokens.add(p.trim());
            }
        }
        if (tokens.isEmpty()) {
            return new ArrayList<>();
        }

        // Consulta simples == 1 palavra
        if (tokens.size() == 1 && !tokens.get(0).equalsIgnoreCase("NAO")) {
            String termo = Normalizador.normalizar(tokens.get(0));
            return arvore.buscar(termo);
        }
        return avaliarExpressao(tokens); // Avalia a expressão
    }

    private List<String> avaliarExpressao(List<String> tokens) {
        // CAMADA 1: Prioridade maxima
        // Aqui separa palavras normais e blocos "NAO palavra"
        List<Object> intermediarioNao = new ArrayList<>();
        
        for (int i = 0; i < tokens.size(); i++) {
            String token = tokens.get(i);

            // Operadores binarios continuam como String para as proximas etapas
            if (token.equalsIgnoreCase("E") || token.equalsIgnoreCase("OU")) {
                intermediarioNao.add(token.toUpperCase());
            } 
            // Tratamento do NAO
            else if (token.equalsIgnoreCase("NAO")) {
                // O operador "NAO" afeta o termo na direita
                if (i + 1 < tokens.size()) {
                    i++; // Avanca o ponteiro pra consumir a palavra apos o "NAO"
                    String termo = Normalizador.normalizar(tokens.get(i));
                    List<String> comPalavra = arvore.buscar(termo); // Busca os arquivos que contem o termo na arvore
                    List<String> negado = OperacoesBooleanas.nao(universoArquivos, comPalavra); // Nao carregar os que tem o termo
  
                    // Trata operadores onde tem termo antes do "NAO" sem o "E" explícito
                    if (!intermediarioNao.isEmpty() && !(intermediarioNao.get(intermediarioNao.size() - 1) instanceof String)) {
                        intermediarioNao.add("E");
                    }
                    intermediarioNao.add(negado);
                }
            } else {
                String termo = Normalizador.normalizar(token);
                List<String> arquivos = arvore.buscar(termo);
                intermediarioNao.add(arquivos);
            }
        }

        // Camada 2: Operador E (Prioridade media) 
        // Percorre a lista resolvendo todas as interseções
        List<Object> intermediarioE = new ArrayList<>();
        int i = 0;
        while (i < intermediarioNao.size()) {
            Object atual = intermediarioNao.get(i);

            if (atual instanceof String && atual.equals("E")) {
                // Remove o operando anterior à esquerda do operador "E"
                @SuppressWarnings("unchecked")
                List<String> anterior = (List<String>) intermediarioE.remove(intermediarioE.size() - 1);
                
                i++; // Avança para o operando à direita do operador "E"
                if (i < intermediarioNao.size()) {
                    @SuppressWarnings("unchecked")
                    List<String> proximo = (List<String>) intermediarioNao.get(i);
                    
                    // Executa a interseção (A intersec B) e armazena o resultado
                    intermediarioE.add(OperacoesBooleanas.e(anterior, proximo));
                }
            } else {
                intermediarioE.add(atual);
            }
            i++;
        }

        // Camada 3: Operador OU (Menor precedencia)
        // Nesta fase, restam apenas listas e marcadores "OU".
        // Todos os blocos restantes são unificados acumulando uniões.
        List<String> resultadoFinal = new ArrayList<>();
        boolean primeiroElemento = true;

        for (Object item : intermediarioE) {
            // Pula a string delimitadora "OU"
            if (item instanceof String && item.equals("OU")) {
                continue;
            }

            @SuppressWarnings("unchecked")
            List<String> listaAtual = (List<String>) item;

            if (primeiroElemento) {
                resultadoFinal = new ArrayList<>(listaAtual);
                primeiroElemento = false;
            } else {
                // Realiza a união sem duplicar arquivos no resultado acumulado
                resultadoFinal = OperacoesBooleanas.ou(resultadoFinal, listaAtual);
            }
        }

        return resultadoFinal;
    }
}