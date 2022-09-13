package jframe;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.time.LocalDate;
import java.util.LinkedList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import clases.Avion;
import clases.Barco;
import clases.Persona;
import clases.Vehiculo;
import validaciones.Mensajes;
import validaciones.ValidarInputs;
import validaciones.ValidarTipos;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

public class Projecto {

	private JFrame frmPersonasCrud;
	private JTextField txtId;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtResidencia;
	private JRadioButton rdbtnNoHijos;

	private LinkedList <Persona> personas = new LinkedList <Persona>();
	private LinkedList <Avion> aviones = new LinkedList <Avion>();
	private LinkedList <Barco> barcos = new LinkedList <Barco>();
	private JList listPersonas;
	private JButton btnFiltrar;
	private JTextField txtIdVehiculo;
	private JTextField txtNombreVehiculo;
	private JTextField txtColorVehiculo;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField txtCantPasejManga;
	private JTextField txtLongitudEslora;
	private JRadioButton rdbBarco;
	private JRadioButton rdbAvion;
	private JComboBox cmbPersonas;
	private JButton btnLimpiarCampos;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 
		 Persona p1 = new Persona(1, "Joaquin", "Genova", "Montevideo", 0, LocalDate.now(), new LinkedList<Vehiculo>());
		 Persona p2 = new Persona(2, "Santiago", "Soberal", "Tacuarembo", 16, LocalDate.now(),new LinkedList<Vehiculo>());
		 Barco b1 = new Barco(1, "El velero", "Rosado", 10.5, 20.5);
		 Barco b2 = new Barco(2, "El Champi", "Marron", 29.5, 10.5);
		 Avion a1 = new Avion(1, "El Aguila", "Blanco", 100.5, 60);
		 Avion a2 = new Avion(1, "El Condor", "Negro", 120.5, 80);
		 
		 b1.setDuenio(p1);
		 b2.setDuenio(p2);
		 a1.setDuenio(p1);
		 a2.setDuenio(p2);
		 p1.getVehiculos().add(b1);
		 p1.getVehiculos().add(a1);
		 p2.getVehiculos().add(a2);
		 p2.getVehiculos().add(b2);
		 
		 
		 //Imprimir lista de Persona 1 al derecho
		 for (Vehiculo v : p1.getVehiculos()) {
			if (v instanceof Barco) {
				System.out.println("Es un barco: " + v);
			}else {
				System.out.println("Es un avion: " + v); 
			}
		}
		//Imprimimos la lista de Persona 2 al reves
		 for (int i=p2.getVehiculos().size() - 1; i >= 0; i--) {
				if (p2.getVehiculos().get(i) instanceof Barco) {
					System.out.println("Es un barco: " + p2.getVehiculos().get(i));
				}else {
					System.out.println("Es un avion: " + p2.getVehiculos().get(i)); 
				}
		}
		 
