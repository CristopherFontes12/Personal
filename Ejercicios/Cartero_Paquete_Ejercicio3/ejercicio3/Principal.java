package ejercicio3;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Principal {

	public static void main(String[] args) throws SQLException {
		
		
		DAOPaquete.eliminarTodo();
		DAOCartero.eliminarTodo();
		
		
		
		//**********INSERTAR REGISTROS**********//
		Cartero c1 = new Cartero();
		
		c1.setNombre("Mister Postman");
		c1.setCi(12345678);
		c1.setEsEmpleado(true); //No entiendo como asignarle TRUE OR FALSE
		
		
		
		if(DAOCartero.insertarCartero(c1)) { 
			System.out.println("Ingreso Cartero ok: " + c1.getNombre());
		}
		
		Cartero c2 = new Cartero();
		c2.setNombre("Marta Sanchez");
		c2.setCi(21112223);
		c2.setEsEmpleado(true);
		
		
		
		if(DAOCartero.insertarCartero(c2)) { 
			System.out.println("Ingreso Cartero ok: " + c2.getNombre());
		}
		
		Cartero c3 = new Cartero();
		c3.setNombre("Elbis Tomas");	
		c3.setCi(34445551);
		c3.setEsEmpleado(false);
		
		
		
		if(DAOCartero.insertarCartero(c3)) { 
			System.out.println("Ingreso Cartero ok: " + c3.getNombre());
		}
		//**********INSERTAR REGISTROS**********//
		
		
		
		//**********LISTAR**********//
		ResultSet res = DAOCartero.listadoCartero();
		listarCarteros(res);
		//**********LISTAR**********//
			
		
		
		//**********MODIFICAR**********//
		c1 = DAOCartero.buscarNombre(c1.getNombre());
		c1.setCi(43235452);
		
		
		
		if(DAOCartero.modificarCartero(c1)) { 
			System.out.println("Modificación CARTERO ok: " + c1.getIdCartero());
		}
		//**********MODIFICAR**********//
		
		
		
		//**********ELIMINAR**********//
		c3 = DAOCartero.buscarNombre(c3.getNombre());
		
		if (DAOCartero.eliminarCartero(c3)) {
			System.out.println("Eliminación Cartero ok: " + c3.getNombre());
		}
		//**********ELIMINAR**********//
		
		
		
		//**********LISTAR**********//
		res = DAOCartero.listadoCartero();
		listarCarteros(res);
		//**********LISTAR**********//
		
		
		
		//**********OBTENER DATOS**********//
		c1=DAOCartero.buscarNombre(c1.getNombre());
		c2=DAOCartero.buscarNombre(c2.getNombre());
		//**********OBTENER DATOS**********//
		
		//**********INSERTAR REGISTROS**********//
		Paquete p1 = new Paquete();
		p1.setCodigo(100);
		p1.setPeso((float) 2.2);
		p1.setCartero(c1);
		
		Paquete p2 = new Paquete();
		p2.setCodigo(101);
		p2.setPeso((float) 3.2);
		p2.setCartero(c2);
		
		Paquete p3 = new Paquete();
		p3.setCodigo(102);
		p3.setPeso((float) 1.2);
		p3.setCartero(c2);
		//Si hubiera fecha: m2.setFechaNacimiento(Date.valueOf("2018-04-24"));
		
		
		if(DAOPaquete.insertarPaquete(p1)) {
			System.out.println("Ingreso Paquete OK del coidgo :"+p1.getCodigo());
		}
		
		if(DAOPaquete.insertarPaquete(p2)) {
			System.out.println("Ingreso Paquete OK del coidgo :"+p2.getCodigo());
		}
		
		if(DAOPaquete.insertarPaquete(p3)) {
			System.out.println("Ingreso Paquete OK del coidgo :"+p3.getCodigo());
		}
		//**********INSERTAR REGISTROS**********//
		
		
		
		//**********LISTAR**********//
		LinkedList<Paquete> paque=DAOPaquete.listadoPaquete();
		listarPaquetes(paque);
		//**********LISTAR**********//
		
		
		
		//**********MODIFICAR**********//
		p2=DAOPaquete.buscarCodigo(p2.getCodigo());
		p2.setPeso((float) 4.3);
		
		if(DAOPaquete.modificarPaquete(p2)) {
			System.out.println("Modificación fue realizada con éxito: " + p2.getIdPaquete());
		}
		//**********MODIFICAR**********//
	
		
		
		//**********LISTAR**********//
		paque=DAOPaquete.listadoPaquete();
		listarPaquetes(paque);
		//**********LISTAR**********//
		
		System.out.println("----- Consulta -------");
		System.out.println();
		System.out.print("La cantidad de paquetes del Cartero 2 es :");
		System.out.println(DAOPaquete.cantidad(2));
	}
	
	
	
	//**********MÉTODO LISTAR 1**********//
	public static void listarCarteros(ResultSet r) throws SQLException {
		
		System.out.println("*** Listado de Carteros ***");
		
		while(r.next()) {
			System.out.print("Id Cartero: " + r.getInt("IDCARTERO")+" - ");
			System.out.print("Nombre: " + r.getString("NOMBRE")+" - ");
			System.out.print("CI: " + r.getInt("CEDULA")+" - ");
			System.out.println("Es Empleado: " + r.getBoolean("ESEMPLEADO"));
		}
		
		System.out.println("=================================");
	}
	//**********MÉTODO LISTAR 1**********//
	
	
	
	//**********MÉTODO LISTAR 2**********//
	public static void listarPaquetes(LinkedList<Paquete> lista) {
		
		for(Paquete p: lista) {
			System.out.print(p.getIdPaquete() + " -- ");
			System.out.print(p.getCodigo() + " -- ");
			System.out.print(p.getPeso() + " -- ");
			System.out.print(p.getCartero().getIdCartero() + " -- ");
			System.out.println(p.getCartero().getNombre() + " >> ");	
		}
		
	}
	//**********MÉTODO LISTAR 2**********//

}
