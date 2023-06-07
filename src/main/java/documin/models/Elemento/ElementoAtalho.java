package documin.models.Elemento;

import documin.models.Documento;

public class ElementoAtalho extends ElementoAbstract {
    private Documento documentoAtalhado;

    public ElementoAtalho(Documento documentoAtalhado /* documento que o atalho representa */) {
        super(calcularPrioridadeDoAtalho(documentoAtalhado), calcularValor(documentoAtalhado));
        this.documentoAtalhado = documentoAtalhado;
    }

    private static int calcularPrioridadeDoAtalho(Documento doc) {
        int somatorioPrioridades = 0;
        int quantidadeTermos = 0;
        for(ElementoAbstract elemento : doc.getElementos()) {
            if(elemento == null) break;
            somatorioPrioridades += elemento.getPrioridade();
            quantidadeTermos+=1;
        }
        return (somatorioPrioridades/quantidadeTermos);
    }

    private static String calcularValor(Documento doc) {
        return doc.getTitulo();
    }

    public String gerarRepresentacaoCompleta() {
        String representacaoGerada = "";
        for(ElementoAbstract elemento :  this.documentoAtalhado.getElementos()) {
            if(elemento.getPrioridade() >= 4) representacaoGerada += elemento.gerarRepresentacaoCompleta() + "\n";
        }
        return representacaoGerada;
    }   

    public String gerarRepresentacaoResumida() {
        String representacaoGerada = "";
        for(ElementoAbstract elemento :  this.documentoAtalhado.getElementos()) {
            if(elemento.getPrioridade() >= 4) representacaoGerada += elemento.gerarRepresentacaoResumida() + "\n";
        }
        return representacaoGerada;
    }
}
