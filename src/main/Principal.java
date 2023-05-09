package main;
import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.JMapViewer;

import grafos.GrafoListaVecinos;
import interfaces.MainForm;
import localidades.Localidad;
import localidades.LogicaLocalidad;

public class Principal {
	private static JMapViewer _mapa = new JMapViewer();
	private static GrafoListaVecinos _grafo = new GrafoListaVecinos();
	
	public static void main(String[] args) {
		LogicaLocalidad.listarLocalidades = new ArrayList<>();
		MainForm ventana = new MainForm(_mapa, _grafo);
		ventana.setVisible(true);
	}
}
