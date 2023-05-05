package interfaces;



import javax.swing.JFrame;

import localidades.ConectarLocalidades;
import localidades.Localidad;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;

public class GestionLocalidades extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3521851389367389426L;
	private JPanel panelMapa;
	private JPanel panelInformacion;
	private JPanel panelBotones;
	private JMapViewer _mapa;
	public  ArrayList<Coordinate> _lasCoordenadas;
	private JTextField textNombre;
	private JTextField textProvincia;
	private JTextField textLatitud;
	private JTextField textLongitud;
	
	private JButton btnGuardar;
	private JButton cancelar;
	private JButton btnGuardarYCrear;
	
	private Coordinate markeradd;
	private JList listaLocalidades;
	
	private ArrayList <Localidad>   listarLocalidades;
	Localidad localidad = new Localidad();


	/**
	 * Create the application.
	 * 
	 */
	public GestionLocalidades() {
		getContentPane().setBackground(Color.WHITE);
		setBackground(Color.WHITE);
		setTitle("Nueva Localidad");
		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		listarLocalidades = new ArrayList<>();
		dibujarVentana();
		crearLocalidad();
		cancelar();
		guardarYagregar();
		detectarCoordenadas();
		
	}
	
	private void dibujarVentana() {
		setBounds(100, 100, 795, 521);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		panelMapa = new JPanel();
		panelMapa.setBackground(Color.WHITE);
		panelMapa.setBounds(297, 11, 430, 385);
		getContentPane().add(panelMapa);
		
		_mapa = new JMapViewer();
		_mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		_mapa.setToolTipText("Selecciona un punto para conseguir la Latitud y Longitud");
		_mapa.setZoom(4);
		_mapa.setBounds(0, 0, 430, 385);
		_mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 15);
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
		listaLocalidades.setBounds(10, 48, 245, 201);
		panelLocalidades.add(listaLocalidades);
		
		
	}
	private void crearLocalidad() {
		
		//Localidad localidad = new Localidad();

		btnGuardar = new JButton("Crear");
		btnGuardar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
				localidad.setNombre(textNombre.getText());
				localidad.setProvincia(textProvincia.getText());
				localidad.setLatitud(Double.parseDouble(textLongitud.getText()));				
				localidad.setLongitud(Double.parseDouble(textLatitud.getText()));
				listarLocalidades.add(localidad);
				ConectarLocalidades.listarLocalidades.add(localidad);
				
				
				setVisible(false);
				System.out.println(listarLocalidades);
				} catch (Exception NumberFormatException ) {
					
					JOptionPane.showMessageDialog(null, "Completar todos los datos",
						      "Error!", JOptionPane.ERROR_MESSAGE);
					
					
				}
				
			}
		});
		btnGuardar.setBounds(10, 12, 89, 23);
		panelBotones.add(btnGuardar);

	}
	

	private void cancelar() {		
		cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm ventana = new MainForm();
				ventana.setVisible(true);
				setVisible(false);
			}
		});
		cancelar.setBounds(263, 12, 89, 23);
		panelBotones.add(cancelar);
	}
	
	private void guardarYagregar() {
		btnGuardarYCrear = new JButton("Guardar  y Crear");
		btnGuardarYCrear.addActionListener(new ActionListener() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					  Localidad localidad = new Localidad();
					  localidad.setNombre(textNombre.getText());
					  localidad.setProvincia(textProvincia.getText());
					  localidad.setLatitud(Double.parseDouble(textLongitud.getText()));
					  localidad.setLongitud(Double.parseDouble(textLatitud.getText()));
					  listarLocalidades.add(localidad);
					  ConectarLocalidades.listarLocalidades.add(localidad);
					 
				
				/////ACA////
				int tamano = listarLocalidades.size();
				final String [] vector = new String [tamano];
				for (int conta=0;conta<tamano;conta++) {
					vector[conta] = listarLocalidades.get(conta).getNombre() + " - " + listarLocalidades.get(conta).getProvincia() + " - " + 
									listarLocalidades.get(conta).getLatitud() + " - " + listarLocalidades.get(conta).getLongitud();
				}
				listaLocalidades.setModel(new javax.swing.AbstractListModel() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					String [] vect = vector;
					@Override
					public int getSize() {
						
						return vect.length;
					}

					@Override
					public Object getElementAt(int index) {
						// TODO Auto-generated method stub
						return vect[index];
					}
					
				});

				System.out.println(listarLocalidades.toString());
				
				}catch (Exception NumberFormatException) {
					JOptionPane.showMessageDialog(null, "Completar todos los datos",
						      "Error!", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		btnGuardarYCrear.setBounds(109, 11, 144, 23);
		panelBotones.add(btnGuardarYCrear);
	}

	private void detectarCoordenadas() {
		_lasCoordenadas = new ArrayList<Coordinate>();

		_mapa.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					 markeradd = (Coordinate) _mapa.getPosition(e.getPoint());
					_lasCoordenadas.add(markeradd);
					_mapa.addMapMarker(new MapMarkerDot(markeradd));
					String Latitud = "" + markeradd.getLat();
					String Longitud = "" + markeradd.getLon();
					textLatitud.setText(Latitud);
					textLongitud.setText(Longitud);

				}

			}

		});
	}

}
