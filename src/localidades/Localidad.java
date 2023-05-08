package localidades;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {

	String nombre;
	String provincia;
	double latitud;
	double longitud;
	Coordinate coordenadas;
	int codigo;
	
	
	public Localidad() {
		
	}
	public Localidad (String nombre, String provincia, double latitud, double longitud, int codigo) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.coordenadas = new Coordinate(latitud, longitud);
		this.codigo = codigo;
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

	public void setCoordenadas(Coordinate coordenadas) {
		this.coordenadas = coordenadas;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
}
