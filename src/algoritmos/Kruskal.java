package algoritmos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import grafos.GrafoListaVecinos;

public class Kruskal {
    private int[] padres;

    public ArrayList<Arista> kruskal(GrafoListaVecinos grafo) {
        // Inicializar los padres de los vértices
        padres = new int[grafo.tamano()];
        for (int i = 0; i < padres.length; i++) {
            padres[i] = i;
        }

        // Obtener todas las aristas del grafo
        ArrayList<Arista> aristas = new ArrayList<>();
        for (int i = 0; i < grafo.tamano(); i++) {
            for (int vecino : grafo.vecinos(i)) {
                if (i < vecino) {  // Solo agregamos la arista una vez
                    aristas.add(new Arista(i, grafo.darNombreArista(i), vecino, grafo.darNombreArista(vecino), grafo.darPeso(i, vecino)));
                }
            }
        }

        // Ordenar las aristas por peso de menor a mayor
        Collections.sort(aristas, Comparator.comparingDouble(Arista::getPeso));

        // Aplicar el algoritmo de Kruskal
        ArrayList<Arista> arbolRecubridor = new ArrayList<>();
        for (Arista arista : aristas) {
            int padreOrigen = buscarPadre(arista.origen);
            int padreDestino = buscarPadre(arista.destino);

            if (padreOrigen != padreDestino) {
                arbolRecubridor.add(arista);
                padres[padreOrigen] = padreDestino;
            }
        }

        return arbolRecubridor;
    }

    private int buscarPadre(int vertice) {
        if (padres[vertice] == vertice) {
            return vertice;
        }

        padres[vertice] = buscarPadre(padres[vertice]);  // Path compression
        return padres[vertice];
    }

    // Clase interna para representar una arista con origen, destino y peso
    public class Arista {
        final int origen;
        final int destino;
        final double peso;
        final String nombreOrigen;
        final String nombreDestino;

        public Arista(int origen, String nombreOrigen, int destino, String nombreDestino, double peso) {
            this.origen = origen;
            this.nombreOrigen = nombreOrigen;
            this.destino = destino;
            this.nombreDestino = nombreDestino;
            this.peso = peso;//grafo.getPeso(origen, destino);  // Obtener el peso del grafo
        }

        public int getOrigen() {
            return origen;
        }
        
        public String getNombreOrigen() {
            return nombreOrigen;
        }
        
        public int getDestino() {
            return destino;
        }
        
        public String getNombreDestino() {
            return nombreDestino;
        }

        
        public double getPeso() {
            return peso;
        }
    }
}
