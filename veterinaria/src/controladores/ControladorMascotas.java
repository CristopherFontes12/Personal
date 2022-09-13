package controladores;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import manejadores.ManejadorClientes;
import manejadores.ManejadorMascotas;
import objetos.Cliente;
import objetos.Mascota;

public class ControladorMascotas {

	public static boolean ingresarNuevaMascota(String nroPatente, String tipo, int edad, String nombre, String ciCliente) {

		boolean pudeCrear;
		Cliente cliente = ManejadorClientes.obtenerCliente(ciCliente);
		Mascota mascota = new Mascota(nroPatente, tipo, nombre, edad, cliente);

		try {
			ManejadorMascotas.guardarMascota(mascota);
			pudeCrear = true;
		} catch (SQLException e) {
			// ca va el cofigo se falla el insert
			pudeCrear = false;
		}

		return pudeCrear;

	}

	public static boolean existeMascota(String nroPatente) {

		boolean existe;
		Mascota mascota = ManejadorMascotas.obtenerMascota(nroPatente);

		if (mascota == null) {
			existe = false;
		} else {
			existe = true;
		}

		return existe;

	}
	
	public static ArrayList<String> obtenerInfoMascotas(){
		
		ArrayList<String> infoMascotas = new ArrayList<>();
		
		ArrayList<Mascota> mascotas = ManejadorMascotas.obtenerTodasMascotas();
		
		for(Mascota mascota: mascotas){
			infoMascotas.add(mascota.toString());
		}
		
		return infoMascotas;
		
	}
	
	public static ArrayList<Mascota> obtenerTodasMascotas(){
		
		return ManejadorMascotas.obtenerTodasMascotas();
		
	}

}
