package modal;
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

public class GUI extends JFrame {

	private JPanel panelUsuario;
	private JTextField textDocumento;
	private JTextField textPrimerNombre;
	private JTextField textSegundoNombre;
	private JTextField textPrimerApellido;
	private JTextField textSegundoApellido;


	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 407, 587);
		panelUsuario = new JPanel();
		panelUsuario.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelUsuario.setBounds(getBounds());
		panelUsuario.setSize(300,900);
		setContentPane(panelUsuario);
		panelUsuario.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("PANEL PERSONA");
		lblNewLabel.setBounds(141, 41, 234, 31);
		panelUsuario.add(lblNewLabel);
		
		JLabel documento = new JLabel("Documento:");
		documento.setBounds(40, 117, 69, 17);
		panelUsuario.add(documento);
		
		JLabel nombre1 = new JLabel("Primer Nombre:");
		nombre1.setBounds(40, 145, 92, 17);
		panelUsuario.add(nombre1);
		
		JLabel nombre2 = new JLabel("Segundo Nombre:");
		nombre2.setBounds(40, 173, 92, 17);
		panelUsuario.add(nombre2);
		
		JLabel apellido1 = new JLabel("Primer Apellido");
		apellido1.setBounds(40, 201, 92, 17);
		panelUsuario.add(apellido1);
		
		JLabel apellido2 = new JLabel("Segundo Apellido:");
		apellido2.setBounds(40, 228, 92, 17);
		panelUsuario.add(apellido2);
		
		textDocumento = new JTextField();
		textDocumento.setColumns(10);
		textDocumento.setBounds(141, 115, 196, 21);
		panelUsuario.add(textDocumento);
		
		textPrimerNombre = new JTextField();
		textPrimerNombre.setColumns(10);
		textPrimerNombre.setBounds(142, 143, 196, 21);
		panelUsuario.add(textPrimerNombre);
		
		textSegundoNombre = new JTextField();
		textSegundoNombre.setColumns(10);
		textSegundoNombre.setBounds(141, 171, 196, 21);
		panelUsuario.add(textSegundoNombre);
		
		textPrimerApellido = new JTextField();
		textPrimerApellido.setColumns(10);
		textPrimerApellido.setBounds(141, 201, 196, 21);
		panelUsuario.add(textPrimerApellido);
		
		textSegundoApellido = new JTextField();
		textSegundoApellido.setColumns(10);
		textSegundoApellido.setBounds(142, 226, 196, 21);
		panelUsuario.add(textSegundoApellido);
		
		
		// *************************************** LOGICA IMPLEMENTADA AL BOTO MODIFICAR *************************************** 
		
		JButton btnModificarPersona = new JButton("Modificar");
		btnModificarPersona.setBounds(31, 384, 306, 27);
		panelUsuario.add(btnModificarPersona);
		btnModificarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Persona p = DaoPersona.findPersona(textPrimerApellido.getText(),textPrimerNombre.getText());
					if (p == null) {
						JOptionPane.showMessageDialog(null, "No se pudo localizar la persona", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}					
					
					//System.out.println("Estoy aca");
					
					Persona camposNuevos = new Persona();
					
					camposNuevos.setId(p.getId());
					camposNuevos.setDocumento(textDocumento.getText());
					camposNuevos.setNombre1(textPrimerNombre.getText());
					camposNuevos.setNombre2(textSegundoNombre.getText());
					camposNuevos.setApellido1(textPrimerApellido.getText());
					camposNuevos.setApellido2(textSegundoApellido.getText());
					
					DaoPersona.edit(camposNuevos);					
					JOptionPane.showMessageDialog(null, "Se modifico correctamente a la persona.", "Modificado", JOptionPane.INFORMATION_MESSAGE);
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				
				
			}
		});
		
		
		
		
		// *************************************** LOGICA IMPLEMENTADA AL BOTO BUSCAR *************************************** 

		
		
		JButton btnBuscarPersona = new JButton("Buscar");
		btnBuscarPersona.setBounds(31, 422, 306, 27);
		panelUsuario.add(btnBuscarPersona);
		btnBuscarPersona.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
			try {
				Persona p = DaoPersona.findPersona(textPrimerApellido.getText(),textPrimerNombre.getText());
				if (p == null) {
					JOptionPane.showMessageDialog(null, "No se pudo localizar la persona", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				textDocumento.setText(p.getDocumento());
				textPrimerNombre.setText(p.getNombre1());
				textSegundoNombre.setText(p.getNombre2());
				textPrimerApellido.setText(p.getApellido1());
				textSegundoApellido.setText(p.getNombre2());
				
				
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
				
			}
		});
		
		// *************************************** LOGICA IMPLEMENTADA AL BOTO ELIMINAR *************************************** 
		
		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(31, 460, 306, 27);
		panelUsuario.add(btnEliminar);		
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					Persona p = DaoPersona.findPersona(textPrimerApellido.getText(),textPrimerNombre.getText());
					if (p == null) {
						JOptionPane.showMessageDialog(null, "No se pudo localizar la persona", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}					

					DaoPersona.delete(Integer.parseInt(p.getId().toString()));					
					JOptionPane.showMessageDialog(null, "Se Elimino correctamente a la persona.", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
					
					
				} catch (Exception e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
				
				
				
				
				
				
			}
		});
		
		
		JButton tabla = new JButton("TABLA");
		tabla.setBounds(31, 498, 306, 27);
		panelUsuario.add(tabla);
		tabla.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				try {
					LinkedList<Persona> personas = DaoPersona.findAll();
					DefaultTableModel modelo = new DefaultTableModel();
					
					modelo.addColumn("ID");
					modelo.addColumn("Documento");				
					modelo.addColumn("Nombre");
					modelo.addColumn("Segundo Nombre");
					modelo.addColumn("Apellido");
					modelo.addColumn("Segundo Apellido");
				 
					for (Persona persona: personas) {
						Object[] datos = {
							persona.getId(),
							persona.getDocumento(),
							persona.getNombre1(),
							persona.getNombre2(),
							persona.getApellido1(),
							persona.getApellido2()
						};
						
						modelo.addRow(datos);
						
						
					}
					
					JTable tabla = new JTable(modelo);
					JOptionPane.showMessageDialog(null, new JScrollPane(tabla));
					
					
					
					
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
			}
		});
		
	}

}
