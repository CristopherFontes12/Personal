package examenFeb2021;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import examenFeb2021.Vendedor.Zona;



public class DAOVendedor {
	//**********SENTENCIAS SQL**********//
			private static final String LISTAR = "SELECT * FROM VENDEDORES";
				
			private static final String INSERTAR = "INSERT INTO VENDEDORES (IDVENDEDOR, NOMBRE, ZONA) VALUES (?,?,?)";
				
			private static final String ELIMINAR = "DELETE FROM VENDEDORES WHERE IDVENDEDOR = ?";
				
			private static final String MODIFICAR = "UPDATE VENDEDORES SET NOMBRE=?, ZONA=? WHERE IDVENDEDOR = ?";
				
			private static final String MAX_IDVENDEDOR="SELECT MAX(IDVENDEDOR)FROM VENDEDORES";
				
			private static final String BUSCAR_ID="SELECT * FROM VENDEDORES WHERE IDVENDEDOR = ?";
			
			private static final String BUSCAR_NOMBRE="SELECT * FROM VENDEDORES WHERE NOMBRE=?";
				
			private static final String BORRAR_TODO="DELETE FROM VENDEDORES";
			//**********FIN SENTENCIAS SQL**********//
			
			
			
			//**********MÉTODO PARA LISTAR**********//
			public static ResultSet listadoVendedores() {
				
				ResultSet resultado=null;
				
				try {
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(LISTAR);
					resultado = sentencia.executeQuery();
					
				}catch(SQLException e) {
					
					e.printStackTrace();
					
				}		
				
				return resultado;
				
			}
			//**********MÉTODO PARA LISTAR**********//	

			
			
			//**********MÉTODO PARA INSERTAR**********//	
			public static boolean insertarVendedores(Vendedor vendedor) {
			
				int resultado = 0;
				try {
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(INSERTAR);
			    	
					//Esto lo usamos para saber cuál es el máximo ID que tiene nuestra tabla y le suma uno.
					int idMaximoVendedor = obtenerID()+1;
					
					sentencia.setInt(1, idMaximoVendedor); 
					sentencia.setString( 2, vendedor.getNombre());
					sentencia.setString( 3, vendedor.getZona().name());
					//Ejecuta la consulta SQL y devuelve la lista en la variable resultado. 
					resultado = sentencia.executeUpdate(); 
				
					return resultado > 0;
					
				}catch(SQLException e) {
					
					e.printStackTrace();
					
				}	
				
				return false;
				
			}
			//**********FIN MÉTODO PARA INSERTAR**********//		
					
			
			
			//**********MÉTODO PARA ELIMINAR**********//
			public static boolean eliminarVendedores(Vendedor vendedor) {
				
				try {
					
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(ELIMINAR);
					sentencia.setInt( 1, vendedor.getIdVendedor());
					
					sentencia.executeUpdate(); 
					System.out.println("Se elimino correctamente un vendedor. Nombre del Vendedor: " + vendedor.getNombre());
					return true;
					
				}catch(SQLException e) {
					
					e.printStackTrace();
					System.out.println("No se pudo eliminar al vendedor");
				}		
				
				return false;
			
			}
			//**********MÉTODO PARA ELIMINAR**********//
			
			
			
			//**********MÉTODO PARA ELIMINAR TODO**********//
			public static boolean eliminarTodo() {   
				
				try {
					
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(BORRAR_TODO);
					 
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
			public static boolean modificarVendedor(Vendedor vendedor) {
				
				int resultado = 0;      
				
				try {
					
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(MODIFICAR);
								
					//Signos de los SET
					sentencia.setString( 1, vendedor.getNombre());
					sentencia.setString(2, vendedor.getZona().toString());
					sentencia.setInt( 3, vendedor.getIdVendedor());
					
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
				
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(MAX_IDVENDEDOR);
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
			
			
			
							
			//**********MÉTODO PARA BUSCAR POR**********//
			public static Vendedor buscarNombre(String nombre) {

				ResultSet resultado = null;
				Vendedor resp = null;
			
				try {
				
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
					sentencia.setString(1,nombre);
					resultado = sentencia.executeQuery(); 
				
					while (resultado.next()){				
						resp = getVendedorFromResultSet(resultado);	
					}
					
				}catch(SQLException e) {
					e.printStackTrace();
				}		
				return resp;
			}
			//**********MÉTODO PARA BUSCAR POR**********//
				
			
			
			//**********MÉTODO PARA BUSCAR POR ID**********//
			public static Vendedor buscarVendedor(int id) {
				
				ResultSet resultado = null;
				Vendedor vend = null;
				
				try {
					
					PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(BUSCAR_ID);
					sentencia.setInt(1,id);
					resultado = sentencia.executeQuery(); 
					
					while (resultado.next()){				
						vend = getVendedorFromResultSet(resultado);	
					}
					
				}catch(SQLException e) {
					
					e.printStackTrace();
					
				}		
				
				return vend;
				
			}
			//**********MÉTODO PARA BUSCAR POR ID**********//
			
			
			
			//**********CONSTRUIMOS UN OBJETO**********//
			private static Vendedor getVendedorFromResultSet(ResultSet resultado) throws SQLException {
				
				int idVendedor= resultado.getInt("IDVENDEDOR");		
				String nombre = resultado.getString("NOMBRE");
				Zona zona=Zona.valueOf(resultado.getString("ZONA").toString());
				
				Vendedor vendedor = new Vendedor (idVendedor, nombre, zona);
				
				return vendedor;
				
			}
			
}
