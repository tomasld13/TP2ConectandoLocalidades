package algoritmos;

import java.util.ArrayList;

import algoritmos.Kruskal.Arista;
import grafos.GrafoListaVecinos;

public class Prb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Prueba del Algoritmo Kruskal
		
		// Creamos el grafo
        GrafoListaVecinos grafo = new GrafoListaVecinos(10);
        grafo.agregarArista(1, 2);
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 4);
		grafo.agregarArista(4, 5);
		grafo.agregarArista(5, 6);
		grafo.agregarArista(6, 7);
		grafo.agregarArista(7, 8);
		grafo.agregarArista(8, 9);
		grafo.agregarArista(2, 8);
		grafo.agregarArista(3, 9);
		grafo.agregarArista(3, 6);
		grafo.agregarArista(4, 6);
		grafo.agregarArista(7, 9);
		grafo.agregarArista(1, 8);
        
        // Creamos un objeto de la clase Kruskal y le pasamos el grafo
        Kruskal kruskal = new Kruskal();
        
        // Obtenemos el árbol de expansión mínima
        ArrayList<Arista> arbol = kruskal.kruskal(grafo);
        
        // Imprimimos las aristas del árbol
        System.out.println("Aristas del árbol de expansión mínima:");
        for (Arista a : arbol) {
            System.out.println(" Origen: " + a.origen + " - Destino:" + a.destino + " - Peso: " + a.peso);
        }
		
	}

}