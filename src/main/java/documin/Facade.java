package documin;

import documin.repositories.DocumentoRepository;
import documin.repositories.VisaoRepository;

// import documin.documento.DocumentoController

public class Facade {
    private DocumentoRepository repositorioDocumentos;
    private VisaoRepository repositorioVisoes;

    public Facade() {
        this.repositorioDocumentos = new DocumentoRepository();
        this.repositorioVisoes = new VisaoRepository();
    }

    public boolean criarDocumento(String titulo) {
        try {return this.repositorioDocumentos.criarDocumento(titulo);}
        catch(Exception error) { handleErrors(error); return false; }
    }

    public boolean criarDocumento(String titulo, int tamanhoMaximo) {
        try {return this.repositorioDocumentos.criarDocumento(titulo, tamanhoMaximo);}
        catch(Exception error) { handleErrors(error); return false; }
    }

    public void removerDocumento(String titulo) {
        repositorioDocumentos.removerDocumento(titulo);
    }

    public int contarElementos(String titulo) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(titulo)
            .getElementos().length;
    }

    public String[] exibirDocumento(String titulo) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(titulo)
            .exibir();
    }

    public int criarTexto(String tituloDoc, String valor, int prioridade) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .criarElemento(valor, prioridade);
    }
    public int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .criarElemento(valor, prioridade, nivel, linkavel);
    }
    public int criarLista(String tituloDoc, String valorLista, int prioridade, String separador, String charLista) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .criarElemento(valorLista, prioridade, separador, charLista);
    }
    public int criarTermos(String tituloDoc, String valorTermos, int prioridade, String separador, String ordem) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .criarElemento(valorTermos, separador, ordem, prioridade);
    }

    public String pegarRepresentacaoCompleta(String tituloDoc, int elementoPosicao) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .getElementos()[elementoPosicao]
            .gerarRepresentacaoCompleta();
    }
    public String pegarrepresentacaoResumida(String tituloDoc, int elementoPosicao) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .getElementos()[elementoPosicao]
            .gerarRepresentacaoResumida();
    }
    public boolean apagarElemento(String tituloDoc, int elementoPosicao) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .apagarElemento(elementoPosicao);
    }
    public void moverParaCima(String tituloDoc, int elementoPosicao) {
        repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .moverElementoUmaPosicaoParaCima(elementoPosicao);
    }
    public void moverParaBaixo(String tituloDoc, int elementoPosicao) {
        repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .moverElementoUmaPosicaoParaBaixo(elementoPosicao);
    }

    public int criarAtalho(String tituloDoc, String tituloDocAtalhado) {
        return repositorioDocumentos
            .getDocumentoPorTitulo(tituloDoc)
            .criarElemento(repositorioDocumentos.getDocumentoPorTitulo(tituloDocAtalhado));
    }

    public int criarVisaoCompleta(String tituloDoc) {
        return repositorioVisoes
            .criarVisao(repositorioDocumentos.getDocumentoPorTitulo(tituloDoc), "completa");
    };
    public int criarVisaoResumida(String tituloDoc) {
        return repositorioVisoes
            .criarVisao(repositorioDocumentos.getDocumentoPorTitulo(tituloDoc), "resumida");
    };
    public int criarVisaoPrioritaria(String tituloDoc, int prioridade) {
        return repositorioVisoes
            .criarVisao(repositorioDocumentos.getDocumentoPorTitulo(tituloDoc), prioridade);
    };
    public int criarVisaoTitulo(String tituloDoc) {
        return repositorioVisoes
            .criarVisao(repositorioDocumentos.getDocumentoPorTitulo(tituloDoc), "titulo");
    };
    String[] exibirVisao(int visaoId) {
        return repositorioVisoes
            .getVisaoById(visaoId)
            .exibir();
    };

    private void handleErrors(Exception erro) {
        System.out.println(erro.getMessage());
    }
}
