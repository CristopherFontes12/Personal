package vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;

public class Vista extends JFrame {

	private JPanel contentPane;
	public JTextField txtNumeroDos;
	public JTextField txtResultado;
	public JTextField txtNumeroUno;
	public JButton btnMultiplicar;


	/**
	 * Create the frame.
	 */
	public Vista() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 581, 375);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 561, 328);
		contentPane.add(panel);
		panel.setLayout(null);
		
		txtNumeroUno = new JTextField();
		txtNumeroUno.setBounds(61, 132, 113, 38);
		panel.add(txtNumeroUno);
		txtNumeroUno.setColumns(10);
		
		txtNumeroDos = new JTextField();
		txtNumeroDos.setColumns(10);
		txtNumeroDos.setBounds(234, 132, 113, 38);
		panel.add(txtNumeroDos);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setBounds(205, 127, 81, 49);
		panel.add(lblNewLabel);
		
		JButton btnMultiplicar = new JButton("Multiplicar");
		btnMultiplicar.setBounds(405, 127, 128, 49);
		panel.add(btnMultiplicar);
		
		txtResultado = new JTextField();
		txtResultado.setBounds(137, 204, 134, 38);
		panel.add(txtResultado);
		txtResultado.setColumns(10);
	}
}
