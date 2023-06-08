package documin;

import org.junit.jupiter.api.Test;

import documin.Facade;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

public class AppTest {
    private Facade driver;

    @BeforeEach
    public void setUp() {
        driver = new Facade();
    }

    @AfterEach
    public void tearDown() {
        driver = null;
    }

    @Test
    public void criarDocumentoSemTamanhoDefinido() {
        boolean isCreated = driver.criarDocumento("Documento teste");
        assertEquals(isCreated, true);
    }

    @Test
    public void criarDocumentoComTamanhoDefinido() {
        boolean isCreated = driver.criarDocumento("Documento teste", 12);
        assertEquals(true, isCreated);
    }

    @Test
    public void criarDocumentoComValoresInvalidos() {
        boolean isCreatedSemTitulo = driver.criarDocumento("");
        boolean isCreatedComTamanhoInvalido = driver.criarDocumento("Titulo valido", 0);

        assertEquals(false, isCreatedSemTitulo);
        assertEquals(false, isCreatedComTamanhoInvalido);
    }
}
