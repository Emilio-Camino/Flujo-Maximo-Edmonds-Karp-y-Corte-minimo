/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.grafos;

/**
 *
 * @author Emilio
 */
public class Pila {
    int[] elementos;
    int tope;
    int maximo;
    
    
   public Pila(int maximo){
   this.elementos = new int[maximo];
   this.tope = -1;
   this.maximo = maximo;
   }
   
   public int peek(){
   return elementos[tope];
   }
   
   public int pop(){
    int pop = elementos[tope];
    elementos[tope] = 0;
    tope--;
    return pop;
   }
   
   public void push(int nuevo){
   if(tope < maximo){
       tope++;
       elementos[tope] = nuevo;
   }
   
   }
   
   public boolean estaVacia(){
      return tope == -1; 
   }
   
   
}
