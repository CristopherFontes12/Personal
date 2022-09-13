package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import modal.Carrera;
import modal.Universidad;

public class CarreraDao {
	
	
	// OBTENER EL COUNT DE TODAS LAS CARREAS. 
	
	private static final String CUENTA_CARRERA = "SELECT COUNT(ID_CARRERA) AS CARRERAS FROM CARRERA";
	
	
	// OBTENER TODAS LAS CARRERAS DE UNA O VARIAS UNIVERSIDADES.
	
	
	private static final String TODAS_CARRERA = "SELECT * FROM CARRERA A INNER JOIN UNIVERSIDAD B ON A.ID_UNIVERSIDAD = B.ID_UNIVERSIDAD ";
	
	// OBTENER UNA CARRARE DE ACUERDO AL ID QUE CORRESPONDA.
	
	
	private static final String BUSCAR_CARRERA_POR_ID = "SELECT * FROM CARRERA WHERE ID_CARRERA =?";	
	
	
	// DAR DE ALTA UNA CARRERA
	
	
	private static final String CREAR_CARRERA = "INSERT INTO CARRERA (NOMBRE, ID_UNIVERSIDAD) VALUES (?,?)";
	
	
	
	private static final String MODIFICAR_CARRERA = "UPDATE CARRERA SET NOMBRE = ?, ID_UNIVERSIDAD = ? WHERE ID_CARRERA = ?";
	
	
	// ELIMINAR UNA CARRERA
	
	private static final String ELIMINAR_CARRERA = "DELETE FROM CARRERA WHERE ID_CARRERA = ?";
	
	/*
	*------------------------------------------- CUENTA CARRERA -------------------------------------------
	*/
	
	public static int cuentaCarrera() throws SQLException {
		int cuenta = 0;
		
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(CUENTA_CARRERA);
		ResultSet resultado = statement.executeQuery();
		
		while (resultado.next()) {
			cuenta = resultado.getInt("CARRERAS");
		}
		
		return cuenta;
	}
	
	
	
	
	/*
	*------------------------------------------- TODAS CARRERA-------------------------------------------
	*/
	
	public static LinkedList<Carrera> todasCarreras() throws SQLException {
	
		LinkedList<Carrera> carreras = new LinkedList<Carrera>();
		
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(TODAS_CARRERA);
		ResultSet resultado = statement.executeQuery();
		
		while  (resultado.next()) {
			Carrera c = new Carrera();
			c.setIdCarrera(resultado.getLong(1));
			c.setNombre(resultado.getString(2));
			Universidad u = new Universidad();
			u.setIdUniversidad(resultado.getLong(3));
			u.setNombre(resultado.getString(4));
			u.setUbicacion(resultado.getString(5));
			c.setUniversidad(u);
			carreras.add(c);
			

		}

		
		return carreras;
		
	}
	
	
	/*
	*------------------------------------------- BUSCAR CARRERA-------------------------------------------
	*/
	
	
	public static Carrera buscarCarreraPorId(Long id) throws SQLException {
		
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_CARRERA_POR_ID);
		statement.setLong(1, id);
		ResultSet resultado = statement.executeQuery();
		
		
		if (resultado.next()) {
		Carrera c = new Carrera();
			c.setIdCarrera(resultado.getLong(1));
			c.setNombre(resultado.getString(2));
		Universidad u = new Universidad();	
			u.setIdUniversidad(resultado.getLong(3));
			c.setUniversidad(u);
			return c;
	
		}else 
			
			return null;
		
	}
		
		/*
		*------------------------------------------- CREAR UNA CARRERA -------------------------------------------
		*/
		
		public static boolean crearCarrera(Carrera c) throws SQLException {
		
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(CREAR_CARRERA);
			statement.setString(1, c.getNombre());
			statement.setLong(2, c.getUniversidad().getIdUniversidad());
			return statement.executeUpdate()>0;

		
	}
	
	
		/*
		*------------------------------------------- MODIFICAR_CARRERA -------------------------------------------
		*/
	
		public static boolean modificarCarrera (Long id, Carrera c) throws SQLException {
			
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(MODIFICAR_CARRERA);
			statement.setString(1, c.getNombre());
			statement.setLong(2, c.getUniversidad().getIdUniversidad());
			statement.setLong(3, id);
			return statement.executeUpdate()>0;
			
			
		}
			
		/*
		*------------------------------------------- MODIFICAR_CARRERA -------------------------------------------
		*/

		public static boolean eliminarCarrera (Long id) throws SQLException {
			
			
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ELIMINAR_CARRERA);
			statement.setLong(1, id);
			return statement.executeUpdate()>0;
			
		}
		
		
}
