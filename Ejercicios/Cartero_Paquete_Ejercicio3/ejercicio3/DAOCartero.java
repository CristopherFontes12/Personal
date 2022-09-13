package ejercicio3;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAOCartero {

	//**********SENTENCIAS SQL**********//
	private static final String LISTAR = "SELECT * FROM CARTEROS";
		
	private static final String INSERTAR = "INSERT INTO CARTEROS (IDCARTERO, NOMBRE, CEDULA, ESEMPLEADO) VALUES (?,?,?,?)";
		
	private static final String ELIMINAR = "DELETE FROM CARTEROS WHERE IDCARTERO = ?";
		
	private static final String MODIFICAR = "UPDATE CARTEROS SET NOMBRE=?, CEDULA=?, ESEMPLEADO=? WHERE IDCARTERO = ?";
		
	private static final String MAX_IDCARTERO="SELECT MAX(IDCARTERO)FROM CARTEROS";
		
	private static final String BUSCAR_ID="SELECT * FROM CARTEROS WHERE IDCARTERO = ?";
	
	private static final String BUSCAR_NOMBRE="SELECT * FROM CARTEROS WHERE NOMBRE=?";
		
	private static final String BORRAR_TODO="DELETE FROM CARTEROS";
	//**********FIN SENTENCIAS SQL**********//
	
	
	
	//**********M�TODO PARA LISTAR**********//
	public static ResultSet listadoCartero() {
		
		ResultSet resultado=null;
		
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(LISTAR);
			resultado = sentencia.executeQuery();
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}		
		
		return resultado;
		
	}
	//**********M�TODO PARA LISTAR**********//	

	
	
	//**********M�TODO PARA INSERTAR**********//	
	public static boolean insertarCartero(Cartero v) {
	
		int resultado = 0;      
		
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR);
	    	
			//Esto lo usamos para saber cu�l es el m�ximo ID que tiene nuestra tabla m�s uno.
			int idMaximo = obtenerID()+1; 
			
			sentencia.setInt( 1, idMaximo); 
			sentencia.setString( 2, v.getNombre());
			sentencia.setInt( 3, v.getCi());
			String bool="F";
			if (v.getEsEmpleado()) {
				bool="T";
			}
			sentencia.setString( 4, bool);
				
			//Ejecuta la consulta SQL y devuelve la lista en la variable resultado. 
			resultado = sentencia.executeUpdate(); 
		
			return resultado > 0;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}	
		
		return false;
		
	}
	//**********M�TODO PARA INSERTAR**********//		
			
	
	
	//**********M�TODO PARA ELIMINAR**********//
	public static boolean eliminarCartero(Cartero v) {
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR);
			sentencia.setInt( 1, v.getIdCartero()); 
			
			sentencia.executeUpdate(); 
			return true;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		
		}		
		
		return false;
	
	}
	//**********M�TODO PARA ELIMINAR**********//
	
	
	
	//**********M�TODO PARA ELIMINAR TODO**********//
	public static boolean eliminarTodo() {   
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BORRAR_TODO);
			 
			sentencia.executeUpdate(); 
			sentencia.close();
			return true;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		
		}		
		
		return false;
	
	}
	//**********M�TODO PARA ELIMINAR TODO**********//
	
	
	
	//**********M�TODO PARA MODIFICAR**********//
	public static boolean modificarCartero(Cartero v) {
		
		int resultado = 0;      
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR);
						
			//Signos de los SET
			sentencia.setString( 1, v.getNombre());
			sentencia.setInt( 2, v.getCi());
			String bool="F";
			if (v.getEsEmpleado()) {
				bool="T";
			}
			sentencia.setString( 3, bool);
			
			
			//Signo ? del WHERE	(variable de la modificacion)	
			sentencia.setInt(4, v.getIdCartero());
							
			resultado = sentencia.executeUpdate();
			return resultado > 0;
			
		}catch(SQLException e) {
		
			e.printStackTrace();
		
		}		
		
		return false;
	
	}
	//**********M�TODO PARA MODIFICAR**********//
	
	
					
	//**********M�TODO PARA EL �LTIMO ID INGRESADO**********//
	public static int obtenerID() {
		
		int res=0;
		ResultSet resultado = null;      
		
		try {
		
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MAX_IDCARTERO);
		    resultado = sentencia.executeQuery();
						    
			while (resultado.next()){				
				res = resultado.getInt(1);
			}			    
						    
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
					
		return res;
	}
	//**********M�TODO PARA EL �LTIMO ID INGRESADO**********//
	
	
	
					
	//**********M�TODO PARA BUSCAR POR**********//
	public static Cartero buscarNombre(String nombre) {

		ResultSet resultado = null;
		Cartero resp = null;
	
		try {
		
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
			sentencia.setString(1,nombre);
			resultado = sentencia.executeQuery(); 
		
			while (resultado.next()){				
				resp = getCarteroFromResultSet(resultado);	
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return resp;
	}
	//**********M�TODO PARA BUSCAR POR**********//
		
	
	
	//**********M�TODO PARA BUSCAR POR ID**********//
	public static Cartero buscarCartero(int id) {
		
		ResultSet resultado = null;
		Cartero cart = null;
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_ID);
			sentencia.setInt(1,id);
			resultado = sentencia.executeQuery(); 
			
			while (resultado.next()){				
				cart = getCarteroFromResultSet(resultado);	
			}
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}		
		
		return cart;
		
	}
	//**********M�TODO PARA BUSCAR POR ID**********//
	
	
	
	//**********CONSTRUIMOS UN OBJETO**********//
	private static Cartero getCarteroFromResultSet(ResultSet resultado) throws SQLException {
		
		int idCartero= resultado.getInt("IDCARTERO");		
		String nombre = resultado.getString("NOMBRE");
		int ci = resultado.getInt("CEDULA");
		String bool = resultado.getString("ESEMPLEADO");
		boolean esEmpleado=false;
		if (bool=="T") {
			esEmpleado=true;
		}
		
		
		Cartero cart = new Cartero (idCartero, nombre, ci, esEmpleado );
		
		return cart;
		
	}


}
