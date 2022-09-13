package ejercicio1;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

public class Principal {

	public static void main(String[] args) throws SQLException {
		DAOPersona.eliminarTodo();
		DAOProfesor.eliminarTodo();
		
		Profesor p1 = new Profesor();
		p1.setNombre("James Wilkins");
		p1.setTelefono("11223344");
		 
		
		if(DAOProfesor.insertarProfesor(p1)) { 
			System.out.println("Profesor ingreso OK"+p1.getNombre());
		}
		
		Profesor p2 = new Profesor();
		p2.setNombre("Laura Stein");
		p2.setTelefono("123123456");
		
		if(DAOProfesor.insertarProfesor(p2)) { 
			System.out.println("Profesor ingreso OK"+p2.getNombre());
		}
		
		Profesor p3 = new Profesor();
		p3.setNombre("Ana Monterroso");	
		p3.setTelefono("33221123");
		
		if(DAOProfesor.insertarProfesor(p3)) { 
			System.out.println("Profesor ingreso ok"+p3.getNombre());
		}
		
		//Muestra por consola el listado de usuarios
		ResultSet res = DAOProfesor.listadoTotal();
		listarProfesores(res);
				
		// Modificar Profesor
		// Esta funcion me busca el nombre del profesor
		// y me carga en la variable p1 el valor de la
		// id correspondiente.
		
		p1=DAOProfesor.buscarNombre(p1.getNombre());
		
		p1.setNombre("Jorge Meyer");
		
		
		
		if(DAOProfesor.modificarProfesor(p1)) { 
			System.out.println("<<<Profesor modificado con éxito>>>");
		}
		
		
		
		// Damos de baja un Profesor
		p3=DAOProfesor.buscarNombre(p3.getNombre());
		if (DAOProfesor.eliminarProfesor(p3)) {
			System.out.println("Eliminamos con exito :"+p3.getNombre());
		}
		
		//Listamos nuevamente
		res = DAOProfesor.listadoTotal();
		listarProfesores(res);
		// Le vamos a asignar los id a p1 y p2 para poder
		// dar de alta las Personas.
		p1=DAOProfesor.buscarNombre(p1.getNombre());
		p2=DAOProfesor.buscarNombre(p2.getNombre());
		
		// Creamos Personas y las damos de alta
		LinkedList<Persona> person = new LinkedList<Persona>();
		person=crearPersonas(p1,p2);
		
		for(Persona p: person) {
			if(DAOPersona.insertarPersona(p)) {
				System.out.println("Alta ok :"+p.getNombre());
			}
		}
		
		
		
		
		LinkedList<Persona> resul_list=DAOPersona.listadoPersona();
		listarPersonas(resul_list);
		
		
		// Modificar un dato de dos Personas
		Persona m2;
		m2=person.get(1);
		m2=DAOPersona.buscarNombre(m2.getNombre());
		m2.setFechaNacimiento(Date.valueOf("1964-04-24"));
		if(DAOPersona.modificarPersona(m2)) {
			System.out.println("Modificado OK "+m2.getNombre());
		}
		
		
		m2=person.get(5);
		m2=DAOPersona.buscarNombre(m2.getNombre());
		m2.setTipo(TipoClase.MENSUAL);
		if(DAOPersona.modificarPersona(m2)) {
			System.out.println("Modificado OK "+m2.getNombre());
		}
		
		resul_list=DAOPersona.listadoPersona();
		listarPersonas(resul_list);
		System.out.println();
		System.out.println("<<<<<< Consulta Final >>>>>>>>");
		System.out.println();
		System.out.print("La cantidad de clases por dia son :");
		System.out.println(DAOPersona.cantidad("PORDIA"));
		
		System.out.print("La cantidad de clases por mes son :");
		System.out.println(DAOPersona.cantidad("MENSUAL"));
		
		System.out.print("La cantidad de clases por semana son :");
		System.out.println(DAOPersona.cantidad("SEMANAL"));
		
		
		
		
		
	}
	// Metodo o funcion para listar los profesores
	public static void listarProfesores(ResultSet r) throws SQLException {
		System.out.println("--- Listado de Profesores ---");
		while(r.next()) {
			System.out.print("Nombre: " + r.getString("NOMBRE")+" -- ");
			System.out.print("Telefono: "+r.getString("TELEFONO")+" -- ");
			System.out.println("Id : " + r.getInt("IDPROFESOR"));
		}
		System.out.println("=================================");
	}
	
