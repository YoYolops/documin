package documin;

import org.junit.jupiter.api.Test;

import documin.models.Documento;
import documin.repositories.DocumentoRepository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class DocumentoTest {
    DocumentoRepository driver;
    Documento singleDocDriver;

    @BeforeEach
    public void setUp() {
        driver = new DocumentoRepository();
        singleDocDriver = new Documento("Documento teste", 3);
    }

    @AfterEach
    public void tearDown() {
        driver = null;
        singleDocDriver = null;
    }

    @Test
    public void criarDocumentoValido() {
        boolean isCreated = driver.criarDocumento("Titulo valido");
        Documento documentoCriado = driver.getDocumentoPorTitulo("Titulo valido");

        assertEquals(true, isCreated);
        assertEquals("Titulo valido", documentoCriado.getTitulo());
    }

    @Test
    public void deletarDocumento() {
        boolean isCreated = driver.criarDocumento("Titulo valido");
        Documento documentoCriado = driver.getDocumentoPorTitulo("Titulo valido");

        assertEquals(true, isCreated);
        assertEquals("Titulo valido", documentoCriado.getTitulo());

        driver.removerDocumento("Titulo valido");
        assertEquals(null, driver.getDocumentoPorTitulo("Titulo valido"));
    }

    @Test
    public void criarDocumentoInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new Documento("  "));
        assertThrows(IllegalArgumentException.class, () -> new Documento("", 3));
        assertThrows(IllegalArgumentException.class, () -> new Documento("Titulo valido", 0));
    }

    @Test
    public void inserirMultiplosElementosEmDocumentoAteEstourarLimite() {
        int indiceConteudo1 = singleDocDriver.criarElemento("Conteudo1 do elemento", 3);
        int indiceConteudo2 = singleDocDriver.criarElemento("Conteudo2 do elemento", 3);
        int indiceConteudo3 = singleDocDriver.criarElemento("Conteudo3 do elemento", 3);

        assertEquals(0, indiceConteudo1);
        assertEquals(1, indiceConteudo2);
        assertEquals(2, indiceConteudo3);
        assertThrows(Error.class, () -> singleDocDriver.criarElemento("Conteudo4 do elemento", 4));
    }

    @Test
    public void exibirElementosDoDoc() {
        singleDocDriver.criarElemento("Conteudo1 do elemento", 3);
        singleDocDriver.criarElemento("Conteudo2 do elemento", 3);
        singleDocDriver.criarElemento("Conteudo3 do elemento", 3);

        assertEquals(3, singleDocDriver.exibir().length);
    }

    @Test
    public void moverElementosDoDocumentoParaBaixo() {
        int indiceInicialConteudo1 = singleDocDriver.criarElemento("Conteudo1 do elemento", 3);
        int indiceInicialConteudo2 = singleDocDriver.criarElemento("Conteudo2 do elemento", 3);
        int indiceInicialConteudo3 = singleDocDriver.criarElemento("Conteudo3 do elemento", 3);

        int novoIndiceElemento3MovidoAbaixo = singleDocDriver.moverElementoUmaPosicaoParaBaixo(indiceInicialConteudo3);
        assertEquals(1, novoIndiceElemento3MovidoAbaixo);
        
        assertEquals(0, indiceInicialConteudo1);
        int novoIndiceElemento1MovidoAbaixo = singleDocDriver.moverElementoUmaPosicaoParaBaixo(indiceInicialConteudo1);
        assertEquals(0, novoIndiceElemento1MovidoAbaixo);
    }

    @Test
    public void moverElementosDoDocumentoParaCima() {
        int indiceInicialConteudo1 = singleDocDriver.criarElemento("Conteudo1 do elemento", 3);
        int indiceInicialConteudo2 = singleDocDriver.criarElemento("Conteudo2 do elemento", 3);
        int indiceInicialConteudo3 = singleDocDriver.criarElemento("Conteudo3 do elemento", 3);

        int novoIndiceElemento3MovidoAcima = singleDocDriver.moverElementoUmaPosicaoParaCima(indiceInicialConteudo3);
        assertEquals(2, novoIndiceElemento3MovidoAcima);
        
        assertEquals(0, indiceInicialConteudo1);
        int novoIndiceElemento1MovidoAbaixo = singleDocDriver.moverElementoUmaPosicaoParaCima(indiceInicialConteudo1);
        assertEquals(1, novoIndiceElemento1MovidoAbaixo);
    }

    @Test
    public void apagarElemento() {
        singleDocDriver.criarElemento("Conteudo1 do elemento", 3);
        singleDocDriver.criarElemento("Conteudo2 do elemento", 3);
        assertEquals(2, singleDocDriver.getElementos().length);

        singleDocDriver.apagarElemento(0);
        assertEquals(1, singleDocDriver.getElementos().length);
    }
}
