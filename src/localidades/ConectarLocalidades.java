package localidades;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;

public class ConectarLocalidades extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static ArrayList<Localidad> listarLocalidades = new ArrayList<>();
	
	 

	/**
	 * Create the frame.
	 */
	public ConectarLocalidades() {
		setTitle("Conectar Localidades");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
