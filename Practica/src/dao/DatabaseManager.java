package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseManager {
	
	
	private static final String URL_ORACLE = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String USER = "PRACTICA";
	private static final String PASSWORD = "PRACTICA";
	
	private static Connection conn;
	
	
	
	static {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn= DriverManager.getConnection(URL_ORACLE,USER,PASSWORD);
			
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("Error al contectarse a la BD: ");
			System.exit(1);
		}
		
	}
	
	public static Connection getConnection() {
		return conn;
	}
	

}
