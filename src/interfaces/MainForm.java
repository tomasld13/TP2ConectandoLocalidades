package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import grafos.GrafoListaVecinos;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;
import java.util.ArrayList;



public class MainForm extends JFrame {


	private JPanel panelMapa;
	private JPanel panelControles;
	private static JMapViewer mapa;
	private JButton btnEliminarLocalidad;
	private JButton btnBuscarArbolMinimo ;
	private JButton btnConectarLocalidades;
	private JButton btnLocalidad;
	protected Object frame;

	private GrafoListaVecinos _grafo;
	/**
	 * Create the application.
	 */
	public MainForm(JMapViewer mapa2, GrafoListaVecinos grafo) {
		mapa = mapa2;
		_grafo = grafo;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		dibujarVentana(mapa);
		nuevaLocalidad();
		generarArbolMinimo();
		eliminarArbolMinimo();		
		conectarLocalidades();

	}
	


	private void conectarLocalidades() {
		
		btnConectarLocalidades = new JButton("Conectar Localidades");
		btnConectarLocalidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("///// Conectar Localidades /////");
				ConectarLocalidades ventana = new ConectarLocalidades(mapa, _grafo);
				ventana.setVisible(true);
				}
		});
		btnConectarLocalidades.setBounds(27, 88, 195, 48);
		panelControles.add(btnConectarLocalidades);
	}

	private void nuevaLocalidad() {
		
		btnLocalidad = new JButton("Nueva Localidad");
		btnLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionLocalidades ventanaLocalidad = new GestionLocalidades(mapa, _grafo);
				ventanaLocalidad.setVisible(true);
				setVisible(false);
			}
		});
		btnLocalidad.setBounds(27, 23, 195, 54);
		panelControles.add(btnLocalidad);
		
	}

	private void dibujarVentana(JMapViewer mapa2) {
		setTitle("Conectando Localidades");
		setBounds(100, 100, 725, 506);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(10, 11, 437, 446);
		getContentPane().add(panelMapa);
	
		mapa = mapa2;
		mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 5);
				
		panelMapa.add(mapa);		
		panelControles = new JPanel();
		panelControles.setBounds(457, 11, 242, 446);
		getContentPane().add(panelControles);		
		panelControles.setLayout(null);
		
		new ArrayList<String>();
		new DefaultListModel<String>();
		
	}

	private void generarArbolMinimo(){
		
		btnBuscarArbolMinimo = new JButton("Buscar Arbol Minimo");
		btnBuscarArbolMinimo.setBounds(27, 147, 195, 54);
		btnBuscarArbolMinimo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0){
				PrbLocalidad ventana = new PrbLocalidad();
				ventana.setVisible(true);
				System.out.println("///// Generar Arbol Minimo /////");
			}
		});
	}

	private void eliminarArbolMinimo(){
		
		btnEliminarLocalidad = new JButton("Eliminar Arbol Minimo");
		btnEliminarLocalidad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				System.out.println("///// Borrar Arbol Minimo / /////");
			}
		});
		btnEliminarLocalidad.setBounds(27, 212, 195, 54);
		panelControles.add(btnEliminarLocalidad);
		panelControles.add(btnBuscarArbolMinimo);
		
		
	}
	
	
}	



