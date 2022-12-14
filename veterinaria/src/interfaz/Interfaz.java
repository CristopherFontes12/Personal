package interfaz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controladores.ControladorClientes;
import controladores.ControladorConsultas;
import controladores.ControladorMascotas;
import controladores.ControladorVeterinarios;
import excepciones.RangoOpcionException;

public class Interfaz {

	public static int EXIT = 7;

	public static String MENSAJE_CLIENTES = "-- Alta de Cliente --\n\n";
	public static String MENSAJE_MASCOTAS = "-- Alta de Mascota --\n\n";
	public static String MENSAJE_VETERINARIO = "-- Alta de Veterinario --\n\n";
	public static String MENSAJE_REGISTRO = "-- Nuevo Registro de Consulta --\n\n";
	public static String MENSAJE_LISTADO_MASCOTAS = "-- Listado de Mascotas --\n\n";
	public static String MENSAJE_LISTADO_CONSULTAS = "-- Listado de Consultas --\n\n";

	public static String MENU = "SISTEMA GESTION DE VETERINARIA\n\n" + "Seleccione una opci?n:\n\n"
			+ "1. Ingresar Cliente\n" + "2. Ingresar Mascota\n" + "3. Ingresar Veterinario\n"
			+ "4. Registrar Consulta\n" + "5. Listar Mascotas\n" + "6. Listar Consultas\n" + "7.Salir\n";

	public static void main(String[] args) {

		// Inicializamos la lectura de la entrada
		InputStreamReader stream = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(stream);

		// Leemos por primera vez
		int opcion = 0;

		// Comienzo la iteración hasta que finalice
		while (opcion != EXIT) {

			// Imprimimos el menu por primera vez
			System.out.println(MENU);
			System.out.println();

			// Pedimos un dato de la entrada
			opcion = opcionElegida(reader);

			switch (opcion) {
			case 1:

				// Opcion de ingresar cliente
				pedirDatosDeCliente(reader);

				break;

			case 2:

				// Opcion de ingresar mascota
				pedirDatosDeMascota(reader);

				break;

			case 3:

				// Opcion de ingresar mascota
				pedirDatosDeVeterinario(reader);

				break;

			case 4:

				pedirDatosDeRegistro(reader);
				break;

			case 5:

				listarInfoMascotas();
				break;

			case 6:

				listarConsultas();
				break;

			default:
				break;
			}

			System.out.println();
			System.out.println();

		}

	}

	private static void listarInfoMascotas() {

		// Imprimimos el mensaje de bienvenida
		System.out.println(MENSAJE_LISTADO_MASCOTAS);
		System.out.println();
		System.out.println();

		ArrayList<String> infoMascotas = ControladorMascotas.obtenerInfoMascotas();
		
		if (infoMascotas.isEmpty()){
			System.out.println("No hay macostas ingresadas en el sistema.");
		}
		else{
			System.out.println("NroPatente,Nombre,Tipo,Edad,Ci Cliente,Nombre Cliente");
			for (String info: infoMascotas){
				System.out.println(info);
			}
		}

	}
	
	private static void listarConsultas() {

		// Imprimimos el mensaje de bienvenida
		System.out.println(MENSAJE_LISTADO_CONSULTAS);
		System.out.println();
		System.out.println();

		ArrayList<String> infoConsultas = ControladorConsultas.obtenerInfoConsultas();
		
		if (infoConsultas.isEmpty()){
			System.out.println("No hay consultas ingresadas en el sistema.");
		}
		else{
			
			for (String info: infoConsultas){
				System.out.println(info);
			}
		}

	}

