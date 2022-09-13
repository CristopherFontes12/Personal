package PRU001;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class DAO_Profesor {
	private static final String LISTAR_PROFESOR = "SELECT * FROM PROFESORES";

	private static final String INSERTAR_PROFESOR = "INSERT INTO PROFESORES (IDPROFESOR, NOMBRE,TELEFONO) VALUES (?,?,?)";

	private static final String ELIMINAR_PROFESOR = "DELETE FROM PROFESORES WHERE IDPROFESOR = ?";

	private static final String MODIFICAR_PROFESOR = "UPDATE PROFESORES SET NOMBRE=?, TELEFONO=? WHERE IDPROFESOR = ?";

	private static final String MAX_PROFESOR = "SELECT MAX(IDPROFESOR)FROM PROFESORES";

	private static final String BUSCAR_ID = "SELECT * FROM PROFESORES WHERE IDPROFESOR = ?";

	private static final String BUSCAR_NOMBRE = "SELECT * FROM PROFESORES WHERE NOMBRE = ?";

	private static final String BORRAR_TODO = "DELETE FROM PROFESORES";

	// Método Listar Profesores
	public static ResultSet listadoProfesores() {
		ResultSet resultado = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(LISTAR_PROFESOR);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultado;
	}

	// Método Insertar profesores
	public static boolean insertarProfesor(Profesor p) {
		int resultado = 0;
		try {

			// Obtengo el ultimo id de profesor guardado en la tabla
			int id = obtenerID() + 1;

			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR_PROFESOR);
			sentencia.setInt(1, id); // Agregar un ID más grande que el que figura en la tabla

			sentencia.setString(2, p.getNombre());
			
			sentencia.setString(3, p.getTelefono());
			
			

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
						
			
			// nombre resultado.
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Eliminar Profesor
	public static boolean eliminarProfesor(Profesor p) {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR_PROFESOR);
			sentencia.setInt(1, p.getIdProfesor()); // Trae el ID de esa clase que pasamos por parámetro

			sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra el profesor con el ID que yo elegí.
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Eliminar todos los profesores
	public static boolean eliminarTodo() {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BORRAR_TODO);

			sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra todos los profesores
			sentencia.close();// cerrar la conexion
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Modificar Profesores
	public static boolean modificarProfesor(Profesor p) {
		int resultado = 0;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR_PROFESOR);
			
			sentencia.setString(1, p.getNombre());
			sentencia.setString(2, p.getTelefono());
			sentencia.setInt(3, p.getIdProfesor());

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			return resultado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Metodo para obtener el ultimo Profesor en la tabla ingresado
	public static int obtenerID() {
		int res = 0;
		ResultSet resultado = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MAX_PROFESOR);
			resultado = sentencia.executeQuery();

			while (resultado.next()) {
				res = resultado.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return res;
	}

	// Método Buscar 1 PROFESOR por Id
	public static Profesor buscarProfesor(int id) {
		ResultSet resultado = null;
		Profesor resp = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_ID);
			sentencia.setInt(1, id);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				resp = getProfesorFromResultSet(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resp;
	}

	// Método Buscar 1 Responsable por Nombre
	public static Profesor buscarProfpNombre(String nombre) {
		ResultSet resultado = null;
		Profesor resp = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
			sentencia.setString(1, nombre);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				resp = getProfesorFromResultSet(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resp;
	}

	// Construimos un objeto de Responsable
	private static Profesor getProfesorFromResultSet(ResultSet resultado) throws SQLException {
		int idProfesor = resultado.getInt("IDPROFESOR");
		String nombre = resultado.getString("NOMBRE");
		String telefono= resultado.getString("TELEFONO");

		Profesor resp = new Profesor(idProfesor, nombre,telefono);

		return resp;

	}

}

	

