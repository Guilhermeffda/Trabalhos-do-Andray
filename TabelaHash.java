class TabelaHash {
    private No[] tabela;
    private int tamanho;
    private int funcaoHash;
    private long colisoes;

    public TabelaHash(int tamanho, int funcaoHash) {
        this.tamanho = tamanho;
        this.funcaoHash = funcaoHash;
        this.tabela = new No[tamanho];
        this.colisoes = 0;
    }

    private int hash1(int chave) {
        return chave % tamanho;
    }

    private int hash2(int chave) {
        double A = 0.6180339887;
        return (int) (tamanho * ((chave * A) % 1));
    }

    private int hash3(int chave) {
        int hash = 0;
        while (chave > 0) {
            hash ^= (chave & 0xFF);
            chave >>= 8;
        }
        return hash % tamanho;
    }

    private int calcularHash(int chave) {
        switch (funcaoHash) {
            case 1: return hash1(chave);
            case 2: return hash2(chave);
            case 3: return hash3(chave);
            default: return hash1(chave);
        }
    }

    public void inserir(Registro registro) {
        int indice = calcularHash(registro.codigo);
        No novoNo = new No(registro);
        if (tabela[indice] == null) {
            tabela[indice] = novoNo;
        } else {
            No atual = tabela[indice];
            while (atual.proximo != null) {
                colisoes++;
                atual = atual.proximo;
            }
            atual.proximo = novoNo;
            colisoes++;
        }
    }

    public int buscar(int codigo) {
        int indice = calcularHash(codigo);
        No atual = tabela[indice];
        int comparacoes = 0;
        while (atual != null) {
            comparacoes++;
            if (atual.registro.codigo == codigo) {
                return comparacoes;
            }
            atual = atual.proximo;
        }
        return comparacoes;
    }

    public long getColisoes() {
        return colisoes;
    }
}