# Trabajo Practico 2

## Participantes:

- Fernando Abrile
- Tomás Ledesma

> Programación 3 - Comisión 1, Universidad Nacional de General Sarmiento.
> 

## Objetivos

El objetivo del trabajo práctico es implementar una aplicación para planificar conexiones

telefónicas entre localidades ubicadas en regiones despobladas. Dado un conjunto de localidades, se debe proporcionar un  árbol generador mínimo entre ellas e informar el costo de la

solución.

La aplicación debe permitir al usuario registrar una serie de localidades, incluyendo el nombre,

provincia, latitud y longitud de cada una. Además, se deben proporcionar los siguientes costos:

- Costo en pesos por kilómetro de una conexión entre dos localidades.
- Porcentaje de aumento del costo si la conexión tiene más de 300 km.
- Costo fijo que se agrega si la conexión involucra localidades de dos provincias distintas.

Cuando el usuario solicita la planificación, se debe resolver el problema de árbol generador

mínimo sobre el grafo completo dado por todas las localidades, y cuyas aristas tienen costos

calculados de acuerdo con los ítems anteriores. Se debe presentar al usuario la solución,

especificando las conexiones telefónicas a construir y el costo total de las instalaciones

Para conseguir el objetivo, el grupo diseñó una solución en Java con los siguientes paquetes de clases.

- algoritmos
    - BFS.java
    - Kruskal.java
- grafo
    - ConsultaDeVecinosTest.java
    - EdicionDeAristasTest.java
    - GrafoListaVecinos.java
- interfaces
    - ConectarLocalidades.java
    - GestionLocalidades.java
    - MainForm.java
- localidades
    - Localidad.java
    - LogicaLocalidad.java
- main
    - Principal.java

En las siguientes secciones se explica el funcionamiento de cada una y los métodos utilizados para alcanzar dicho objetivo.

## Clases, Paquetes, Métodos y Comportamientos.

## Paquete Main

> Principal.java
> 

La clase principal es el *main* del proyecto, la misma tendrá la función de iniciar la aplicación y lo hará haciendo un llamado a la clase **MainForm.java.**

Contiene como atributos:

- _mapa: El JMapWiewer que será usado en toda la app.
- _grafo: El GrafoListaVecinos que será usado en toda la app.

La estructura de la misma es la siguiente. 

```java
public class Principal {
	private static JMapViewer _mapa = new JMapViewer();
	private static GrafoListaVecinos _grafo = new GrafoListaVecinos();

	public static void main(String[] args) {
		MainForm ventana = new MainForm(_mapa, _grafo);
		ventana.setVisible(true);

	}
}
```

### **Paquete Localidades**

> Localidad.java
> 

La clase Localidad tiene como función principal la creación del objeto **Localidad**, contiene las propiedades del Objeto.

Sus atributos principales son:

- *******String******* nombre
- *******String******* provincia
- ***********Coordinate*********** coordenadas
- ****int**** Codigo
- **********ArrayList<String>********** vecinos

Cada uno de sus atributos posee un get() y un set() correspondiente a cada uno que serán utilizados a lo largo del proyecto. A su vez, se sobre-escribio el metodo toString() para mostrar información de la **********Localidad.**********

La estructura de la misma es la siguiente. 

```java
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
	public Localidad() {	}
	public Localidad (String nombre, String provincia, double latitud, double longitud, 
										int codigo) {
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
```

> LogicaLocalidad.java
> 

La clase ****************************************LogicaLocalidad.java**************************************** es la que se encargara de las operaciónes lógicas que pueden realizarse sobre una localidad, como crear una nueva localidad, validar la existencia de una localidad,  agregar una localidad al Grafo y generar el Árbol Mínimo.

Esta clase hace uso de funciones y propiedades de las clases BFS, Kruskal, y GrafosListaVecinos.

La estructura de sus métodos es la siguiente:

1. **********************************crearLocalidad()**********************************

```
public static Localidad crearLocalidad(Localidad localidad, String nombre, 
																			String provincia, double latitud,double longitud, 
																			JList<String> listaLocalidades) {
		localidad = new Localidad(nombre, provincia, latitud, longitud, 
															listarLocalidades.size());
		listarLocalidades.add(localidad);
		
		Coordinate coor = new Coordinate(latitud, longitud);
		if(!existeLocalidad(coor)) {
			localidad = new Localidad(nombre, provincia, latitud, longitud, 
																listarLocalidades.size());	
			listarLocalidades.add(localidad);
		}
		return localidad;
	}
```

