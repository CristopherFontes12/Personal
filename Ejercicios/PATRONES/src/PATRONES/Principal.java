package PATRONES;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Principal {

	public static void main(String[] args)throws SQLException {

			DAO_Programa.eliminarTodo();
			DAO_Desarrollador.eliminarTodo();

			Desarrollador d1 = new Desarrollador();
			d1.setCedula(11111111);
			d1.setNombre("Sebastian Merenyi");
			d1.setFechaIngreso(Date.valueOf("1990-01-01"));
			d1.setCategoria(Categoria.JUNIOR);
			boolean ingreso;
			ingreso = DAO_Desarrollador.insertarDesarrollador(d1);
			if (ingreso) {
				System.out.println("El desarrollador fue ingresado con éxito");
			}

			Desarrollador d2 = new Desarrollador();
			d2.setCedula(22222222);
			d2.setNombre("Juan Perez");
			d2.setFechaIngreso(Date.valueOf("1980-02-02"));
			d2.setCategoria(Categoria.MASTER);
			ingreso = DAO_Desarrollador.insertarDesarrollador(d2);
			if (ingreso) {
				System.out.println("El desarrollador fue ingresado con éxito");
			}

			Desarrollador d3 = new Desarrollador();
			d3.setCedula(33333333);
			d3.setNombre("Julio Pereira");
			d3.setFechaIngreso(Date.valueOf("1970-03-03"));
			d3.setCategoria(Categoria.SEMIJUNIOR);
			ingreso = DAO_Desarrollador.insertarDesarrollador(d3);
			if (ingreso) {
				System.out.println("El desarrollador fue ingresado con éxito");
			}
			
			Desarrollador d4 = new Desarrollador();
			d4.setCedula(44444444);
			d4.setNombre("Ana López");
			d4.setFechaIngreso(Date.valueOf("1970-03-03"));
			d4.setCategoria(Categoria.SENIOR);
			ingreso = DAO_Desarrollador.insertarDesarrollador(d4);
			if (ingreso) {
				System.out.println("El desarrollador fue ingresado con éxito");
			}

			// Tira por consola el listado de Desarrolladores
			ResultSet res = DAO_Desarrollador.listadoDesarrolladores();
			listarDesarrolladores(res);

			// Modificar Desarrollador
			// Esta funcion me busca el nombre del desarrollador
			// y me carga en m1 el valor de la id correspondiente

			d1 = DAO_Desarrollador.buscarNombre(d1.getNombre());
			System.out.println(d1);
			d1.setNombre("Diego Forlan");
			System.out.println(d1);
			// Faltaba colocar el id del item o registro a modificar

			ingreso = DAO_Desarrollador.modificarDesarrollador(d1);
			if (ingreso) {
				System.out.println("<<<El desarrollador fue modificado con éxito>>>");
			}

			// Damos de baja un Desarrollador
			d3 = DAO_Desarrollador.buscarNombre(d3.getNombre());
			if (DAO_Desarrollador.eliminarDesarrollador(d3)) {
				System.out.println("Eliminamos con exito :" + d3.getNombre());
			}

			// Listamos nuevamente
			res = DAO_Desarrollador.listadoDesarrolladores();
			listarDesarrolladores(res);

			// Creamos Programas y las damos de alta
			Programa p1 = new Programa();
			p1.setIdPrograma(1);
			;
			p1.setDescripcion("Juego");
			;
			p1.setFechaIngreso(Date.valueOf("2021-01-01"));
			p1.setTiempo(10);
			;
			p1.setEstado(Estado.ENDESARROLLO);
			p1.setDesarrollador(d1);;

			Programa p2 = new Programa();
			p2.setIdPrograma(2);
			;
			p2.setDescripcion("Finanzas");
			;
			p2.setFechaIngreso(Date.valueOf("2021-02-02"));
			p2.setTiempo(20);
			;
			p2.setEstado(Estado.ENESPERA);
			p2.setDesarrollador(d1);

			Programa p3 = new Programa();
			p3.setIdPrograma(3);
			;
			p3.setDescripcion("Videoconferencia");
			;
			p3.setFechaIngreso(Date.valueOf("2021-03-03"));
			p3.setTiempo(30);
			;
			p3.setEstado(Estado.FINALIZADO);
			p3.setDesarrollador(d2);

			
			if (DAO_Programa.insertarPrograma(p1)) {
				System.out.println("Alta ok :" + p1.getDescripcion());
			}

			if (DAO_Programa.insertarPrograma(p2)) {
				System.out.println("Alta ok :" + p2.getDescripcion());
			}
			if (DAO_Programa.insertarPrograma(p3)) {
				System.out.println("Alta ok :" + p3.getDescripcion());
			}

			LinkedList<Programa> pro = DAO_Programa.listadoPrograma();
			listarProgramas(pro);
			
			// Modificar un dato de una Persona
					p2 = DAO_Programa.buscarDescripcion(p2.getDescripcion());
					p2.setFechaIngreso(Date.valueOf("1993-07-21"));
					if (DAO_Programa.modificarPrograma(p2)) {
						System.out.println("Modificado OK " + p2.getDescripcion());
					}
			
			
			//Listamos nuevamente los programas
			pro = DAO_Programa.listadoPrograma();
			listarProgramas(pro);
			
		}

	// Metodo o funcion para listar los desarrolladores
	public static void listarDesarrolladores(ResultSet r) throws SQLException {
		System.out.println("--- Listado de Desarrolladores ---");
		while (r.next()) {
			System.out.println("***************************************");
			System.out.println("Cedula: " + r.getInt("CEDULA"));
			System.out.println("Nombre: " + r.getString("NOMBRE"));
			System.out.println("Fecha de ingreso: " + r.getDate("FECHAINGRESO"));
			System.out.println("Categoria: " + r.getString("CATEGORIA"));
		}
		System.out.println("=================================");
	}

	// Metodo o funcion para listar los Programas
	public static void listarProgramas(LinkedList<Programa> lista) {
		for (Programa m : lista) {
			System.out.print(m.getIdPrograma() + " -- ");
			System.out.print(m.getDescripcion() + " -- ");
			System.out.print(m.getFechaIngreso() + " -- ");
			System.out.print(m.getTiempo() + " -- ");
			System.out.print(m.getEstado() + " -- ");
			System.out.println(m.getDesarrollador().getCedula() + " >> ");

		}

	}

}
