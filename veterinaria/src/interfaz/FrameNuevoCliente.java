package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controladores.ControladorClientes;
import controladores.ControladorConsultas;

public class FrameNuevoCliente implements ActionListener {

	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelNombre;
	private JLabel labelApellido;
	private JLabel labelCi;

	/** Atributos de TexField */
	private JTextField textNombre;
	private JTextField textApellido;
	private JTextField textCi;

	/** Atributos de Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;

	public FrameNuevoCliente(JFrame framePadre) {

		this.labelApellido = new JLabel("Apellido:");
		this.labelNombre = new JLabel("Nombre:");
		this.labelCi = new JLabel("CI:");

		this.textCi = new JTextField(15);
		this.textNombre = new JTextField(15);
		this.textApellido = new JTextField(15);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nuevo Cliente");
		frame.setSize(600, 400);
		frame.setResizable(false);
		frame.setLocationRelativeTo(framePadre);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevoClientePanel = new JPanel(new GridBagLayout());

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 10, 10, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevoClientePanel.add(this.labelNombre, constraints);

		constraints.gridx = 1;
		nuevoClientePanel.add(this.textNombre, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		nuevoClientePanel.add(this.labelApellido, constraints);

		constraints.gridx = 1;
		nuevoClientePanel.add(this.textApellido, constraints);

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevoClientePanel.add(this.labelCi, constraints);

		constraints.gridx = 1;
		nuevoClientePanel.add(this.textCi, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoClientePanel.add(buttonIngresar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevoClientePanel.add(buttonCancelar, constraints);

		nuevoClientePanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos del cliente"));

		frame.add(nuevoClientePanel);

		frame.pack();
		frame.setVisible(true);

		this.frame = frame;

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
		String fieldApellido = this.textApellido.getText();
		String fieldCi = this.textCi.getText();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (fieldNombre.equals("") || fieldApellido.equals("") || fieldCi.equals("")) {
			JOptionPane.showMessageDialog(frame, "Debe completar todos los datos solicitados.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Valiamos ahora, que no exista un cliente con dicha CI
		boolean existe = ControladorClientes.existeCliente(fieldCi);

		if (existe) {
			JOptionPane.showMessageDialog(frame, "El cliente con dicha CI ya se ecuentra registrado.",
					"Cliente Existente!", JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Si estamos aquí,..quiere decir que no hay errores. Almacenamos el
		// cliente y volvemos al menu
		boolean almacenado = ControladorClientes.ingresarNuevoCliente(fieldNombre, fieldApellido, fieldCi);

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "El cliente ha sido registrado con éxito.",
					"Cliente Registrado!", JOptionPane.INFORMATION_MESSAGE);
			
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
