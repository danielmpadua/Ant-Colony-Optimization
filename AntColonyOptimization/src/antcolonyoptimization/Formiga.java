package antcolonyoptimization;

import java.util.ArrayList;

public class Formiga {
    private ArrayList<Vertice> caminho_percorrido = new ArrayList<Vertice>(); // array "dinamico" do java.
    private float custo_percurso=0;
    private Vertice vertice_atual;
    private int contador=0;

    public int getContador() {
        return contador;
    }

    public Vertice getVerticeAtual() {
        return vertice_atual;
    }

    public void setVerticeAtual(Vertice vertice_atual) {
        this.vertice_atual = vertice_atual;
    }

    public float getCustoPercurso() {
        return custo_percurso;
    }

    public void setCustoPercurso(float valor) {
        this.custo_percurso = custo_percurso + valor;
    }
    
    public Vertice getCaminhoPercorrido(int posicao){ // para não usa a função get do ArrayList diretamente (mais amigavel).
        return caminho_percorrido.get(posicao); // retorna o vertice na posicao indicada do array.
    }
    

    public void setCaminhoPercorrido(Vertice valor){ // para não usa a função add do ArrayList diretamente (mais amigavel).
        caminho_percorrido.add(valor); // adiciona o vertice no array.
        contador ++;
    }
    
    public void resetarFormiga(Vertice origem){
        this.caminho_percorrido.clear();
        this.contador = 0;
        this.setVerticeAtual(origem);
        this.setCaminhoPercorrido(origem);
        
    }
}

    