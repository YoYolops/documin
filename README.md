# DocuMin   


O DocuMin é uma estrutura mínima para a criação de documentos. Cada documento é representado por uma sequência de elementos. Um elemento pode representar um texto, um tópico, uma lista, um conjunto de termos ou até um documento.

Todos os elementos têm características em comum, como a relevância do elemento e propriedades, mas cada elemento pode ter também aspectos únicos. Nesse sistema, será preciso criar e armazenar documentos, bem como adicionar, editar ou remover elementos de um documento.

Além dos documentos, é possível criar visões de um documento. Uma visão representa uma forma alternativa de apresentar um documento. Por exemplo, uma visão pode mostrar apenas os elementos relevantes. Outra visão pode mostrar apenas os elementos de tópicos.

Para fazer o sistema, implemente a classe de Facade abaixo. Essa classe representa apenas um ponto de entrada para as demais classes do sistema. A chamada a um método de um Facade pode simplesmente repassar essa chamada para algum dos controles do sistema. Você pode importar qualquer classe adicional a Facade, mas deve manter os mesmos métodos indicados abaixo.

```java
documin.Facade
package documin;

// import documin.documento.DocumentoController

public class Facade {

    // private DocumentoController documentoController;

    public Facade() {
        // // exemplo de chamada no construtor:
        // this.documentoController = new DocumentoController();
    }

    public boolean criarDocumento(String titulo) {
        // // exemplo de chamada a ser implementado
        //  return this.documentoController.criarDocumento(titulo);
    }

}
```


Você é livre para organizar as classes e controllers da maneira que achar mais adequada, inclusive criando os métodos, classes e atributo que quiser. Só é importante que respeite as seguintes regras:
A classe Facade deve estar no pacote "documin"
As assinaturas dos métodos não devem ser alterados
A classe Facade deve ter apenas 1 construtor sem parâmetros

## Documentos
Um documento é identificado por um título. Um título é uma identificação única que pode ser composta por quaisquer caracteres, exceto string vazia ou formada apenas por espaços. O título identifica unicamente o documento. Ao criar um documento é opcional a passagem de um tamanho. Quando presente, o tamanho delimita o número de elementos que um documento pode ter e, caso não seja passado, o documento pode ter uma quantidade ilimitada de elementos.

Caso o título já esteja cadastrado o método retorna apenas "false". Caso o tamanho seja inválido (tamanho menor ou igual a zero), a exceção IllegalArgumentException deve ser lançada. Por fim, se o documento for criado com sucesso, o método retorna true.

É possível também remover um documento do sistema a partir de seu título. Além disso, deve ser possível retornar o número de elementos cadastrados em um documento. Para essa operação, o número retornado inicialmente é 0.

Por fim, um documento pode retornar sua representação em um array de strings que representam seus elementos. Cada componente do array é a representação textual do elemento na mesma posição daquele documento. Ainda, o array deve ter o mesmo tamanho do número de elementos presentes no documento (independente do seu limite de tamanho). Caso o documento não tenha elementos, isto retorna um array vazio.

Em qualquer operação que exija um título de um documento, a exceção IllegalArgumentException deve ser lançada caso o título seja uma string vazia ou composta apenas de espaços. Em qualquer operação em que se espera que o documento esteja cadastrado, a exceção NoSuchElementException deve ser lançada caso não esteja presente.

Operações na Facade:
- boolean criarDocumento(String titulo)
- boolean criarDocumento(String titulo, int tamanhoMaximo)
- void removerDocumento(String titulo)
- int contarElementos(String titulo)
- String[] exibirDocumento(String titulo)


## Elementos do Documento
Um documento é composto por uma sequência de elementos de diferentes tipos. Cada elemento pode ter um valor, propriedades e prioridade e podem ser exibidos de duas formas distintas: uma versão completa e uma versão resumida.


Todo elemento em um documento têm um conjunto de atributos:
- prioridade: valor inteiro entre 1-5 (inclusive), indicando elementos de menor prioridade (1) até os de maior prioridade (5)
- valor: uma string representando os dados desse elemento
- propriedades: um mapa de strings para strings que representam propriedades particulares de cada elemento

Além disso, é possível executar as seguintes operações em cada elemento:
- gerar representação completa: retorna uma string que representa a visão completa de um elemento
- gerar representação resumida: retorna uma string com uma representação resumida de um elemento

Considerando o contexto do elemento no documento, deve ser possível realizar as seguintes operações:
- criar elemento: cria o elemento na posição imediatamente depois do último elemento criado. Retorna a posição do elemento. A primeira posição de um elemento é zero.
- mover elemento uma posição acima: troca a posição do elemento com a do elemento imediatamente vizinho mais próximo do início do documento. Caso o documento esteja na primeira posição, ele não é afetado.
- mover elemento uma posição abaixo: troca a posição do elemento com a do elemento imediatamente vizinho mais próximo do final do documento. Caso o documento esteja no final do documento, ele não é afetado.
- apagar elemento do documento: remove o elemento do documento. não existe 'posição vazia' entre elementos, mesmo após a remoção.

