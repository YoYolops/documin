package documin.models;

import documin.models.Elemento.ElementoAbstract;

public class Documento {
    private String titulo;
    private ElementoAbstract[] elementos;

    public Documento(String titulo) {
        if(titulo.trim().equals("")) throw new IllegalArgumentException("Documentos precisam de título válido");
        this.titulo = titulo;
    }

    public Documento(String titulo, int tamanho) {
        if(titulo.trim().equals("")) throw new IllegalArgumentException("Documentos precisam de título válido");
        if(tamanho <= 0) throw new IllegalArgumentException("Tamanho de documento inválido");
        
        this.titulo = titulo;
        this.elementos = new ElementoAbstract[tamanho];
    }

    public int criarElemento() {
        return 1;
    }

    public int moverElementoUmaPosicaoParaCima() {

    }

    public int moverElementoUmaPosicaoParaBaixo() {

    }

    public int apagarElemento() {
        
    }
}
