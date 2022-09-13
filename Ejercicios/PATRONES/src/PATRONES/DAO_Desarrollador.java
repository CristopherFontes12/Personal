package PATRONES;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAO_Desarrollador {
	// Defino una constante que no cambia durante la ejecución del programa
	private static final String LISTAR_DESARROLLADOR = "SELECT * FROM DESARROLLADOR";

	private static final String INSERTAR_DESARROLLADOR = "INSERT INTO DESARROLLADOR (CEDULA, NOMBRE, FECHAINGRESO, CATEGORIA) VALUES (?,?,?,?)";

	private static final String ELIMINAR_DESARROLLADOR = "DELETE FROM DESARROLLADOR WHERE CEDULA = ?";

	private static final String MODIFICAR_DESARROLLADOR = "UPDATE DESARROLLADOR SET NOMBRE=?, FECHAINGRESO=?, CATEGORIA=?, WHERE CEDULA = ?";

	// private static final String MAX_CEDULA = "SELECT MAX(CEDULA)FROM
	// DESARROLADOR";

	private static final String BUSCAR_NOMBRE = "SELECT * FROM DESARROLLADOR WHERE NOMBRE=?";

	private static final String BUSCAR_CEDULA = "SELECT * FROM DESARROLLADOR WHERE CEDULA=?";

	private static final String BORRAR_TODO = "DELETE FROM DESARROLLADOR";

	// private static final String CONSULTA = "SELECT COUNT(*) AS CANTIDAD FROM
	// DESARROLLADOR WHERE CATEGORIA=?";

	// Método Listar desarrollador
	// Método Listar Desarrolladores
	public static ResultSet listadoDesarrolladores() {
		ResultSet resultado = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(LISTAR_DESARROLLADOR);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	// Método Insertar Desarrollador
	public static boolean insertarDesarrollador(Desarrollador a) {
		int resultado = 0;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR_DESARROLLADOR);
			// int idMaximo = obtenerID() + 1; // Saber el máximo ID que tiene la tabla

			sentencia.setInt(1, a.getCedula());

			sentencia.setString(2, a.getNombre());

			sentencia.setDate(3, a.getFechaIngreso());

			sentencia.setString(4, a.getCategoria().name());

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			// System.out.println("El resultado es: "+resultado);
			return resultado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Eliminar una Desarrollador
	public static boolean eliminarDesarrollador(Desarrollador p) {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR_DESARROLLADOR);
			sentencia.setInt(1, p.getCedula()); // Trae la cedula que le pasamos por parámetro

			sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra el auto con la matrícula que yo elegí.
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Metodo Eliminar todas las Desarrolladores
	public static boolean eliminarTodo() {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BORRAR_TODO);

			sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra todos las desarrolladores
			sentencia.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Modificar Desarrollador
	public static boolean modificarDesarrollador(Desarrollador p) {
		int resultado = 0;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR_DESARROLLADOR);

			sentencia.setString(1, p.getNombre());

			sentencia.setDate(2, p.getFechaIngreso());

			sentencia.setString(3, p.getCategoria().name());

			sentencia.setInt(4, p.getCedula());

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			return resultado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Buscar una Desarrollador con x nombre
	public static Desarrollador buscarNombre(String nombre) {
		ResultSet resultado = null;
		Desarrollador resp = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
			sentencia.setString(1, nombre);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				resp = getDesarrolladorFromResultSet(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resp;
	}

	// Buscar una Desarrollador con x cedula
	public static Desarrollador buscarCedula(int cedula) {
		ResultSet resultado = null;
		Desarrollador resp = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_CEDULA);
			sentencia.setInt(1, cedula);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				resp = getDesarrolladorFromResultSet(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resp;
	}

	// Obtenemos un objeto del tipo Persona que viene en un resultado de una
	// consulta
	private static Desarrollador getDesarrolladorFromResultSet(ResultSet resultado) throws SQLException {

		int idPersona = resultado.getInt("CEDULA");
		String nombre = resultado.getString("NOMBRE");
		Date fechaIng = resultado.getDate("FECHAINGRESO");

		Categoria cat = Categoria.valueOf(resultado.getString("CATEGORIA")); // esto convierte el valor String a un
																				// Enumerado

		Desarrollador des = new Desarrollador(idPersona, nombre, fechaIng, cat);

		return des;
	}

	/*
	 * public static int obtenerID() { int res = 0; ResultSet resultado = null; try
	 * { PreparedStatement sentencia =
	 * DatabaseManager.getConnection().prepareStatement(MAX_CEDULA); resultado =
	 * sentencia.executeQuery();
	 * 
	 * while (resultado.next()) { res = resultado.getInt(1); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return res; }
	 * 
	 * public static int cantidad(String dato) { int res = 0; ResultSet resultado =
	 * null; try { PreparedStatement sentencia =
	 * DatabaseManager.getConnection().prepareStatement(CONSULTA);
	 * sentencia.setString(1, dato); resultado = sentencia.executeQuery();
	 * 
	 * while (resultado.next()) { res = resultado.getInt("CANTIDAD"); }
	 * 
	 * } catch (SQLException e) { e.printStackTrace(); }
	 * 
	 * return res;
	 */
}
