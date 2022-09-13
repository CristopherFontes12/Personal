package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import objetos.Cliente;
import objetos.Consulta;
import objetos.Mascota;
import objetos.Veterinario;

public class ManejadorConsultas {
	
	private static PreparedStatement consultaInsertarConsulta;
	private static PreparedStatement verTodasConsultas;
	
	
	static{
		
		try {
			consultaInsertarConsulta = DatabaseManager.getConnection().prepareStatement("INSERT INTO CONSULTA(FECHA,NRO_PATENTE_MASCOTA,CODIGO_VETERINARIO) VALUES(?,?,?)");
			verTodasConsultas = DatabaseManager.getConnection().prepareStatement("SELECT * FROM CONSULTA");

		} catch (SQLException e) {
			System.out.println("Error al crear consultas preparadas para Manejador de Clientes");
		}
	}
	
	public static void guardarNuevaConsulta(Consulta consulta) throws SQLException {
		
		// pasamos la fecha del tipo de Date de java al tipo Date de sql
		java.sql.Date fechaSQL = new java.sql.Date(consulta.getFecha().getTime());
		
		consultaInsertarConsulta.setDate(1, fechaSQL);
		consultaInsertarConsulta.setString(2, consulta.getMascota().getNroPatente());
		consultaInsertarConsulta.setString(3, consulta.getVeterinario().getCodigo());
		consultaInsertarConsulta.executeQuery();

	}
	
	public static ArrayList<Consulta> obtenerTodasConsultas(){
		
		ArrayList<Consulta> resultado = new ArrayList<>();
		
		try {
			
			ResultSet resultadoConsulta = verTodasConsultas.executeQuery();
			
			while (resultadoConsulta.next()){
				
				String nroPatente = resultadoConsulta.getString(1);
				String codigoVeterinario = resultadoConsulta.getString(2);
				java.sql.Date  fechaSQL = resultadoConsulta.getDate(3);
				
				Veterinario veterinario = ManejadorVeterinarios.obtenerVeterinario(codigoVeterinario);
				Mascota mascota = ManejadorMascotas.obtenerMascota(nroPatente);
				
				Date fecha = new Date(fechaSQL.getTime());
				
				Consulta consulta = new Consulta(mascota, veterinario, fecha);	
				
				resultado.add(consulta);
			}
			
			
		} catch (SQLException e) {
			
		}
		
		return resultado;
		
	}

}
