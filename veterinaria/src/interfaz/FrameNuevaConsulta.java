package interfaz;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controladores.ControladorConsultas;
import controladores.ControladorMascotas;
import controladores.ControladorVeterinarios;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import objetos.Mascota;
import objetos.Veterinario;

public class FrameNuevaConsulta implements DocumentListener, ActionListener {

	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelPatente;
	private JLabel labelCodigo;
	private JLabel labelFecha;

	/** Atributos de TexField */
	private JTextField textPatente;

	/** Tabla */
	private JTable tablaMascotas;

	/** Combo */
	private JComboBox<String> comboVeterinarios;

	/** Date Picker */
	private JDatePickerImpl datePicker;

	/** Botones */
	private JButton buttonIngresar;
	private JButton buttonCancelar;

	public FrameNuevaConsulta(JFrame framePadre) {

		this.labelPatente = new JLabel("Nro Patente:");
		this.labelCodigo = new JLabel("Veterinario:");
		this.labelFecha = new JLabel("Fecha:");
		this.textPatente = new JTextField(15);

		JButton buttonIngresar = new JButton("Ingresar");
		buttonIngresar.addActionListener(this);

		JButton buttonCancelar = new JButton("Cancelar");
		buttonCancelar.addActionListener(this);

		this.buttonIngresar = buttonIngresar;
		this.buttonCancelar = buttonCancelar;

		this.initalizeFrame(framePadre);

	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Nueva consulta");
		frame.setSize(450, 350);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel nuevaConsultaPanel = new JPanel(new GridBagLayout());
		nuevaConsultaPanel.setSize(500, 400);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(2, 2, 2, 2);

		constraints.gridx = 0;
		constraints.gridy = 0;
		nuevaConsultaPanel.add(this.labelPatente, constraints);

		constraints.gridx = 1;
		nuevaConsultaPanel.add(this.textPatente, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		this.tablaMascotas = this.cargarTablaMascotas();
		JScrollPane pane1 = new JScrollPane(this.tablaMascotas);
		Dimension dim1 = this.tablaMascotas.getPreferredSize();
		pane1.setPreferredSize(
				new Dimension(dim1.width, this.tablaMascotas.getRowHeight() * this.tablaMascotas.getRowCount() + 1));
		nuevaConsultaPanel.add(pane1, constraints);

		constraints.anchor = GridBagConstraints.WEST;

		constraints.gridx = 0;
		constraints.gridy = 2;
		nuevaConsultaPanel.add(this.labelCodigo, constraints);

		constraints.gridx = 1;
		this.comboVeterinarios = this.cargarComboVeterinarios();
		nuevaConsultaPanel.add(this.comboVeterinarios, constraints);
		constraints.anchor = GridBagConstraints.WEST;

		constraints.gridx = 0;
		constraints.gridy = 3;
		nuevaConsultaPanel.add(this.labelFecha, constraints);

		constraints.gridx = 1;
		this.datePicker = this.creareDatePicker();
		nuevaConsultaPanel.add(this.datePicker, constraints);

		constraints.gridx = 0;
		constraints.gridy = 4;
		constraints.gridwidth = 3;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevaConsultaPanel.add(buttonIngresar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 4;
		constraints.anchor = GridBagConstraints.CENTER;
		nuevaConsultaPanel.add(buttonCancelar, constraints);

		nuevaConsultaPanel.setBorder(
				BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Datos de la consulta"));

		frame.add(nuevaConsultaPanel);

		this.textPatente.getDocument().addDocumentListener(this);

		// frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}

	private JDatePickerImpl creareDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
	}

	private JComboBox<String> cargarComboVeterinarios() {

		JComboBox<String> combo = new JComboBox<>();

		List<Veterinario> veterinarios = ControladorVeterinarios.obtenerTodosVeterinarios();

		String[] nombreColumnas = { "Codigo", "Especialidad", "Nombre", "CI" };

		for (Veterinario v : veterinarios) {
			combo.addItem(v.getCodigo());
		}

		return combo;

	}

	private JTable cargarTablaMascotas() {

		ArrayList<Mascota> mascotas = ControladorMascotas.obtenerTodasMascotas();

		String[] nombreColumnas = { "Nro Patente", "Nombre", "Tipo", "CI Cliente" };

		/*
		 * El tamaño de la tabla es, 4 columnas (cantidad de datos a mostrar) y
		 * la cantidad de filas depende de la cantida de mascotas
		 */
		Object[][] datos = new Object[mascotas.size()][4];

		/* Cargamos la matriz con todos los datos */
		int fila = 0;

		for (Mascota m : mascotas) {

			datos[fila][0] = m.getNroPatente();
			datos[fila][1] = m.getNombre();
			datos[fila][2] = m.getTipo();
			datos[fila][3] = m.getCliente().getCi();
			fila++;
		}

		/*
		 * Este codigo indica que las celdas no son editables y que son todas
		 * del tipos String
		 */
		DefaultTableModel model = new DefaultTableModel(datos, nombreColumnas) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

			@Override
			public Class<?> getColumnClass(int columnIndex) {
				return String.class;
			}
		};

		JTable table = new JTable(model);
		table.setAutoscrolls(true);
		table.setCellSelectionEnabled(false);
		table.setSize(500, 250);
		table.setRowSelectionAllowed(true);

		this.tablaMascotas = table;

		return table;
	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.filtrar(e);

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.filtrar(e);
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.filtrar(e);
	}

	private void filtrar(DocumentEvent e) {

		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaMascotas.getModel());
		filtro.setRowFilter(RowFilter.regexFilter(this.textPatente.getText(), 0));

		this.tablaMascotas.setRowSorter(filtro);

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

		int filaSeleccionadaMascotas = this.tablaMascotas.getSelectedRow();
		String codigoVeterinario = (String) this.comboVeterinarios.getSelectedItem();
		Date fecha = (Date) this.datePicker.getModel().getValue();

		// Si alguno es vacío, mostramos una ventana de mensaje
		if (filaSeleccionadaMascotas < 0) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar una mascota.", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		if (fecha == null) {
			JOptionPane.showMessageDialog(frame, "Debe seleccionar una fecha para la consulta", "Datos incompletos!",
					JOptionPane.WARNING_MESSAGE);

			return;
		}

		// Obtenemos los datos de la tabla
		String patenteMascota = (String) this.tablaMascotas.getValueAt(filaSeleccionadaMascotas, 0);

		// Si estamos aquí,..quiere decir que no hay errores. Almacenamos el
		// veterinario y volvemos al menu
		boolean almacenado = ControladorConsultas.ingresarNuevaConsulta(codigoVeterinario, patenteMascota, fecha);

		if (almacenado) {
			JOptionPane.showMessageDialog(frame, "La consulta se ha registrado con éxito.", "Consulta Registrada!",
					JOptionPane.INFORMATION_MESSAGE);

			// cerramos la ventanta
			this.frame.dispose();

		} else {
			JOptionPane.showMessageDialog(frame, "Hubo un error al almacenar. Intente nuevamente más tarde",
					"Error al registrar!", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void accionCancelar() {
		// si se cancela, se eliminar la ventana
		this.frame.dispose();

	}

}
