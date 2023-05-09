package interfaces;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import algoritmos.BFS;
import algoritmos.Kruskal;
import algoritmos.Kruskal.Arista;
import grafos.GrafoListaVecinos;
import localidades.LogicaLocalidad;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JFormattedTextField;
import javax.swing.JTextPane;
import java.awt.Color;
import javax.swing.DropMode;



public class MainForm extends JFrame {
	private JPanel panelMapa;
	private JPanel panelControles;
	private static JMapViewer _mapa;
	private JButton btnConectarLocalidades;
	private JButton btnLocalidad;
	private MapPolygonImpl _poligono;
	

	private GrafoListaVecinos _grafo;
	private JButton btnBuscarArbolMinimo;
	
	/**
	 * Create the application.
	 */
	
	public MainForm(JMapViewer mapa, GrafoListaVecinos grafo) {
		_mapa = mapa;
		_grafo = grafo;
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		dibujarVentana(_mapa);
		nuevaLocalidad();
		generarArbolMinimo();	
		conectarLocalidades();

	}
	
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
							Mensaje += " Origen: " + a.getNombreOrigen() + " - Destino:" + a.getNombreDestino() + " - Precio: $" + formatoNumero.format(a.getPeso()) +"\n";
						}
						JOptionPane.showMessageDialog(null, Mensaje, "Arbol Minimo",JOptionPane.INFORMATION_MESSAGE);
					}catch(Exception ex) {
						JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			btnBuscarArbolMinimo.setBounds(27, 147, 195, 48);
			panelControles.add(btnBuscarArbolMinimo);
		}
	

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
}	


