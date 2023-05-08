package grafos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import localidades.Localidad;

import org.openstreetmap.gui.jmapviewer.Coordinate;

import localidades.Localidad;

public class GrafoListaVecinos {
	private ArrayList<HashSet<Integer>> vecinos;
	private Map<Integer, Localidad> localidades;
	// Constructor
	public GrafoListaVecinos()
	{
		vecinos = new ArrayList<HashSet<Integer>>();
		//for(int i=1; i<=n; ++i)
		localidades = new HashMap<Integer, Localidad>();
	}
	
	public void agregarVertice(int vertice, Localidad localidad) {
		vecinos.add(new HashSet<Integer>());
        localidades.put(vertice, localidad);
        System.out.println("Se agrego Localidad en grafo: "+localidad.getNombre());
    }
	
	public void eliminarVertice(int vertice) {
        localidades.remove(vertice);
    }
	
	public void agregarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		vecinos.get(i).add(j);
		vecinos.get(j).add(i);
	}
	
	public void eliminarArista(int i, int j)
	{	
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		if(existeArista(i, j)) 
			throw new IllegalArgumentException("No se puede agregar la misma arista dos veces");
		
		vecinos.get(i).remove(j);
		vecinos.get(j).remove(i);
	}
	
	public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		return vecinos.get(i).contains(j);
	}
	
	// Cantidad de vertices
	public int tamano()
	{
		return vecinos.size();
	}
	
	// Vecinos de un vertice
	public Set<Integer> vecinos(int i)
	{
		verificarVertice(i);
		
		return vecinos.get(i);
	}
	
	public int grado(int i)
	{
		verificarVertice(i);
		
		return vecinos(i).size();
	}
	
	public String darNombreArista(int i) {
		Localidad localidad = localidades.get(i);
		return localidad.getNombre();
	}
	
	public Coordinate darCoordenadaArista(int i) {
		Localidad localidad = localidades.get(i);
		return localidad.getCoordenadas();
	}
	// Verifica que sea un vertice valido
	private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= vecinos.size() )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " + i);
	}

	// Verifica que i y j sean distintos
	private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
	
	public double darPeso(int x, int y) {
		Localidad localidad1 = localidades.get(x);
		Localidad localidad2 = localidades.get(y);
		
		BigDecimal precioKM = new BigDecimal("2500.00");
		BigDecimal distintasProvincias = new BigDecimal("15000.00"); //Aumento del 10% si las localidades son de provincias distintas.
		int aumento300KM = 20; //Aumento del 20% si la distancia es mayor a 300km.
		
		double total;
		
		double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(localidad2.getCoordenadas().getLat() - localidad1.getCoordenadas().getLat());  
        double dLng = Math.toRadians(localidad2.getCoordenadas().getLon() - localidad1.getCoordenadas().getLon());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(localidad1.getCoordenadas().getLat())) * Math.cos(Math.toRadians(localidad2.getCoordenadas().getLat()));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;
        
        if (distancia > 300)
        	distancia = distancia + ((aumento300KM/100) * distancia);
        
        BigDecimal precio = new BigDecimal(distancia);
        precio = precio.multiply(precioKM);
        
        if(localidad1.getProvincia() != localidad2.getProvincia())
        	precio = precio.add(distintasProvincias);
        
        precio = precio.setScale(2, BigDecimal.ROUND_CEILING);
        total = precio.doubleValue();
        
        return total;  
	}
	
	public String toString(int i) {
		return "" + this.vecinos;
	}
}