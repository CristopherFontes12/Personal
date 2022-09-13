package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objetos.Cliente;
import objetos.Veterinario;

public class ManejadorVeterinarios {
	
	private static PreparedStatement consultaInsertarVeterinario;
	private static PreparedStatement consultaVeterinarioPorCodigo;
	private static PreparedStatement consultaTodosVeterinarios;
	
	static{
		
		try {
			consultaInsertarVeterinario = DatabaseManager.getConnection().prepareStatement("INSERT INTO VETERINARIOS(CODIGO,ESPECIALIDAD,NOMBRE,CI) VALUES(?,?,?,?)");
			consultaVeterinarioPorCodigo = DatabaseManager.getConnection().prepareStatement("SELECT * FROM VETERINARIOS WHERE CODIGO=?");
			consultaTodosVeterinarios =  DatabaseManager.getConnection().prepareStatement("SELECT * FROM VETERINARIOS");

		} catch (SQLException e) {
			System.out.println("Error al crear consultas preparadas para Manejador de Clientes");
		}
	}
	
	public static void guardaVeterinario(Veterinario veterinario) throws SQLException {
		
		consultaInsertarVeterinario.setString(1, veterinario.getCodigo());
		consultaInsertarVeterinario.setString(2, veterinario.getEspecialidad());
		consultaInsertarVeterinario.setString(3, veterinario.getNombre());
		consultaInsertarVeterinario.setString(4, veterinario.getCi());
		consultaInsertarVeterinario.executeQuery();

	}
	
	public static Veterinario obtenerVeterinario(String codigo){
		
		Veterinario resultado = null;
		
		try {
			consultaVeterinarioPorCodigo.setString(1, codigo);
			ResultSet resultadoConsulta = consultaVeterinarioPorCodigo.executeQuery();
			
			if (resultadoConsulta.next()){
				
				String especialidad = resultadoConsulta.getString(2);
				String nombre = resultadoConsulta.getString(3);
				String ci = resultadoConsulta.getString(4);
				
				resultado = new Veterinario(codigo, especialidad, nombre, ci);
				
			}
			
			
		} catch (SQLException e) {
			
		}
		
		return resultado;
		
	}
	
	public static List<Veterinario> obtenerTodosVeterinarios(){
		
		List<Veterinario> resultado = new ArrayList<>();
		
		try {
			ResultSet resultadoConsulta = consultaTodosVeterinarios.executeQuery();
			
			while (resultadoConsulta.next()){
				
				String codigo = resultadoConsulta.getString(1);
				String especialidad = resultadoConsulta.getString(2);
				String nombre = resultadoConsulta.getString(3);
				String ci = resultadoConsulta.getString(4);
				
				Veterinario v = new Veterinario(codigo, especialidad, nombre, ci);
				resultado.add(v);
				
			}
			
			
		} catch (SQLException e) {
			
		}
		
		return resultado;
		
	}

}
