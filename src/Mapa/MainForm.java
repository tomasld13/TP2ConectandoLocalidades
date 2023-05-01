package Mapa;

import java.awt.EventQueue;
import Mapa.GestionLocalidades;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import javax.swing.DefaultListModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JLabel;
import java.awt.Button;

public class MainForm extends JFrame {

	private JFrame frmConectandoLocalidades;
	private JPanel panelMapa;
	private JPanel panelControles;
	private JMapViewer _mapa;
	private ArrayList<Coordinate> _lasCoordenadas;
	private JButton btnEliminarLocalidad;
	private MapPolygonImpl _poligono;
	private JButton btnBuscarArbolMinimo ;
	private ArrayList<String> localidades;
	private DefaultListModel<String> modelo;
	private JButton btnNewButton;


	/**
	 * Create the application.
	 */
	public MainForm() {

		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		
		setTitle("Conectando Localidades");
		setBounds(100, 100, 725, 506);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBounds(10, 11, 437, 446);
		getContentPane().add(panelMapa);
	
	
		_mapa = new JMapViewer();
		_mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 15);
				
		panelMapa.add(_mapa);
		
		panelControles = new JPanel();
		panelControles.setBounds(457, 11, 242, 446);
		getContentPane().add(panelControles);		
		panelControles.setLayout(null);
		
		localidades = new ArrayList<String>();
		modelo = new DefaultListModel<String>();
		
		detectarCoordenadas();
		generarArbolMinimo();
		eliminarArbolMinimo();		
		agregarLocalidad(localidades, modelo);

	}
	
	private void detectarCoordenadas() 
	{
		_lasCoordenadas = new ArrayList<Coordinate>();
				
		_mapa.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
			if (e.getButton() == MouseEvent.BUTTON1)
			{
				Coordinate markeradd = (Coordinate)
				_mapa.getPosition(e.getPoint());
				_lasCoordenadas.add(markeradd);
				String nombre = JOptionPane.showInputDialog("Nombre: ");
				_mapa.addMapMarker(new MapMarkerDot(nombre, markeradd));
				System.out.println(nombre);	
				localidades.add(nombre);
				System.out.println(_lasCoordenadas);
			}
			
			}

			
		});
	}

	private void generarArbolMinimo() 
	{
		btnBuscarArbolMinimo = new JButton("Buscar Arbol Minimo");
		btnBuscarArbolMinimo.setBounds(10, 150, 195, 54);
		btnBuscarArbolMinimo.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				_poligono = new MapPolygonImpl(_lasCoordenadas);
				_mapa.addMapPolygon(_poligono);
			}
		});
	}

	private void eliminarArbolMinimo() 
	{
		btnEliminarLocalidad = new JButton("Eliminar Arbol Minimo");
		btnEliminarLocalidad.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				 _mapa.removeMapPolygon(_poligono);
			}
		});
		btnEliminarLocalidad.setBounds(10, 215, 195, 54);
		panelControles.add(btnEliminarLocalidad);
		panelControles.add(btnBuscarArbolMinimo);
		
		JButton btnLocalidad = new JButton("Nueva Localidad");
		btnLocalidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GestionLocalidades ventanaLocalidad = new GestionLocalidades();
				ventanaLocalidad.setVisible(true);
				ventanaLocalidad.setAlwaysOnTop(true);
				
			}
		});
		btnLocalidad.setBounds(10, 26, 195, 54);
		panelControles.add(btnLocalidad);
		
		btnNewButton = new JButton("Conectar Localidades");
		btnNewButton.setBounds(10, 91, 195, 48);
		panelControles.add(btnNewButton);

		

	}
	private void agregarLocalidad(ArrayList<String> localidades, DefaultListModel<String> modelo) {
		// TODO Auto-generated method stub
		modelo.removeAllElements();
		for (int i = 0; i<localidades.size(); i++) {
			modelo.addElement(localidades.get(i));
		}
	}	
}	