	private static void pedirDatosDeVeterinario(BufferedReader reader) {

		// Imprimimos el mensaje de bienvenida
		System.out.println(MENSAJE_VETERINARIO);
		System.out.println();

		try {

			// Pedimos uno a uno los datos del curso

			System.out.print("Codigo: ");
			String codigo = reader.readLine();
			System.out.println();

			System.out.print("Especialidad: ");
			String especialidad = reader.readLine();
			System.out.println();

			System.out.print("Nombre: ");
			String nombre = reader.readLine();
			System.out.println();

			System.out.print("CI: ");
			String ci = reader.readLine();
			System.out.println();

			// Mostramos la informaci?n ingresada para pedir la confirmaci?n

			System.out.println();
			System.out.println();
			System.out.println("Se ingresar? en el sistema el Veterinario con los siguientes datos:");
			System.out.println();
			System.out.println("CODIGO: " + codigo);
			System.out.println("ESPECIALIDAD: " + especialidad);
			System.out.println("NOMBRE: " + nombre);
			System.out.println("CI: " + ci);
			System.out.println();
			System.out.print("?Confirma los datos? (Y/N)");

			// Leemos la confirmaci?n del usuario

			String confirma = reader.readLine();

			// Mientras que no seleccione Y o N, volvemos a pedirle la
			// confimaci?n

			while (!(confirma.equals("Y") || confirma.equals("N"))) {
				System.out.println();
				System.out.print("?Confirma los datos? (Y/N)");
				confirma = reader.readLine();
			}

			// Si confirma que no, volvemos al menu
			System.out.println();
			System.out.println();

			if (confirma.equals("N")) {

				System.out.print("Alta de veterinario cancelado!");
			} else {

				// Si confirma que si, validamos que el veterinario con dicho
				// codigo no exista previamente en el sistema

				boolean existeVeterinario = ControladorVeterinarios.existeVeterinario(codigo);

				if (existeVeterinario) {

					System.out.print("Ya existe un veterinario con CODIGO " + codigo + " en el sistema.");
				} else {

					// Si llegamos aqui intentamos crear el veterinario, Si se
					// da alg?n error, mostramos un msj y volvemos al menu

					boolean pudeCrear = ControladorVeterinarios.ingresarNuevaVeterinario(codigo, especialidad, nombre,
							ci);

					if (pudeCrear) {
						System.out.print("Veterinario con CODIGO " + codigo + " creado exitosamente!");
					} else {
						System.out.print("Ocurri? un error al guardar el veterinario. Intente nuevamente.");
					}
				}

			}

		} catch (IOException e) {
			System.out.print("Ocurri? un error al leer los datos de pantalla. Intente nuevamente.");
		}

	}

	private static void pedirDatosDeRegistro(BufferedReader reader) {

		// Imprimimos el mensaje de bienvenida
		System.out.println(MENSAJE_REGISTRO);
		System.out.println();

		try {

			// Pedimos uno a uno los datos del curso

			System.out.print("Patente Mascota: ");
			String nroPatenteMascota = reader.readLine();
			System.out.println();

			System.out.print("Codigo Veterinario: ");
			String codigoVeterinaio = reader.readLine();
			System.out.println();

			System.out.print("Fecha (dd/MM/YYYY): ");
			String fechaString = reader.readLine();
			System.out.println();

			// Mostramos la informaci?n ingresada para pedir la confirmaci?n

			System.out.println();
			System.out.println();
			System.out.println("Se ingresar? en el sistema una Consulta con los siguientes datos:");
			System.out.println();
			System.out.println("PATENTE MASCOTA: " + nroPatenteMascota);
			System.out.println("CODIGO VETERINARIO: " + codigoVeterinaio);
			System.out.println("FECHA: " + fechaString);
			System.out.println();
			System.out.print("?Confirma los datos? (Y/N)");

			// Leemos la confirmaci?n del usuario

			String confirma = reader.readLine();

			// Mientras que no seleccione Y o N, volvemos a pedirle la
			// confimaci?n

			while (!(confirma.equals("Y") || confirma.equals("N"))) {
				System.out.println();
				System.out.print("?Confirma los datos? (Y/N)");
				confirma = reader.readLine();
			}

			// Si confirma que no, volvemos al menu
			System.out.println();
			System.out.println();

			if (confirma.equals("N")) {

				System.out.print("Alta de consulta cancelada!");
			} else {

				// Si confirma que si, validamos que el veterinario y la mascota
				// esten previamente en el sistema

				boolean existeVeterinario = ControladorVeterinarios.existeVeterinario(codigoVeterinaio);
				boolean existeMascota = ControladorMascotas.existeMascota(nroPatenteMascota);

				if (!existeVeterinario) {

					System.out
							.print("No se encuentra un Veterinario con CODIGO " + codigoVeterinaio + " en el sistema.");
				} else {

					if (!existeMascota) {
						System.out.print(
								"No se encuentra una Mascota con NRO PATENTE " + nroPatenteMascota + " en el sistema.");
					} else {

						// Transofrmamos la fecha de Strign en el formato
						// indicado al tipo Date.
						SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

						try {
							Date fecha = sdf.parse(fechaString);

							// Si llegamos aqui intentamos crear el cliente, Si
							// se da alg?n error, mostramos un msj y volvemos al
							// menu

							boolean pudeCrear = ControladorConsultas.ingresarNuevaConsulta(codigoVeterinaio,
									nroPatenteMascota, fecha);

							if (pudeCrear) {
								System.out.print("Consulta creada exitosamente!");
							} else {
								System.out.print("Ocurri? un error al guardar la consulta. Intente nuevamente.");
							}
						} catch (ParseException e) {
							System.out.print("Error en el formato de la fecha.");
						}

					}
				}

			}

		} catch (IOException e) {
			System.out.print("Ocurri? un error al leer los datos de pantalla. Intente nuevamente.");
		}

	}

