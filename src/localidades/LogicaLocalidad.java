package localidades;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListModel;

import interfaces.ConectarLocalidades;
import interfaces.GestionLocalidades;

public class LogicaLocalidad {

	public static void agregarNueva(Localidad localidad, String nombre, String provincia, double latitud,
			double longitud) {
		localidad.setNombre(nombre);
		localidad.setProvincia(provincia);
		localidad.setLatitud(latitud);
		localidad.setLongitud(longitud);
		GestionLocalidades.listarLocalidades.add(localidad);
		ConectarLocalidades.listarLocalidades.add(localidad);

	}

	public static void AgregarVarias(Localidad localidad, String nombre, String provincia, double latitud,
			double longitud, ArrayList<Localidad> listarLocalidades, JList<String> listaLocalidades) {

		localidad = new Localidad();
		localidad.setNombre(nombre);
		localidad.setProvincia(provincia);
		localidad.setLatitud(latitud);
		localidad.setLongitud(longitud);
		GestionLocalidades.listarLocalidades.add(localidad);
		ConectarLocalidades.listarLocalidades.add(localidad);
		

	}

	public static ListModel<String> crearModel(DefaultListModel<String> dLM) {
		int tamano = GestionLocalidades.listarLocalidades.size();
		final String[] vector = new String[tamano];
		for (int conta = 0; conta < tamano; conta++) {
			vector[conta] = GestionLocalidades.listarLocalidades.get(conta).getNombre() + " - "
					+ GestionLocalidades.listarLocalidades.get(conta).getProvincia() + " - " + GestionLocalidades.listarLocalidades.get(conta).getLatitud()
					+ " - " + GestionLocalidades.listarLocalidades.get(conta).getLongitud();
			if (!dLM.contains(vector[conta]))
					dLM.addElement(vector[conta]);
		}
		return dLM;
	}


	
	

}