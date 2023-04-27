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
                    aristas.add(new Arista(i, vecino, grafo.darPeso(i, vecino)));
                }
            }
        }

        // Ordenar las aristas por peso de menor a mayor
        Collections.sort(aristas, Comparator.comparingInt(Arista::getPeso));

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
        final int peso;

        public Arista(int origen, int destino, int peso) {
            this.origen = origen;
            this.destino = destino;
            this.peso = peso;//grafo.getPeso(origen, destino);  // Obtener el peso del grafo
        }

        public int getOrigen() {
            return origen;
        }

        public int getDestino() {
            return destino;
        }

        public int getPeso() {
            return peso;
        }
    }
}
