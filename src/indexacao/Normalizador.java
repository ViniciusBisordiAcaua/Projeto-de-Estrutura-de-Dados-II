// recebe uma palavra e converte tudo para minúsculo, remove pontuações e acentos 

package indexacao;

import java.text.Normalizer

public class Normalizador {
    public static String normalizar(String palavra) {
        // se a variavel estiver vazia (com a segunda parte sendo se tirando as estremidades vazia ela fica vazia)
        if (palavra == null || palavra.trim().isEmpty()) { 
            return ""; // basicamente vai retornar null só que em string
        } 
        // resultado é a palavra em minusculo
        String resultado = palavra.toLowerCase();
        // resultado é a palavra em minusculo e sem acento
        resultado = Normalizer.normalize(resultado, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        //resultado é a palavra em minusculo, sem acento e sem pontuação e caracteres especias 
        resultado = resultado.replaceAll("^[^a-z0-9]+|[^a-z0-9]+$", "");

        return resultado.trim(); // trim() remove os espaços vazios (com espaço) que estão na estremidade
    }
}