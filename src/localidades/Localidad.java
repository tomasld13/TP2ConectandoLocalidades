package localidades;


import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {

	String nombre;
	String provincia;
	double latitud;
	double longitud;
	Coordinate coordenadas;
	int codigo;
	public static ArrayList<String> vecinos = new ArrayList<>();
	
	public ArrayList<String> getVecinos() {
		return vecinos;
	}
	public static void setVecinos(ArrayList<String> vecinos) {
		Localidad.vecinos = vecinos;
	}
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
	
	@Override
	public String toString() {
		return "Lugar Seleccionado: "+ this.getNombre()+ ". \n"
				+"Provincia: "+ this.getProvincia()+". \n"
				+"Coordenadas: "+ this.getCoordenadas()+ ". \n"
				+"Vecinos " + this.getVecinos()+ ". \n";
	}
	
}
