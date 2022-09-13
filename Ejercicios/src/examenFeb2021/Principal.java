package examenFeb2021;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;


import examenFeb2021.Vendedor.Zona;


public class Principal {

	public static void main(String[] args) throws SQLException {
		
		
		DAOCliente.eliminarTodo();
		DAOVendedor.eliminarTodo();
		
		
		Vendedor v1 = new Vendedor();
		v1.setNombre("Alfonsina");
		v1.setZona(Zona.NORTE);
		
		Vendedor v2 = new Vendedor();
		v2.setNombre("Naia");
		v2.setZona(Zona.OESTE);
		
		Vendedor v3 = new Vendedor();
		v3.setNombre("Lorenzo");
		v3.setZona(Zona.ESTE);
		
		Vendedor v4 = new Vendedor();
		v4.setNombre("Cristian");
		v4.setZona(Zona.SUR);
		
		Vendedor v5 = new Vendedor();
		v5.setNombre("Joaquin");
		v5.setZona(Zona.ESTE);
		
		try{
			DAOVendedor.insertarVendedores(v1);
			DAOVendedor.insertarVendedores(v2);
			DAOVendedor.insertarVendedores(v3);
			DAOVendedor.insertarVendedores(v4);
			DAOVendedor.insertarVendedores(v5);
			System.out.println("<<<<<<Los vendedores fueron insertados con éxito>>>>>>");
			System.out.println("------------------------------------------------------");
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("<<<<<<Los vendedores no fueron insertados >>>>>>");
			System.out.println("------------------------------------------------------");
		}
		ResultSet rsVendedor = DAOVendedor.listadoVendedores();
		listadoVendedores(rsVendedor);
		
		
		//Modificar vendedor
		v2=DAOVendedor.buscarNombre(v2.getNombre());
		
		v2.setNombre("Nahia");
		
		if(DAOVendedor.modificarVendedor(v2)) { 
			System.out.println("<<<<<<El vendedor fue modificado con éxito, nombre del Vendedor: " + v2.getNombre() + ">>>>>>");
			System.out.println("------------------------------------------------------");
		}
		
		
		//Eliminar vendedor
		v5=DAOVendedor.buscarNombre(v5.getNombre());
		try {
			DAOVendedor.eliminarVendedores(v5);
		}catch(Exception e) {
			e.printStackTrace();
		}
		 
		ResultSet rsVendedors = DAOVendedor.listadoVendedores();
		listadoVendedores(rsVendedors);
		System.out.println("--------------------------------------------------------");
		
		v1=DAOVendedor.buscarNombre(v1.getNombre());
		v2=DAOVendedor.buscarNombre(v2.getNombre());
		v3=DAOVendedor.buscarNombre(v3.getNombre());
		v4=DAOVendedor.buscarNombre(v4.getNombre());
		v5=DAOVendedor.buscarNombre(v5.getNombre());
		
		
		Cliente c1 = new Cliente();
		c1.setNombre("Lautro");
		c1.setFechaIngreso(Date.valueOf("2021-02-19"));
		c1.setCiudad("Montevideo");
		c1.setVendedor(v1);
		
		Cliente c12 = new Cliente();
		c12.setNombre("Jota C");
		c12.setFechaIngreso(Date.valueOf("2020-04-10"));
		c12.setCiudad("Durazno");
		c12.setVendedor(v1);
		
		Cliente c13 = new Cliente();
		c13.setNombre("Ana Laura");
		c13.setFechaIngreso(Date.valueOf("2019-05-24"));
		c13.setCiudad("Rivera");
		c13.setVendedor(v1);
		
		Cliente c2 = new Cliente();
		c2.setNombre("Esteban");
		c2.setFechaIngreso(Date.valueOf("2021-01-08"));
		c2.setCiudad("Durazno");
		c2.setVendedor(v2);
		
		Cliente c3 = new Cliente();
		c3.setNombre("Agustina");
		c3.setFechaIngreso(Date.valueOf("2021-01-26"));
		c3.setCiudad("Paso de los Toros");
		c3.setVendedor(v1);
		
		Cliente c4 = new Cliente();
		c4.setNombre("Delina");
		c4.setFechaIngreso(Date.valueOf("2021-02-05"));
		c4.setCiudad("Chuy");
		c4.setVendedor(v3);
		
		
		
		try {
			DAOCliente.insertarClientes(c1);
			DAOCliente.insertarClientes(c2);
			DAOCliente.insertarClientes(c3);
			DAOCliente.insertarClientes(c4);
			DAOCliente.insertarClientes(c12);
			DAOCliente.insertarClientes(c13);
			
			System.out.println("<<<<<<Los clientes fueron insertados con éxito>>>>>>");
			System.out.println("------------------------------------------------------");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("<<<<<<Los clientes no fueron insertados>>>>>>");
			System.out.println("------------------------------------------------------");
		}
		ResultSet result = DAOCliente.listadoClientes();
		listadoClientes(result);

		c1=DAOCliente.buscarNombre(c1.getNombre());
		
		c1.setNombre("Lautaro");
		
		//Modificar Cliente
		if(DAOCliente.modificarCliente(c1)) { 
			System.out.println("<<<El Cliente fue modificado con éxito>>>");
		}
		
		
		//Eliminar Cliente
		c4=DAOCliente.buscarNombre(c4.getNombre());
		if(DAOCliente.eliminarCliente(c1)) { 
			System.out.println("<<<El Cliente fue eliminado con éxito>>>");
		}
		
		System.out.println("<<<<<<Lista de clientes con valores actualizados>>>>>>");
	    result = DAOCliente.listadoClientes();
		listadoClientes(result);
		System.out.println("--------------------------------------------------------");
		
		
		System.out.println();
		System.out.println();
		System.out.print("La cantidad de Clientes del Vendedor 1 es :");
		System.out.println(DAOCliente.cantidad(1));
		
		
	}

	// Metodo o funcion para listar los Clients
		public static void listadoClientes(ResultSet r) throws SQLException {
			System.out.println("--- Listado de Clientes ---");
			while(r.next()) {
				System.out.println("Nombre: " + r.getString("NOMBRE")+ " -- Fecha de Ingreso: " + r.getDate("FECHAINGRESO")+
						" -- Ciudad: "+ r.getString("CIUDAD"));
			}
			System.out.println("---------------------------------");
		}
		// Metodo o funcion para listar los Vendedores
		public static void listadoVendedores(ResultSet rs) throws SQLException {
			System.out.println("--- Listado de Vendedores ---");
			while(rs.next()) {
				System.out.println("Nombre: " + rs.getString("NOMBRE")+ " -- Zona: " + Zona.valueOf(rs.getString("ZONA").toString()));
			}
			System.out.println("---------------------------------");
		}
}
