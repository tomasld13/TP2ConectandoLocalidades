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
		for(int i=1; i<=n; ++i)
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

	public int darPeso(int a, int b) {
		//Ejemplos de pesos para imitar el que se mostro en el pdf de clase. Falta implementar los pesos por distancia en km.
		String tot;
		if(a < b) tot = a + "" + b;
		else tot = b + "" + a;
		switch(tot) {
			case "12":
				return 4;
			case "23":
				return 8;
			case "34":
				return 6;
			case "45":
				return 9;
			case "56":
				return 10;
			case "67":
				return 3;
			case "78":
				return 1;
			case "89":
				return 6;
			case "28":
				return 12;
			case "39":
				return 3;
			case "36":
				return 4;
			case "46":
				return 13;
			case "79":
				return 5;
			default:
				return 8;
		}
	}
	
}