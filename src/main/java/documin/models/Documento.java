package documin.models;

import java.util.ArrayList;
import java.util.List;

import documin.models.Elemento.ElementoAbstract;
import documin.models.Elemento.ElementoAtalho;
import documin.models.Elemento.ElementoLista;
import documin.models.Elemento.ElementoTermos;
import documin.models.Elemento.ElementoTexto;
import documin.models.Elemento.ElementoTitulo;

public class Documento {
    // Elementos já nascem associados a um Documento
    private String titulo;
    private ElementoAbstract[] elementos;
    private int tamanhoMaximo = 0;
    private boolean isAtalho = false;

    public Documento(String titulo) {
        if(titulo.trim().equals("")) throw new IllegalArgumentException("Documentos precisam de título válido");
        this.titulo = titulo;
    }

    public Documento(String titulo, int tamanho) {
        if(titulo.trim().equals("")) throw new IllegalArgumentException("Documentos precisam de título válido");
        if(tamanho <= 0) throw new IllegalArgumentException("Tamanho de documento inválido");
        
        this.titulo = titulo;
        this.tamanhoMaximo = tamanho;
        this.elementos = new ElementoAbstract[tamanho];
    }

    public int criarElemento(String valor, int prioridade) {
        ElementoTexto novoElementoTexto = new ElementoTexto(prioridade, valor);
        return inserirNovoElemento(novoElementoTexto);
    }

    public int criarElemento(String valor, int prioridade, int nivel, boolean linkavel) {
        ElementoTitulo novoElementoTitulo = new ElementoTitulo(prioridade, valor, nivel, linkavel);
        return inserirNovoElemento(novoElementoTitulo);
    }

    public int criarElemento(String valorLista, int prioridade, String separador, String charLista) {
        ElementoLista novoElementoLista = new ElementoLista(prioridade, valorLista, separador, charLista);
        return inserirNovoElemento(novoElementoLista);
    }

    public int criarElemento(String valorTermos, String separador, String ordem, int prioridade) {
        ElementoTermos novoElementoTermos = new ElementoTermos(prioridade, valorTermos, separador, ordem);
        return inserirNovoElemento(novoElementoTermos);
    }

    public int criarElemento(Documento documento) {
        if(this.isAtalho) throw new IllegalStateException("Um documento que é atalho não pode compor outro atalho");
        ElementoAtalho novoElementoAtalho = new ElementoAtalho(documento); 
        this.isAtalho = true;
        return inserirNovoElemento(novoElementoAtalho);
    }

    public int moverElementoUmaPosicaoParaCima(int posicaoElemento) {
        if(posicaoElemento >= elementos.length || elementos[posicaoElemento] == null) throw new NullPointerException("Impossível mover elemento para cima, posição inválida");
        if(posicaoElemento == elementos.length-1) return posicaoElemento;
        ElementoAbstract[] novoArrayElementos = new ElementoAbstract[elementos.length];

        for(int i = 0; i < elementos.length; i++) {
            if(i == posicaoElemento) novoArrayElementos[i] = elementos[i+1];
            else if(i == posicaoElemento+1) novoArrayElementos[i] = elementos[i-1];
            else novoArrayElementos[i] = elementos[i];
        }
        elementos = novoArrayElementos;
        return posicaoElemento+1;
    }

    public int moverElementoUmaPosicaoParaBaixo(int posicaoElemento) {
        if(posicaoElemento < 0 || elementos[posicaoElemento] == null) throw new NullPointerException("Impossível mover elemento para baixo, posição inválida");
        if(posicaoElemento == 0) return posicaoElemento;
        ElementoAbstract[] novoArrayElementos = new ElementoAbstract[elementos.length];

        for(int i = 0; i < elementos.length; i++) {
            if(i == posicaoElemento-1) novoArrayElementos[i] = elementos[i+1];
            else if(i == posicaoElemento) novoArrayElementos[i] = elementos[i-1];
            else novoArrayElementos[i] = elementos[i];
        }
        elementos = novoArrayElementos;
        return posicaoElemento-1;
    }

    public boolean apagarElemento(int posicaoElemento) {
        if(posicaoElemento < 0 || posicaoElemento == elementos.length  || elementos[posicaoElemento] == null) throw new NullPointerException("Impossível apagar elemento, posição inválida");
        ElementoAbstract[] novoArrayElementos = new ElementoAbstract[elementos.length];
        boolean isDeleted = false;

        for(int i = 0; i < elementos.length; i++) {
            if(i == posicaoElemento) {
                isDeleted = true;
                continue;
            }
            if(isDeleted) novoArrayElementos[i-1] = elementos[i];
            else novoArrayElementos[i] = elementos[i];
        }
        elementos = novoArrayElementos;
        return isDeleted;
    }

    private int inserirNovoElemento(ElementoAbstract novoElemento) {
        if(tamanhoMaximo != 0 && elementos[elementos.length-1] != null) throw new Error("Número máximo de elementos nesse documento já foi atingido");
        else if(tamanhoMaximo == 0 && elementos[elementos.length-1] != null) {
            ElementoAbstract[] novoArrayElementos = new ElementoAbstract[elementos.length+1];
            novoArrayElementos[novoArrayElementos.length-1] = novoElemento;
            elementos = novoArrayElementos;
            return novoArrayElementos.length-1;
        }
        else {
            int indexCounter = 0;
            for(ElementoAbstract elemento : elementos) {
                if(elemento == null) elementos[indexCounter] = novoElemento;
                indexCounter += 1;
            }
            return indexCounter;
        }
    }

    public String[] exibir() {
        ElementoAbstract[] elementosCopia = this.getElementos();
        String[] representacoes = new String[elementosCopia.length];
        int indexCounter = 0;

        for(ElementoAbstract elemento : elementosCopia) representacoes[indexCounter] = elemento.getValor();
        return representacoes;
    }

    public ElementoAbstract[] getElementos() { //limpa os nulls do array antes de retorná-lo
        List<ElementoAbstract> listaElementos = new ArrayList<ElementoAbstract>();
        for(ElementoAbstract elemento : elementos) {
            if(elemento != null) listaElementos.add(elemento);
            else break;
        }

        ElementoAbstract[] returnalArray = new ElementoAbstract[listaElementos.size()];
        listaElementos.toArray(returnalArray);
        return returnalArray;
    }

    public String getTitulo() { return this.titulo; }
}
