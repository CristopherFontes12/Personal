package logica;


import java.util.Scanner;

public class ScannerMain {

	public static void main(String[] args) {
		
		int id;
		String documento;
		String apellido1;
		String apellido2;
		String nombre1;
		String nombre2;
		Scanner input = new Scanner( System.in);
		int opcion = 0;
		
		while (opcion!=5)
		{
			System.out.println("*--------- 1 - Ingresar ---------*");
			System.out.println("*--------- 2 - Borrar ---------*");
			System.out.println("*--------- 3 - Actualizar ---------*");
			System.out.println("*--------- 4 - Mostrar datos ---------*");
			System.out.println("*--------- 5 - Salir ---------*");
			System.out.println("---------------------");
			
			System.out.print("Ingrese una opcion");
			opcion=input.nextInt();
			
			switch (opcion) {
			case 1:
				System.out.println("INGRESE EL ID");
				id=input.nextInt();
				System.out.println("INGRESE DOCUMENTO");
				documento=input.next();
				System.out.println("INGRESE PRIMER APELLIDO");
				apellido1=input.next();
				System.out.println("INGRESE SEGUNDO APELLIDO");
				apellido2=input.next();
				System.out.println("INGRESE PRIMER NOMBRE");
				nombre1=input.next();
				System.out.println("INGRESE SEGUNDO NOMBRE");
				nombre2=input.next();
				DAOPersona.insert(id, documento, apellido1, apellido2, nombre1, nombre2);
				break;
				
			case 2:
				System.out.println("Ingrese el ID para borrar");
				id=input.nextInt();
				boolean respuesta = DAOPersona.delete(id);
				if(respuesta) {
					System.out.println("Se borro correctamente");
				}
				else {
					System.out.println("NO SE ENCONTRÓ UNA PERSONA CON EL ID INGRESADO");
				}
				break;
			case 3:
				System.out.println("Ingrese el id a actualizar");
				id=input.nextInt();
				Persona p1=DAOPersona.findPersonaID(id);
				System.out.println("Ingrese el documento");
				documento=input.next();
				p1.setDocumento(documento);
				System.out.println("Ingrese el apellido");
				apellido1=input.next();
				p1.setApellido1(apellido1);
				System.out.println("Ingrese el segundo apellido");
				apellido2=input.next();
				p1.setApellido2(apellido2);
				System.out.println("Ingrese el segundo nombre");
				nombre1=input.next();
				p1.setNombre1(nombre1);
				System.out.println("Ingrese el segundo nombre");
				nombre2=input.next();
				p1.setNombre2(nombre2);
				DAOPersona.actualizar(p1, id);
				System.out.println(p1);
				break;
			case 4:
				System.out.println("Ingrese el ID a mostrar");
				id=input.nextInt();
				Persona p2;
				p2 = DAOPersona.findPersonaID(id);
				System.out.println(p2);
				break;
			case 5:
				System.out.println("BYE BYE");
				break;
			default:
				break;
			}
		}
	}
}

		
	


