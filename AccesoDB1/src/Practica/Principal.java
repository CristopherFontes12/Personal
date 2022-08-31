package Practica;

import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection connection = DatabaseManager.getConnection();

		
		System.out.println("POR AQUI CONINUARA LA EJECICION DEL PROGRAMA, SI NO OCURRE UNA EXEPCION**********************");
		
        
		
		
		String idPersonaST = JOptionPane.showInputDialog("Id: ");
        Integer idPersona; // Integer es el tipo wrapper del tipo nativo int
        idPersona = Integer.parseInt(idPersonaST);
        String documento = JOptionPane.showInputDialog("Documento: ");
        String apellido1 = JOptionPane.showInputDialog("Apellido1: ");
        String apellido2 = JOptionPane.showInputDialog("Apellido2: ");
        String nombre1 = JOptionPane.showInputDialog("Nombre1: ");
        String nombre2 = JOptionPane.showInputDialog("Nombre2: ");

        String INSERTAR_PERSONA = "INSERT INTO PERSONA (ID_PERSONA,DOCUMENTO,APELLIDO1,APELLIDO2,NOMBRE1,NOMBRE2) values (?,?,?,?,?,?)";
        

		
        
		
		//String consulta = "Select documento, nombre1, apellido1 from persona where apellido1 = " + "'" + apellido + "'" ;
		
		
        
        
        

		try {

			
			PreparedStatement statement = connection.prepareStatement(INSERTAR_PERSONA);
			statement.setInt(1,idPersona);
			statement.setString(2, documento);
			statement.setString(3, apellido1);
			statement.setString(4, apellido2);
			statement.setString(5, nombre1);
			statement.setString(6, nombre2);
			
			
			
			//Retorna el numero de registros afectados por la ejecucion 
			int filasIngresadas = statement.executeUpdate();
			System.out.println("Se modificaron: " + filasIngresadas);
			
			
			
			
			
			
			//ResultSet personasRS = setencia.executeQuery(consulta);
			//personasRS.next();
			//System.out.println(personasRS.getString(1) + " " + " " + personasRS.getString(2) + " " + personasRS.getString(3));
			
			//while (statement.next()) {
			//System.out.println(statement.getString(1) + " " + " " + statement.getString(2) + " " + statement.getString(3));
				
				
				
			//}
			
			
		} 
		
			catch (Exception e) {
			System.out.println("******************************************************************************");
			System.out.println("Error al ejecutar la consulta -> " + INSERTAR_PERSONA);
			System.out.println("******************************************************************************");
			e.printStackTrace();
			return;
		}	
		
		String consulta = "Select id_persona ,documento, apellido1, apellido2 ,nombre1, nombre2  from persona order by id_persona";
		
		
		try {
			
			
		
			// Select para mostrar todos los registros. 
					Statement setencia = connection.createStatement();
					ResultSet personasRS = setencia.executeQuery(consulta);
					while (personasRS.next()) {
					System.out.println(personasRS.getString(1) + " " + " " + personasRS.getString(2) + " " + personasRS.getString(3) + " " + personasRS.getString(4) + " " + personasRS.getString(5));
								
								
								
				}
						
		
		
		
		
		
		
		
		
		} catch (Exception e) {
			System.out.println("******************************************************************************");
			System.out.println("Error al ejecutar la consulta -> " + consulta);
			System.out.println("******************************************************************************");
			e.printStackTrace();
			return;
		
		
		
		
		
		
		
		
		
		
		
		} 
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	try { 
			
			
			connection.close();
			System.out.println("Conexion cerrada con existo, ya no es posible acceder a la base de datos");
			
			
		} catch (Exception e) {
			
			System.out.println("Error al cerrar la coneccion!!");
			e.printStackTrace();
			return;
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
