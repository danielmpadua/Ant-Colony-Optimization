package antcolonyoptimization;

public class Configuracao {
    private Vertice origem; // origem do grafo.
    private Vertice destino; // objetivo onde a formiga deve chegar.
    private String nome_arquivo;
    private float taxa_evaporacao; // P (letra grega ro)
    private int qtd_formigas;
    private int qtd_interacoes;

    public Vertice getOrigem() {
        return origem;
    }

    public void setOrigem(Vertice origem) {
        this.origem = origem;
    }

    public Vertice getDestino() {
        return destino;
    }

    public void setDestino(Vertice destino) {
        this.destino = destino;
    }

    public String getNomeArquivo() {
        return nome_arquivo;
    }

    public void setNomeArquivo(String nome_arquivo) {
        this.nome_arquivo = nome_arquivo;
    }

    public float getTaxaEvaporacao() {
        return taxa_evaporacao;
    }

    public void setTaxaEvaporacao(double taxa_evaporacao) { // a main considera numeros como 0.07 do tipo double
        this.taxa_evaporacao = (float)taxa_evaporacao;
    }

    public int getQtdFormigas() {
        return qtd_formigas;
    }

    public void setQtdFormigas(int qtd_formigas) {
        this.qtd_formigas = qtd_formigas;
    }

    public int getQtdInteracoes() {
        return qtd_interacoes;
    }

    public void setQtdInteracoes(int qtd_interacoes) {
        this.qtd_interacoes = qtd_interacoes;
    }
}
