package programas;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class DAOPaquete {

	//**********SENTENCIAS SQL**********//
	private static final String LISTAR_P = "SELECT * FROM PAQUETE";
		
	private static final String INSERTAR_P = "INSERT INTO PAQUETE (IDPAQUETE, CODIGO, PESO, IDCARTERO) VALUES (?,?,?,?)";
		
	private static final String ELIMINAR_P = "DELETE FROM PAQUETE WHERE IDPAQUETE = ?";
		
	private static final String MODIFICAR_P = "UPDATE PAQUETE SET CODIGO=?, PESO=?, IDCARTERO=? WHERE IDPAQUETE = ?";
		
	private static final String MAX_IDPAQUETE="SELECT MAX(IDPAQUETE)FROM PAQUETE";
		
	private static final String BUSCAR_CODIGO="SELECT * FROM PAQUETE WHERE CODIGO=?";
		
	private static final String BORRAR_TODO="DELETE FROM PAQUETE";
	//**********FIN SENTENCIAS SQL**********//
	
	
	
	//**********MÉTODO PARA LISTAR**********//
	public static LinkedList<Paquete> listadoPaquete() {
		
		ResultSet resultado=null;
		LinkedList<Paquete> Paquetes = new LinkedList<Paquete>();
		
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(LISTAR_P);
			resultado = sentencia.executeQuery(); 
            
			while (resultado.next()){
				  Paquete Paquete = getPaqueteFromResultSet(resultado);
				  Paquetes.add(Paquete);							
			}
			
			return Paquetes;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			return null;
			
		}		
		
	}
	//**********MÉTODO PARA LISTAR**********//	

	
	
	//**********MÉTODO PARA INSERTAR**********//	
	public static boolean insertarPaquete(Paquete v) {
	
		int resultado = 0;      
		
		try {
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(INSERTAR_P);
	    	
			//Esto lo usamos para saber cuál es el máximo ID que tiene nuestra tabla más uno.
			int idMaximo = obtenerID()+1; 
			
			sentencia.setInt( 1, idMaximo); 
			sentencia.setInt( 2, v.getCodigo());
			sentencia.setFloat( 3, v.getPeso());
			sentencia.setInt( 4, v.getCartero().getIdCartero());
				
			//Ejecuta la consulta SQL y devuelve la lista en la variable resultado. 
			resultado = sentencia.executeUpdate(); 
		
			return resultado > 0;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
			
		}	
		
		return false;
		
	}
	//**********MÉTODO PARA INSERTAR**********//		
			
	
	
	//**********MÉTODO PARA ELIMINAR**********//
	public static boolean eliminarPaquete(Paquete v) {
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(ELIMINAR_P);
			sentencia.setInt( 1, v.getIdPaquete()); 
			
			sentencia.executeUpdate(); 
			return true;
			
		}catch(SQLException e) {
			
			e.printStackTrace();
		
		}		
		
		return false;
	
	}
	//**********MÉTODO PARA ELIMINAR**********//
	
	
	
	//**********MÉTODO PARA ELIMINAR TODO**********//
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
	//**********MÉTODO PARA ELIMINAR TODO**********//
	
	
	
	//**********MÉTODO PARA MODIFICAR**********//
	public static boolean modificarPaquete(Paquete v) {
		
		int resultado = 0;      
		
		try {
			
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MODIFICAR_P);
						
			//Signos de los SET
			sentencia.setInt( 1, v.getCodigo());
			sentencia.setFloat( 2, v.getPeso());
			sentencia.setInt( 3, v.getCartero().getIdCartero());
			
			//Singno del WHERE		
			sentencia.setInt(4, v.getIdPaquete());
							
			resultado = sentencia.executeUpdate();
			return resultado > 0;
			
		}catch(SQLException e) {
		
			e.printStackTrace();
		
		}		
		
		return false;
	
	}
	//**********MÉTODO PARA MODIFICAR**********//
	
	
					
	//**********MÉTODO PARA EL ÚLTIMO ID INGRESADO**********//
	public static int obtenerID() {
		
		int res=0;
		ResultSet resultado = null;      
		
		try {
		
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(MAX_IDPAQUETE);
		    resultado = sentencia.executeQuery();
						    
			while (resultado.next()){				
				res = resultado.getInt(1);
			}			    
						    
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		
					
		return res;
	}
	//**********MÉTODO PARA EL ÚLTIMO ID INGRESADO**********//
	
	
	
					
	//**********MÉTODO PARA BUSCATR POR**********//
	public static Paquete buscarCodigo(int codigo) {

		ResultSet resultado = null;
		Paquete resp = null;
	
		try {
		
			PreparedStatement sentencia = DatabaseManager.getConnection().prepareStatement(BUSCAR_CODIGO);
			sentencia.setInt(1,codigo);
			resultado = sentencia.executeQuery(); 
		
			while (resultado.next()){				
				resp = getPaqueteFromResultSet(resultado);	
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}		
		return resp;
	}
	//**********MÉTODO PARA BUSCATR POR**********//
					
	
	//**********OBTENEMOS UN OBJETO**********//
	private static Paquete getPaqueteFromResultSet(ResultSet resultado) throws SQLException {

		int idPaquete = resultado.getInt("IDPAQUETE");		
		int codigo = resultado.getInt("CODIGO");
		float peso = resultado.getFloat("PESO");
		
		Cartero cart = DAOCartero.buscarCartero(resultado.getInt("IDCARTERO"));


		Paquete paqu = new Paquete(idPaquete, codigo, peso, cart);

		return paqu;
		
	}


}
