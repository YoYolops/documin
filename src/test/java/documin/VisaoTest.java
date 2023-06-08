package documin;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import documin.models.Documento;
import documin.repositories.VisaoRepository;

public class VisaoTest {
    VisaoRepository driver;
    Documento documentoDefault;

    @BeforeEach
    public void setUp() {
        driver = new VisaoRepository();
        documentoDefault = new Documento("Titulo doc");
    }

    @AfterEach
    public void tearDown() {
        driver = null;
        documentoDefault = null;
    }

    @Test
    public void criarVisaoSemDocumento() {
        assertThrows(IllegalArgumentException.class, () -> driver.criarVisao(null, "completa"));
        assertThrows(IllegalArgumentException.class, () -> driver.criarVisao(null, "resumida"));
        assertThrows(IllegalArgumentException.class, () -> driver.criarVisao(null, "titulo"));
    }

    @Test
    public void criarVisaoComTipoInexistente() {
        assertThrows(RuntimeException.class, () -> driver.criarVisao(documentoDefault, "tipo inexistente"));
    }

    @Test
    public void criarVisaoValida() {
        int idDaVisaoCriada = driver.criarVisao(documentoDefault, 1);
        assertEquals(1, idDaVisaoCriada);
    }
}
