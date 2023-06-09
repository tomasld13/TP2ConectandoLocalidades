package interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.MapPolygonImpl;

import grafos.GrafoListaVecinos;
import localidades.Localidad;
import localidades.LogicaLocalidad;

import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import org.openstreetmap.gui.jmapviewer.JMapViewer.ZOOM_BUTTON_STYLE;

public class ConectarLocalidades extends JFrame {

	/**
	 * 
	 */
	private JPanel contentPane;
	private static JMapViewer _mapa;
	private DefaultListModel<String> DLM = new DefaultListModel<String>();
	
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 * @param _grafo 
	 * @param _grafo 
	 */
	
	public ConectarLocalidades(JMapViewer mapa, GrafoListaVecinos _grafo) {
		setTitle("Conectar Localidades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 799, 610);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panelMapa = new JPanel();
		panelMapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelMapa.setBounds(377, 11, 396, 368);
		contentPane.add(panelMapa);
		_mapa = mapa;
		_mapa.setZoomButtonStyle(ZOOM_BUTTON_STYLE.VERTICAL);
		_mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		_mapa.setToolTipText("Selecciona un punto para conseguir la Latitud y Longitud");
		_mapa.setZoom(4);
		_mapa.setBounds(0, 0, 396, 368);
		_mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 5);
		panelMapa.setLayout(null);		
		panelMapa.add(_mapa);
		
		
		JPanel panelLocalidades = new JPanel();
		panelLocalidades.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelLocalidades.setBounds(10, 11, 357, 528);
		contentPane.add(panelLocalidades);
		panelLocalidades.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Localidades creadas");
		lblNewLabel.setBounds(10, 11, 211, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLocalidades.add(lblNewLabel);
		
		JList<String> listaLocalidadesConectar = new JList<String>();
		listaLocalidadesConectar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listaLocalidadesConectar.setBounds(10, 44, 337, 430);
		panelLocalidades.add(listaLocalidadesConectar);
		listaLocalidadesConectar.setModel(LogicaLocalidad.crearModel(DLM));
		
		JButton btnInfo = new JButton("Ver Info");
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, LogicaLocalidad.listarLocalidades.get(listaLocalidadesConectar.getSelectedIndex()), "Info Localidad",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnInfo.setBounds(258, 494, 89, 23);
		panelLocalidades.add(btnInfo);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(559, 512, 214, 48);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int[] seleccionado = listaLocalidadesConectar.getSelectedIndices();
				for(int i=0; i < seleccionado.length; i++) {
					if(i != seleccionado.length-1) {
						LogicaLocalidad.conectarLocalidadesGrafo(seleccionado[i], seleccionado[i+1], _grafo);
						JOptionPane.showMessageDialog(null, LogicaLocalidad.listarLocalidades.get(seleccionado[i]).getNombre() +" conectada con: " + LogicaLocalidad.listarLocalidades.get(seleccionado[i+1]).getNombre(), "Conexión",JOptionPane.INFORMATION_MESSAGE);
					}
					for(int j = 0; j < seleccionado.length; j++) {
						if(j != i) {
							LogicaLocalidad.listarLocalidades.get(seleccionado[i]).agregarVecino(LogicaLocalidad.listarLocalidades.get(seleccionado[j]).getNombre());
						}
							
					}
				}
			}
		});

		btnConectar.setBounds(10, 11, 89, 23);
		panel.add(btnConectar);
		
		JButton btnAtras = new JButton("Atras");
		btnAtras.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				MainForm ventana = new MainForm(_mapa, _grafo);
				ventana.setVisible(true);
			}
		});
		btnAtras.setBounds(108, 11, 89, 23);
		panel.add(btnAtras);
		
	}
}