1. **********************************************agregarLocalidadGrafo()**********************************************

```
public static void agregarLocalidadGrafo(Localidad localidad, GrafoListaVecinos grafo) {
		grafo.agregarVertice(localidad.getCodigo(), localidad);
	}
	
	public static void conectarLocalidadesGrafo(int a, int b, GrafoListaVecinos grafo) {
		grafo.agregarArista(a, b);
	}
```

1. ************************crearModel()************************ **Este model se utiliza para visibilizar las localidades en los JList**

```
public static ListModel<String> crearModel(DefaultListModel<String> dLM) {
		int tamano = listarLocalidades.size();
		
		final String[] vector = new String[tamano];
		for (int conta = 0; conta < tamano; conta++) {
			vector[conta] = conta + " - " + listarLocalidades.get(conta).getNombre() + " - "
					+ listarLocalidades.get(conta).getProvincia() + " - " + 
					listarLocalidades.get(conta).getCoordenadas().getLat()
					+ " - " + listarLocalidades.get(conta).getCoordenadas().getLon();
			if (!dLM.contains(vector[conta]))
					dLM.addElement(vector[conta]);
		}		
		return dLM;
	}
```

1. **********************************existeLocalidad()**********************************

```
public static Boolean existeLocalidad(Coordinate coordenadas) {
		for(Localidad loca : listarLocalidades) {
			if (loca.getCoordenadas().equals(coordenadas)) {
				return true;
			}
		}		
		return false;
	}
```

1. ******************************generarArbolMinimo()******************************

```
public static ArrayList<Arista> generarArbolMinimo(GrafoListaVecinos _grafo) {
		if(!BFS.esConexo(_grafo))
			throw new IllegalArgumentException("Todos los pares de localidades tienen que 
																				estar conectados al menos por un camino.");
		Kruskal kruskal = new Kruskal();		        
        // Obtenemos el árbol de expansión mínima
        return kruskal.kruskal(_grafo);		  
	}
```

> LogicaLocalidadTest.java
> 

La clase **************************************************LogicaLocalidadTest.java************************************************** es una clase que evaluá la funcionalidad de la clase LogicaLocalidad utilizando al objeto Localidad mediante JUnit Test.

Su estructura es la siguiente:

```java
public class LogicaLocalidadTest {
	
	GrafoListaVecinos grafo;
	Localidad localidad_1,localidad_2,localidad_3,localidad_4;
	Coordinate coordenada;
	
	@Before
	public void setUp() throws Exception {
		LogicaLocalidad.listarLocalidades = new ArrayList<Localidad>();
		localidad_1 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, 
																-58.447265625,0);
		localidad_2 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, 
																-58.447265625,0);
		localidad_3 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, 
																-58.447265625,0);
		localidad_4 = new Localidad("CABA", "Buenos Aires", -34.488447837809304, 
																-58.447265625,0);
		coordenada = new Coordinate(-34.488447837809304, -58.447265625);
		LogicaLocalidad.listarLocalidades.add(localidad_1);
		LogicaLocalidad.listarLocalidades.add(localidad_2);
		LogicaLocalidad.listarLocalidades.add(localidad_3);
		LogicaLocalidad.listarLocalidades.add(localidad_4);		
	}

	@Test
	public void testAgregarLocalidad() {
		assertTrue(LogicaLocalidad.listarLocalidades.add(localidad_1));
	}

	@Test
	public void testMismaLocalidad() {
		assertTrue(LogicaLocalidad.existeLocalidad(coordenada));
	}
	
}
```

## Paquete Interfaces

El paquete interfaces almacena todas las clases referenciadas a interfaz grafica, interfaz que utiliza la libreria ************************************javax.swing.*************************************. Y las interfaces que creamos en el proyecto son:

> MainForm.java
> 

 La interfaz **************************MainForm.java************************** es la ventana principal del proyecto, es desde done el usuario seleccionara la opción de Crear Localidad, Conectar Localidades o  Generar Árbol Mínimo.

Su diseño es el siguiente.

![Untitled](Trabajo%20Practico%202%2018fb63b760d949fabe24c6550f6bc5b5/Untitled.png)

