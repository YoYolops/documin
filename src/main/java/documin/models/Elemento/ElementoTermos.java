package documin.models.Elemento;

import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;

public class ElementoTermos extends ElementoAbstract {
    
    public ElementoTermos(int prioridade, String valor, String separador, String ordem) {
        super(prioridade, valor);
        if(!ordem.equals("NENHUM") || !ordem.equals("ALFABÉTICA") || !ordem.equals("TAMANHO")) throw new IllegalArgumentException("Ordem dos termos inválida");

        final Map<String, String> propriedades = this.getPropriedades();
        propriedades.put("separador", separador);
        propriedades.put("ordem", ordem);
    }

    public String gerarRepresentacaoCompleta() {
        List<String> valoresOrdenados = handleSort(this.getPropriedades().get("ordem"), this.getValor().split(" "));
        String representacao = "Total termos: " + valoresOrdenados.size() + "\n" + "- ";

        for(int i = 0; i < valoresOrdenados.size(); i++) {
            representacao += valoresOrdenados.get(i);
            if(i+1 < valoresOrdenados.size()) representacao += ", ";
        }

        return representacao;
    }

    public String gerarRepresentacaoResumida() {
        final Map<String, String> propriedades = this.getPropriedades();
        List<String> valoresOrdenados = handleSort(propriedades.get("ordem"), this.getValor().split(" "));
        String representacao = "";

        for(int i = 0; i < valoresOrdenados.size(); i++) {
            representacao += valoresOrdenados.get(i);
            if(i+1 < valoresOrdenados.size()) representacao += " " + propriedades.get("separador") + " ";
        }

        return representacao;
    }

    private List<String> ordenarValoresAlfabeticamente(String[] valores) {
        List<String> valoresList = Arrays.asList(valores);
        Comparator<String> comparaAlfabeticamente = (str1, str2) -> str1.toLowerCase().compareTo(str2.toLowerCase());
        Collections.sort(valoresList, comparaAlfabeticamente);
        return valoresList;
    }

    private List<String> ordenarValoresPorTamanho(String[] valores) {
        List<String> valoresList = Arrays.asList(valores);
        Comparator<String> comparaPorTamanho = (str1, str2) -> str1.length() - str2.length();
        Collections.sort(valoresList, comparaPorTamanho);
        return valoresList;
    }

    private List<String> handleSort(String sortOption, String[] valores) {
        switch (sortOption) {
            case "NENHUM": return Arrays.asList(valores);
            case "ALFABÉTICA": return ordenarValoresAlfabeticamente(valores);
            case "TAMANHO": return ordenarValoresPorTamanho(valores);
            default: throw new IllegalArgumentException("Esperava um tipo de ordem especificado, mas não encontrou nenhum");
        }
    }
}