Por fim, existem 4 tipos básicos de elementos, com suas respectivas propriedades e representações:

![image](https://github.com/YoYolops/documin/assets/66336628/e8086899-fb77-48f3-8505-da53ceaeb41d)

Veja os exemplos abaixo:

![image](https://github.com/YoYolops/documin/assets/66336628/21c64d4e-881e-4fe3-91e6-867fc245ac44)

Algumas observações:
O link do título é gerado com a concatenação do nível com o valor textual em maiúsculo e sem espaços
A ordem alfabética dos termos ignora a capitalização da palavra
Em caso de empate na ordem de tamanho (palavras de mesmo tamanho) deve ser mantida a ordem em que os termos aparecem inicialmente no valor

Operações na Facade:
- int criarTexto(String tituloDoc, String valor, int prioridade)
- int criarTitulo(String tituloDoc, String valor, int prioridade, int nivel, boolean linkavel)
- int criarLista(String tituloDoc, String valorLista, int prioridade, String separador, String charLista)
- int criarTermos(String tituloDoc, String valorTermos, int prioridade, String separador, String ordem)
- String pegarRepresentacaoCompleta(String tituloDoc, int elementoPosicao)
- String pegarrepresentacaoResumida(String tituloDoc, int elementoPosicao)
- boolean apagarElemento(String tituloDoc, int elementoPosicao)
- void moverParaCima(String tituloDoc, int elementoPosicao)
- void moverParaBaixo(String tituloDoc, int elementoPosicao)


## Atalhos - Documentos como elementos
Atalho é um tipo de elemento. Um elemento de "Atalho" é a representação de um documento como elemento de outro documento. Apenas um documento sem atalhos pode se tornar um atalho (compor um elemento de atalho) e, um documento que é atalho não pode ter atalhos adicionados. Ao tentar fazer essas operações em um documento, uma IllegalStateException deve ser lançada.

Os atributos do Atalho são gerados automaticamente a partir do documento:
- prioridade (1-5) : média das prioridades dos elementos do documento referenciado
- valor : ID do documento referenciado
- representação completa: concatenação das representações completas dos elementos internos de prioridade 4 e 5
- representação resumo: concatenação das representações resumidas dos elementos internos de prioridade 4 e 5

Operações na Facade:
- int criarAtalho(String tituloDoc, String tituloDocReferenciado)
- Visão de um Documento
- Deve ser possível exportar o documento através de uma "visão". Existem 4 tipos de visão, que representam maneiras distintas de exibir um documento. Cada visão é cadastrada com um número sequencial (iniciando em 0).

Existem 4 tipos de visualização, onde cada uma retorna uma String[], de acordo com os tipos da visão definidos a seguir:
- Completa: …é a representação completa de cada elemento do documento referenciado;
- Resumida: …é a representação resumida de cada elemento do documento referenciado;
- Prioritária: …é a representação completa de cada elemento do documento referenciado que tenha prioridade maior (ou igual) que um determinado valor informado como parâmetro;
- Títulos: …é a representação resumida de cada elemento do tipo título.

Operações na Facade:
- int criarVisaoCompleta(String tituloDoc)
- int criarVisaoResumida(String tituloDoc)
- int criarVisaoPrioritaria(String tituloDoc, int prioridade)
- int criarVisaoTitulo(String tituloDoc)
- String[] exibirVisao(int visaoId)


# Visão de um Documento
Deve ser possível exportar o documento através de uma "visão". Existem 4 tipos de visão, que representam maneiras distintas de exibir um documento. Cada visão é cadastrada com um número sequencial (iniciando em 0).

Existem 4 tipos de visualização, onde cada uma retorna uma String[], de acordo com os tipos da visão definidos a seguir:
- Completa: …é a representação completa de cada elemento do documento referenciado;
- Resumida: …é a representação resumida de cada elemento do documento referenciado;
- Prioritária: …é a representação completa de cada elemento do documento referenciado que tenha prioridade maior (ou igual) que um determinado valor informado como parâmetro;
- Títulos: …é a representação resumida de cada elemento do tipo título.

Operações na Facade:
- int criarVisaoCompleta(String tituloDoc)
- int criarVisaoResumida(String tituloDoc)
- int criarVisaoPrioritaria(String tituloDoc, int prioridade)
- int criarVisaoTitulo(String tituloDoc)
- String[] exibirVisao(int visaoId)