La estructura que tienen las posibles acciones que puede tomar el usuario son las siguientes.

1. Nueva Localidad

```
private void nuevaLocalidad() {
		
		btnLocalidad = new JButton("Nueva Localidad");
		btnLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionLocalidades ventanaLocalidad = new GestionLocalidades(_mapa, _grafo);
				ventanaLocalidad.setVisible(true);
				setVisible(false);
			}
		});
		btnLocalidad.setBounds(27, 23, 195, 54);
		panelControles.add(btnLocalidad);
		
	}
```

1. Conectar Localidades

```
private void conectarLocalidades() {
		btnConectarLocalidades = new JButton("Conectar Localidades");
		btnConectarLocalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("///// Conectar Localidades /////");
				ConectarLocalidades ventana = new ConectarLocalidades(_mapa, _grafo);
				ventana.setVisible(true);
				}
		});
		btnConectarLocalidades.setBounds(27, 88, 195, 48);
		panelControles.add(btnConectarLocalidades);
	}
```

1. Generar Árbol Mínimo

```java
private void generarArbolMinimo(){
			
			btnBuscarArbolMinimo = new JButton("Generar Arbol Minimo");
			btnBuscarArbolMinimo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						NumberFormat formatoNumero = NumberFormat.getNumberInstance();
						ArrayList<Arista> arbol = LogicaLocalidad.generarArbolMinimo(_grafo);
						System.out.println("///// Generar Arbol Minimo /////");
						String Mensaje = "";
						for (Arista a : arbol) {
							Mensaje += " Origen: " + a.getNombreOrigen() + " - Destino:" + 
									a.getNombreDestino() + " - Precio: $" + 
									formatoNumero.format(a.getPeso()) +"\n";
						}
						JOptionPane.showMessageDialog(null, Mensaje, "Arbol Minimo",
																					JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!",
																					JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnBuscarArbolMinimo.setBounds(27, 147, 195, 48);
			panelControles.add(btnBuscarArbolMinimo);
		}
```

A su vez existe un método que lleva a cabo el diseño de la ventana llama ********************************dibujarVentana()******************************** que recibirá como parámetro al ********Mapa******** que sera el mismo en todo el proyecto.

```java
private void dibujarVentana(JMapViewer mapa) {
		setTitle("Conectando Localidades");
		setBounds(100, 100, 725, 506);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(10, 11, 437, 446);
		getContentPane().add(panelMapa);
	
		_mapa = mapa;
		_mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 5);
				
		panelMapa.add(_mapa);		
		panelControles = new JPanel();
		panelControles.setForeground(Color.BLACK);
		panelControles.setBounds(457, 11, 242, 446);
		getContentPane().add(panelControles);		
		panelControles.setLayout(null);
		
		new ArrayList<String>();
		new DefaultListModel<String>();
		
	}
```

> GestionLocalidades.java
> 

La interfaz **GestionLocalidades** es la ventana mediante la cual el usuario creara una localidad nueva, ingresando su Nombre, Provincia, Coordenadas, para las cuales el usuario podrá utilizar el Mapa disponible y al seleccionar un punto en el mismo automáticamente se cargaran los campos de **********Latitud y Longitud,********** una vez que todos los campos están completos la Localidad se crea y se agrega a una lista que esta dentro de la clase *******************ConectarLocalidades******************* con el fin de facilitar la conexión de las Localidades creadas.

El diseño de la interfaz es el siguiente.

![Untitled](Trabajo%20Practico%202%2018fb63b760d949fabe24c6550f6bc5b5/Untitled%201.png)

Existen 4 métodos que se utilizan dentro de esta interfaz, ya sea para Guardar la Localidad, volver a la ventana Anterior, Limpiar los campos, detectar las Coordenadas al momento que el usuario selecciona un punto en el mapa así como la de dibujar la Ventana.

La estructura de estos métodos es la siguiente.

1. ******************************************detectarCoordenadas()******************************************

