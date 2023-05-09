package localidades;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import algoritmos.BFS;
import algoritmos.Kruskal;
import algoritmos.Kruskal.Arista;
import grafos.GrafoListaVecinos;
import interfaces.GestionLocalidades;

public class LogicaLocalidad {

	public static Localidad crearLocalidad(Localidad localidad, String nombre, String provincia, double latitud,
			double longitud, ArrayList<Localidad> listarLocalidades, JList<String> listaLocalidades, int codigo) {

		localidad = new Localidad(nombre, provincia, latitud, longitud, codigo);
		GestionLocalidades.listarLocalidades.add(localidad);
		
		return localidad;
	}
	
	public static void agregarLocalidadGrafo(Localidad localidad, GrafoListaVecinos grafo) {
		grafo.agregarVertice(localidad.getCodigo(), localidad);
	}
	
	public static void conectarLocalidadesGrafo(int a, int b, GrafoListaVecinos grafo) {
		grafo.agregarArista(a, b);
	}
	
	public static ListModel<String> crearModel(DefaultListModel<String> dLM) {
		int tamano = GestionLocalidades.listarLocalidades.size();
		
		final String[] vector = new String[tamano];
		for (int conta = 0; conta < tamano; conta++) {
			vector[conta] = GestionLocalidades.listarLocalidades.get(conta).getNombre() + " - "
					+ GestionLocalidades.listarLocalidades.get(conta).getProvincia() + " - " + GestionLocalidades.listarLocalidades.get(conta).getCoordenadas().getLat()
					+ " - " + GestionLocalidades.listarLocalidades.get(conta).getCoordenadas().getLon();
			if (!dLM.contains(vector[conta]))
					dLM.addElement(vector[conta]);
		}		
		return dLM;
	}

	public static ArrayList<Arista> generarArbolMinimo(GrafoListaVecinos _grafo) {
		if(!BFS.esConexo(_grafo))
			throw new IllegalArgumentException("Todos los pares de localidades tienen que estar conectados al menos por un camino.");
		Kruskal kruskal = new Kruskal();		        
        // Obtenemos el árbol de expansión mínima
        return kruskal.kruskal(_grafo);		  
	}

	@Override
	public String toString() {
		return "toString Logica Localidad";
	}
  
}