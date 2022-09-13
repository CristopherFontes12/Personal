package PRU001;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Principal {

	public static void main(String[] args) throws SQLException {

		DAO_Persona.eliminarTodo();
		DAO_Profesor.eliminarTodo();

		Profesor p1 = new Profesor();
		p1.setNombre("Juan Perez");
		p1.setTelefono("54213542");
		boolean ingreso;
		ingreso = DAO_Profesor.insertarProfesor(p1);

		if (ingreso) {
			System.out.println("El usuario fue ingresado con éxito");
		}

		Profesor p2 = new Profesor();
		p2.setNombre("Carlos Diaz");
		p2.setTelefono("65484126");
		ingreso = DAO_Profesor.insertarProfesor(p2);
		if (ingreso) {
			System.out.println("El usuario fue ingresado con éxito");
		}

		Profesor p3 = new Profesor();
		p3.setNombre("Ana Godoy");
		p3.setTelefono("541265412");
		ingreso = DAO_Profesor.insertarProfesor(p3);
		if (ingreso) {
			System.out.println("El usuario fue ingresado con éxito");
		}

		// Tira por consola el listado de usuarios
		ResultSet res = DAO_Profesor.listadoProfesores();
		listarProfesores(res);

		// Modificar Responsable
		// Esta funcion me busca el nombre del responsable
		// y me carga en r1 el valor de la id correspondiente

		p1 = DAO_Profesor.buscarProfpNombre(p1.getNombre());

		p1.setNombre("ELSA PAYO");
		// Faltaba colocar el id del item o registro a modificar

		ingreso = DAO_Profesor.modificarProfesor(p1);
		if (ingreso) {
			System.out.println("<<<El usuario fue modificado con éxito>>>");
		}

		// Damos de baja un Responsable
		p3 = DAO_Profesor.buscarProfpNombre(p3.getNombre());
		if (DAO_Profesor.eliminarProfesor(p3)) {
			System.out.println("Eliminamos con exito :" + p3.getNombre());
		}

		// Listamos nuevamente
		res = DAO_Profesor.listadoProfesores();
		listarProfesores(res);
		
		// Le vamos a asignar los id a r1 y r2 para poder
		// dar de alta las mascotas.
		p1 = DAO_Profesor.buscarProfpNombre(p1.getNombre());
		p2 = DAO_Profesor.buscarProfpNombre(p2.getNombre());

		// Creamos Mascotas y las damos de alta
		Persona a1 = new Persona();
		a1.setIdPersona(1);
		a1.setNombre("Persona1");
		a1.setFechaIngreso(Date.valueOf("2020-11-09"));
		a1.setFechaNacimiento(Date.valueOf("1990-01-01"));
		a1.setTipoclase(TipoClase.PORDIA);
		a1.setProfesor(p1);

		Persona a2 = new Persona();
		a2.setIdPersona(2);
		a2.setNombre("Persona2");
		a2.setFechaIngreso(Date.valueOf("2008-11-09"));
		a2.setFechaNacimiento(Date.valueOf("1990-01-10"));
		a2.setTipoclase(TipoClase.MENSUAL);
		a2.setProfesor(p2);

		Persona a3 = new Persona();
		a3.setIdPersona(3);
		a3.setNombre("Persona3");
		a3.setFechaIngreso(Date.valueOf("2020-10-22"));
		a3.setFechaNacimiento(Date.valueOf("1990-12-10"));
		a3.setTipoclase(TipoClase.SEMANAL);
		a3.setProfesor(p1);

		if (DAO_Persona.insertarPersona(a1)) {
			System.out.println("Alta ok :" + a1.getNombre());
		}

		if (DAO_Persona.insertarPersona(a2)) {
			System.out.println("Alta ok :" + a2.getNombre());
		}
		if (DAO_Persona.insertarPersona(a3)) {
			System.out.println("Alta ok :" + a3.getNombre());
		}

		LinkedList<Persona> perso = DAO_Persona.listadoPersona();
		listarPersonas(perso);

		// Modificar un dato de una Mascota
		a2 = DAO_Persona.buscarNombre(a2.getNombre());
		a2.setFechaNacimiento(Date.valueOf("1999-04-24"));
		if (DAO_Persona.modificarPersona(a2)) {
			System.out.println("Modificado OK " + a2.getNombre());
		}

		perso = DAO_Persona.listadoPersona();
		listarPersonas(perso);
		
		
		System.out.print("La cantidad de persona por dia son :");
		System.out.println(DAO_Persona.cantidad("PORDIA"));
		System.out.print("La cantidad de persona por Semana son :");
		System.out.println(DAO_Persona.cantidad("SEMANAL"));
		System.out.print("La cantidad de persona por Mes son :");
		System.out.println(DAO_Persona.cantidad("MENSUAL"));
	}

	// Metodo o funcion para listar los PROFESORES
	public static void listarProfesores(ResultSet r) throws SQLException {
		System.out.println("--- Listado de Responsables ---");
		while (r.next()) {
			System.out.println("Nombre: " + r.getString("NOMBRE"));
			System.out.println("Telefono: " + r.getString("TELEFONO"));
			System.out.println("Id Profesor: " + r.getInt("IDPROFESOR"));
		}
		System.out.println("=================================");
	}

	// Metodo o funcion para listar las mascotas
	public static void listarPersonas(LinkedList<Persona> lista) {
		for (Persona m : lista) {
			System.out.print(m.getIdPersona() + " -- ");
			System.out.print(m.getNombre() + " -- ");
			System.out.print(m.getFechaIngreso() + " -- ");
			System.out.print(m.getFechaNacimiento() + " -- ");
			System.out.println(m.getTipoclase());
			
			
			System.out.println(m.getProfesor().getNombre() + " >> ");

		}

		

	}
	
	

}
