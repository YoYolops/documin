package documin.repositories;

import java.util.ArrayList;
import java.util.List;

import documin.models.Documento;
import documin.models.Visao.VisaoCompleta;
import documin.models.Visao.VisaoInterface;
import documin.models.Visao.VisaoPrioritaria;
import documin.models.Visao.VisaoResumida;
import documin.models.Visao.VisaoTitulos;

public class VisaoRepository {
    List<VisaoInterface> visoes;
    int idCounter;

    public VisaoRepository() {
        visoes = new ArrayList<VisaoInterface>();
        idCounter = 0;
    }

    public int criarVisao(Documento doc, String tipoDeVisao) {
        int novoIdGerado = generateId();

        switch(tipoDeVisao) {
            case "completa": visoes.add(new VisaoCompleta(doc, novoIdGerado)); break;
            case "resumida": visoes.add(new VisaoResumida(doc, novoIdGerado)); break;
            case "titulo": visoes.add(new VisaoTitulos(doc, novoIdGerado)); break;
            default: throw new RuntimeException("Erro ao criar visão, incapaz de decidir que tipo de visão deve ser criada");
        }

        return novoIdGerado;
    }

    public int criarVisao(Documento doc, int prioridade) {
        int novoIdGerado = generateId();
        visoes.add(new VisaoPrioritaria(doc, prioridade, novoIdGerado));
        return novoIdGerado;
    }

    public int generateId() {
        idCounter += 1;
        return idCounter;
    }

    public VisaoInterface getVisaoById(int id) {
        for(VisaoInterface visao : visoes) {
            if(visao.getId() == id) return visao;
        }
        return null;
    }
}
