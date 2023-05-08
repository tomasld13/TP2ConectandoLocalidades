package localidades;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;
import grafos.*;

import grafos.GrafoListaVecinos;
import interfaces.ConectarLocalidades;
import interfaces.GestionLocalidades;

public class LogicaLocalidad {
	
	GrafoListaVecinos grafos = new GrafoListaVecinos(GestionLocalidades.listarLocalidades.size());


	public static Localidad crearLocalidad(Localidad localidad, String nombre, String provincia, double latitud,
			double longitud, ArrayList<Localidad> listarLocalidades, JList<String> listaLocalidades) {

		localidad = new Localidad(nombre, provincia, latitud, longitud);
		GestionLocalidades.listarLocalidades.add(localidad);
		ConectarLocalidades.listarLocalidades.add(localidad);
		GestionLocalidades.listarLocalidades.indexOf(localidad);
		
		return localidad;
	}
	
	public static void agregarLocalidadGrafo(Localidad localidad, GrafoListaVecinos grafo) {
		grafo.agregarVertice(GestionLocalidades.listarLocalidades.size(), localidad);
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

	

	
	

}