package grafos;


import static org.junit.Assert.*;
import org.junit.Test;

public class ConsultaDeVecinosTest

{
	@Test(expected = IllegalArgumentException.class)
	public void verticeNegativoTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.vecinos(-1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void verticeExcedidoTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.vecinos(5);
	}

	@Test
	public void todosAisladosTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		
		assertEquals(0, grafo.vecinos(2).size());
	}
	
	@Test
	public void verticeUniversalTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		
		grafo.agregarArista(1, 0);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		
		int[] esperado = {0, 2, 3};
		Assert.iguales(esperado, grafo.vecinos(1));
	}
	
	@Test
	public void verticeNormalTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		
		grafo.agregarArista(0, 2);
		grafo.agregarArista(1, 2);
		grafo.agregarArista(1, 3);
		
		int[] esperados = {0, 1};
		Assert.iguales(esperados, grafo.vecinos(2));
	}
}