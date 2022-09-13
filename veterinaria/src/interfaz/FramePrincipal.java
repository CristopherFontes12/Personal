package interfaz;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class FramePrincipal {
	
	/**
	 *  Método que se ejecuta cuando se inicia la aplicación. Invoca a "createAndShowGUI"
	 *  para que se generen los componentes.
	 */
	public static void main(String[] args) {

		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/** Método que inicializa toda la ventatna principal */
	
	private static void createAndShowGUI() {

		
		JFrame frame = new JFrame("Veterinaria");
		
		frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		initializeMenuBar(frame);

		// Display the window.
		frame.setVisible(true);
	}

	
	/** Inicializamos la barra de menu de arriba */
	
	private static void initializeMenuBar(JFrame frame) {
		
		JMenuBar menuBar = new JMenuBar();
		
		initializeMenuClientes(menuBar, frame);
		initializeMenuMascotas(menuBar, frame);
		initializeMenuVeterinario(menuBar, frame);
		initializeMenuConsultas(menuBar, frame);
		
		frame.setJMenuBar(menuBar);

	}

	/** Inicialicación de botones del menu de clientes */
	
	private static void initializeMenuClientes(JMenuBar menuBar, final JFrame frame) {
		
		JMenu clientes = new JMenu("Clientes");
		
		JMenuItem nuevoCliente = new JMenuItem("Nuevo Cliente");
		
		nuevoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameNuevoCliente(frame);
            	
            }
        });

		clientes.add(nuevoCliente);
		menuBar.add(clientes);
		
	}
	
	/** Inicialicación de botones del menu de Mascotas */
	
	private static void initializeMenuMascotas(JMenuBar menuBar, final JFrame frame) {
		
		JMenu mascotas = new JMenu("Mascotas");
		
		JMenuItem nuevaMascota = new JMenuItem("Nueva Mascota");
		
		nuevaMascota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameNuevaMascota(frame);
            	
            }
        });
		
		JMenuItem listarMastcotas = new JMenuItem("Listar Mascotas");
		
		listarMastcotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameListarMascotas(frame);
            	
            }
        });

		mascotas.add(nuevaMascota);
		mascotas.add(listarMastcotas);
		
		menuBar.add(mascotas);
		
	}
	
	/** Inicialicación de botones del menu de Veterinario */
	
	private static void initializeMenuVeterinario(JMenuBar menuBar, final JFrame frame) {
		
		JMenu veterinarios = new JMenu("Veterinarios");
		
		JMenuItem nuevoVeterinario = new JMenuItem("Nuevo Veterinario");
		
		nuevoVeterinario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameNuevoVeterinario(frame);
            	
            }
        });

		veterinarios.add(nuevoVeterinario);
		
		menuBar.add(veterinarios);
		
	}
	
	/** Inicialicación de botones del menu de Consultas */
	
	private static void initializeMenuConsultas(JMenuBar menuBar, final JFrame frame) {
		
		JMenu consultas = new JMenu("Consultas");
		
		JMenuItem nuevaConsulta = new JMenuItem("Nueva Consulta");
		
		nuevaConsulta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameNuevaConsulta(frame);
            	
            }
        });
		
		JMenuItem listarConsultas = new JMenuItem("Listar Consultas");
		
		listarConsultas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                
            	new FrameListarConsultas(frame);
            	
            }
        });

		consultas.add(nuevaConsulta);
		consultas.add(listarConsultas);
		
		menuBar.add(consultas);		
	}

}