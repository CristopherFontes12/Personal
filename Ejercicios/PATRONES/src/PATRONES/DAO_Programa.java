package PATRONES;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAO_Programa {
	// Defino una constante que no cambia durante la ejecución del programa
	private static final String LISTAR_PROGRAMAS = "SELECT * FROM PROGRAMAS";

	private static final String INSERTAR_PROGRAMA = "INSERT INTO PROGRAMAS (IDPROGRAMA, DESCRIPCION, FECHAINGRESO, TIEMPO, ESTADO, CEDULA) VALUES (?,?,?,?,?,?)";

	private static final String ELIMINAR_PROGRAMA = "DELETE FROM PROGRAMAS WHERE IDPROGRAMA = ?";

	private static final String MODIFICAR_PROGRAMA = "UPDATE PROGRAMAS SET DESCRIPCION=?, FECHAINGRESO=?, TIEMPO=?, ESTADO=?, CEDULA=? WHERE IDPROGRAMA = ?";

	private static final String MAX_IDPROGRAMA = "SELECT MAX(IDPROGRAMA)FROM PROGRAMAS";

	private static final String BUSCAR_ID = "SELECT * FROM PROGRAMAS WHERE IDPROGRAMA=?";

	private static final String BORRAR_TODO = "DELETE FROM PROGRAMAS";
	private static final String BUSCAR_DESCRIPCION = "SELECT * FROM PROGRAMAS WHERE DESCRIPCION=?";

	private static final String CONSULTA = "SELECT COUNT(*) AS CANTIDAD FROM PROGRAMAS WHERE ESTADO=?";

	// Método Listar Programas
	public static LinkedList<Programa> listadoPrograma() {
		ResultSet resultado = null;
		LinkedList<Programa> programas = new LinkedList<Programa>();
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(LISTAR_PROGRAMAS);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				Programa programa = getProgramaFromResultSet(resultado);
				programas.add(programa);

			}
			return programas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// Método Insertar Programa
	public static boolean insertarPrograma(Programa a) {
		int resultado = 0;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR_PROGRAMA);
			int idMaximo = obtenerID() + 1; // Saber el máximo ID que tiene la tabla

			sentencia.setInt(1, idMaximo); // Agregar un ID más grande que el que figura en la tabla

			sentencia.setString(2, a.getDescripcion());

			sentencia.setDate(3, a.getFechaIngreso());

			sentencia.setInt(4, a.getTiempo());

			sentencia.setString(5, a.getEstado().name());

			sentencia.setInt(6, a.getDesarrollador().getCedula());

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			// System.out.println("El resultado es: "+resultado);
			return resultado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Eliminar una Programa
	public static boolean eliminarPrograma(Programa p) {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR_PROGRAMA);
			sentencia.setInt(1, p.getIdPrograma()); // Trae la matrícula que le pasamos por parámetro

			sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra el auto con la matrícula que yo elegí.
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Metodo Eliminar todas las Programas
	public static boolean eliminarTodo() {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BORRAR_TODO);

			sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra todos las mascotas
			sentencia.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Modificar Programa
	public static boolean modificarPrograma(Programa p) {
		int resultado = 0;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR_PROGRAMA);

			sentencia.setString(1, p.getDescripcion());

			sentencia.setDate(2, p.getFechaIngreso());

			sentencia.setInt(3, p.getTiempo());

			sentencia.setString(4, p.getEstado().name());

			sentencia.setInt(5, p.getDesarrollador().getCedula());

			sentencia.setInt(6, p.getIdPrograma());

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			return resultado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Buscar una Programa con x id
	public static Programa buscarId(int id) {
		ResultSet resultado = null;
		Programa resp = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_ID);
			sentencia.setInt(1, id);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				resp = getProgramaFromResultSet(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resp;
	}

	// Obtenemos un objeto del tipo Programa que viene en un resultado de una
	// consulta
	private static Programa getProgramaFromResultSet(ResultSet resultado) throws SQLException {

		int idPrograma = resultado.getInt("IDPrograma");
		String desc = resultado.getString("DESCRIPCION");
		Date fechaIng = resultado.getDate("FECHAINGRESO");
		int tiempo = resultado.getInt("TIEMPO");
		Estado est = Estado.valueOf(resultado.getString("ESTADO")); // esto convierte el valor String a un
																	// Enumerado

		Desarrollador desa = DAO_Desarrollador.buscarCedula(resultado.getInt("CEDULA"));

		Programa per = new Programa(idPrograma, desc, fechaIng, tiempo, est, desa);

		return per;
	}

	public static int obtenerID() {
		int res = 0;
		ResultSet resultado = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MAX_IDPROGRAMA);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				res = resultado.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	public static int cantidad(String dato) {
		int res = 0;
		ResultSet resultado = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(CONSULTA);
			sentencia.setString(1, dato);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				res = resultado.getInt("CANTIDAD");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}// Buscar un programa con x descripcion
	public static Programa buscarDescripcion(String descripcion) {
		ResultSet resultado = null;
		Programa resp = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_DESCRIPCION);
			sentencia.setString(1, descripcion);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
			while (resultado.next()){				
				resp = getProgramaFromResultSet(resultado);	
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return resp;
	}
}
	