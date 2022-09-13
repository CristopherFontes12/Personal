package interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

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
import javax.swing.RowSorter;
import javax.swing.ScrollPaneLayout;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import controladores.ControladorMascotas;
import objetos.Cliente;
import objetos.Mascota;

public class FrameListarMascotas implements DocumentListener, ItemListener {

	/** Frame de la ventana */
	private JFrame frame;

	/** Atributos de labels */
	private JLabel labelPatente;
	private JLabel labelTipo;

	private JComboBox<String> comboTipo;

	/** Atributos de TexField */
	private JTextField textPatente;

	/** Tabla */
	private JTable tablaMascotas;

	/** Tipo Seleccionado (por defecto vale todos) */
	private String tipoSeleccionado = "Todos";

	public FrameListarMascotas(JFrame framePadre) {

		this.labelPatente = new JLabel("Nro Patente:");
		this.labelTipo = new JLabel("Tipo:");
		this.textPatente = new JTextField(15);

		this.initalizeFrame(framePadre);
	}

	private void initalizeFrame(JFrame framePadre) {

		JFrame frame = new JFrame("Listado de Mascotas");
		frame.setSize(600, 600);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel listarMascotasPanel = new JPanel(new GridBagLayout());
		listarMascotasPanel.setSize(600, 600);

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.WEST;
		constraints.insets = new Insets(10, 2, 2, 10);

		constraints.gridx = 0;
		constraints.gridy = 0;
		listarMascotasPanel.add(this.labelPatente, constraints);

		constraints.gridx = 1;
		listarMascotasPanel.add(this.textPatente, constraints);

		constraints.gridx = 0;
		constraints.gridy = 1;
		listarMascotasPanel.add(this.labelTipo, constraints);

		constraints.gridx = 1;
		this.comboTipo = this.completarComboTipo();
		this.comboTipo.setSelectedIndex(0);
		listarMascotasPanel.add(this.comboTipo, constraints);

		constraints.gridx = 0;
		constraints.gridy = 5;
		constraints.gridwidth = 5;
		constraints.anchor = GridBagConstraints.CENTER;
		this.tablaMascotas = this.cargarTablaMascotas();
		listarMascotasPanel.add(new JScrollPane(this.tablaMascotas), constraints);

		listarMascotasPanel
				.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Lista de Mascotas"));

		frame.add(listarMascotasPanel);

		this.textPatente.getDocument().addDocumentListener(this);
		this.comboTipo.addItemListener(this);

		// frame.pack();
		frame.setVisible(true);

		this.frame = frame;

	}

	private JTable cargarTablaMascotas() {

		ArrayList<Mascota> mascotas = ControladorMascotas.obtenerTodasMascotas();

		String[] nombreColumnas = { "Nro Patente", "Nombre", "Tipo", "Edad", "CI Cliente", "Nombre Cliente" };

		/*
		 * El tamaño de la tabla es, 6 columnas (cantidad de datos a mostrar) y
		 * la cantidad de filas depende de la cantida de mascotas
		 */
		Object[][] datos = new Object[mascotas.size()][6];

		/* Cargamos la matriz con todos los datos */
		int fila = 0;

		for (Mascota m : mascotas) {

			datos[fila][0] = m.getNroPatente();
			datos[fila][1] = m.getNombre();
			datos[fila][2] = m.getTipo();
			datos[fila][3] = m.getEdad();
			datos[fila][4] = m.getCliente().getCi();
			datos[fila][5] = m.getCliente().getNombre();
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

		this.tablaMascotas = table;

		return table;

	}

	private JComboBox<String> completarComboTipo() {

		String[] valores = { "Todos", "Perro", "Gato", "Conejo", "Pajaro" };
		return new JComboBox<>(valores);

	}

	@Override
	public void changedUpdate(DocumentEvent e) {
		this.filtrar();

	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		this.filtrar();

	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		this.filtrar();

	}

	public void filtrar() {
		
		/* Para filtrar, debemos considerar el campo de texto y ademas el valor seleccionado en el tipo. 
		 * Si el tipo es todos, debemos filtrar solo por e texto, 
		 * si el tipo es distinto de todos debemos filtar por tipo y por el texto.
		 */
		
		if (!this.tipoSeleccionado.equals("Todos")) {

			List<RowFilter<Object,Object>> filters = new ArrayList<>(2);
			filters.add(RowFilter.regexFilter(this.textPatente.getText(), 0));
			filters.add(RowFilter.regexFilter(this.tipoSeleccionado, 2));

			TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaMascotas.getModel());
			filtro.setRowFilter(RowFilter.andFilter(filters));
			this.tablaMascotas.setRowSorter(filtro);

		}
		else{
			
			TableRowSorter<TableModel> filtro = new TableRowSorter<>(this.tablaMascotas.getModel());
			filtro.setRowFilter(RowFilter.regexFilter(this.textPatente.getText(), 0));
			
			this.tablaMascotas.setRowSorter(filtro);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent event) {
		
		/* Cuando cambia el valor, almacenamos el alor actual, para poder acceder desde el filtro */

		JComboBox comboBox = (JComboBox) event.getSource();
		Object item = event.getItem();

		if (event.getStateChange() == ItemEvent.SELECTED) {

			String valorSeleccionado = item.toString();
			this.tipoSeleccionado = valorSeleccionado;
			this.filtrar();
		}

	}

}
