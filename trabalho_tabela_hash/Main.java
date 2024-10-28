package Trabalho_tabela_Hash;

import java.util.Random;

public class Main {
    private static final int[] TAMANHOS = {10000, 100000, 1000000};
    private static final int[] QUANTIDADES = {1000000, 5000000, 20000000};
    private static final int NUM_FUNCOES_HASH = 3;
    private static final int NUM_BUSCAS = 5;

    public static void main(String[] args) {
        Random random = new Random(42); // Seed fixa para reprodutibilidade

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
                    long tempoInsercao = tempoFim - tempoInicio;
                    int colisoes = tabela.getColisoes();

                    // Busca
                    long tempoBuscaTotal = 0;
                    for (int i = 0; i < NUM_BUSCAS; i++) {
                        int codigoBusca = dados[random.nextInt(dados.length)].codigo;
                        tempoInicio = System.nanoTime();
                        tabela.buscar(codigoBusca);
                        tempoFim = System.nanoTime();
                        tempoBuscaTotal += (tempoFim - tempoInicio);
                    }
                    long tempoBuscaMedio = tempoBuscaTotal / NUM_BUSCAS;

                    System.out.printf("Tamanho: %d, Quantidade: %d, Função Hash: %d\n", tamanho, quantidade, funcaoHash);
                    System.out.printf("Tempo de Inserção: %d ns, Colisões: %d\n", tempoInsercao, colisoes);
                    System.out.printf("Tempo Médio de Busca: %d ns\n\n", tempoBuscaMedio);
                }
            }
        }
    }

    private static Registro[] gerarDados(int quantidade, Random random) {
        Registro[] dados = new Registro[quantidade];
        for (int i = 0; i < quantidade; i++) {
            dados[i] = new Registro(random.nextInt(1000000000));
        }
        return dados;
    }
}