		//Dar vuelta lista Persona 1
		 
		
		 
		 
		 EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Projecto window = new Projecto();
					window.frmPersonasCrud.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Projecto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPersonasCrud = new JFrame();
		frmPersonasCrud.setResizable(false);
		frmPersonasCrud.setTitle("Personas CRUD");
		frmPersonasCrud.setBounds(100, 100, 466, 466);
		frmPersonasCrud.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPersonasCrud.getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 471, 441);
		frmPersonasCrud.getContentPane().add(tabbedPane);

		JPanel panelAlta = new JPanel();
		tabbedPane.addTab("Alta", null, panelAlta, null);
		panelAlta.setLayout(null);

		JLabel lblPersona = new JLabel("ID:");
		lblPersona.setBounds(12, 154, 81, 17);
		panelAlta.add(lblPersona);

		txtId = new JTextField();
		txtId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloNumeros(e);
			}
		});
		txtId.setBounds(101, 154, 353, 21);
		panelAlta.add(txtId);
		txtId.setColumns(10);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(12, 185, 81, 17);
		panelAlta.add(lblNombre);

		txtNombre = new JTextField();
		txtNombre.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloLetras(e);
			}
		});
		txtNombre.setColumns(10);
		txtNombre.setBounds(101, 185, 353, 21);
		panelAlta.add(txtNombre);

		JLabel lblApellido = new JLabel("Apellido:");
		lblApellido.setBounds(12, 216, 81, 17);
		panelAlta.add(lblApellido);

		txtApellido = new JTextField();
		txtApellido.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloLetras(e);
			}
		});
		txtApellido.setColumns(10);
		txtApellido.setBounds(101, 216, 353, 21);
		panelAlta.add(txtApellido);

		JLabel lblResidencia = new JLabel("Residencia:");
		lblResidencia.setBounds(12, 247, 81, 17);
		panelAlta.add(lblResidencia);

		txtResidencia = new JTextField();
		txtResidencia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarNumerosYLetras(e);
			}
		});
		txtResidencia.setColumns(10);
		txtResidencia.setBounds(101, 247, 353, 21);
		panelAlta.add(txtResidencia);

		JLabel lblHijos = new JLabel("Hijos:");
		lblHijos.setBounds(12, 276, 81, 17);
		panelAlta.add(lblHijos);

		JSpinner spinHijos = new JSpinner();
		spinHijos.setBounds(101, 274, 189, 22);
		spinHijos.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
		panelAlta.add(spinHijos);

		JTextField txtNacimiento = new JTextField();
		txtNacimiento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarFechas(e);
			}
		});
		txtNacimiento.setToolTipText("Formato adecuado: yyyy-dd-mm");
		txtNacimiento.setColumns(10);
		txtNacimiento.setBounds(101, 305, 353, 21);
		panelAlta.add(txtNacimiento);

		JLabel lblNacimiento = new JLabel("Nacimiento:");
		lblNacimiento.setBounds(12, 305, 81, 17);
		panelAlta.add(lblNacimiento);

		JButton btnIngresarPersona = new JButton("Ingresar");
		btnIngresarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!ValidarTipos.ValidarInt("ID", txtId.getText())) {
					return;
				}

				if (!ValidarTipos.ValidarString("nombre", txtNombre.getText())) {
					return;
				}

				if (!ValidarTipos.ValidarString("apellido", txtApellido.getText())) {
					return;
				}

				if (!ValidarTipos.ValidarString("residencia", txtResidencia.getText())) {
					return;
				}

				if (!ValidarTipos.ValidarFecha("fecha", txtNacimiento.getText())) {
					return;
				}
				
				
				/*for (int i = 0; i < personas.length; i++) {
					if (personas[i] != null) {
						if (personas[i].getIdPersona() == Integer.parseInt(txtId.getText())) {
							Mensajes.MostrarError("ID repetido, ingrese otro");
							return;
						}
					}
				}*/

				Persona p = new Persona();
				p.setIdPersona(Integer.parseInt(txtId.getText()));
				p.setNombre(txtNombre.getText());
				p.setApellidos(txtApellido.getText());
				p.setDptoResidencia(txtResidencia.getText());
				if(rdbtnNoHijos.isSelected()) {
					p.setCantHijos(0);
					
				}else {
					p.setCantHijos(Integer.parseInt(String.valueOf(spinHijos.getValue())));
				}
				
				p.setFechaNacimiento(LocalDate.parse(txtNacimiento.getText()));
				
				if(personas.contains(p)) {
					Mensajes.MostrarError("ID repetido, ingrese otro");
					return;
				}
				personas.add(p);
				
				Mensajes.MostrarExito("Persona dada de alta con exito");
				actualizarListas();

			}
		});
		btnIngresarPersona.setBounds(12, 338, 442, 27);
		panelAlta.add(btnIngresarPersona);

		btnLimpiarCampos = new JButton("Limpiar campos");
		btnLimpiarCampos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtId.setText("");
				txtNombre.setText("");
				txtApellido.setText("");
				txtResidencia.setText("");
				txtNacimiento.setText("");
				spinHijos.setValue(1);
				rdbtnNoHijos.setSelected(false);
				spinHijos.setEnabled(true);
			}
		});
		btnLimpiarCampos.setBounds(12, 377, 442, 27);
		panelAlta.add(btnLimpiarCampos);

		rdbtnNoHijos = new JRadioButton("No Hijos");
		rdbtnNoHijos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				spinHijos.setEnabled(!rdbtnNoHijos.isSelected());
				spinHijos.setValue(1);
			}
		});
		rdbtnNoHijos.setBounds(298, 273, 156, 22);
		panelAlta.add(rdbtnNoHijos);

		JLabel lblImagen = new JLabel("  Alta de Personas - UTEC");
		lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
		lblImagen.setIcon(new ImageIcon(Projecto.class.getResource("/jframe/grupo.png")));
		lblImagen.setBounds(12, 12, 442, 130);
		panelAlta.add(lblImagen);

		panelAlta.add(rdbtnNoHijos);

		JPanel panelConsultas = new JPanel();
		tabbedPane.addTab("Consultas", null, panelConsultas, null);
		panelConsultas.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 77, 440, 323);
		panelConsultas.add(scrollPane);

		listPersonas = new JList();
		scrollPane.setViewportView(listPersonas);
		listPersonas.setBorder(new LineBorder(Color.BLACK, 1, true));

		JLabel lblNombre_1 = new JLabel("Id:");
		lblNombre_1.setBounds(12, 12, 54, 17);
		panelConsultas.add(lblNombre_1);

		JTextField txtIdConsultas = new JTextField();
		txtIdConsultas.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloNumeros(e);
			}
		});
		txtIdConsultas.setColumns(10);
		txtIdConsultas.setBounds(68, 12, 384, 21);
		panelConsultas.add(txtIdConsultas);

		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean encontre = false;
				
				if (!ValidarTipos.ValidarInt("ID", txtIdConsultas.getText())) {
					return;
				}
				
				DefaultListModel m = new DefaultListModel();
				
				for (Persona per : personas) {
					if(per.getIdPersona() == Integer.parseInt(txtIdConsultas.getText())) {
						m.addElement(per.listar());
						encontre = true;
						break;
					}
				}
				
				if(!encontre) {
					Mensajes.MostrarError("No se encontro Personas en la lista.");
					return;
				}
				
				listPersonas.setModel(m);
			}
		});
		btnFiltrar.setBounds(12, 41, 440, 27);
		panelConsultas.add(btnFiltrar);
		
		JPanel pVehiculos = new JPanel();
		tabbedPane.addTab("Vehiculos", null, pVehiculos, null);
		pVehiculos.setLayout(null);
		
		txtIdVehiculo = new JTextField();
		txtIdVehiculo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloNumeros(e);
			}
		});
		txtIdVehiculo.setColumns(10);
		txtIdVehiculo.setBounds(148, 30, 306, 21);
		pVehiculos.add(txtIdVehiculo);
		
		JLabel lblPersona_1 = new JLabel("ID:");
		lblPersona_1.setBounds(12, 30, 81, 17);
		pVehiculos.add(lblPersona_1);
		
		txtNombreVehiculo = new JTextField();
		txtNombreVehiculo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloLetras(e);
			}
		});
		txtNombreVehiculo.setColumns(10);
		txtNombreVehiculo.setBounds(148, 59, 306, 21);
		pVehiculos.add(txtNombreVehiculo);
		
		JLabel lblPersona_1_1 = new JLabel("Nombre");
		lblPersona_1_1.setBounds(12, 59, 81, 17);
		pVehiculos.add(lblPersona_1_1);
		
		txtColorVehiculo = new JTextField();
		txtColorVehiculo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloLetras(e);
			}
		});
		txtColorVehiculo.setColumns(10);
		txtColorVehiculo.setBounds(148, 85, 306, 21);
		pVehiculos.add(txtColorVehiculo);
		
		JLabel lblPersona_1_2 = new JLabel("Color");
		lblPersona_1_2.setBounds(12, 85, 81, 17);
		pVehiculos.add(lblPersona_1_2);
		
		JLabel lblPersona_1_2_1 = new JLabel("Dueño");
		lblPersona_1_2_1.setBounds(12, 114, 81, 17);
		pVehiculos.add(lblPersona_1_2_1);
		
		cmbPersonas = new JComboBox();
		cmbPersonas.setBounds(148, 109, 306, 26);
		pVehiculos.add(cmbPersonas);
		
		JLabel lblLongitudEslora = new JLabel("Longitud / Eslora");
		lblLongitudEslora.setBounds(12, 177, 135, 17);
		pVehiculos.add(lblLongitudEslora);
		
		JLabel lblCantPasej = new JLabel("Cant. Pasej / Manga");
		lblCantPasej.setBounds(12, 204, 135, 17);
		pVehiculos.add(lblCantPasej);
		
		rdbBarco = new JRadioButton("Barco");
		rdbBarco.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLongitudEslora.setText("");
				txtCantPasejManga.setText("");
			}
		});
		rdbBarco.setSelected(true);
		buttonGroup.add(rdbBarco);
		rdbBarco.setBounds(319, 143, 135, 25);
		pVehiculos.add(rdbBarco);
		
		rdbAvion = new JRadioButton("Avion");
		rdbAvion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtLongitudEslora.setText("");
				txtCantPasejManga.setText("");
			}
		});
		buttonGroup.add(rdbAvion);
		rdbAvion.setBounds(148, 144, 135, 25);
		pVehiculos.add(rdbAvion);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(cmbPersonas.getItemCount() <= 1 ) {
					Mensajes.MostrarError("Debe ingresar una persona primero");
					return;
				}
				
				if(cmbPersonas.getSelectedItem().toString().equals("Seleccione una persona:")) {
					Mensajes.MostrarError("Debe seleccionar una persona primero");
					return;
				}
				
				if(!ValidarTipos.ValidarInt("ID", txtIdVehiculo.getText())) {
					return;
				}
				
				if(!ValidarTipos.ValidarString("Nombre", txtNombreVehiculo.getText())) {
					return;
				}
				
				if(!ValidarTipos.ValidarString("Nombre", txtColorVehiculo.getText())) {
					return;
				}
				
				Persona duenio = personas.get(cmbPersonas.getSelectedIndex()-1);
				if(rdbBarco.isSelected()) {				
					if(!ValidarTipos.ValidarDouble("Eslora", txtLongitudEslora.getText())) {
						return;
					}
					
					if(!ValidarTipos.ValidarDouble("Manga", txtCantPasejManga.getText())) {
						return;
					}
					
					Barco b = new Barco();
					b.setIdVehiculo(Integer.parseInt(txtIdVehiculo.getText()));
					b.setNombre(txtNombreVehiculo.getText());
					b.setColor(txtColorVehiculo.getText());
					b.setEslora(Double.parseDouble(txtLongitudEslora.getText()));
					b.setManga(Double.parseDouble(txtCantPasejManga.getText()));
					b.setDuenio(duenio);
					duenio.getVehiculos().add(b);
					
					Mensajes.MostrarExito("Barco del dueño "+duenio.getNombre()+" creado");
					actualizarListas();

				}else {
					
					if(!ValidarTipos.ValidarDouble("Longitud", txtLongitudEslora.getText())) {
						return;
					}
					
					if(!ValidarTipos.ValidarInt("Cantidad de Pasajeros", txtCantPasejManga.getText())) {
						return;
					}
					
					Avion a = new Avion();
					a.setIdVehiculo(Integer.parseInt(txtIdVehiculo.getText()));
					a.setNombre(txtNombreVehiculo.getText());
					a.setColor(txtColorVehiculo.getText());
					a.setLongitud(Double.parseDouble(txtLongitudEslora.getText()));
					a.setCantPasajeros(Integer.parseInt(txtCantPasejManga.getText()));
					a.setDuenio(duenio);
					duenio.getVehiculos().add(a);

					Mensajes.MostrarExito("Avion del dueño "+duenio.getNombre()+" creado");
					actualizarListas();
				}
			}
		});
		btnAgregar.setBounds(12, 242, 442, 27);
		pVehiculos.add(btnAgregar);
		
		txtCantPasejManga = new JTextField();
		txtCantPasejManga.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(rdbBarco.isSelected()) {
					ValidarInputs.ValidarSoloNumerosYComa(e);
				}else {
					ValidarInputs.ValidarSoloNumeros(e);
				}
			}
		});
		txtCantPasejManga.setColumns(10);
		txtCantPasejManga.setBounds(148, 203, 306, 21);
		pVehiculos.add(txtCantPasejManga);
		
		txtLongitudEslora = new JTextField();
		txtLongitudEslora.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ValidarInputs.ValidarSoloNumerosYComa(e);
			}
		});
		txtLongitudEslora.setColumns(10);
		txtLongitudEslora.setBounds(148, 177, 306, 21);
		pVehiculos.add(txtLongitudEslora);

		actualizarListas();
	}
	
	private void actualizarListas() {
		DefaultListModel m = new DefaultListModel();
		cmbPersonas.removeAllItems();
		cmbPersonas.addItem("Seleccione una persona:");
		
		for (Persona personas : personas) {
			m.addElement(personas.listar());
			cmbPersonas.addItem(personas.listar());
		}
		
		listPersonas.setModel(m);
		btnLimpiarCampos.doClick();
	}
}
