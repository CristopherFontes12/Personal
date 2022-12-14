package ejercicio1;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAOPersona {
	
	// Defino una constante que no cambia durante la ejecuci?n del programa
	private static final String LISTAR_PERSONAS = "SELECT * FROM PERSONAS";
		
	private static final String INSERTAR_PERSONA = "INSERT INTO PERSONAS (IDPERSONA, NOMBRE, FECHAINGRESO, FECHANACIMIENTO, TIPOCLASE, IDPROFESOR) VALUES (?,?,?,?,?,?)";
		
	private static final String ELIMINAR_PERSONA = "DELETE FROM PERSONAS WHERE IDPERSONA = ?";
		
	private static final String MODIFICAR_PERSONA = "UPDATE PERSONAS SET NOMBRE=?, FECHAINGRESO=?, FECHANACIMIENTO=?, TIPOCLASE=?, IDPROFESOR=? WHERE IDPERSONA = ?";
		
	private static final String MAX_ID="SELECT MAX(IDPERSONA)FROM PERSONAS";
		
	private static final String BUSCAR_NOMBRE="SELECT * FROM PERSONAS WHERE NOMBRE=?";
		
	private static final String BORRAR_TODO="DELETE FROM PERSONAS";
	
	private static final String CONSULTA="SELECT COUNT(*) AS CANTIDAD FROM PERSONAS WHERE TIPOCLASE=?";
		//M?todo Listar Personas
		public static LinkedList<Persona> listadoPersona() {
			ResultSet resultado=null;
			LinkedList<Persona> personas = new LinkedList<Persona>();
			try {
				PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(LISTAR_PERSONAS);
				resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ?sta variable de nombre resultado.
	            while (resultado.next()){
					  Persona persona = getPersonaFromResultSet(resultado);
					  personas.add(persona);		
									
				}
				return personas;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}		
			
		}
		
		

		//M?todo Insertar Persona
			public static boolean insertarPersona(Persona p) {
				int resultado = 0;      
				try {
					PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR_PERSONA);
			    	int idMaximo = obtenerID()+1; // Saber el m?ximo ID que tiene la tabla
					sentencia.setInt( 1, idMaximo); // Agregar un ID m?s grande que el que figura en la tabla
					
					sentencia.setString(2, p.getNombre());
					sentencia.setDate(3, p.getFechaIngreso());
					sentencia.setDate(4, p.getFechaNacimiento());
					
					sentencia.setString(5, p.getTipo().name());
					
					sentencia.setInt(6, p.getProfesor().getIdProfesor());
					
					resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ?sta variable de nombre resultado.
					
					return resultado>0;
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return false;
			}
			
			
			//M?todo Eliminar una Persona
			public static boolean eliminarPersona(Persona p) {   
				try {
					PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR_PERSONA);
					sentencia.setInt( 1, p.getIdPersona()); // Trae el ID de esa clase que pasamos por par?metro
					
					sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra la mascota con el ID que yo eleg?.
					return true;
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return false;
			}
			
			// Metodo Eliminar todas las Persona
					public static boolean eliminarTodo() {   
						try {
							PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BORRAR_TODO);
							 
							sentencia.executeUpdate(); // Ejecutar esa consulta SQL y borra todos las mascotas
							sentencia.close();
							return true;
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						return false;
					}
			
			//M?todo Modificar Persona
					public static boolean modificarPersona(Persona p) {
						int resultado = 0;      
						try {
							PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR_PERSONA);
												
							sentencia.setString(1, p.getNombre());
							
							sentencia.setDate(2, p.getFechaIngreso());
							sentencia.setDate(3, p.getFechaNacimiento());
							
							sentencia.setString(4, p.getTipo().name());
							
							sentencia.setInt(5, p.getProfesor().getIdProfesor());
							
							sentencia.setInt(6, p.getIdPersona());
							
							resultado = sentencia.executeUpdate(); // Ejecutar esa consulta SQL y devuelve la lista en ?sta variable de nombre resultado.
							return resultado>0;
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						return false;
					}
					
					// Metodo para obtener el ultimo idmascota en la tabla ingresado
			public static int obtenerID() {
					int res=0;
					ResultSet resultado = null;      
					try {
						PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MAX_ID);
						resultado = sentencia.executeQuery();	
						    
						while (resultado.next()){				
							 res = resultado.getInt(1);
						}
						}catch(SQLException e) {
							e.printStackTrace();
						}		
						
						
						return res;
					}
					
			// Buscar una Persona x nombre
			public static Persona buscarNombre(String nombre) {
				ResultSet resultado = null;
				Persona resp = null;
				try {
					PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
					sentencia.setString(1,nombre);
					resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ?sta variable de nombre resultado.
					while (resultado.next()){				
						resp = getPersonaFromResultSet(resultado);	
					}
					}catch(SQLException e) {
						e.printStackTrace();
					}		
					return resp;
					}
			
			public static int cantidad(String dato) {
				// dato recibido por parametro es el dato a buscar
				ResultSet resultado=null;
				int resul=0;
				try {
					PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(CONSULTA);
					sentencia.setString(1,dato);
					resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ?sta variable de nombre resultado.
					while (resultado.next()){				
						resul = resultado.getInt("CANTIDAD");	
					}
					}catch(SQLException e) {
						e.printStackTrace();
					}
				return resul;
			}
					
		// Obtenemos un objeto del tipo Persona que viene en un resultado de una consulta
		private static Persona getPersonaFromResultSet(ResultSet resultado) throws SQLException {
						
			int idPersona = resultado.getInt("IDPERSONA");		
			String nombre = resultado.getString("NOMBRE");
			Date fecha_ing = resultado.getDate("FECHAINGRESO");
			Date fecha_nac = resultado.getDate("FECHANACIMIENTO");
		    TipoClase tipo = TipoClase.valueOf(resultado.getString("TIPOCLASE"));
		    Profesor prof =DAOProfesor.buscarProfesor(resultado.getInt("IDPROFESOR"));
						
						
			Persona pers = new Persona(idPersona, nombre, fecha_ing, fecha_nac,tipo,prof );
						
						return pers;
					}
					
		

}
