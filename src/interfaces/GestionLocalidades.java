package interfaces;

import javax.swing.JFrame;

import localidades.Localidad;
import localidades.LogicaLocalidad;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;
import org.openstreetmap.gui.jmapviewer.interfaces.MapMarker;

import grafos.GrafoListaVecinos;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

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
	private JMapViewer _mapa;
	private JTextField textNombre;
	// private JTextField textProvincia;
	private JTextField textLatitud;
	private JTextField textLongitud;

	private JButton atras;
	private JButton btnGuardar;

	private Coordinate markeradd;
	public JList<String> listaLocalidades;

	Localidad localidad;
	static DefaultListModel<String> DLM = new DefaultListModel<String>();

	private GrafoListaVecinos _grafo;

	List<MapMarker> marcadoresLocalidades = new ArrayList<MapMarker>();

	/**
	 * Create the application.
	 * 
	 */
	public GestionLocalidades(JMapViewer mapa, GrafoListaVecinos grafo) {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Nueva Localidad");
		_grafo = grafo;
		_mapa = mapa;
		initialize(_mapa);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(JMapViewer mapa) {
		dibujarVentana();
		atras();
		detectarCoordenadas(mapa);

	}

	private void dibujarVentana() {
		setBounds(100, 100, 795, 521);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panelMapa = new JPanel();
		panelMapa.setBackground(Color.WHITE);
		panelMapa.setBounds(297, 11, 430, 385);
		getContentPane().add(panelMapa);

		_mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		_mapa.setToolTipText("Selecciona un punto para conseguir la Latitud y Longitud");
		_mapa.setBounds(0, 0, 430, 385);
		_mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 5);
		panelMapa.setLayout(null);

		panelMapa.add(_mapa);

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

		JComboBox<String> provinciaComBox = new JComboBox<String>();
		provinciaComBox.setModel(new DefaultComboBoxModel<String>(
				new String[] { "Buenos Aires", "Ciudad Autónoma de Buenos Aires", "Catamarca", "Chaco", "Chubut",
						"Córdoba", "Corrientes", "Entre Ríos", "Formosa", "Jujuy", "La Pampa", "La Rioja", "Mendoza",
						"Misiones", "Neuquén", "Río Negro", "Salta", "San Juan", "San Luis", "Santa Cruz", "Santa Fe",
						"Santiago del Estero", "Tierra del Fuego, Antártida e Islas del Atlántico Sur", "Tucumán" }));
		provinciaComBox.setSelectedIndex(0);
		provinciaComBox.setBounds(66, 83, 171, 22);
		panelInformacion.add(provinciaComBox);

		JLabel lblProvincia = new JLabel("Provincia");
		lblProvincia.setBounds(10, 87, 63, 14);
		panelInformacion.add(lblProvincia);

		textNombre = new JTextField();
		textNombre.setBounds(66, 49, 171, 20);
		panelInformacion.add(textNombre);
		textNombre.setColumns(10);

		// textProvincia = new JTextField();
		// textProvincia.setBounds(66, 84, 171, 20);
		// panelInformacion.add(textProvincia);
		// textProvincia.setColumns(10);

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
		listaLocalidades.setModel(LogicaLocalidad.crearModel(DLM));
		listaLocalidades.setBorder(new LineBorder(new Color(0, 0, 0)));
		listaLocalidades.setBounds(10, 48, 234, 201);
		panelLocalidades.add(listaLocalidades);

		btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (!textNombre.getText().isEmpty()) {
						Localidad creada = LogicaLocalidad.crearLocalidad(localidad, textNombre.getText(),
								provinciaComBox.getSelectedItem().toString(),
								Double.parseDouble(textLongitud.getText()), Double.parseDouble(textLatitud.getText()),
								listaLocalidades);
						listaLocalidades.setModel(LogicaLocalidad.crearModel(DLM));
						LogicaLocalidad.agregarLocalidadGrafo(creada, _grafo);
						_mapa.addMapMarker(new MapMarkerDot(textNombre.getText(), markeradd));
						
						//Se crea nuevo puntero
						_mapa.addMapMarker(new MapMarkerDot("Nueva Localidad", new Coordinate(0,0)));
						limpiar();
					} else {
						JOptionPane.showMessageDialog(null, "Agregue un nombre a la Localidad", "Error!",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception NumberFormatException) {
					JOptionPane.showMessageDialog(null, "Completar todos los datos", "Error!",
							JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		});

		btnGuardar.setBounds(140, 11, 113, 23);
		panelBotones.add(btnGuardar);
	}

	protected void limpiar() {
		textNombre.setText("");
		// textProvincia.setText("");
		textLatitud.setText("");
		textLongitud.setText("");
	}

	private void atras() {
		atras = new JButton("Atras");
		atras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// _mapa.removeAllMapMarkers();
				// for(MapMarker mm : marcadoresLocalidades) {
				// _mapa.addMapMarker(mm);
				// }
				setVisible(false);
				MainForm ventana = new MainForm(_mapa, _grafo);
				ventana.setVisible(true);
			}
		});
		atras.setBounds(263, 12, 89, 23);
		panelBotones.add(atras);
	}

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

	@Override
	public String toString() {
		return "toString Gestion Localidades";
	}
}
