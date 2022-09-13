package viewPersona;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.DaoPersona;
import modal.Persona;

import java.awt.Component;
import java.awt.Rectangle;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.awt.event.ActionEvent;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JTabbedPane;

public class GUI extends JFrame {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;


	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 564, 629);
		
		JPanel panelUsuario = new JPanel();
		panelUsuario.setLayout(null);
		getContentPane().add(panelUsuario, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("PANEL PERSONA");
		lblNewLabel.setBounds(24, 5, 85, 14);
		panelUsuario.add(lblNewLabel);
		
		JLabel documento = new JLabel("Documento:");
		documento.setBounds(114, 5, 61, 14);
		panelUsuario.add(documento);
		
		JLabel nombre1 = new JLabel("Primer Nombre:");
		nombre1.setBounds(180, 5, 78, 14);
		panelUsuario.add(nombre1);
		
		JLabel nombre2 = new JLabel("Segundo Nombre:");
		nombre2.setBounds(263, 5, 88, 14);
		panelUsuario.add(nombre2);
		
		JLabel apellido1 = new JLabel("Primer Apellido");
		apellido1.setBounds(356, 5, 75, 14);
		panelUsuario.add(apellido1);
		
		JLabel apellido2 = new JLabel("Segundo Apellido:");
		apellido2.setBounds(436, 5, 89, 14);
		panelUsuario.add(apellido2);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(25, 24, 96, 20);
		panelUsuario.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(126, 24, 96, 20);
		panelUsuario.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(227, 24, 96, 20);
		panelUsuario.add(textField_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(328, 24, 96, 20);
		panelUsuario.add(textField_3);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(429, 24, 96, 20);
		panelUsuario.add(textField_4);
		
		JButton btnModificarPersona = new JButton("Modificar");
		btnModificarPersona.setBounds(163, 457, 77, 23);
		panelUsuario.add(btnModificarPersona);
		
		JButton btnBuscarPersona = new JButton("Buscar");
		btnBuscarPersona.setBounds(180, 359, 65, 23);
		panelUsuario.add(btnBuscarPersona);
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(279, 49, 73, 23);
		panelUsuario.add(btnEliminar);
		
		JButton tabla = new JButton("TABLA");
		tabla.setBounds(357, 49, 65, 23);
		panelUsuario.add(tabla);
		
	}
}
