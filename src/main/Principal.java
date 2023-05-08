package main;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import grafos.GrafoListaVecinos;
import interfaces.MainForm;

public class Principal {
	private static JMapViewer _mapa = new JMapViewer();
	private static GrafoListaVecinos _grafo = new GrafoListaVecinos();

	public static void main(String[] args) {
		MainForm ventana = new MainForm(_mapa, _grafo);
		ventana.setVisible(true);
		
	}
}
