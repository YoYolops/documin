package documin.models.Elemento;

import java.util.Map;

public abstract class ElementoAbstract {
    private int prioridade;
    private String valor;
    private Map<String, String> propriedades;

    public ElementoAbstract(int prioridade, String valor) {
        this.prioridade = prioridade;
        this.valor = valor;
    }

    public int getPrioridade() { return this.prioridade; }
    public String getValor() { return this.valor; }
    public Map<String, String> getPropriedades() { return this.propriedades; }

    public void setPrioridade(int valor) { this.prioridade = valor; }
    public void setValor(String valor) { this.valor = valor; }

    public abstract String gerarRepresentacaoCompleta();
    public abstract String gerarRepresentacaoResumida();
}
