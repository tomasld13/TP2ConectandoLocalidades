package Mapa;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapMarkerDot;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.List;
import javax.swing.JList;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class GestionLocalidades extends JFrame {

	private JFrame frame;
	private JMapViewer _mapa;
	private ArrayList<Coordinate> _lasCoordenadas;
	private JTextField textNombre;
	private JTextField textProvincia;
	private JTextField textLatitud;
	private JTextField textLongitud;
	private JList listaLocalidades;



	/**
	 * Create the application.
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
		dibujarVentana();
		detectarCoordenadas();
		
	}
	
	private void dibujarVentana() {
		setBounds(100, 100, 795, 521);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBackground(Color.WHITE);
		panelMapa.setBounds(297, 11, 430, 385);
		getContentPane().add(panelMapa);
		
		_mapa = new JMapViewer();
		_mapa.setToolTipText("Selecciona un punto para conseguir la Latitud y Longitud");
		_mapa.setZoom(4);
		_mapa.setBounds(10, 46, 410, 328);
		_mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 15);
		panelMapa.setLayout(null);
		panelMapa.add(_mapa);
		
		JLabel lblNewLabel_5 = new JLabel("Mapa");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblNewLabel_5.setBounds(10, 11, 102, 24);
		panelMapa.add(lblNewLabel_5);
		
		JPanel panelInformacion = new JPanel();
		panelInformacion.setBackground(Color.WHITE);
		panelInformacion.setBorder(null);
		panelInformacion.setBounds(10, 11, 265, 189);
		getContentPane().add(panelInformacion);
		panelInformacion.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nombre");
		lblNewLabel.setBounds(10, 48, 74, 28);
		panelInformacion.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Latitud");
		lblNewLabel_1.setBounds(10, 122, 52, 14);
		panelInformacion.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Provincia");
		lblNewLabel_2.setBounds(10, 87, 63, 14);
		panelInformacion.add(lblNewLabel_2);
		
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
		
		JLabel lblNewLabel_3 = new JLabel("Longitud");
		lblNewLabel_3.setBounds(10, 157, 52, 14);
		panelInformacion.add(lblNewLabel_3);
		
		textLongitud = new JTextField();
		textLongitud.setBounds(66, 151, 171, 20);
		panelInformacion.add(textLongitud);
		textLongitud.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Informacion");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblNewLabel_4.setBounds(10, 11, 201, 26);
		panelInformacion.add(lblNewLabel_4);
		
		JPanel panelBotones = new JPanel();
		panelBotones.setBackground(Color.WHITE);
		panelBotones.setBounds(402, 425, 367, 46);
		getContentPane().add(panelBotones);
		panelBotones.setLayout(null);
		
		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
							}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		panelBotones.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(263, 12, 89, 23);
		panelBotones.add(btnNewButton_1);
		
		JButton btnGuardarYAgregar = new JButton("Guardar y Agregar");
		btnGuardarYAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				listaLocalidades.add(textProvincia);
			}
		});
		btnGuardarYAgregar.setBounds(109, 11, 144, 23);
		panelBotones.add(btnGuardarYAgregar);
		
		JPanel panelLocalidades = new JPanel();
		panelLocalidades.setBackground(Color.WHITE);
		panelLocalidades.setBounds(10, 211, 265, 260);
		getContentPane().add(panelLocalidades);
		panelLocalidades.setLayout(null);
		
		listaLocalidades = new JList();
		listaLocalidades.setBounds(10, 229, 189, -173);
		panelLocalidades.add(listaLocalidades);
		
		JLabel lblNewLabel_4_1 = new JLabel("Localidades");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.ITALIC, 24));
		lblNewLabel_4_1.setBounds(10, 11, 217, 26);
		panelLocalidades.add(lblNewLabel_4_1);
		
		
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
				_mapa.addMapMarker(new MapMarkerDot(markeradd));
				String Latitud = ""+ markeradd.getLat();
				String Longitud = ""+ markeradd.getLon();
				textLatitud.setText(Latitud);
				textLongitud.setText(Longitud);
				

			}
			
			}

			
		});
	}
}
