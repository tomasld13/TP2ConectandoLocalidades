package localidades;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.openstreetmap.gui.jmapviewer.Coordinate;

import grafos.GrafoListaVecinos;
import interfaces.GestionLocalidades;

public class LogicaLocalidadTest {
	
	GrafoListaVecinos grafo;
	Localidad localidad_1,localidad_2,localidad_3,localidad_4;
	Coordinate coordenada;
	ArrayList<Localidad> listarLocalidades = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		localidad_1 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, -58.447265625,0);
		localidad_2 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, -58.447265625,0);
		localidad_3 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, -58.447265625,0);
		localidad_4 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, -58.447265625,0);
		coordenada = new Coordinate(-34.488447837809304, -58.447265625);
		listarLocalidades.add(localidad_1);
		listarLocalidades.add(localidad_2);
		listarLocalidades.add(localidad_3);
		listarLocalidades.add(localidad_4);		
	}

	@Test
	public void testAgregarLocalidad() {
		assertTrue(GestionLocalidades.listarLocalidades.add(localidad_1));
	}

	@Test
	public void testMismaLocalidad() {
		assertTrue(LogicaLocalidad.existeLocalidad(listarLocalidades, coordenada));
	}
	
}