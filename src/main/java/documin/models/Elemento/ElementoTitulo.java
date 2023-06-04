package documin.models.Elemento;

import java.util.Map;

public class ElementoTitulo extends ElementoAbstract {
    public ElementoTitulo(int prioridade, String valor, int nivel, boolean linkavel) {
        super(prioridade, valor);
        if(nivel < 1 || nivel > 5) throw new IllegalArgumentException("Valor do nível é inválido");


        final Map<String, String> propriedades = this.getPropriedades();
        propriedades.put("nivel", Integer.toString(nivel));
        propriedades.put("linkavel", Boolean.toString(linkavel));
    }

    public String gerarRepresentacaoCompleta() {
        final Map<String, String> propriedades = this.getPropriedades();
        return propriedades.get("nivel") + 
            ". " + this.getValor() + " -- " +
            propriedades.get("nivel")+this.getValor().toUpperCase().replaceAll("\\s+","");
    }

    public String gerarRepresentacaoResumida() {
        return this.getPropriedades().get("nivel") + 
        ". " + this.getValor();
    }
}
