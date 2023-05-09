package grafos;

import static org.junit.Assert.*;
import org.junit.Test;

public class EdicionDeAristasTest
{
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeNegativoTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarArista(-1, 3);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void primerVerticeExcedidoTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		grafo.agregarVertice(4, null);
		
		grafo.agregarArista(5, 2);
	}

	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeNegativoTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		
		grafo.agregarArista(2, -1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void segundoVerticeExcedidoTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		
		grafo.agregarArista(2, 5);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void agregarLoopTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		
		grafo.agregarArista(2, 2);
	}

	@Test
	public void aristaExistenteTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		
		grafo.agregarArista(2, 3);
		assertTrue( grafo.existeArista(2, 3) );
	}

	@Test
	public void aristaOpuestaTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		
		grafo.agregarArista(2, 3);
		assertTrue( grafo.existeArista(3, 2) );
	}

	@Test
	public void aristaInexistenteTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		grafo.agregarVertice(4, null);
		
		grafo.agregarArista(2, 3);
		assertFalse( grafo.existeArista(1, 4) );
	}

	@Test
	public void agregarAristaDosVecesTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		
		grafo.agregarArista(2, 3);
		grafo.agregarArista(3, 2);
		grafo.agregarArista(2, 3);

		assertTrue( grafo.existeArista(2, 3) );
	}
	
	@Test
	public void eliminarAristaExistenteTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		grafo.agregarVertice(4, null);
		
		grafo.agregarArista(2, 4);
		
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4) );
	}

	@Test
	public void eliminarAristaInexistenteTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		grafo.agregarVertice(4, null);
		
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4) );
	}
	
	@Test
	public void eliminarAristaDosVecesTest()
	{
		GrafoListaVecinos grafo = new GrafoListaVecinos();
		grafo.agregarVertice(0, null);
		grafo.agregarVertice(1, null);
		grafo.agregarVertice(2, null);
		grafo.agregarVertice(3, null);
		grafo.agregarVertice(4, null);
		
		grafo.agregarArista(2, 4);
		
		grafo.eliminarArista(2, 4);
		grafo.eliminarArista(2, 4);
		assertFalse( grafo.existeArista(2, 4) );
	}
}
