package algoritmos;

import java.util.ArrayList;

import algoritmos.Kruskal.Arista;
import grafos.GrafoListaVecinos;
import localidades.Localidad;

public class Prb {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Prueba del Algoritmo Kruskal
		
		// Creamos el grafo
		GrafoListaVecinos grafo = new GrafoListaVecinos(8);
		grafo.agregarVertice(0, new Localidad("San Miguel", "Buenos Aires", -34.54335, -58.71229));
		grafo.agregarVertice(1, new Localidad("José C. Paz", "Buenos Aires", -34.51541, -58.76813));
		grafo.agregarVertice(2, new Localidad("La Plata", "Buenos Aires", -34.92145, -57.95453));
		grafo.agregarVertice(3, new Localidad("Córdoba", "Córdoba", -31.42008, -64.18877));
		grafo.agregarVertice(4, new Localidad("Rosario", "Santa Fe", -32.95113, -60.66632));
		grafo.agregarVertice(5, new Localidad("Mendoza", "Mendoza", -32.88946, -68.84584));
		grafo.agregarVertice(6, new Localidad("Bariloche", "Río Negro", -41.13348, -71.31028));
		grafo.agregarVertice(7, new Localidad("Ushuaia", "Tierra del Fuego", -54.80362, -68.30512));

		grafo.agregarArista(0, 1);
		grafo.agregarArista(0, 2);
		grafo.agregarArista(0, 3);
		grafo.agregarArista(0, 4);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		grafo.agregarArista(1, 5);
		grafo.agregarArista(2, 4);
		grafo.agregarArista(2, 5);
		grafo.agregarArista(3, 5);
		grafo.agregarArista(4, 6);
		grafo.agregarArista(5, 6);
        
        // Creamos un objeto de la clase Kruskal
        Kruskal kruskal = new Kruskal();
        
        // Obtenemos el árbol de expansión mínima
        ArrayList<Arista> arbol = kruskal.kruskal(grafo);
        
        // Imprimimos las aristas del árbol
        System.out.println("Aristas del árbol de expansión mínima:");
        for (Arista a : arbol) {
            System.out.println(" Origen: " + a.getNombreOrigen() + " - Destino:" + a.getNombreDestino() + " - Precio: $" + a.getPeso());
        }
		
	}

}