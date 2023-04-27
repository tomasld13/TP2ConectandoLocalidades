package grafos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GrafoListaVecinos {
	private ArrayList<HashSet<Integer>> vecinos;

	// Constructor
	public GrafoListaVecinos(int n)
	{
		vecinos = new ArrayList<HashSet<Integer>>();
		for(int i=0; i<n; ++i)
			vecinos.add(new HashSet<Integer>());
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
	
}
