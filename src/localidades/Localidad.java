package localidades;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {
	
	

	String nombre;
	String provincia;
	Double latitud;
	Double longitud;
	Coordinate coordenadas;
	
	public Localidad() {
		
	}

	public Localidad (String nombre, String provincia, Double latitud, Double longitud) {
		this.nombre = nombre;
		this.provincia = provincia;
		this.latitud = latitud;
		this.longitud = longitud;
		coordenadas = new Coordinate (latitud.shortValue(), longitud.shortValue());
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public Double getLatitud() {
		return latitud;
	}

	public void setLatitud(Double latitud) {
		this.latitud = latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLongitud(Double longitud) {
		this.longitud = longitud;
	}
}
