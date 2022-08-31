package modal;

import java.awt.EventQueue;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import dao.DaoPersona;

public class Principal {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method 
		
		
		
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
			
		
		
		
		
	}
		
		
}		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*System.out.println();
		System.out.println("*******************************DAO PERSONA*********************************");
		System.out.println();
		
		
		int cuenta = DaoPersona.cuentaPersonas();
		
		if (cuenta>0) {
			System.out.println("Hay " + cuenta + " registros en la tabla PERSONA");
			
			
	        String documento = JOptionPane.showInputDialog("Documento: ");
	        String apellido1 = JOptionPane.showInputDialog("Apellido1: ");
	        String apellido2 = JOptionPane.showInputDialog("Apellido2: ");
	        String nombre1 = JOptionPane.showInputDialog("Nombre1: ");
	        String nombre2 = JOptionPane.showInputDialog("Nombre2: ");
	        
	        
	        Persona per = new Persona(documento, apellido1, apellido2, nombre1, nombre2);
	        
	        if (DaoPersona.insert(per)) {
	        	System.out.println("Se ha insertado una persona en la base de datos");	    	        	
	        }else {
				System.out.println("No se ha insartado una persona en la base de datos");
			}
			
			
			
			LinkedList<String> personas = null;
			
			personas = DaoPersona.findAll();
			
			for (String p : personas) {
				
				System.out.println(p);
			}

			
			
		
		}else {
			
			
			System.out.println("No hay registros en la tabla PERSONA");
			
		}
		
*/

