package documin.models.Visao;

import documin.models.Documento;
import documin.models.Elemento.ElementoAbstract;

public class VisaoPrioritaria implements VisaoInterface {
    private String conteudo = "";
    private int id;

    public VisaoPrioritaria(Documento documento, int prioridade, int id) {
        if(documento == null) throw new IllegalArgumentException("Não pode construir visão de um documento null");
        this.id = id;
        ElementoAbstract[] elementosDoDocumentos = documento.getElementos();

        for(ElementoAbstract elemento : elementosDoDocumentos) {
            if(elemento == null) break;
            if(elemento.getPrioridade() >= prioridade) conteudo += elemento.gerarRepresentacaoResumida() + "\n";
        }
    }

    public String exibir() {
        return this.conteudo;
    }

    public int getId() { return this.id; }
}