	private static void pedirDatosDeCliente(BufferedReader reader) {

		// Imprimimos el mensaje de bienvenida
		System.out.println(MENSAJE_CLIENTES);
		System.out.println();

		try {

			// Pedimos uno a uno los datos del curso

			System.out.print("Nombre: ");
			String nombre = reader.readLine();
			System.out.println();

			System.out.print("Apellido: ");
			String apellido = reader.readLine();
			System.out.println();

			System.out.print("CI: ");
			String ci = reader.readLine();
			System.out.println();

			// Mostramos la informaci?n ingresada para pedir la confirmaci?n

			System.out.println();
			System.out.println();
			System.out.println("Se ingresar? en el sistema el CLiente con los siguientes datos:");
			System.out.println();
			System.out.println("NOMBRE: " + nombre);
			System.out.println("APELLIDO: " + apellido);
			System.out.println("NOMBRE: " + ci);
			System.out.println();
			System.out.print("?Confirma los datos? (Y/N)");

			// Leemos la confirmaci?n del usuario

			String confirma = reader.readLine();

			// Mientras que no seleccione Y o N, volvemos a pedirle la
			// confimaci?n

			while (!(confirma.equals("Y") || confirma.equals("N"))) {
				System.out.println();
				System.out.print("?Confirma los datos? (Y/N)");
				confirma = reader.readLine();
			}

			// Si confirma que no, volvemos al menu
			System.out.println();
			System.out.println();

			if (confirma.equals("N")) {

				System.out.print("Alta de cliente cancelada!");
			} else {

				// Si confirma que si, validamos que el cliente con dicha CI no
				// exista previamente en el sistema

				boolean existeCliente = ControladorClientes.existeCliente(ci);

				if (existeCliente) {

					System.out.print("Ya existe un cliente con CI " + ci + " en el sistema.");
				} else {

					// Si llegamos aqui intentamos crear el cliente, Si se da
					// alg?n error, mostramos un msj y volvemos al menu

					boolean pudeCrear = ControladorClientes.ingresarNuevoCliente(nombre, apellido, ci);

					if (pudeCrear) {
						System.out.print("Cliente con CI " + ci + " creado exitosamente!");
					} else {
						System.out.print("Ocurri? un error al guardar el cliente. Intente nuevamente.");
					}
				}

			}

		} catch (IOException e) {
			System.out.print("Ocurri? un error al leer los datos de pantalla. Intente nuevamente.");
		}

	}

