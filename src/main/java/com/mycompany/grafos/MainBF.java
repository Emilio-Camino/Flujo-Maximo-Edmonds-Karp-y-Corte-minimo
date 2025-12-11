package com.mycompany.grafos;
import java.util.Scanner;

public class MainBF {

	public static int comp=0,fails,desp;
	static long totTime=0;
	public static void main(String[] args) {
		char [] pattern;
		int result;
		long startTime=System.currentTimeMillis();
		Scanner leer=new Scanner(System.in);
		System.out.println("Ingrese la cadena del Texto: ");
		char [] text = leer.next().toCharArray();
                
		System.out.println("Ingrese la cadena del Patron: ");
		pattern=leer.next().toCharArray();
                
		boolean hallado = findBruteFMulti(text,pattern);
		if(!hallado){
			System.out.print("No Exitoso \n");
                }
		totTime= (System.currentTimeMillis()-startTime);
                System.out.println("Tiempo de computo: "+totTime/1e3 + "[s]");
		leer.close();
		
	}
        	public static boolean findBruteFMulti(char[] text, char[] pattern ) {
		int n = text.length;
		int m = pattern.length;
                boolean hallado = false;
                int contador = 0;
		for (int i=0; i<= n-m; i++) {
			int k=0;
			while(k<m && text[i+k] == pattern[k])
				k++;
			comp=comp+k;
			if(k==m){
                            contador++;
                            hallado = true;
			    System.out.println("Posicion Hallado: " +(i)+ "\n"+
                                    "Numero de comparaciones "+comp+"\n"+
                                    "Numero de desplazamientos: "+ (desp+1)+
                                    "\nNumero de fallos: "+(fails+1)+"\n" );
                        }
			fails=i;
			desp=fails;
		}
                
                System.out.println("Numero de patrones encontrados: " + contador);
                return hallado;
	}
}

/*
 * Texto: abacaabaccabacabaabb 
 * Patr�n: abacab
 */