```
private void detectarCoordenadas(JMapViewer mapa) {
		_mapa = mapa;
		_mapa.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					List<MapMarker> marcadores = new ArrayList<MapMarker>();

					for (MapMarker mm : _mapa.getMapMarkerList()) {
						marcadores.add(mm);
					}

					if (marcadores.size() > 0) {
						int tamaño = marcadores.size() - 1;
						marcadores.remove(tamaño);
						_mapa.removeAllMapMarkers();
						for (MapMarker mm : marcadores) {
							_mapa.addMapMarker(mm);
						}
					}
					
					markeradd = (Coordinate) _mapa.getPosition(e.getPoint());
					_mapa.addMapMarker(new MapMarkerDot(textNombre.getText(), markeradd));

					String Latitud = "" + markeradd.getLat();
					String Longitud = "" + markeradd.getLon();
					textLatitud.setText(Latitud);
					textLongitud.setText(Longitud);
				}
			}
		});
	}
```

1. **************atras()**************

```
private void atras() {
		atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainForm ventana = new MainForm(_mapa, _grafo);
				ventana.setVisible(true);
			}
		});
		atras.setBounds(263, 12, 89, 23);
		panelBotones.add(atras);
	}
```

1. ******************limpiar()******************

```
protected void limpiar() {
		textNombre.setText("");
		textLatitud.setText("");
		textLongitud.setText("");
	}
```

1. ************************************Guardar Localidad************************************

```
btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textNombre.getText().isEmpty()) {
						Localidad creada = LogicaLocalidad.crearLocalidad(localidad, 
																								textNombre.getText(),
								provinciaComBox.getSelectedItem().toString(),
								Double.parseDouble(textLongitud.getText()), 
																		Double.parseDouble(textLatitud.getText()),
								listaLocalidades);
						listaLocalidades.setModel(LogicaLocalidad.crearModel(DLM));
						LogicaLocalidad.agregarLocalidadGrafo(creada, _grafo);
						_mapa.addMapMarker(new MapMarkerDot(textNombre.getText(), markeradd));
						
						//Se crea nuevo puntero
						_mapa.addMapMarker(new MapMarkerDot("Nueva Localidad", new Coordinate(0,0)));
						limpiar();
					} else {
						JOptionPane.showMessageDialog(null, "Agregue un nombre a la Localidad", 
																								"Error!",JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception NumberFormatException) {
					JOptionPane.showMessageDialog(null, "Completar todos los datos", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});
```

> ConetarLocalidades.java
> 

La interfaz ************************ConectarLocalidades.java************************ tiene como propósito principal, conectar dos Localidades existen entre si. Para ello, al momento de la creación de una nueva Localidad, esta misma se agrega dentro de un ******************************ArrayList<Localidad>****************************** el cual esta dentro de la clase LogicaLocalidades. Luego este ArrayList es utilizado para la conexión de las Localidades.

El método utilizado para conectar las localidades tiene la siguiente estructura.

```
public void actionPerformed(ActionEvent e) {
				int[] seleccionado = listaLocalidadesConectar.getSelectedIndices();
				for(int i=0; i < seleccionado.length; i++) {
					if(i != seleccionado.length-1) {
						LogicaLocalidad.conectarLocalidadesGrafo(seleccionado[i], seleccionado[i+1], 
																											_grafo);
						JOptionPane.showMessageDialog(null, 
							LogicaLocalidad.listarLocalidades.get(seleccionado[i]).getNombre() +
							" conectada con: " + 
							LogicaLocalidad.listarLocalidades.get(seleccionado[i+1]).getNombre(), 
							"Conexión",JOptionPane.INFORMATION_MESSAGE);
					}
					for(int j = 0; j < seleccionado.length; j++) {
						if(j != i) {
							LogicaLocalidad.listarLocalidades.get(seleccionado[i])
								.agregarVecino(LogicaLocalidad.listarLocalidades.get(seleccionado[j])
								.getNombre());
						}
							
					}
				}
			}
		});
```

Ademas del método de ConectarLocalidades, dentro de la interfaz se llevan a cabo 2 acciones mas.

1. ********************verInfo()******************** este método muestra la información de una localidad especifica y así saber si esta conectada a alguna otra localidad.

```
JButton btnInfo = new JButton("Ver Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, 
			LogicaLocalidad.listarLocalidades.get(listaLocalidadesConectar.getSelectedIndex()), 
			"Info Localidad",JOptionPane.INFORMATION_MESSAGE);
			}
		});
```

1. método para volver a la ventana anterior **************atras()**************

```
Button btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainForm ventana = new MainForm(_mapa, _grafo);
				ventana.setVisible(true);
			}
		});
```

