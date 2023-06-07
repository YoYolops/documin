package documin.models.Visao;

import documin.models.Documento;
import documin.models.Elemento.ElementoAbstract;

public class VisaoCompleta implements VisaoInterface {
    private int id;
    private String[] representacoes;

    public VisaoCompleta(Documento documento, int id) {
        if(documento == null) throw new IllegalArgumentException("Não pode construir visão de um documento null");

        this.id = id;
        ElementoAbstract[] elementosDoDocumento = documento.getElementos();
        representacoes = new String[elementosDoDocumento.length];
        int indexCounter = 0;

        for(ElementoAbstract elemento : elementosDoDocumento) {
            if(elemento == null) break;
            representacoes[indexCounter] = elemento.gerarRepresentacaoCompleta();
            indexCounter += 1;
        }
    }

    public String[] exibir() {
        return this.representacoes;
    }

    public int getId() { return this.id; }
}
