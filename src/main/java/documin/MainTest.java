package documin;

import documin.models.Elemento.ElementoAbstract;
import documin.models.Elemento.ElementoLista;
import documin.models.Elemento.ElementoTitulo;

public class MainTest {
    public static void main(String args[]) {
        ElementoAbstract elemento = new ElementoTitulo(1, "Aqui jaz yo", 3, false);
        System.out.println(elemento.getClass());
        System.out.println(elemento.getClass().getSimpleName());
    }
}