	// Metodo o funcion para listar las Personas
	public static void listarPersonas(LinkedList<Persona> lista) {
		System.out.println("*** Listado de PERSONAS ***");
		for(Persona p: lista) {
			System.out.print(p.getIdPersona()+" -- ");
			System.out.print(p.getNombre()+" -- ");
			System.out.print(p.getFechaIngreso()+" -- ");
			System.out.print(p.getFechaNacimiento()+" -- ");
			System.out.print(p.getTipo()+" -- ");
			System.out.println(p.getProfesor().getNombre()+" >> ");
			
		}
		
		// otro metodo para imprimir podria ser :
		// for(Persona p: lista) {
		//    System.out.println(p);// utilizando el metodo toString de
		                            // la clase 
		// }
		
		
	}


	public static LinkedList<Persona> crearPersonas(Profesor p1,Profesor p2) {
		LinkedList<Persona> persona = new LinkedList<Persona>();
		Persona m1=new Persona("Oscar Correa",Date.valueOf("2018-05-02"),Date.valueOf("1995-11-09"),TipoClase.PORDIA,p1);
		Persona m2=new Persona("Ana Duarte",Date.valueOf("2015-6-23"),Date.valueOf("1975-08-17"),TipoClase.PORDIA,p1);
		Persona m3=new Persona("Arturo Vidal",Date.valueOf("2019-10-12"),Date.valueOf("1991-07-08"),TipoClase.MENSUAL,p1);
		Persona m4=new Persona("Claudio Gallo",Date.valueOf("2018-4-30"),Date.valueOf("2001-03-26"),TipoClase.SEMANAL,p1);
		Persona m5=new Persona("Hugo Rivas",Date.valueOf("2016-09-25"),Date.valueOf("1980-01-24"),TipoClase.PORDIA,p2);
		Persona m6=new Persona("Laura Canosa",Date.valueOf("2021-01-5"),Date.valueOf("1987-07-11"),TipoClase.PORDIA,p2);
		Persona m7=new Persona("Karina Rojas",Date.valueOf("2017-06-26"),Date.valueOf("1986-06-29"),TipoClase.MENSUAL,p2);
		Persona m8=new Persona("Marta Fumero",Date.valueOf("2017-08-20"),Date.valueOf("1982-10-02"),TipoClase.SEMANAL,p1);
		Persona m9=new Persona("Elbio Bruno",Date.valueOf("2014-12-5"),Date.valueOf("2005-11-11"),TipoClase.PORDIA,p2);
		Persona m10=new Persona("Carlos Grauer",Date.valueOf("2020-10-13"),Date.valueOf("1968-03-28"),TipoClase.SEMANAL,p1);
		Persona m11=new Persona("Gustavo Jodal",Date.valueOf("2016-12-28"),Date.valueOf("1994-08-22"),TipoClase.PORDIA,p1);
		Persona m12=new Persona("Brian Corbo",Date.valueOf("2012-09-22"),Date.valueOf("1997-05-30"),TipoClase.MENSUAL,p1);
		Persona m13=new Persona("Enzo Matei",Date.valueOf("2018-09-5"),Date.valueOf("1999-04-27"),TipoClase.MENSUAL,p1);
		Persona m14=new Persona("William Lauson",Date.valueOf("2017-09-12"),Date.valueOf("1995-07-27"),TipoClase.MENSUAL,p1);
		persona.add(m1);
		persona.add(m2);
		persona.add(m3);
		persona.add(m4);
		persona.add(m5);
		persona.add(m6);
		persona.add(m7);
		persona.add(m8);
		persona.add(m9);
		persona.add(m10);
		persona.add(m11);
		persona.add(m12);
		persona.add(m13);
		persona.add(m14);
		
		return persona;
	}

}
