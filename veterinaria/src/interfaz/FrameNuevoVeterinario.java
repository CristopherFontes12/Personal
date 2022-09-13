package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.ControladorClientes;
import controladores.ControladorVeterinarios;

public class FrameNuevoVeterinario implements ActionListener{
	
	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelNombre;
	private JLabel labelCodigo;
	private JLabel labelCi;
	private JLabel labelEspecialidad;

	/** Atributos de TexField */
	private JTextField textNombre;
	private JTextField textCodigo;
	private JTextField textCi;
	
	/** Combo de especialidad */
	private JComboBox<String> comboEspecialidad;

	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;

	public FrameNuevoVeterinario(JFrame framePadre) {

		this.labelCodigo = new JLabel("Codigo:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelCi = new JLabel("CI:");
		this.labelEspecialidad = new JLabel("Especialidad:");

		this.textCi = new JTextField(15);
		this.textNombre = new JTextField(15);
		this.textCodigo = new JTextField(15);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nuevo Veterinario");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevoVeterinarioPanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevoVeterinarioPanel.add(this.labelCodigo, constraints);

		constraints.gridx = 1;
		nuevoVeterinarioPanel.add(this.textCodigo, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevoVeterinarioPanel.add(this.labelEspecialidad, constraints);

		constraints.gridx = 1;
		this.comboEspecialidad = this.cargarComboEspecialidad();
		nuevoVeterinarioPanel.add(this.comboEspecialidad, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevoVeterinarioPanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		nuevoVeterinarioPanel.add(this.textNombre, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevoVeterinarioPanel.add(this.labelCi, constraints);

		constraints.gridx = 1;
		nuevoVeterinarioPanel.add(this.textCi, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoVeterinarioPanel.add(buttonIngresar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoVeterinarioPanel.add(buttonCancelar, constraints);

		nuevoVeterinarioPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del veterinario"));

		frame.add(nuevoVeterinarioPanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}

	private JComboBox<String> cargarComboEspecialidad() {
		
		String[] valores = {"Cirugía", "Consulta", "Alimentación"};
		return new JComboBox<>(valores);
	}

	/**
	 * Como implementos Action Listener, quiere decir que soy escuchado de
	 * eventos. Este método es quien se ejecutan cuando tocan un boton .
	 */
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
		String fieldCodigo = this.textCodigo.getText();
		String fieldCi = this.textCi.getText();
		String fieldEspecialidad = (String) this.comboEspecialidad.getSelectedItem();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldCodigo.equals("") || fieldCi.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Valiamos ahora, que no exista un cliente con dicha CI
		boolean existe = ControladorVeterinarios.existeVeterinario(fieldCodigo);

		if (existe) {
			JOptionPane.showMessageDialog(frame, "El veterinario con dicho Codigo ya se ecuentra registrado.",
					"Veterinario Existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Si estamos aquí,..quiere decir que no hay errores. Almacenamos el
		// veterinario y volvemos al menu
		boolean almacenado = ControladorVeterinarios.ingresarNuevaVeterinario(fieldCodigo, fieldEspecialidad, fieldNombre, fieldCi);

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El veterinario ha sido registrado con éxito.",
					"Veterinario Registrado!", JOptionPane.INFORMATION_MESSAGE);
			
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
