package examenFeb2021;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;



public class dataBaseManager {

private static Connection databaseConnection;
	
	private static String CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String USUARIO = "UTEC1";
	private static String CLAVE = "UTEC1";
	
	static{
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			System.out.println("-------------------------------------------------------------------------------");
			System.out.println("Se encontr� el Driver para oracle DB - La librer�a necesaria est� referenciada.");
			
		
			try {
				
			Locale.setDefault(new Locale("es","ES"));
			databaseConnection = DriverManager.getConnection(CONNECTION_STRING,USUARIO,CLAVE);
			System.out.println("Se ha establecido la conexi�n a la base de datos.");
			System.out.println("-------------------------------------------------------------------------------");
			
			} catch (SQLException e) {
				
			System.out.println("Error intentando conectar a la base de datos, no se pudo instanciar una conexi�n.");
			System.out.println("---------------------------------------------------------------------------------");
			e.printStackTrace();
			
			} 

	} catch (ClassNotFoundException e) {
		
		System.out.println("No tienes el driver en tu build-patch?");
		System.out.println("---------------------------------------------------------------------------------");
		e.printStackTrace();
		
	}//try
		
	}//static
	public static Connection getConnection() {
		
		return databaseConnection;
		
	}//get
	
}//class
