package localidades;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {

	String nombre;
	String provincia;
	double latitud;
	double longitud;
	Coordinate coordenadas;
	public Localidad() {
		
	}
	public Localidad (String nombre, String provincia, double latitud, double longitud) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.coordenadas = new Coordinate(latitud, longitud);
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public Coordinate getCoordenadas() {
		return coordenadas;
	}
	public Coordinate getCoordenadas() {
		return coordenadas;
	}

	public void setCoordenadas(Coordinate coordenadas) {
		this.coordenadas = coordenadas;
	}
}
