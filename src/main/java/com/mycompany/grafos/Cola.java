package com.mycompany.grafos;

public class Cola {
    int[] elementos;
    int frente;
    int fin;
    int maximo;

    public Cola(int maximo) {
        this.elementos = new int[maximo];
        this.frente = -1;
        this.fin = -1;
        this.maximo = maximo;
    }

    public boolean estaVacia() {
        return frente == -1;
    }

    public boolean estaLlena() {
        // Cola circular: está llena si avanzar fin una posición alcanza frente
        return ((fin + 1) % maximo) == frente;
    }

    public void encolar(int nuevo) {
        if (estaLlena()) {
            System.out.println("Cola llena, no se puede encolar.");
            return;
        }

        if (estaVacia()) {
            frente = 0;
            fin = 0;
            elementos[fin] = nuevo;
        } else {
            fin = (fin + 1) % maximo; // incremento circular
            elementos[fin] = nuevo;
        }
    }

    public int desencolar() {
        if (estaVacia()) {
            System.out.println("Cola vacía, no se puede desencolar.");
            return -1;
        }

        int valor = elementos[frente];

        // Si queda solo un elemento, reiniciamos
        if (frente == fin) {
            frente = fin = -1;
        } else {
            frente = (frente + 1) % maximo; // avance circular
        }

        return valor;
    }

    public int peek() {
        if (estaVacia()) {
            System.out.println("Cola vacía.");
            return -1;
        }
        return elementos[frente];
    }
}