## Paquete Grafos

> GrafoListaVecinos.java
> 

La clase ****************************GrafoListaVecinos.java**************************** contiene los métodos que serán utilizados para modificar y crear Aristas del grafo.

Contiene los siguientes métodos.

1. ****************agregarVertices()****************

```
public void agregarVertice(int vertice, Localidad localidad) {
		vecinos.add(new HashSet<Integer>());
        localidades.put(vertice, localidad);
    }
```

1. **********************************eliminarVertice()**********************************

```
public void eliminarVertice(int vertice) {
        localidades.remove(vertice);
    }
```

1. ******agregarArista()******

```
public void agregarArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		vecinos.get(i).add(j);
		vecinos.get(j).add(i);
	}
```

1. ************eliminarArista()************

```
public void eliminarArista(int i, int j)
	{	
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		vecinos.get(i).remove(j);
		vecinos.get(j).remove(i);
	}
```

1. ****************************existeArista()****************************

```
public boolean existeArista(int i, int j)
	{
		verificarVertice(i);
		verificarVertice(j);
		verificarDistintos(i, j);
		
		return vecinos.get(i).contains(j);
	}
```

1. **************************************verificarVertices()**************************************

```
private void verificarVertice(int i)
	{
		if( i < 0 )
			throw new IllegalArgumentException("El vertice no puede ser negativo: " + i);
		
		if( i >= vecinos.size() )
			throw new IllegalArgumentException("Los vertices deben estar entre 0 y |V|-1: " +
																																									 i);
	}
```

1. **************************************verificarDestinos()**************************************

```
private void verificarDistintos(int i, int j)
	{
		if( i == j )
			throw new IllegalArgumentException("No se permiten loops: (" + i + ", " + j + ")");
	}
```

1. ******************darPeso()******************

```
public double darPeso(int x, int y) {
		Localidad localidad1 = localidades.get(x);
		Localidad localidad2 = localidades.get(y);
		
		BigDecimal precioKM = new BigDecimal("250.00");
		BigDecimal distintasProvincias = new BigDecimal("15000.00"); //Aumento del 10% 
																			//si las localidades son de provincias distintas.
		int aumento300KM = 20; //Aumento del 20% si la distancia es mayor a 300km.
		
		double total;
		
		double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(localidad2.getCoordenadas().getLat() 
																			- localidad1.getCoordenadas().getLat());  
        double dLng = Math.toRadians(localidad2.getCoordenadas().getLon() 
																			- localidad1.getCoordenadas().getLon());  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(localidad1.getCoordenadas().getLat())) 
								* Math.cos(Math.toRadians(localidad2.getCoordenadas().getLat()));  
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
```

> ****************************************************ConsultaDeVecinosTest.java****************************************************
> 

La clase ************************************************************************ConsultaDeVecinosTest.java************************************************************************ es la clase que comprueba el funcionamiento de **********************************ConsultaDeVecinos********************************** mediante JUnit Test.

La clase contiene la siguiente estructura.

```java
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
```

> ****************************************************EdicionDeAristasTest.java****************************************************
> 

La clase ************************************************************************EdicionDeAristasTest.java************************************************************************ es la clase que comprueba el manejo de las Aristas mediante JUnit Test utilizando los métodos de ************************************GrafoListaVecinos.java************************************

La clase contiene la siguiente estructura.

```
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
```

## ************************************Paquete Algoritmos************************************

El paquete algoritmo incluye los algoritmos de ************************Kurskal************************  y ********BFS******** 

> ************************Kruskal.java************************
> 

La clase **Kruskal.java** contiene el algoritmo del autor ************************Kruskal************************, el cual se encarga de generar un Árbol Mínimo según un grafo pasado por parámetro, contiene una subclase para las ****************Aristas**************** 

La estructura de la clase ******Kruska.java****** es la siguiente

