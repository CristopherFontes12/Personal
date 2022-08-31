package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import modal.Persona;
import oracle.net.aso.s;

public class DaoPersona {

	
	
	// Parte 4 
	
	
	private static final String ALL_PERSONAS = "SELECT * FROM PERSONA";
	
	
	private static final String BUSCAR_PERSONA= "SELECT * FROM PERSONA WHERE APELLIDO1=? AND NOMBRE1=?";
	
	
	
	private static final String UPDATE_PERSONA = "UPDATE PERSONA SET DOCUMENTO=?, APELLIDO1=?, APELLIDO2=?, NOMBRE1=?, NOMBRE2=? WHERE ID_PERSONA=?";

	
	
	private static final String DELETE_PERSONA = "DELETE FROM PERSONA WHERE ID_PERSONA=?"; 


	
	
	
	// Buscar mediante el Nombre y Apellido.
	
	public static Persona findPersona(String apellido1, String nombre1) throws SQLException {
		
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(BUSCAR_PERSONA);
		statement.setString(1, apellido1);
		statement.setString(2, nombre1);
		
		ResultSet resultado = statement.executeQuery();
		
		if (resultado.next()) {
			long id = resultado.getLong("ID_PERSONA");
			String documento = resultado.getString("DOCUMENTO");
			String primerNombre = resultado.getString("NOMBRE1");
			String segundoNombre = resultado.getString("NOMBRE2");
			String primerApellido = resultado.getString("APELLIDO1");
			String segundoApellido = resultado.getString("APELLIDO2");
			Persona p = new Persona(id, documento, primerApellido, segundoApellido, primerNombre, segundoNombre);
		
			return p;		
		
		}else {
			return null;
		}		
		
	}


	
	
	public static boolean edit(Persona persona) throws SQLException {
		
		
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(UPDATE_PERSONA);

		statement.setString(1, persona.getDocumento());
		statement.setString(2, persona.getApellido1());
		statement.setString(3, persona.getApellido2());
		statement.setString(4, persona.getNombre1());
		statement.setString(5, persona.getNombre2());
		statement.setLong(6, persona.getId());
		int retorno = statement.executeUpdate();
		
		
		return retorno > 0;
			
		
	}


	
	
	public static boolean delete(int IdEmpleado) throws SQLException {
		
		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(DELETE_PERSONA);

		statement.setLong(1, IdEmpleado);
		int retorno = statement.executeUpdate();
		
		
		return retorno > 0;
		
	}
	
	
	// ALL_PERSONAS
		
		public static LinkedList<Persona> findAll() throws SQLException{
			
			
	
			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_PERSONAS);
			ResultSet resultado = statement.executeQuery();
			LinkedList<Persona> personas = new LinkedList<>();
			
			while (resultado.next()) {
				Persona p = new Persona();
				p.setId(resultado.getLong(1));
				p.setDocumento(resultado.getString(2));
				p.setNombre1(resultado.getString(3));
				p.setNombre2(resultado.getString(4));
				p.setApellido1(resultado.getString(5));
				p.setApellido2(resultado.getString(6));
				personas.add(p);
		
			}
			
			return personas;
			
			

		}
	


	
	// Pare 1 2 y 3 de la semana 3 
	
	
//	private static final String CUENTA_PERSONAS = "SELECT COUNT(ID_PERSONA) AS CUENTA FROM PERSONA";
//	
//	
//	private static final String ALL_PERSONAS = "SELECT * FROM PERSONA";
//	
//	
//	private static final String INSERT_PERSONA = "INSERT INTO PERSONA (ID_PERSONA, DOCUMENTO, APELLIDO1, APELLIDO2,NOMBRE1, NOMBRE2) VALUES (?,?,?,?,?,?)";
//	
//	
//	
//
//	
//	// CUENTA_PERSONAS
//	
//	public static int cuentaPersonas() {
//		
//		int Cuenta = 0;
//		
//		try {
//			
//			
//			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(CUENTA_PERSONAS);
//			ResultSet resultado = statement.executeQuery();
//			
//			while (resultado.next()) {
//				Cuenta = resultado.getInt("CUENTA");
//			}
//			
//		} 
//		
//		catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return Cuenta;
//	}
//	
//	
//	// ALL_PERSONAS
//	
//	public static LinkedList<String> findAll(){
//		
//	LinkedList<String> personas = new LinkedList<>();
//
//	try {
//		
//		PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(ALL_PERSONAS);
//		ResultSet resultado = statement.executeQuery();
//		
//		while (resultado.next()) {
//			
//			String persona = resultado.getString("APELLIDO1");
//			personas.add(persona);
//		}
//		
//		return personas;
//		
//		
//	} catch (SQLException e) {
//		// TODO: handle exception
//		e.printStackTrace();
//		return null;
//		
//	}
//	
//	}
//	
//	
//	
//	public static boolean insert(Persona p) {
//		
//		try {
//			
//			PreparedStatement statement = DatabaseManager.getConnection().prepareStatement(INSERT_PERSONA);
//			
//			statement.setLong(1, 200);
//			statement.setString(2, p.getDocumento());
//			statement.setString(3, p.getApellido1());
//			statement.setString(4, p.getApellido2());
//			statement.setString(5, p.getNombre1());
//			statement.setString(6, p.getNombre2());
//			
//			int Retorno = statement.executeUpdate();
//			
//			return Retorno > 0;
//			
//			
//			
//			
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return false;
//		}
//	}
//	
//	
//	
	
}
	

