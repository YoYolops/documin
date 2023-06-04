package documin.models.Elemento;

public class ElementoTexto extends ElementoAbstract {
    public ElementoTexto(int prioridade, String valor) {
        super(prioridade, valor);
    }

    public String gerarRepresentacaoCompleta() { return this.getValor(); }
    public String gerarRepresentacaoResumida() { return this.getValor(); }
}
