package prueba;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;

	public GUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		JPanel panelAlta = new JPanel();
		tabbedPane.addTab("Alta", null, panelAlta, null);
		panelAlta.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(12, 27, 106, 17);
		panelAlta.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(123, 25, 340, 21);
		panelAlta.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(123, 56, 340, 21);
		panelAlta.add(textField_1);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(12, 58, 106, 17);
		panelAlta.add(lblNewLabel_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(123, 89, 340, 21);
		panelAlta.add(textField_2);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(12, 91, 106, 17);
		panelAlta.add(lblNewLabel_2);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(123, 122, 340, 21);
		panelAlta.add(textField_3);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(12, 124, 106, 17);
		panelAlta.add(lblNewLabel_3);
		
		JButton btnAlta = new JButton("Alta");
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAlta.setBounds(12, 153, 451, 27);
		panelAlta.add(btnAlta);
		
		JPanel panelBaja = new JPanel();
		tabbedPane.addTab("Baja", null, panelBaja, null);
		panelBaja.setLayout(null);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(123, 12, 340, 21);
		panelBaja.add(textField_4);
		
		JLabel lblNewLabel_3_1 = new JLabel("New label");
		lblNewLabel_3_1.setBounds(12, 14, 106, 17);
		panelBaja.add(lblNewLabel_3_1);
		
		JButton btnBaja = new JButton("Baja");
		btnBaja.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnBaja.setBounds(12, 43, 451, 27);
		panelBaja.add(btnBaja);
		
		JPanel panelModif = new JPanel();
		tabbedPane.addTab("Modificacion", null, panelModif, null);
		panelModif.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(12, 56, 106, 17);
		panelModif.add(lblNewLabel_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(123, 54, 340, 21);
		panelModif.add(textField_5);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(123, 85, 340, 21);
		panelModif.add(textField_6);
		
		JLabel lblNewLabel_1_1 = new JLabel("New label");
		lblNewLabel_1_1.setBounds(12, 87, 106, 17);
		panelModif.add(lblNewLabel_1_1);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(123, 118, 340, 21);
		panelModif.add(textField_7);
		
		JLabel lblNewLabel_2_1 = new JLabel("New label");
		lblNewLabel_2_1.setBounds(12, 120, 106, 17);
		panelModif.add(lblNewLabel_2_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(123, 151, 340, 21);
		panelModif.add(textField_8);
		
		JLabel lblNewLabel_3_2 = new JLabel("New label");
		lblNewLabel_3_2.setBounds(12, 153, 106, 17);
		panelModif.add(lblNewLabel_3_2);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnModificar.setBounds(12, 182, 451, 27);
		panelModif.add(btnModificar);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(12, 18, 451, 26);
		panelModif.add(comboBox);
		
		JPanel panelConsulta = new JPanel();
		tabbedPane.addTab("Consulta", null, panelConsulta, null);
		panelConsulta.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 195, 451, 224);
		panelConsulta.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnConsultarTodo = new JButton("Consultar todos");
		btnConsultarTodo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnConsultarTodo.setBounds(12, 156, 451, 27);
		panelConsulta.add(btnConsultarTodo);
		
		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnFiltrar.setBounds(12, 117, 451, 27);
		panelConsulta.add(btnFiltrar);
		
		JLabel lblNewLabel_3_2_1 = new JLabel("New label");
		lblNewLabel_3_2_1.setBounds(12, 87, 106, 17);
		panelConsulta.add(lblNewLabel_3_2_1);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(123, 85, 340, 21);
		panelConsulta.add(textField_9);
		
		JLabel lblNewLabel_3_2_2 = new JLabel("New label");
		lblNewLabel_3_2_2.setBounds(12, 56, 106, 17);
		panelConsulta.add(lblNewLabel_3_2_2);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(123, 54, 340, 21);
		panelConsulta.add(textField_10);
		
		JLabel lblNewLabel_3_2_3 = new JLabel("New label");
		lblNewLabel_3_2_3.setBounds(12, 25, 106, 17);
		panelConsulta.add(lblNewLabel_3_2_3);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(123, 23, 340, 21);
		panelConsulta.add(textField_11);
	}
}
