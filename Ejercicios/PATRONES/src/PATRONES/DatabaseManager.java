package PATRONES;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
	public static Connection databaseConnection;
	static String usuario = "PATRONES";
	static String clave = "PATRONES";

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
