package logica;



import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;

//import accesoBD.DatabaseManager;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOPersona {
	
	// Primero se definen las consultas SQL y las guardo en un String
	private static final String ALL_PERSONAS = "SELECT * FROM PERSONA";	

	private static final String CUENTA_PERSONAS = "SELECT COUNT(ID_PERSONA) AS TOTAL FROM PERSONA";
	
	private static final String INSERT_PERSONA = "INSERT INTO PERSONA (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2, NOMBRE1, NOMBRE2) values (?,?,?,?,?,?)";
	
	private static final String BUSCAR_PERSONAS =  "SELECT * FROM PERSONA WHERE APELLIDO1=? AND NOMBRE1=?";
	
	private static final String BUSCAR_PERSONA_ID = "SELECT * FROM PERSONA WHERE ID_PERSONA=?";
	
	private static final String UPDATE_PERSONA = "UPDATE PERSONA SET DOCUMENTO=?, APELLIDO1=?, APELLIDO2=?, NOMBRE1=?, NOMBRE2=? WHERE ID_PERSONA=?";
	
	private static final String DELETE_PERSONA = "DELETE FROM PERSONA WHERE ID_PERSONA=?"; 
	
	
	////////////////////////////  MOSTRAR CANTIDAD DE PERSONAS Este método cuenta la cantidad de personas
	public static int cuentaPersonas() {
		int cuenta = 0;
		
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(CUENTA_PERSONAS);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				cuenta = resultado.getInt("TOTAL");
			}
			
		} catch(SQLException e) {
			e.printStackTrace();;
		}
		return cuenta;
	}
	
/////////////////////////////////////////////MOSTRAR TODAS LAS PERSONAS
	
	public static LinkedList<String> findAll(){
		
		try {
			LinkedList<String> personas = new LinkedList<String>();
			
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_PERSONAS);
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {
				String persona = resultado.getString("APELLIDO1");
				personas.add(persona);
			}
			return personas;
					
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

//////////////////////////////////////////////////////////INGRESAR PERSONA
	
	
	
	public static boolean insert(int Id, String Doc, String Ap1, String Ap2, String Nom1, String Nom2) {
		
		try {	
			 PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_PERSONA);
			 
			 statement.setInt(1, Id);
			 statement.setString(2, Doc);
			 statement.setString(3, Ap1);
			 statement.setString(4, Ap2);
			 statement.setString(5, Nom1);
			 statement.setString(6, Nom2);
			 
			 int retorno = statement.executeUpdate();
			 return retorno>0;
			 
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	
	////////////////////////////////////////PARA BUSCAR por APELLIDO1 Y NOMBRE1
	public static LinkedList<Persona> findPersona(String ape1, String nom1) {
		LinkedList<Persona> personas = new LinkedList<Persona>();
		
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_PERSONAS);
			statement.setString(1, ape1);
			statement.setString(2, nom1);
			
			ResultSet resultado = statement.executeQuery();
			
			while(resultado.next()) {

				int idPersona = resultado.getInt("ID_PERSONA");
				String documento = resultado.getString("DOCUMENTO");
				String apellido1 = resultado.getString("APELLIDO1");
				String apellido2 = resultado.getString("APELLIDO2");
				String nombre1 = resultado.getString("NOMBRE1");
				String nombre2 = resultado.getString("NOMBRE2");
				
				Persona p = new Persona(idPersona,documento,apellido1,apellido2,nombre1,nombre2);
				
				personas.add(p);
				System.out.println(p);
			}
		
		return personas;
				
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}
		}

//////////////////////////////////////////////BUSCAR PERSONA POR ID_PERSONA
	
	public static Persona findPersonaID(int id) {
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_PERSONA_ID);	
			statement.setInt(1,id);
			ResultSet resultado = statement.executeQuery();
			Persona persona=null;
			while(resultado.next()) {

				int idPersona = resultado.getInt("ID_PERSONA");
				String documento = resultado.getString("DOCUMENTO");
				String apellido1 = resultado.getString("APELLIDO1");
				String apellido2 = resultado.getString("APELLIDO2");
				String nombre1 = resultado.getString("NOMBRE1");
				String nombre2 = resultado.getString("NOMBRE2");
				persona = new Persona(idPersona,documento,apellido1,apellido2,nombre1,nombre2);				
			}
			
		return persona;
				
	} catch (SQLException e) {
		e.printStackTrace();
		return null;
	}	
}
	
	
	//////////////////////////////////////////////////////////UPDATE PERSONA
	
	public static boolean actualizar (Persona persona, int id) {
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_PERSONA);
			
			
			statement.setString(1, persona.getDocumento());
			statement.setString(2, persona.getApellido1());
			statement.setString(3, persona.getApellido2());
			statement.setString(4, persona.getNombre1());
			statement.setString(5, persona.getNombre2());
			statement.setInt(6, id);
			
			int retorno = statement.executeUpdate();
			
			return retorno > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	//////////////////////////////////////////////////////////////DELETE PERSONA
	
	public static boolean delete(int id_persona) {
		try {
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_PERSONA);
			statement.setInt(1,id_persona);
			
			int retorno = statement.executeUpdate();
			return retorno>0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}	
		
	}
}
	



			

