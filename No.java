
// Aqui definimos o No, ele representa uma lista encadeada

class No {
    Registro registro;
    No proximo;

    public No(Registro registro) {
        this.registro = registro;
        this.proximo = null;
    }
}

