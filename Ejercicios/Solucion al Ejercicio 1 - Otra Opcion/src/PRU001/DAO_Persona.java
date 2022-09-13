package PRU001;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAO_Persona {

	// Defino una constante que no cambia durante la ejecución del programa
	private static final String LISTAR_PERSONAS = "SELECT * FROM PERSONAS";

	private static final String INSERTAR_PERSONA = "INSERT INTO PERSONAS (IDPERSONA, NOMBRE, FECHA_INGRESO, FECHA_NACIMIENTO, TIPO_CLASE, IDPROFESOR) VALUES (?,?,?,?,?,?)";

	private static final String ELIMINAR_PERSONA = "DELETE FROM PERSONAS WHERE IDPEROSONA = ?";

	private static final String MODIFICAR_PERSONA = "UPDATE PERSONAS SET NOMBRE=?, FECHA_INGRESO=?, FECHA_NACIMIENTO=?, TIPO_CLASE=?, IDPROFESOR=? WHERE IDPERSONA = ?";

	private static final String MAX_IDPERSONA = "SELECT MAX(IDPERSONA)FROM PERSONAS";

	private static final String BUSCAR_NOMBRE = "SELECT * FROM PERSONAS WHERE NOMBRE=?";

	private static final String BORRAR_TODO = "DELETE FROM PERSONAS";

	private static final String CONSULTA = "SELECT COUNT(*) AS CANTIDAD FROM PERSONAS WHERE TIPO_CLASE=?";

	// Método Listar Personas
	public static LinkedList<Persona> listadoPersona() {
		ResultSet resultado = null;
		LinkedList<Persona> personas = new LinkedList<Persona>();
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(LISTAR_PERSONAS);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				Persona persona = getPersonaFromResultSet(resultado);
				personas.add(persona);

			}
			return personas;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}

	}

	// Método Insertar Persona
	public static boolean insertarPersona(Persona a) {
		int resultado = 0;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR_PERSONA);
			int idMaximo = obtenerID() + 1; // Saber el máximo ID que tiene la tabla

			sentencia.setInt(1, idMaximo); // Agregar un ID más grande que el que figura en la tabla

			sentencia.setString(2, a.getNombre());

			sentencia.setDate(3, a.getFechaIngreso());

			sentencia.setDate(4, a.getFechaNacimiento());

			sentencia.setString(5, a.getTipoclase().name());

			sentencia.setInt(6, a.getProfesor().getIdProfesor());
			
			

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			// System.out.println("El resultado es: "+resultado);
			return resultado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Método Eliminar una Persona
	public static boolean eliminarPersona(Persona p) {
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR_PERSONA);
			sentencia.setInt(1, p.getIdPersona()); // Trae la matrícula que le pasamos por parámetro

			sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra el auto con la matrícula que yo elegí.
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Metodo Eliminar todas las Personas
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

	// Método Modificar Persona
	public static boolean modificarPersona(Persona p) {
		int resultado = 0;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR_PERSONA);

			sentencia.setString(1, p.getNombre());

			sentencia.setDate(2, p.getFechaIngreso());

			sentencia.setDate(3, p.getFechaNacimiento());

			sentencia.setString(4, p.getTipoclase().name());

			sentencia.setInt(5, p.getProfesor().getIdProfesor());

			sentencia.setInt(6, p.getIdPersona());

			resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			return resultado > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	// Buscar una persona con x nombre
	public static Persona buscarNombre(String nombre) {
		ResultSet resultado = null;
		Persona resp = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
			sentencia.setString(1, nombre);
			resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de
													// nombre resultado.
			while (resultado.next()) {
				resp = getPersonaFromResultSet(resultado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resp;
	}

	// Obtenemos un objeto del tipo Persona que viene en un resultado de una
	// consulta
	private static Persona getPersonaFromResultSet(ResultSet resultado) throws SQLException {

		int idPersona = resultado.getInt("IDPERSONA");
		String nombre = resultado.getString("NOMBRE");
		Date fechaIng = resultado.getDate("FECHA_INGRESO");
		Date fechaNac = resultado.getDate("FECHA_NACIMIENTO");
		TipoClase tipo = TipoClase.valueOf(resultado.getString("TIPO_CLASE")); // esto convierte el valor String a un
																				// Enumerado

		Profesor prof = DAO_Profesor.buscarProfesor(resultado.getInt("IDPROFESOR"));

		Persona per = new Persona(idPersona, nombre, fechaIng, fechaNac, tipo, prof);

		return per;
	}

	public static int obtenerID() {
		int res = 0;
		ResultSet resultado = null;
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MAX_IDPERSONA);
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
	}

}
