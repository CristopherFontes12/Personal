package examenFeb2021;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;





public class DAOCliente {
	//**********SENTENCIAS SQL**********//
		private static final String LISTAR = "SELECT * FROM CLIENTES";
			
		private static final String INSERTAR = "INSERT INTO CLIENTES (IDCLIENTE, NOMBRE, FECHAINGRESO, CIUDAD, IDVENDEDOR) VALUES (?,?,?,?,?)";
			
		private static final String ELIMINAR = "DELETE FROM CLIENTES WHERE IDCLIENTE = ?";
			
		private static final String MODIFICAR = "UPDATE CLIENTES SET NOMBRE=?, FECHAINGRESO=?, CIUDAD=?, IDVENDEDOR=? WHERE IDCLIENTE = ?";
			
		private static final String MAX_IDCLIENTE="SELECT MAX(IDCLIENTE) FROM CLIENTES";
			
		private static final String BUSCAR_ID="SELECT * FROM CLIENTES WHERE IDCLIENTE = ?";
		
		private static final String BUSCAR_IDVENDEDOR="SELECT * FROM CLIENTES WHERE IDVENDEDOR =?";
		
		private static final String BUSCAR_NOMBRE="SELECT * FROM CLIENTES WHERE NOMBRE=?";
			
		private static final String BORRAR_TODO="DELETE FROM CLIENTES";
		
		private static final String CONSULTA = "SELECT COUNT(*) AS CANTIDAD FROM CLIENTES WHERE IDVENDEDOR=?";
		//**********FIN SENTENCIAS SQL**********//
		
		
		
		//**********MÉTODO PARA LISTAR**********//
		public static ResultSet listadoClientes() {
			
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
		public static boolean insertarClientes(Cliente cliente) {
		
			int resultado = 0;
			try {
				PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(INSERTAR);
		    	
				//Esto lo usamos para saber cuál es el máximo ID que tiene nuestra tabla y le suma uno.
				int idMaximoCliente = obtenerID()+1;
				
				sentencia.setInt(1, idMaximoCliente); 
				sentencia.setString( 2, cliente.getNombre());
				sentencia.setDate( 3, cliente.getFechaIngreso());
				sentencia.setString( 4, cliente.getCiudad());
				sentencia.setInt( 5, cliente.getVendedor().getIdVendedor());
				//Ejecuta la consulta SQL y devuelve la lista en la variable resultado. 
				resultado = sentencia.executeUpdate(); 
			
				return resultado > 0;
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}	
			
			return false;
			
		}
			
				
		
		
		//**********MÉTODO PARA ELIMINAR**********//
		public static boolean eliminarCliente(Cliente cliente) {
			
			try {
				
				PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(ELIMINAR);
				sentencia.setInt( 1, cliente.getIdCliente());
				
				sentencia.executeUpdate(); 
				System.out.println("Se elimino correctamente el Cliente. Nombre del Cliente: " + cliente.getNombre());
				return true;
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				System.out.println("No se pudo eliminar al cliente");
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
		public static boolean modificarCliente(Cliente cliente) {
			
			int resultado = 0;      
			
			try {
				
				PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(MODIFICAR);
							
				//Signos de los SET
				sentencia.setString( 1, cliente.getNombre());
				sentencia.setDate(2, cliente.getFechaIngreso());
				sentencia.setString( 3, cliente.getCiudad());
				sentencia.setInt(4, cliente.getVendedor().getIdVendedor());
				sentencia.setInt(5, cliente.getIdCliente());
								
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
			
				PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(MAX_IDCLIENTE);
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
		public static Cliente buscarNombre(String nombre) {

			ResultSet resultado = null;
			Cliente cliente = null;
		
			try {
			
				PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(BUSCAR_NOMBRE);
				sentencia.setString(1,nombre);
				resultado = sentencia.executeQuery(); 
			
				while (resultado.next()){				
					cliente = getClienteFromResultSet(resultado);	
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}		
			return cliente;
		}
		//**********MÉTODO PARA BUSCAR POR**********//
			
		
		
		//**********MÉTODO PARA BUSCAR POR ID**********//
		public static Cliente buscarCliente(int id) {
			
			ResultSet resultado = null;
			Cliente clnt = null;
			
			try {
				
				PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(BUSCAR_ID);
				sentencia.setInt(1,id);
				resultado = sentencia.executeQuery(); 
				
				while (resultado.next()){				
					clnt = getClienteFromResultSet(resultado);	
				}
				
			}catch(SQLException e) {
				
				e.printStackTrace();
				
			}		
			
			return clnt;
			
		}
		//**********MÉTODO PARA BUSCAR CLIENTES**********//
		public static LinkedList<Cliente> buscarClienteVendedor() {
			ResultSet resultado=null;
			LinkedList<Cliente> clienteVendedor = new LinkedList<Cliente>();
			try {
				PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(LISTAR);
				resultado = sentencia.executeQuery(); // Ejecutar esa consulta SQL y devuelve la lista en ésta variable de nombre resultado.
	            while (resultado.next()){
					  Cliente cliente = getClienteFromResultSet(resultado);
					  clienteVendedor.add(cliente);		
									
				}
				return clienteVendedor;
			}catch(SQLException e) {
				e.printStackTrace();
				return null;
			}		
			
		}
		//**********MÉTODO PARA BUSCAR POR IDVENDEDOR**********//
				public static Cliente buscarClienteVendedor(int id) {
					
					ResultSet resultado = null;
					Cliente clnt = null;
					
					try {
						
						PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(BUSCAR_IDVENDEDOR);
						sentencia.setInt(1,id);
						resultado = sentencia.executeQuery(); 
						
						while (resultado.next()){				
							clnt = getClienteFromResultSet(resultado);	
						}
						
					}catch(SQLException e) {
						
						e.printStackTrace();
						
					}		
					
					return clnt;
					
				}
				//**********MÉTODO PARA BUSCAR POR IDVENDEDOR**********//
				
				// Metodo para consultar la cantidad de clientes de un vendedor pasado por parametro
				public static int cantidad(int dato) {
					int res = 0;
					ResultSet resultado = null;
					try {
						PreparedStatement sentencia = dataBaseManager.getConnection().prepareStatement(CONSULTA);
						sentencia.setInt(1, dato);
						resultado = sentencia.executeQuery();

						while (resultado.next()) {
							res = resultado.getInt("CANTIDAD");
						}

					} catch (SQLException e) {
						e.printStackTrace();
					}

					return res;
					
				}	

				
		
		//**********CONSTRUIMOS UN OBJETO**********//
		private static Cliente getClienteFromResultSet(ResultSet resultado) throws SQLException {
			
			int idCliente= resultado.getInt("IDCLIENTE");		
			String nombre = resultado.getString("NOMBRE");
			Date fecha = resultado.getDate("FECHAINGRESO");
			String ciudad = resultado.getString("CIUDAD");
			Vendedor idVendedor = DAOVendedor.buscarVendedor(resultado.getInt("IDVENDEDOR"));
			
			
			Cliente client = new Cliente (idCliente, nombre, fecha, ciudad, idVendedor );
			
			return client;
			
		}
}
