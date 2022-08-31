package Practica;

import java.sql.Statement;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DatabaseManager {

	private static Connection  databaseConnection;
	
	private static String CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String USUARIO = "JAVA";
	private static String CLAVE = "JAVA";
	
	
	static {
		
		databaseConnection = null;
		
		
		
		try {
			
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("Se encontro el driver para Oracle DB - La liberia esta referenciada");
			
			
		try { // Intentamos instanciar el objeto connection 
			
			
			databaseConnection = DriverManager.getConnection(CONNECTION_STRING,USUARIO,CLAVE);
			
			System.out.println("Conexion creada con existo, es posible acceder a la base de datos!");
			
			
			
			
		}catch (SQLException e) {
			// TODO: handle exception
			
			System.out.println("No logramos instanciar una conexion!!");
			
			
		}		// Try intentamos instanciar el objeto connection
			
		}
			catch (Exception e) {
				// TODO: handle exception
				
				System.out.println("No tienens el drive en tu build-path?");
				e.printStackTrace();
		
			} // Try Tenemo vinculad la clase en nuestro proyecto
		
	}
	
	
		public static Connection getConnection() {
			return databaseConnection;			
			
		}// Public class DatabaseManager
	

	
		
}	

