/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.grafos;

/**
 *
 * @author P3E004-K
 */
public final class GrafoController {

   GrafoMA grafoDirigido;
   GrafoMA grafoNoDirigido;
   
   GrafoController(){
   this.grafoDirigido = initGrafoDirigido();
   this.grafoNoDirigido = initGrafoNoDirigido();
   }
   
public GrafoMA initGrafoDirigido() {
    GrafoMA g = new GrafoMA(8, true);

    //Insertar Vértices
    g.insertarVertice("s");
    g.insertarVertice("2");
    g.insertarVertice("3");
    g.insertarVertice("4");
    g.insertarVertice("5");
    g.insertarVertice("6");
    g.insertarVertice("7");
    g.insertarVertice("t");

    //Insertar Aristas (Origen, Destino, Peso)

    // --- Desde el nodo s (0) ---
    g.insertarArista(0, 1, 10);
    g.insertarArista(0, 2, 5);
    g.insertarArista(0, 3, 15);

    // --- Desde el nodo 1  ---
    g.insertarArista(1, 2, 4);
    g.insertarArista(1, 4, 9);
    g.insertarArista(1, 5, 15);

    // --- Desde el nodo 2 ---
    g.insertarArista(2, 3, 4);
    g.insertarArista(2, 5, 8);

    // --- Desde el nodo 3 ---
    g.insertarArista(3, 6, 30); 

    // --- Desde el nodo 4 ---
    g.insertarArista(4, 5, 15);
    g.insertarArista(4, 7, 10);

    // --- Desde el nodo 5 ---
    g.insertarArista(5, 6, 15);
    g.insertarArista(5, 7, 10);

    // --- Desde el nodo 6 ---
    g.insertarArista(6, 2, 6);
    g.insertarArista(6, 7, 10);

    return g;
}
   
public GrafoMA initGrafoNoDirigido() {
    GrafoMA g = new GrafoMA(5, false);

    //Insertar Vértices
    g.insertarVertice("s");
    g.insertarVertice("2");
    g.insertarVertice("3");
    g.insertarVertice("4");
    g.insertarVertice("t");

    //Insertar Aristas (Origen, Destino, Peso)
    // --- Conexiones del Nodo 0 (s) ---
    g.insertarArista(0, 3, 10);
    g.insertarArista(0, 1, 20);
    g.insertarArista(0, 2, 30);
    // --- Conexiones del Nodo 1 ---
    g.insertarArista(1, 2, 40);
    g.insertarArista(1, 4, 20);
    
    // --- Conexiones del Nodo 2 ---
    g.insertarArista(2, 4, 20);

    // --- Conexiones del Nodo 3 ---
    g.insertarArista(3, 2, 15);
    g.insertarArista(3, 4, 20);


    return g;
}
   
}
