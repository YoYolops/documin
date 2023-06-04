package documin.models.Elemento;

import java.util.Map;

public class ElementoLista extends ElementoAbstract {
    public ElementoLista(int prioridade, String valor, String separador, String caractereDeLista) {
        super(prioridade, valor);
        final Map<String, String> propriedades = this.getPropriedades();
        propriedades.put("separador", separador);
        propriedades.put("caractereDeLista", caractereDeLista);
    }

    public String gerarRepresentacaoCompleta() {
        String[] splittedValor = this.getValor().split(" ");
        String representacao = "";

        for(String str : splittedValor) {
            representacao += this.getPropriedades().get("caractereDeLista") + " " + str + "\n";
        }

        return representacao;
    }
    public String gerarRepresentacaoResumida() {
        String[] splittedValor = this.getValor().split(" ");
        String representacao = "";

        for(int i = 0; i < splittedValor.length; i++) {
            representacao += splittedValor[i];
            if(i+1 < splittedValor.length) representacao += " " + this.getPropriedades().get("separador") + " ";
        }

        return representacao;
    }
}
