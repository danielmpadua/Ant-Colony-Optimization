# Ant-Colony-Optimization (ACO)

Trabalho acadêmico: Algoritmo bioinspirado desenvolvido em Java.

## Sobre o algoritmo

O ACO é um algoritmo bioinspirado, incluído na área de Computação Natural. Foi desenvolvido em 1992 por Marco Dorigo. É amplamente aplicado a problemas de otimização em roteamento e grafos.

### Objetivo do trabalho

Implementar um ACO utilizando os conceitos de POO trabalhados durante a disciplina na construção de:

    • Diagrama de Classes que implemente como classes:

      – Formiga

      – Vértice

      – Aresta

      – Configuração
  
    • Algoritmo em linguagem Java aplicado a dois datasets:

      – Grafo 1: Composto de 190 arestas com origem em 1 e destino em 20

        • Iterações = 3000

        • p = 0.07

        • Formigas = 10

      – Grafo 2: Composto de 8020 arestas com origem em 1 e destino em 100

        • Iterações = 3000

        • p = 0.07

        • Formigas = 50
        
 Executar 30 testes para cada grafo e coletar o melhor caminho e custo obtido.

### Como usar

Após fazer o dowload dos arquivos, localize o a pasta "AntColonyOptimization" e abra como um projeto na IDE NetBeans.

Você pode alterar os parâmetros de configuração na classe AntColonyOptimization.java, indicando:

    - o grafo desejado;
    
    - os vertice de origem e destino;
    
    - quantidade de formigas;
    
    - quantidade de interações;
    
    - taxa de evaporação do feromônio.

## Realizando testes

Após alterar os parâmetros de configuração, basta executar o algoritmo. Os resultados serão exibidos no proprio terminal da IDE.

Caso queira usar outros grafos, basta seguir o modelo dos grafos usados originalmente, indicando o novo grafo na configuração.

O tempo de execução varia de acordo com o processamento da máquina em que se realiza o teste, podendo demorar alguns minutos.

## Realizando testes

O resultado é a convergência, ou seja, o caminho mais escolhido pelas formigas. Espera-se que o resultado seja o caminho de menor custo, porém não é algo garantido, sendo que um mesmo senário pode ter resultados diferentes quando feito varios testes.

A convergência depende dos dados de entrada, principalmente da quantidade de formigas e de interações, podendo haver uma convergência rápida (em poucas interações), lenta (após muitas interações) ou não existir.

## Autores

* **[Daniel Pádua](https://github.com/danielmpadua)** - *Estudante de Engenharia de Computação do IFMG - Campus Bambuí*
* **[Lucas dos Anjos](https://github.com/lucas0anjos2)** - *Estudante de Engenharia de Computação do IFMG - Campus Bambuí*
