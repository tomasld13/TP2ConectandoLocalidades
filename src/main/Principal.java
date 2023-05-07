package main;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import interfaces.MainForm;

public class Principal {
	private static JMapViewer _mapa = new JMapViewer();

	public static void main(String[] args) {
		System.out.println(_mapa);
		MainForm ventana = new MainForm(_mapa);
		ventana.setVisible(true);
		
	}
}
