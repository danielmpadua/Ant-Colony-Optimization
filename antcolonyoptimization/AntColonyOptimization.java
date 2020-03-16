/*
================================================
||       Ant Colony Optimization (ACO)        ||
||                                            ||
|| Alunos: Daniel Mariano Pádua               ||
||         Lucas dos Anjos                    ||
||                                            ||
|| Engenharia de Computação - Turma 4         ||
||                                            ||
|| Instituto Federal de Minas Gerais - Bambuí ||
||                                            ||
================================================
*/



package antcolonyoptimization;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class AntColonyOptimization {

    public static void main(String[] args) {
  //----------------------------------------------- CONFIGURAÇÕES ------------------------------------------------------        
        Vertice origem = new Vertice();
        origem.setId(1);
        Vertice destino = new Vertice();
        destino.setId(100);
        
        Configuracao config = new Configuracao();
        config.setNomeArquivo("grafo2.txt");
        config.setDestino(destino);
        config.setOrigem(origem);
        config.setQtdFormigas(50);
        config.setQtdInteracoes(3000);
        config.setTaxaEvaporacao(0.07);
        
        
            
        try{ // Se ler o arquivo, executará o programa.
            
 //------------------------------- LEITURA DE ARQUIVO E CRIAÇÃO DA MATRIZ GRAFO ------------------------------------------             
            FileInputStream arquivo = new FileInputStream(config.getNomeArquivo()); // Seleciona o arquivo (endereço)
            InputStreamReader input = new InputStreamReader(arquivo);               // Lê o arquivo
            BufferedReader br = new BufferedReader(input);                          // Lê por linha

            String linha;
            int [][]grafo = new int[9000][3];   // Matriz do arquivo lido.
            int contador= 0;                    // Numero da linha

            do{
               linha = br.readLine();
               if(linha != null){               // Enquanto houver conteudo

                   String[] palavras = linha.split("	"); // Divide a linha de acordo com os espaços encontrados

                   for(int i=0; i< palavras.length; i++){   // Tamanho do vetor de palaras (numero de colunas)
                       if(contador>0){                      // Ignora a primeira linha que possui palavras, e não números.
                           grafo[contador-1][i] = Integer.parseInt(palavras[i]); // Converte o numero que era String para int, e joga ele na matriz.                      
                       }  
                   } 
               }
               contador++;
            }while(linha != null);  // Executa o laço enquanto houver conteudo
            arquivo.close();        // Fecha o arquivo.
//--------------------------------------------- INICIANDO O PROGRAMA ---------------------------------------------------- 
            
            float evaporacao = (1-config.getTaxaEvaporacao());                   
            //======================================== Iniciando a Matriz de Feromonio ============================================================================             
            float[][] feromonios = new float[contador-1][8]; // Matriz que contem feromonio e probabilidade                
            for(int i=0; i<contador-2; i++){ // Inicializando matriz de feromônio
                feromonios[i][5]= (float)0.01;  //Feromonio
            }
            //============================================= Interações ==================================================================
            for(int a=0; a<config.getQtdInteracoes();a++){ // Interaçoes a serem feitas.
                //======================================== Iniciando a Matriz de Feromonio ============================================================================                             
            for(int i=0; i<contador-2; i++){ // Inicializando matriz de feromônio e probabilidade            
                feromonios[i][0]= (int)i;                                        //ID
                feromonios[i][1]= (int)grafo[i][0];                              //Vertice i
                feromonios[i][2]= (int)grafo[i][1];                              //Vertice j
                feromonios[i][3]= (int)grafo[i][2];                              //Peso
                feromonios[i][4]= (float)1/grafo[i][2];                          //Custo
                feromonios[i][6]= (float)feromonios[i][5]*feromonios[i][4];      //Custo*Feromonio
            }
                
                ArrayList<Integer> todos_caminho_percorrido = new ArrayList<Integer>();
                int contadorV = 0;  
                todos_caminho_percorrido.add(origem.getId()); // Todos os caminhos percorridos na atual interação
                contadorV++;
                //=================================== Matriz de Custo dos Caminhos ========================================================================
                Object[][] caminhos = new Object[(config.getQtdFormigas()*config.getQtdInteracoes())][3]; // Matriz que contem os caminhos feitos por cada formiga
                int conta_caminhos = 0; // Contador da matriz caminhos
                int conta_formigas = 1;
                //======================================= Trajetoria das Formigas ==========================================================================
                for(int b=0; b< config.getQtdFormigas(); b++){ //Tajetoria de cada formiga
                    
                    boolean chegou= false;                  // Verifica se a formiga chegou no destino final.
                   
                    Formiga formiga = new Formiga();        // Formiga que vai percorrer o grafo.
                    formiga.setVerticeAtual(origem);        // O ponto de partida da formiga.                   
                    formiga.setCaminhoPercorrido(origem);   // O ponto de partida é adicionado ao array de caminho percorrido

                    do{ // Enquanto não achar o destino
                        
                        boolean tem_caminho= false;// Verifica se ainda tem caminho valido
                        ArrayList<Float> porcentagem = new ArrayList<Float>(); // Array que contem a porcentagem de ir para o vertice e o proprio vertice
                        int id_porcentagem = 0; // Contador do array de porcentagens (par = porcentagem / impar = vertice )
                        porcentagem.add((float)0); // A porcentagem é 0
                        id_porcentagem ++;
                        porcentagem.add((float)0); // O vertice é 0 
                        id_porcentagem ++;
                        
                        if(formiga.getVerticeAtual().getId() == config.getDestino().getId()){ // Verifica se a formiga chegou no destino final                            
                            chegou = true;                            
                            String vertices; // Mostra os vertices percorridos
                            vertices = Integer.toString(formiga.getCaminhoPercorrido(0).getId()); 
                            
                            for(int i=0; i<(formiga.getContador()-1); i++){ // Procurando as arestas percorridas na matriz para fazer o calculo dos custos.          
                                for(int j=0; j< contador-2; j++){
                                    if(formiga.getCaminhoPercorrido(i).getId() == feromonios[j][1] && formiga.getCaminhoPercorrido(i+1).getId() == feromonios[j][2]){
                                        formiga.setCustoPercurso(feromonios[j][3]);
                                        vertices =vertices + "-" + Integer.toString(formiga.getCaminhoPercorrido(i+1).getId());
                                        j= contador-2; // Encerra o laço
                                    }
                                }
                            }
                            
                            caminhos[conta_caminhos][0] = conta_formigas; // ID da formiga
    
                            caminhos[conta_caminhos][1] = vertices; // Exibe os caminhos percorridos

                            caminhos[conta_caminhos][2] = (int)formiga.getCustoPercurso(); // Exibe o custo (peso) total do caminho percorrido
                           
                            System.out.println("Formiga | Caminho Percorrido | Custo total ");
                            System.out.println(caminhos[conta_caminhos][0] + "       " + caminhos[conta_caminhos][1] + "       " + caminhos[conta_caminhos][2] );
                            
                            if(conta_formigas == config.getQtdFormigas()){ // Se for a ultima formiga, volta para a primeira
                                conta_formigas = 1;
                                System.out.println("--------------------- Interaçao "+ (a+1) +" ----------------------------");
                            }
                            conta_caminhos++;
                            conta_formigas++;
                            
                            
                            for(int j=0;j< formiga.getContador(); j++){   // Adiciona o caminho feito pela formiga no array que contem todos os caminhos                           
                                for(int i=0; i<contadorV; i++){             
                                    if(todos_caminho_percorrido.get(i) == formiga.getCaminhoPercorrido(j).getId()){                                   

                                    }else{
                                        if(i == (contadorV-1)){
                                            todos_caminho_percorrido.add(formiga.getCaminhoPercorrido(j).getId());
                                            contadorV++;
                                        }
                                    }
                                }
                            }
                            
                        }else{
                           
                            //-----------> Fazendo o Somatorio de Custo*Feromonio dos Caminhos que a Formiga Pode Escolher <-----------
                            
                            float soma=0; // Somatorio de Custo*Feromonio
                            
                            for(int i=0; i<contador-2; i++){
                                if(feromonios[i][1] == formiga.getVerticeAtual().getId()){ // Quando o ponto de partida na matriz é igual o vertice atual da formiga
                                    boolean calcular = false;
                                    for(int j=0; j< formiga.getContador(); j++){// Percorre o vetor de caminhos feitos pela formiga                                
                                        if(formiga.getCaminhoPercorrido(j).getId()== feromonios[i][2]){ // Se o proximo vertice ja foi percorrido, nao entra no somatorio
                                            j= formiga.getContador(); // Encerra o laço.
                                            calcular = false;
                                        }else{ // Se o proximo vertice for novo, sera feito o calculo.
                                            calcular = true;
                                        }
                                    }
                                    if(calcular == true){
                                        soma= soma+feromonios[i][6];
                                    }
                                }
                            }
                           
                            //---------------------------------> Inserindo as Probabilidades na Matriz <-----------------------------------
                            
                            for(int i=0; i< contador-2; i++){ // Preenche as probabilidades dos caminhos na matriz
                                if(feromonios[i][1] == formiga.getVerticeAtual().getId()){ // Quando o ponto de partida na matriz é igual o vertice atual da formiga
                                    for(int j=0; j< formiga.getContador(); j++){// Percorre o vetor de caminhos feitos pela formiga                                
                                        if(formiga.getCaminhoPercorrido(j).getId()== feromonios[i][2]){ // Se o proximo vertice ja foi percorrido, sua probabilidade é 0
                                            feromonios[i][7]= 0; // Probabilidade
                                            j= formiga.getContador(); // Encerra o laço.
                                        }else{                  // Se o proximo vertice for novo, sera feito o calculo da probabilidade.
                                            if(j == formiga.getContador()-1){
                                                feromonios[i][7]= (float)feromonios[i][6]/soma; // Probabilidade

                                                porcentagem.add(feromonios[i][7]); //Porcentagem
                                                id_porcentagem ++;
                                                porcentagem.add(feromonios[i][2]); //Vertice
                                                id_porcentagem ++;

                                                tem_caminho= true; // Indica que achou um caminho valido.
                                            }
                                        }
                                    }
                                }else{ // Tira a probabilidade de passar em caminho errado.      
                                      feromonios[i][7]= 0; // Probabilidade                                    
                                }
                            }           
                            //------------------------------------> Tratando Caminho Invalido <-----------------------------------------                           
                            if(tem_caminho == false){
                                formiga.resetarFormiga(origem);
                            }else{
                            //------------------------------------> Caminhando com a formiga <-----------------------------------------             
                                float aleatorio = (float) Math.random();  
                                int p = 0; // Posicao do array de porcentagem
                                while (aleatorio > porcentagem.get(p)){                                  
                                    p= p+2;
                                    porcentagem.set(p, porcentagem.get(p-2)+porcentagem.get(p));                                    
                                }
                                Vertice vertice = new Vertice();        // vertice para onde a formiga vai
                                float x= porcentagem.get(p+1);          // auxiliar para converter float em int
                                vertice.setId((int)x);                  // recebe o vertice escolhido
                                formiga.setVerticeAtual(vertice);       // a formiga caminhou
                                formiga.setCaminhoPercorrido(vertice);  // o vertice atual foi armazenado no array de caminhos percorridos              
                                
                            }                                                     
                        }                        
                    }while(chegou == false);                   
                }
                //==================================== Atualizando a Matriz de Feromonios =============================================     

                for(int i=0;i< contador-2;i++){
                    float somatorio=0;  //Somatorio dos custos
                    int num_formiga=0;   //Equivale ao conta_caminhos 
                    for(int j=0; j< contadorV-1;j++){  // percorre o array que contem todos os caminhos                      
                        if(todos_caminho_percorrido.get(j)== config.getDestino().getId()){
                            num_formiga++; //Se o valor é igual o destino e nao acabou os itens do array, entao começou o caminho da formiga posterior
                            j++; // Impede o caminhoi,j onde i=destino final e j=origem.
                        }
                        if(todos_caminho_percorrido.get(j)==((int)(feromonios[i][1])) && todos_caminho_percorrido.get(j+1)== ((int)(feromonios[i][2]))){
                            int x= Integer.parseInt(caminhos[num_formiga][2].toString());
                            somatorio = somatorio + (float)1/x;
                        }
                    }
                    feromonios[i][5] = (evaporacao*feromonios[i][5])+somatorio;
                }
            }
        }
        catch(Exception e){ // se nao ler o arquivo, exibe mensagem de erro e para o programa.
            System.out.println("Erro ao ler o arquivo!\n"
                    + "Verifique se o nome/endereço do arquivo está inserido corretamente ou se o arquivo segue o padrão definido abaixo:\n");
            System.out.println("origem	destino	custo\n"
                             + " x1       y1      z1\n"
                             + " x2       y2      z2\n"
                             + "...      ...     ...\n"
                             + " xn       yn      zn\n");
        }                     
    }

}
