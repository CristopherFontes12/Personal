package controladores;

import java.sql.SQLException;
import java.util.List;

import manejadores.ManejadorClientes;
import objetos.Cliente;

public class ControladorClientes {
	
	public static boolean ingresarNuevoCliente(String nombre, String apellido, String ci){
		
		boolean pudeCrear;
		Cliente cliente = new Cliente(nombre, apellido, ci);
		
		try{
			ManejadorClientes.guardarCliente(cliente);
			pudeCrear = true;
		}
		catch (SQLException e){
			// ca va el cofigo se falla el insert
			pudeCrear = false;
		}
		
		return pudeCrear;
		
	}
	
	public static boolean existeCliente(String ci){
		
		boolean existe;
		Cliente cliente = ManejadorClientes.obtenerCliente(ci);
		
		if (cliente==null){
			existe = false;
		}
		else{
			existe = true;
		}
		
		return existe;
		
	}
	
	public List<Cliente> obtenerTodosClientes(){
		
		return ManejadorClientes.obtenerTodosClientes();
	}

}