```java
public class Kruskal {
    private int[] padres;

    public ArrayList<Arista> kruskal(GrafoListaVecinos grafo) {
        // Inicializar los padres de los vértices
        padres = new int[grafo.tamano()];
        for (int i = 0; i < padres.length; i++) {
            padres[i] = i;
        }

        // Obtener todas las aristas del grafo
        ArrayList<Arista> aristas = new ArrayList<>();
        for (int i = 0; i < grafo.tamano(); i++) {
            for (int vecino : grafo.vecinos(i)) {
                if (i < vecino) {  // Solo agregamos la arista una vez
                    aristas.add(new Arista(i, grafo.darVertice(i).getNombre(), 
										grafo.darVertice(i).getCoordenadas(), vecino, grafo.darVertice(vecino)
										.getNombre(), grafo.darVertice(vecino).getCoordenadas(), 
										grafo.darPeso(i, vecino)));
                }
            }
        }

        // Ordenar las aristas por peso de menor a mayor
        Collections.sort(aristas, Comparator.comparingDouble(Arista::getPeso));

        // Aplicar el algoritmo de Kruskal
        ArrayList<Arista> arbolRecubridor = new ArrayList<>();
        for (Arista arista : aristas) {
            int padreOrigen = buscarPadre(arista.origen);
            int padreDestino = buscarPadre(arista.destino);

            if (padreOrigen != padreDestino) {
                arbolRecubridor.add(arista);
                padres[padreOrigen] = padreDestino;
            }
        }

        return arbolRecubridor;
    }
```

Esta clase tiene un método **************************buscarPadre()************************** con la siguiente estructura. Este método cumple la función de Union-Find.

```java
private int buscarPadre(int vertice) {
        if (padres[vertice] == vertice) {
            return vertice;
        }

        padres[vertice] = buscarPadre(padres[vertice]);
        return padres[vertice];
    }
```

La subClase **********************Arista,********************** tiene la siguiente estructura

```java
public class Arista {
        final private int origen;
        final private String nombreOrigen;
        final private Coordinate coordOrigen;
        final private int destino;
        final private String nombreDestino;
        final private Coordinate coordDestino;
        final private double peso;

        public Arista(int origen, String nombreOrigen, Coordinate coordOrigen, 
											int destino, String nombreDestino, Coordinate coordDestino, 
											double peso) {
            this.origen = origen;
            this.nombreOrigen = nombreOrigen;
            this.coordOrigen = coordOrigen;
            this.destino = destino;
            this.nombreDestino = nombreDestino;
            this.coordDestino = coordDestino;
            this.peso = peso;
        }
        
		public int getOrigen() {
            return origen;
        }
        
        public String getNombreOrigen() {
            return nombreOrigen;
        }
        
        public Coordinate getCoordenadaOrigen() {
            return coordOrigen;
        }
        public int getDestino() {
            return destino;
        }
        
        public String getNombreDestino() {
            return nombreDestino;
        }
        
        public Coordinate getCoordenadaDestino() {
            return coordDestino;
        }
        
        public double getPeso() {
            return peso;
        }
    }
```

> BFS.java
> 

La clase ****************[BFS.java](http://BFS.java)**************** se encargara de  validar si nuestro árbol es conexo, y saber si las aristas son alcanzables, así también como saber que vecinos quedan pendientes de agregar al árbol.

Estos métodos tienen la siguiente estructura.

1. ******************esConexo()******************

```
public static boolean esConexo(GrafoListaVecinos g) 
	{
		if (g == null)
			throw new IllegalArgumentException("El grafo no puede ser null.");
		
		return g.tamano() == 0 || alcanzables(g, 0).size() == g.tamano();
	}
```

1. **************************alcanzables()**************************

```
public static Set<Integer> alcanzables(GrafoListaVecinos g, int origen) 
	{
		Set<Integer> ret = new HashSet<Integer>();
		inicializarRecorrido(g, origen);
		
		while (!L.isEmpty()) 
		{
			int i = L.get(0);
			marcados[i] = true;
			
			ret.add(i);
			agregarVecinosPendientes(g, i);
			L.remove(0);
		}
		return ret;
	}
```

1. **********agregarVecinosPendientes()**********

```
private static void agregarVecinosPendientes(GrafoListaVecinos g, int vertice) 
	{		
		for (int vecino : g.vecinos(vertice))
			if (!marcados[vecino] && !L.contains(vecino))
				L.add(vecino);
	}
```

1. ********************************************inicializarRecorrido()********************************************

```
private static void inicializarRecorrido(GrafoListaVecinos g, int origen) 
	{
		L = new LinkedList<Integer>();
		marcados = new boolean[g.tamano()];
		L.add(origen);
	}
```
