package com.mycompany.grafos;

import java.util.Arrays;

public class GrafoMA{

    private boolean dirigido; 
    private final int maxNodos;             
    private int numVertices; 
    private Vertice[] listaVertice;
    private int matrizAdy[][];

    public GrafoMA(boolean d) {
        maxNodos = numVertices = 0;
        dirigido = d;
    }

    public GrafoMA(int nMax, boolean d) { 
        dirigido = d;
        maxNodos = nMax;
        numVertices = 0;
        listaVertice = new Vertice[nMax];
        matrizAdy = new int[nMax][nMax];   //MATRIZ PONDERADA
    } 

public void insertarArista(int i, int j, int peso) {

    if (i >= numVertices || j >= numVertices) {
        System.out.println("Error: vértice fuera de rango");
        return;
    }

    matrizAdy[i][j] = peso;

    if (!dirigido)
        matrizAdy[j][i] = peso;
}

public void eliminarArista(int i, int j) {
    matrizAdy[i][j] = 0;
    if (!dirigido) 
        matrizAdy[j][i] = 0; 
} 

public void insertarVertice(String etiqueta) {

    if (numVertices >= maxNodos) {
        System.out.println("Error, se supera el número de nodos máximo");
        return;
    }

    this.listaVertice[numVertices] = new Vertice(numVertices, etiqueta);

    for (int j = 0; j < numVertices; j++) {
        matrizAdy[numVertices][j] = 0;
        matrizAdy[j][numVertices] = 0;
    }

    numVertices++;
}

public void imprimirMatrizAdyacencia() {
    System.out.println("Matriz de Adyacencia (Pesos):");

    System.out.print("    ");
    for (int i = 0; i < numVertices; i++)
        System.out.print((i + 1) + " ");
    System.out.println();

    System.out.println("   " + "-".repeat(numVertices * 3));

    for (int i = 0; i < numVertices; i++) {
        System.out.print((i + 1) + " | ");
        for (int j = 0; j < numVertices; j++) {
            System.out.print(matrizAdy[i][j] + " ");
        }
        System.out.println();
    }
}

public static void imprimirGrafoResidual(int[][] matrizEntrada) {
    System.out.println("Grafo Residual:");

    System.out.print("    ");
    for (int i = 0; i < matrizEntrada.length; i++)
        System.out.printf("%2d ", (i + 1));
    System.out.println();

    System.out.println("   " + "-".repeat(matrizEntrada.length * 3));

    for (int i = 0; i < matrizEntrada.length; i++) {
        System.out.print((i + 1) + " | ");
        for (int j = 0; j < matrizEntrada.length; j++) {
            System.out.printf("%2d ", matrizEntrada[i][j]);
        }
        System.out.println();
    }
}


// ======================= EDMONDS - KARP =======================

public int[][] edmondsKarp(int origen, int destino, int[][] capacidadResidual) {

    int flujoMaximo = 0;

    int[] padre = new int[numVertices];

    while (bfs(origen, destino, padre, capacidadResidual)) {

        int flujoAumentante = Integer.MAX_VALUE;

        for (int v = destino; v != origen; v = padre[v]) {
            int u = padre[v];
            flujoAumentante = Math.min(flujoAumentante, capacidadResidual[u][v]);
        }

        System.out.print("Camino aumentante: ");
        imprimirCamino(origen, destino, padre);
        System.out.println(" | Flujo aniadido: " + flujoAumentante);

        for (int v = destino; v != origen; v = padre[v]) {
            int u = padre[v];
            capacidadResidual[u][v] -= flujoAumentante;
            capacidadResidual[v][u] += flujoAumentante;
        }

        flujoMaximo += flujoAumentante;
    }

    System.out.println("El flujo maximo (Edmonds-Karp) es de: " + flujoMaximo);
    return capacidadResidual;
}

public boolean bfs(int origen, int destino, int[] padre, int[][] capacidadResidual) {
    boolean[] visitado = new boolean[numVertices];
    // Inicializar arreglos
    for (int i = 0; i < numVertices; i++) {
        visitado[i] = false;
        padre[i] = -1;
    }

    Cola colaVertices = new Cola(numVertices);
    colaVertices.encolar(origen);
    visitado[origen] = true;

    while (!colaVertices.estaVacia()) {
        int u = colaVertices.desencolar();

        for (int v = 0; v < numVertices; v++) {
            if (!visitado[v] && capacidadResidual[u][v] > 0) {
                padre[v] = u;
                visitado[v] = true;

                if (v == destino) {
                    return true; //Camino aumentante encontrado
                }

                colaVertices.encolar(v);
            }
        }
    }

    return false; //No existe camino aumentante
}

private void imprimirCamino(int origen, int destino, int[] padre) {
    if (destino == origen) {
        System.out.print(origen);
        return;
    }
    if (padre[destino] == -1) {
        System.out.print("No hay camino valido");
        return;
    }
    imprimirCamino(origen, padre[destino], padre);
    System.out.print(" -> " + destino);
}

//======================= CORTE MINIMO =======================
public void corteMinimo(int[][] grafoResidual, int origen){
    boolean[] esAlcanzable = new boolean[numVertices];
    dfs(origen, esAlcanzable, grafoResidual);
    System.out.println("\nARISTAS DEL CORTE MINIMO");
    
    for (int i = 0; i < numVertices; i++) {
        for (int j = 0; j < numVertices; j++) {
            // Solamente hay un corte valido si
            // La arista es alcanzable desde s pero NO en t,
            // y en el grafo original existia esa arista con capacidad > 0
            if (esAlcanzable[i] && !esAlcanzable[j] && this.matrizAdy[i][j] > 0) {
                System.out.printf("Arista de corte: %d -> %d (Capacidad: %d)\n", 
                                  i, j, this.matrizAdy[i][j]);
            }
        }
    }    
}

public boolean dfs(int origen, boolean[] visitado, int[][] capacidadResidual) {

    // Inicializar arreglos
    for (int i = 0; i < numVertices; i++) {
        visitado[i] = false;
    }

    Pila pila = new Pila(numVertices);
    pila.push(origen);
    visitado[origen] = true;

    while (!pila.estaVacia()) {
        int u = pila.pop();

        for (int v = 0; v < numVertices; v++) {
            if (!visitado[v] && capacidadResidual[u][v] > 0) {
                visitado[v] = true;
                pila.push(v);
            }
        }
    }

    return false; //No existe camino aumentante
}



public int[][] clonarMatrizAdy() {
    
    int[][] copia = new int[this.matrizAdy.length][this.matrizAdy[0].length];

    for (int i = 0; i < this.matrizAdy.length; i++) {
        copia[i] = this.matrizAdy[i].clone();  // clonado fila por fila
    }

    return copia;
}

// ======================= VERTICE =======================

public class Vertice {
    int idVertice;
    String etiqueta;
    boolean visitado;

    public Vertice(int id, String etiqueta){
        this.idVertice = id;
        this.etiqueta = etiqueta;
        this.visitado = false;
    }

    @Override
    public String toString(){
        return String.format("%d", idVertice);
    }
}

public void mostrarVertice(int v) {
    System.out.print(listaVertice[v]);
}

}