package logica;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

	private static Connection databaseConnection;
	
	private static String CONNECTION_STRING = "jdbc:oracle:thin:@localhost:1522:xe";   // conexi�n
	private static String USUARIO = "JAVA";                                            // usuario
	private static String CLAVE = "JAVA";                                             // clave
	
	static {
		databaseConnection = null;
	
	
	try { //Se tiene vinculada la clase en nuestro proyecto?
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		System.out.println("Se encontr� el Driver para Oracle DB - La librer�a necesaria est� referenciada");
	
		try { // Intentamos instanciar el objeto connection
			databaseConnection = DriverManager.getConnection(CONNECTION_STRING,USUARIO,CLAVE);
			System.out.println("Conexi�n creada con �xito, es posible acceder a la base de datos");
		
		} catch(SQLException e) {
			System.out.println("No logramos instanciar una conexi�n");
			e.printStackTrace();
		} // try{ Intentamos instanciar el objeto connection
	
	}catch (ClassNotFoundException e) {
		System.out.println("No tienes el driver en tu build-path?");
		e.printStackTrace();
	} // try tenemos vinculada la clase en nuestro proyecto?
	
}
	


public static Connection getConnection() {
	return databaseConnection;
	}
}