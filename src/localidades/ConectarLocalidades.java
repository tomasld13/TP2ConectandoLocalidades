package localidades;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.JMapViewer;

import interfaces.MainForm;

import java.util.ArrayList;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.border.EtchedBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ConectarLocalidades extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JMapViewer _mapa;
	DefaultListModel DLM = new DefaultListModel();

	/**
	 * Launch the application.
	 */
	public static ArrayList<Localidad> listarLocalidades = new ArrayList<>();
	
	 

	/**
	 * Create the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ConectarLocalidades() {
		
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
		_mapa = new JMapViewer();
		_mapa.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		_mapa.setToolTipText("Selecciona un punto para conseguir la Latitud y Longitud");
		_mapa.setZoom(4);
		_mapa.setBounds(0, 0, 396, 368);
		_mapa.setDisplayPosition(new Coordinate(-34.521, -58.7008), 15);
		panelMapa.setLayout(null);
		
		panelMapa.add(_mapa);
		
		
		JPanel panelLocalidades = new JPanel();
		panelLocalidades.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panelLocalidades.setBounds(10, 11, 357, 528);
		contentPane.add(panelLocalidades);
		panelLocalidades.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Localidades creadas");
		lblNewLabel.setBounds(10, 11, 155, 22);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panelLocalidades.add(lblNewLabel);
		
		JList listaLocalidades = new JList();
		listaLocalidades.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		listaLocalidades.setBounds(10, 44, 337, 430);
		panelLocalidades.add(listaLocalidades);
		
		JPanel panel = new JPanel();
		panel.setBounds(559, 512, 214, 48);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnConectar = new JButton("Conectar");
		btnConectar.setBounds(10, 11, 89, 23);
		panel.add(btnConectar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainForm ventana = new MainForm();
				ventana.setVisible(true);
				setVisible(false);
			}
		});
		btnCancelar.setBounds(108, 11, 89, 23);
		panel.add(btnCancelar);
		
			int tamano = listarLocalidades.size();
		final String [] vector = new String [tamano];
		for (int conta=0;conta<tamano;conta++) {
			vector[conta] = listarLocalidades.get(conta).getNombre() + " - " + listarLocalidades.get(conta).getProvincia() + " - " + 
							listarLocalidades.get(conta).getCoordenadas().getLat() + " - " + listarLocalidades.get(conta).getCoordenadas().getLon();
			DLM.addElement(vector[conta]);
		}

		
	}
}
