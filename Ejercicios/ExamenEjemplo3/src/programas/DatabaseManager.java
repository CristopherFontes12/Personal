package programas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseManager {
	
	public static Connection databaseConnection;
	static String usuario="SISTEMA1";
	static String clave="SISTEMA1";

	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("Se encontro driver");

			
			try {
				System.out.println("conectamos");
				databaseConnection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", usuario, clave);

			} catch (SQLException e) {
				System.out.println("No logramos conectar");

			}

		} catch (ClassNotFoundException e) {
			System.out.println("No tienes el driver en tu build");
			e.printStackTrace();

		}
	}
	
	public static Connection getConnection() {
		return databaseConnection;
	}
}


