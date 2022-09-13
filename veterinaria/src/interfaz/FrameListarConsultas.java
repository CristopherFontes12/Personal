package interfaz;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controladores.ControladorConsultas;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import objetos.Consulta;

public class FrameListarConsultas implements ActionListener {
	
	/** Frame */
	private Frame frame;

	/** Tabla */
	private JTable tablaConsultas;

	/** Date Picker */
	private JDatePickerImpl datePicker;

	/** Labels */
	private JLabel labelFecha;

	/** Buttons */
	private JButton botonFiltrar;
	private JButton botonLimpiar;

	public FrameListarConsultas(JFrame framePadre) {

		this.labelFecha = new JLabel("Fecha:");

		JButton botonFiltrar = new JButton("Filtrar");
		botonFiltrar.addActionListener(this);

		JButton botonLimpiar = new JButton("Limpiar Filtro");
		botonLimpiar.addActionListener(this);

		this.botonFiltrar = botonFiltrar;
		this.botonLimpiar = botonLimpiar;

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Listado de Mascotas");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel listarConsultasPanel = new JPanel(new GridBagLayout());
		listarConsultasPanel.setSize(600, 600);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		listarConsultasPanel.add(this.labelFecha, constraints);

		constraints.gridx = 1;
		this.datePicker = this.crearDatePicker();
		listarConsultasPanel.add(this.datePicker, constraints);

		constraints.gridx = 2;
		constraints.gridy = 0;
		listarConsultasPanel.add(this.botonFiltrar, constraints);

		constraints.gridx = 3;
		constraints.gridy = 0;
		listarConsultasPanel.add(this.botonLimpiar, constraints);

		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.gridwidth = 6;
		constraints.anchor = GridBagConstraints.CENTER;
		this.tablaConsultas = this.cargarTablaConsultas();
//		JScrollPane pane1 = new JScrollPane(this.tablaConsultas);
//		Dimension dim1 = this.tablaConsultas.getPreferredSize();
//		pane1.setPreferredSize(
//				new Dimension(600, 600));
		listarConsultasPanel.add(new JScrollPane(this.tablaConsultas), constraints);

		listarConsultasPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lista de Consultas"));

		frame.add(listarConsultasPanel);

		this.botonFiltrar.addActionListener(this);
		this.botonLimpiar.addActionListener(this);

		// frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}

	private JTable cargarTablaConsultas() {

		ArrayList<Consulta> consultas = ControladorConsultas.obtenerTodasConsultas();

		String[] nombreColumnas = { "Fecha", "Nro Patente", "Codigo Veterinario", "CI Veterinario" };

		/*
		 * El tamaño de la tabla es, 4 columnas (cantidad de datos a mostrar) y
		 * la cantidad de filas depende de la cantida de consultas
		 */
		Object[][] datos = new Object[consultas.size()][4];

		/* Cargamos la matriz con todos los datos */
		int fila = 0;

		SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");

		for (Consulta c : consultas) {

			datos[fila][0] = formateadorFecha.format(c.getFecha());
			datos[fila][1] = c.getMascota().getNroPatente();
			datos[fila][2] = c.getVeterinario().getCodigo();
			datos[fila][3] = c.getVeterinario().getCi();
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
		table.setSize(600, 600);

		this.tablaConsultas = table;

		return table;

	}

	private JDatePickerImpl crearDatePicker() {

		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		return datePicker;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.botonFiltrar) {
			this.accionFiltrar();
		} else {
			this.accionLimpiarFiltro();
		}

	}

	private void accionLimpiarFiltro() {

		this.tablaConsultas.setRowSorter(null);
		this.datePicker.getModel().setValue(null);
	}

	private void accionFiltrar() {

		TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaConsultas.getModel());

		Date fecha = (Date) this.datePicker.getModel().getValue();

		SimpleDateFormat formateadorFecha = new SimpleDateFormat("dd/MM/yyyy");

		if (fecha != null) {

			String fechaString = formateadorFecha.format(fecha);

			filtro.setRowFilter(RowFilter.regexFilter(fechaString, 0));

			this.tablaConsultas.setRowSorter(filtro);

		}
	}

}
