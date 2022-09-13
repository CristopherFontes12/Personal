package logica;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Principal {

	public static void main(String[] args) {
		
		
		System.out.println("----------------------------------------------------------");
		System.out.println("--------------------DAO PERSONA---------------------------");
		System.out.println("----------------------------------------------------------");
					
	/*	int cuenta=DAOPersona.cuentaPersonas();
		
		if(cuenta>0) {
			System.out.println("Hay "+cuenta+" registros en la tabla Persona");
			
			//Aqui creo una lista de personas nulas para que luego se carguen aqui
			LinkedList<String> personas = null;
			
			//Asigno dicha lista nula a la función FINDALL
			personas = DAOPersona.findAll();
			
			//Recorro la lista antes definida e imprimo los valores
			for (String p:personas){
				System.out.println(p);
				
			}
			
		}else {
			System.out.println("No hay registros en la tabla Persona");
		}*/
		
		System.out.println("----------------");
		
		
		
		//BUSCAR PERSONA
		DAOPersona.findPersona("PÉREZ", "LUANA");
		
		
		System.out.println("---------------------------");
		
		
		//UPDATE PERSONA
		String documento = JOptionPane.showInputDialog("Documento: ");
		String apellido1 = JOptionPane.showInputDialog("Apellido1: ");
		String apellido2 = JOptionPane.showInputDialog("Apellido2: ");
		String nombre1 = JOptionPane.showInputDialog("Nombre1: ");
		String nombre2 = JOptionPane.showInputDialog("Nombre2: ");
		
		Persona p1 =  new Persona(4, documento, apellido1, apellido2, nombre1, nombre2);
		
		DAOPersona.actualizar(p1, 4);
		
		System.out.println(p1);
		
		System.out.println("-------------------");
		
		
		//DELETE PERSONA
		DAOPersona.delete(4);
		
		
		
		
	}
}

		