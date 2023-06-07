package documin.repositories;

import java.util.ArrayList;
import java.util.List;

import documin.models.Documento;

public class DocumentoRepository {
    List<Documento> documentos;

    public DocumentoRepository() {
        documentos = new ArrayList<Documento>();
    }

    public Documento getDocumentoPorTitulo(String tituloDoc) {
        for(Documento documento : documentos) {
            if(documento.getTitulo().equals(tituloDoc)) return documento;
        }
        
        return null;
    }

    public boolean criarDocumento(String titulo) {
        if(verificarSeTituloJaExiste(titulo)) return false;
        documentos.add(new Documento(titulo));
        return true;
    }

    public boolean criarDocumento(String titulo, int tamanhoMaximo) {
        if(verificarSeTituloJaExiste(titulo)) return false;
        documentos.add(new Documento(titulo, tamanhoMaximo));
        return true;
    }

    private boolean verificarSeTituloJaExiste(String tituloDoc) {
        for(Documento documento : documentos) {
            if(documento.getTitulo().equals(tituloDoc)) return true;
        }
        return false;
    }
}
