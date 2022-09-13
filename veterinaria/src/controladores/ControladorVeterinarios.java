package controladores;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import manejadores.ManejadorClientes;
import manejadores.ManejadorMascotas;
import manejadores.ManejadorVeterinarios;
import objetos.Cliente;
import objetos.Mascota;
import objetos.Veterinario;

public class ControladorVeterinarios {

	public static boolean ingresarNuevaVeterinario(String codigo, String especialidad, String nombre, String ci) {

		boolean pudeCrear;
		Veterinario veterinario = new Veterinario(codigo, especialidad, nombre, ci);

		try {
			ManejadorVeterinarios.guardaVeterinario(veterinario);
			pudeCrear = true;
		} catch (SQLException e) {
			// ca va el cofigo se falla el insert
			pudeCrear = false;
		}

		return pudeCrear;

	}

	public static boolean existeVeterinario(String codigo) {

		boolean existe;
		Veterinario veterinario = ManejadorVeterinarios.obtenerVeterinario(codigo);

		if (veterinario == null) {
			existe = false;
		} else {
			existe = true;
		}

		return existe;

	}

	public static List<Veterinario> obtenerTodosVeterinarios() {
		return ManejadorVeterinarios.obtenerTodosVeterinarios();
	}

}
