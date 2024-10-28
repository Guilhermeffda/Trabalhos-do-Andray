package trabalho_tabela_hash;

public class No {

    // Aqui definimos o No, ele representa uma lista encadeada

    Registro registro;
    No proximo;

    public No(Registro registro) {

        this.registro = registro;
        this.proximo = null;

    }

}
