package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.ControladorClientes;
import controladores.ControladorMascotas;
import manejadores.ManejadorClientes;
import objetos.Cliente;

public class FrameNuevaMascota implements ActionListener {
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelPatente;
	private JLabel labelNombre;
	private JLabel labelEdad;
	private JLabel labelTipo;
	private JLabel labelCliente;
	
	private JComboBox<String> comboTipo;
	private JComboBox<String> comboClientes;

	/** Atributos de TexField */
	private JTextField textNombre;
	private JTextField textPatente;
	private JTextField textEdad;

	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;
	
	/** Lista de clientes del sistema */
	private List<Cliente> clientes;
	
	public FrameNuevaMascota(JFrame framePadre) {

		this.labelPatente = new JLabel("Nro Patente:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelEdad = new JLabel("Edad:");
		this.labelTipo = new JLabel("Tipo:");
		this.labelCliente = new JLabel("Cliente:");

		this.textNombre = new JTextField(15);
		this.textPatente = new JTextField(15);
		this.textEdad = new JTextField(15);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {
		
		JFrame frame = new JFrame("Nueva Mascota");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevaMascotaPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevaMascotaPanel.add(this.labelPatente, constraints);

		constraints.gridx = 1;
		nuevaMascotaPanel.add(this.textPatente, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevaMascotaPanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		nuevaMascotaPanel.add(this.textNombre, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevaMascotaPanel.add(this.labelTipo, constraints);

		constraints.gridx = 1;
		this.comboTipo = this.completarComboTipo();
		nuevaMascotaPanel.add(this.comboTipo, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevaMascotaPanel.add(this.labelEdad, constraints);
		
		constraints.gridx = 1;
		nuevaMascotaPanel.add(this.textEdad, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 4;
		nuevaMascotaPanel.add(this.labelCliente, constraints);
		
		constraints.gridx = 1;
		this.comboClientes = this.completarComboClientes();
		nuevaMascotaPanel.add(this.comboClientes, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevaMascotaPanel.add(buttonIngresar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 6;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevaMascotaPanel.add(buttonCancelar, constraints);

		nuevaMascotaPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos de la mascota"));

		frame.add(nuevaMascotaPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;
		
	}

	private JComboBox<String> completarComboClientes() {
		
		this.clientes = ManejadorClientes.obtenerTodosClientes();
		
		JComboBox<String> combo = new JComboBox<>();
		
		for(Cliente c : this.clientes){
			combo.addItem(c.getCi());
		}
		
		return combo;
		
		
	}

	private JComboBox<String> completarComboTipo() {
		
		String[] valores = {"Perro", "Gato", "Conejo", "Pajaro"};
		return new JComboBox<>(valores);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/* Debo primero conocer que botón fue clickeado */

		if (e.getSource() == this.buttonCancelar) {
			this.accionCancelar();
		} else {
			this.accionIngesar();
		}
		
	}
	
	private void accionIngesar() {

		// Si es ingresar se validan datos!

		String fieldNombre = this.textNombre.getText();
		String fieldEdad = this.textEdad.getText();
		String fieldPatente = this.textPatente.getText();
		String fieldTipo = (String) this.comboTipo.getSelectedItem();
		String ciCliente = (String) this.comboClientes.getSelectedItem();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldPatente.equals("") || fieldEdad.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		int edad = 0;
		
		// Convertimos a numero la edad
		try{
			edad = Integer.valueOf(fieldEdad);
		} catch (Exception e){
			JOptionPane.showMessageDialog(frame, "La edad debe ser un número entero.", "Datos incorrectos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Valiamos ahora, que no exista un cliente con dicha CI
		boolean existe = ControladorMascotas.existeMascota(fieldPatente);

		if (existe) {
			JOptionPane.showMessageDialog(frame, "La mascota con dicha Patente ya se ecuentra registrada.",
					"Cliente Existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}
		
		boolean almacenado = ControladorMascotas.ingresarNuevaMascota(fieldPatente, fieldTipo, edad, fieldNombre, ciCliente);

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "La mascto ha sido registrada con éxito.",
					"Mascota Registrada!", JOptionPane.INFORMATION_MESSAGE);
			
			// cerramos la ventanta
			this.frame.dispose();

			
		}
		else{
			JOptionPane.showMessageDialog(frame, "Hubo un error al almacenar. Intente nuevamente más tarde",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}
}
