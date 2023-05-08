package interfaces;

import javax.swing.JFrame;

import localidades.Localidad;
import localidades.LogicaLocalidad;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import grafos.GrafoListaVecinos;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class GestionLocalidades extends JFrame {

	/**
	 * 
	 */

	private JPanel panelMapa;
	private JPanel panelInformacion;
	private JPanel panelBotones;
	private JMapViewer mapa;
	public 	ArrayList<Coordinate> _lasCoordenadas;
	private JTextField textNombre;
	private JTextField textProvincia;
	private JTextField textLatitud;
	private JTextField textLongitud;

	private JButton atras;
	private JButton btnGuardar;

	private Coordinate markeradd;
	public  JList<String> listaLocalidades;

	public static ArrayList<Localidad> listarLocalidades = new ArrayList<>();
	Localidad localidad;
	static DefaultListModel<String> DLM = new DefaultListModel<String>();
	
	private GrafoListaVecinos _grafo;
	/**
	 * Create the application.
	 * 
	 */
	public GestionLocalidades(JMapViewer mapa, GrafoListaVecinos grafo) {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Nueva Localidad");
		_grafo = grafo;
		mapa = mapa;
		initialize(mapa);
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JMapViewer mapa2) {
		listarLocalidades = new ArrayList<>();
		dibujarVentana();
		atras();
		detectarCoordenadas(mapa2);

	}
	private void dibujarVentana() {
		setBounds(100, 100, 795, 521);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panelMapa = new JPanel();
		panelMapa.setBackground(Color.WHITE);
		panelMapa.setBounds(297, 11, 430, 385);
		getContentPane().add(panelMapa);

		
		mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		mapa.setToolTipText("Selecciona un punto para conseguir la Latitud y Longitud");
		mapa.setBounds(0, 0, 430, 385);
		mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 5);
		panelMapa.setLayout(null);

		panelMapa.add(mapa);

		JLabel lblMapa = new JLabel("Mapa");
		lblMapa.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblMapa.setBounds(186, 410, 58, 29);
		panelMapa.add(lblMapa);

		panelInformacion = new JPanel();
		panelInformacion.setBackground(Color.WHITE);
		panelInformacion.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelInformacion.setBounds(10, 11, 265, 189);
		getContentPane().add(panelInformacion);
		panelInformacion.setLayout(null);

		JLabel lblNombreLocalidad = new JLabel("Nombre");
		lblNombreLocalidad.setBounds(10, 48, 74, 28);
		panelInformacion.add(lblNombreLocalidad);

		JLabel lblLatitud = new JLabel("Latitud");
		lblLatitud.setBounds(10, 122, 52, 14);
		panelInformacion.add(lblLatitud);

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 87, 63, 14);
		panelInformacion.add(lblProvincia);

		textNombre = new JTextField();
		textNombre.setBounds(66, 49, 171, 20);
		panelInformacion.add(textNombre);
		textNombre.setColumns(10);

		textProvincia = new JTextField();
		textProvincia.setBounds(66, 84, 171, 20);
		panelInformacion.add(textProvincia);
		textProvincia.setColumns(10);

		textLatitud = new JTextField();
		textLatitud.setBounds(66, 116, 171, 20);
		panelInformacion.add(textLatitud);
		textLatitud.setColumns(10);

		JLabel lblLongitud = new JLabel("Longitud");
		lblLongitud.setBounds(10, 157, 52, 14);
		panelInformacion.add(lblLongitud);

		textLongitud = new JTextField();
		textLongitud.setBounds(66, 151, 171, 20);
		panelInformacion.add(textLongitud);
		textLongitud.setColumns(10);

		JLabel lblInformacion = new JLabel("Informacion");
		lblInformacion.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblInformacion.setBounds(10, 11, 201, 26);
		panelInformacion.add(lblInformacion);

		panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		panelBotones.setBounds(402, 425, 367, 46);
		getContentPane().add(panelBotones);
		panelBotones.setLayout(null);

		JPanel panelLocalidades = new JPanel();
		panelLocalidades.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelLocalidades.setBackground(Color.WHITE);
		panelLocalidades.setBounds(10, 211, 265, 260);
		getContentPane().add(panelLocalidades);
		panelLocalidades.setLayout(null);

		JLabel lblLocalidades = new JLabel("Localidades");
		lblLocalidades.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblLocalidades.setBounds(10, 11, 217, 26);
		panelLocalidades.add(lblLocalidades);

		listaLocalidades = new JList<String>();
		listaLocalidades.setBorder(new LineBorder(new Color(0, 0, 0)));
		listaLocalidades.setBounds(10, 48, 234, 201);
		panelLocalidades.add(listaLocalidades);

		
		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {									
					Localidad creada = LogicaLocalidad.crearLocalidad(localidad,textNombre.getText(),textProvincia.getText(),Double.parseDouble(textLongitud.getText()),
												Double.parseDouble(textLatitud.getText()),listarLocalidades, listaLocalidades);	
					listaLocalidades.setModel(LogicaLocalidad.crearModel(DLM));
					LogicaLocalidad.agregarLocalidadGrafo(creada, _grafo);
					limpiar();
				} catch (Exception NumberFormatException) {
					JOptionPane.showMessageDialog(null, "Completar todos los datos", "Error!",JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		btnGuardar.setBounds(140, 11, 113, 23);
		panelBotones.add(btnGuardar);
	}

	protected void limpiar() {
		textNombre.setText("");
		textProvincia.setText("");
		textLatitud.setText("");
		textLongitud.setText("");
	}
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
	private void detectarCoordenadas(JMapViewer mapa2) {
		_lasCoordenadas = new ArrayList<Coordinate>();
		mapa = mapa2;
		mapa.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					markeradd = (Coordinate) mapa.getPosition(e.getPoint());
					_lasCoordenadas.add(markeradd);
					mapa.addMapMarker(new MapMarkerDot(textNombre.getText(), markeradd));
					String Latitud = "" + markeradd.getLat();
					String Longitud = "" + markeradd.getLon();
					textLatitud.setText(Latitud);
					textLongitud.setText(Longitud);
				}
			}
		});
	}	
}
