import java.util.Random;

public class Main {
    private static final int[] TAMANHOS = {10000, 100000, 1000000};
    private static final int[] QUANTIDADES = {1000000, 3000000, 5000000};
    private static final int NUM_FUNCOES_HASH = 3;
    private static final int NUM_BUSCAS = 5;
    private static final long SEED = 42;

    public static void main(String[] args) {
        Random random = new Random(SEED);

        for (int tamanho : TAMANHOS) {
            for (int quantidade : QUANTIDADES) {
                Registro[] dados = gerarDados(quantidade, random);

                for (int funcaoHash = 1; funcaoHash <= NUM_FUNCOES_HASH; funcaoHash++) {
                    TabelaHash tabela = new TabelaHash(tamanho, funcaoHash);

                    // Inserção
                    long tempoInicio = System.nanoTime();
                    for (Registro r : dados) {
                        tabela.inserir(r);
                    }
                    long tempoFim = System.nanoTime();
                    long tempoInsercao = (tempoFim - tempoInicio) / 1_000_000; // Convertendo para milissegundos
                    long colisoes = tabela.getColisoes();

                    // Busca
                    long tempoBuscaTotal = 0;
                    int comparacoesTotal = 0;
                    for (int i = 0; i < NUM_BUSCAS; i++) {
                        int codigoBusca = dados[random.nextInt(dados.length)].codigo;
                        tempoInicio = System.nanoTime();
                        int comparacoes = tabela.buscar(codigoBusca);
                        tempoFim = System.nanoTime();
                        tempoBuscaTotal += (tempoFim - tempoInicio);
                        comparacoesTotal += comparacoes;
                    }
                    double tempoBuscaMedio = (tempoBuscaTotal / (double)NUM_BUSCAS) / 1_000_000.0; // Convertendo para milissegundos com precisão decimal
                    int comparacoesMedio = comparacoesTotal / NUM_BUSCAS;

                    System.out.printf("Tamanho: %d, Quantidade: %d, Função Hash: %d\n", tamanho, quantidade, funcaoHash);
                    System.out.printf("Tempo de Inserção: %d ms, Colisões: %d\n", tempoInsercao, colisoes);
                    System.out.printf("Tempo Médio de Busca: %.3f ms, Comparações Médias: %d\n\n", tempoBuscaMedio, comparacoesMedio);
                }
            }
        }
    }

    private static Registro[] gerarDados(int quantidade, Random random) {
        Registro[] dados = new Registro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            dados[i] = new Registro(100000000 + random.nextInt(900000000)); // Gera códigos de 9 dígitos
        }
        return dados;
    }
}