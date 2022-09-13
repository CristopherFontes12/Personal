package controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import manejadores.ManejadorClientes;
import manejadores.ManejadorConsultas;
import manejadores.ManejadorMascotas;
import manejadores.ManejadorVeterinarios;
import objetos.Cliente;
import objetos.Consulta;
import objetos.Mascota;
import objetos.Veterinario;

public class ControladorConsultas {

	public static boolean ingresarNuevaConsulta(String codigoVeterinario, String nroPatenteMascota, Date fecha) {

		boolean pudeCrear;
		
		Mascota mascota = ManejadorMascotas.obtenerMascota(nroPatenteMascota);
		Veterinario veterinario = ManejadorVeterinarios.obtenerVeterinario(codigoVeterinario);
		Consulta consulta = new Consulta(mascota, veterinario, fecha);

		try {
			ManejadorConsultas.guardarNuevaConsulta(consulta);
			pudeCrear = true;
		} catch (SQLException e) {
			// ca va el cofigo se falla el insert
			pudeCrear = false;
		}

		return pudeCrear;

	}
	
	public static ArrayList<String> obtenerInfoConsultas(){
		
		ArrayList<String> infoMascotas = new ArrayList<>();
		
		ArrayList<Consulta> consultas = ManejadorConsultas.obtenerTodasConsultas();
		
		for(Consulta consulta: consultas){
			infoMascotas.add(consulta.toString());
		}
		
		return infoMascotas;
		
	}
	
	public static  ArrayList<Consulta> obtenerTodasConsultas(){
		return ManejadorConsultas.obtenerTodasConsultas();
	}

}
