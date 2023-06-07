package documin.models.Visao;

import documin.models.Documento;
import documin.models.Elemento.ElementoAbstract;

public class VisaoTitulos implements VisaoInterface {
    private String conteudo = "";
    private int id;
    private String[] representacoes;

    public VisaoTitulos(Documento documento, int id) {
        if(documento == null) throw new IllegalArgumentException("Não pode construir visão de um documento null");

        this.id = id;
        ElementoAbstract[] elementosDoDocumentos = documento.getElementos();
        int indexCounter = 0;
        representacoes = new String[elementosDoDocumentos.length];

        for(ElementoAbstract elemento : elementosDoDocumentos) {
            if(elemento == null) break;
            if(elemento.getClass().getSimpleName().equals("ElementoTitulo")) {
                representacoes[indexCounter] = elemento.gerarRepresentacaoResumida();
            }
            indexCounter += 1;
        }
    }

    public String[] exibir() {
        return this.representacoes;
    }

    public int getId() { return this.id; }
}
