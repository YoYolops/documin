package documin.models.Visao;

import documin.models.Documento;
import documin.models.Elemento.ElementoAbstract;

public class VisaoResumida implements VisaoInterface {
    private String conteudo = "";
    private int id;
    private String[] representacoes;

    public VisaoResumida(Documento documento, int id) {
        if(documento == null) throw new IllegalArgumentException("Não pode construir visão de um documento null");

        this.id = id;
        ElementoAbstract[] elementosDoDocumento = documento.getElementos();
        representacoes = new String[elementosDoDocumento.length];
        int indexCounter = 0;

        for(ElementoAbstract elemento : elementosDoDocumento) {
            if(elemento == null) break;
            representacoes[indexCounter] = elemento.gerarRepresentacaoResumida();
            indexCounter += 1;
        }
    }

    public String[] exibir() {
        return this.representacoes;
    }

    public int getId() { return this.id; }
}