	private static void pedirDatosDeMascota(BufferedReader reader) {

		// Imprimimos el mensaje de bienvenida
		System.out.println(MENSAJE_MASCOTAS);
		System.out.println();

		try {

			// Pedimos uno a uno los datos del curso

			System.out.print("Numero Patente: ");
			String nroPatente = reader.readLine();
			System.out.println();

			System.out.print("Tipo: ");
			String tipo = reader.readLine();
			System.out.println();

			System.out.print("Nombre: ");
			String nombre = reader.readLine();
			System.out.println();

			System.out.print("Edad: ");
			String edadString = reader.readLine();
			System.out.println();

			System.out.print("CI Cliente: ");
			String ciCliente = reader.readLine();
			System.out.println();

			// Mostramos la informaci?n ingresada para pedir la confirmaci?n

			System.out.println();
			System.out.println();
			System.out.println("Se ingresar? en el sistema la Mascota con los siguientes datos:");
			System.out.println();
			System.out.println("NUMERO PATENTE: " + nroPatente);
			System.out.println("TIPO: " + tipo);
			System.out.println("NOMBRE: " + nombre);
			System.out.println("EDAD: " + edadString);
			System.out.println("CI CLIENTE: " + ciCliente);
			System.out.println();
			System.out.print("?Confirma los datos? (Y/N)");

			// Leemos la confirmaci?n del usuario

			String confirma = reader.readLine();

			// Mientras que no seleccione Y o N, volvemos a pedirle la
			// confimaci?n

			while (!(confirma.equals("Y") || confirma.equals("N"))) {
				System.out.println();
				System.out.print("?Confirma los datos? (Y/N)");
				confirma = reader.readLine();
			}

			// Si confirma que no, volvemos al menu
			System.out.println();
			System.out.println();

			if (confirma.equals("N")) {

				System.out.print("Alta de mascota cancelada!");
			} else {

				// ANtes de proceder, validamos tres cosas,...
				// 1. Que exista el cliente
				// 2. Que no exista otra mascota con la misma patente
				// 3. Que el valor de edad es un n?mero

				boolean existeCliente = ControladorClientes.existeCliente(ciCliente);

				boolean esNumeroEdad = true;
				int edad = -1;
				try {
					edad = Integer.valueOf(edadString);
				} catch (NumberFormatException e) {
					esNumeroEdad = false;
				}

				boolean existeMascota = ControladorMascotas.existeMascota(nroPatente);

				// Si no existe el cliente, motramos un msj

				if (!existeCliente) {
					System.out.print("No se encuentra un Cliente con CI " + ciCliente + " en el sistema.");
				} else {

					// Si el cliente existe, validamos que la edad sea un valor
					// num?rico

					if (!esNumeroEdad) {
						System.out.print("La edad debe ser un n?mero v?lido.");
					} else {

						// POr ultimo, validamos que no exista mascota con ea
						// patente

						if (existeMascota) {
							System.out.print(
									"Ya existe una masctoa con el NRO de PATENTE " + nroPatente + " en el sistema.");
						} else {
							// Si llegamos aqui intentamos crear la mascota, Si
							// se da alg?n error, mostramos un msj y volvemos al
							// menu

							boolean pudeCrear = ControladorMascotas.ingresarNuevaMascota(nroPatente, tipo, edad, nombre,
									ciCliente);

							if (pudeCrear) {
								System.out.print("Mascota con NRO PATENTE " + nroPatente + " creada exitosamente!");
							} else {
								System.out.print("Ocurri? un error al guardar la mascota. Intente nuevamente.");
							}
						}

					}

				}

			}

		} catch (IOException e) {
			System.out.print("Ocurri? un error al leer los datos de pantalla. Intente nuevamente.");
		}

	}

	private static int opcionElegida(BufferedReader reader) {

		int resultado = 0;
		String opcionLeida = null;

		try {
			opcionLeida = reader.readLine();
			resultado = Integer.valueOf(opcionLeida);
			validarRangoOpcion(resultado);
		} catch (IOException e) {
			System.out.println("Error al leer de la entrada estandar, ingrese nuevamente su opci?n\n");
		} catch (NumberFormatException e) {
			System.out.println("Debe ingresar un n?mero, ingrese nuevamente su opci?n\n");

		} catch (RangoOpcionException e) {
			System.out.println(e.getMessage());
		}

		return resultado;
	}

	// Metodo que valida que la opcion esté entre 1 y 4
	private static void validarRangoOpcion(int opcion) throws RangoOpcionException {

		if (opcion <= 0 || opcion > 8) {
			throw new RangoOpcionException("El rango de la opcion debe estar entre 1 y 7");
		}

	}

}
