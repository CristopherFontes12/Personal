package programas;
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
		
		c1.setNombre("Harry Potter");
		c1.setCi("11111111");
		c1.getEsEmpleado(); //No entiendo como asignarle TRUE OR FALSE
		
		boolean ingreso;
		ingreso = DAOCartero.insertarCartero(c1);
		
		if(ingreso) { 
			System.out.println("1- El ingreso fue realizado con éxito: " + c1.getNombre());
		}
		
		Cartero c2 = new Cartero();
		c2.setNombre("Hermione Granger");
		c2.setCi("22222222");
		c2.getEsEmpleado();
		
		ingreso = DAOCartero.insertarCartero(c2);
		
		if(ingreso) { 
			System.out.println("1- El ingreso fue realizado con éxito: " + c2.getNombre());
		}
		
		Cartero c3 = new Cartero();
		c3.setNombre("Ron Weasley");	
		c3.setCi("33333333");
		c3.getEsEmpleado();
		
		ingreso = DAOCartero.insertarCartero(c3);
		
		if(ingreso) { 
			System.out.println("1- El ingreso fue realizado con éxito: " + c3.getNombre());
		}
		//**********INSERTAR REGISTROS**********//
		
		
		
		//**********LISTAR**********//
		ResultSet res = DAOCartero.listadoCartero();
		listarCarteros(res);
		//**********LISTAR**********//
			
		
		
		//**********MODIFICAR**********//
		c1 = DAOCartero.buscarNombre(c1.getNombre());
		c1.setCi("44444444");
		
		ingreso = DAOCartero.modificarCartero(c1);
		
		if(ingreso) { 
			System.out.println("1- La modificación fue realizada con éxito: " + c1.getIdCartero());
		}
		//**********MODIFICAR**********//
		
		
		
		//**********ELIMINAR**********//
		c3 = DAOCartero.buscarNombre(c3.getNombre());
		
		if (DAOCartero.eliminarCartero(c3)) {
			System.out.println("1- La eliminación fue realizada con exito: " + c3.getNombre());
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
			System.out.println("2- El ingreso se realizó con éxito :"+p1.getCodigo());
		}
		
		if(DAOPaquete.insertarPaquete(p2)) {
			System.out.println("2- El ingreso se realizó con éxito :"+p2.getCodigo());
		}
		
		if(DAOPaquete.insertarPaquete(p3)) {
			System.out.println("2- El ingreso se realizó con éxito :"+p3.getCodigo());
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
			System.out.println("2- La modificación fue realizada con éxito: " + p2.getIdPaquete());
		}
		//**********MODIFICAR**********//
	
		
		
		//**********LISTAR**********//
		paque=DAOPaquete.listadoPaquete();
		listarPaquetes(paque);
		//**********LISTAR**********//
	}
	
	
	
	//**********MÉTODO LISTAR 1**********//
	public static void listarCarteros(ResultSet r) throws SQLException {
		
		System.out.println("*** Listado de Carteros ***");
		
		while(r.next()) {
			System.out.println("Id Cartero: " + r.getInt("IDCARTERO"));
			System.out.println("Nombre: " + r.getString("NOMBRE"));
			System.out.println("CI: " + r.getInt("CI"));
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
