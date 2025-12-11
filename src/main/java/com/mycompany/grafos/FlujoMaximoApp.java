/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos;

import static com.mycompany.grafos.GrafoMA.imprimirGrafoResidual;

/**
 *
 * @author Emilio
 */
public class FlujoMaximoApp {
    public static void main(String[] args) {
        GrafoController grafos = new GrafoController();
    
    // --- ESTA SECCION PERMITE CARGAR METODOS POR PRIEMRA VEZ EN EL COMPILADOR
    long tiempoCargar = System.nanoTime();
    imprimirGrafoResidual(grafos.grafoDirigido.edmondsKarp(0, 7, grafos.grafoDirigido.clonarMatrizAdy()));
    grafos.grafoDirigido.corteMinimo(grafos.grafoDirigido.edmondsKarp(0, 7, grafos.grafoDirigido.clonarMatrizAdy()), 0);
    System.out.printf("%n%n%n");
    // -- ASI LA SIGUIENTE EJECUCION ES JUSTA PARA EL PRIMER GRAFO
        
        
    long tiempoDirigido = System.nanoTime();
    //Probando el algoritmo de Flujo Máximo en el primer Grafo    
    int[][] capResidualDirigido = grafos.grafoDirigido.clonarMatrizAdy();
    grafos.grafoDirigido.edmondsKarp(0, 7, capResidualDirigido);
    imprimirGrafoResidual(capResidualDirigido);
    grafos.grafoDirigido.corteMinimo(capResidualDirigido, 0);
    System.out.printf("Tiempo de Ejecucion: %.2f ms%n%n",(System.nanoTime() - tiempoDirigido)/1_000_000.0);
    
    //Probando el algoritmo de Flujo Máximo en el segundo Grafo
    long tiempoNoDirigido = System.nanoTime();
    int[][] capResidualNoDirigido = grafos.grafoNoDirigido.clonarMatrizAdy();
    grafos.grafoNoDirigido.edmondsKarp(0, 4, capResidualNoDirigido);
    imprimirGrafoResidual(capResidualNoDirigido);
    grafos.grafoNoDirigido.corteMinimo(capResidualNoDirigido, 0);
    System.out.printf("Tiempo de Ejecucion: %.2f ms%n%n",(System.nanoTime() - tiempoNoDirigido)/1_000_000.0);
    
    }
    
    
}
