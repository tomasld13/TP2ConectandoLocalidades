package localidades;


import java.util.ArrayList;

import org.openstreetmap.gui.jmapviewer.Coordinate;

public class Localidad {

	private String nombre;
	private String provincia;
	private Coordinate coordenadas;
	private int codigo;
	private ArrayList<String> vecinos = new ArrayList<>();
	
	public ArrayList<String> getVecinos() {
		return vecinos;
	}
	
	public void agregarVecino(String nombre) {
		if(!vecinos.contains(nombre))
			vecinos.add(nombre);
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
