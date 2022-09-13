package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import objetos.Cliente;
import objetos.Mascota;

public class ManejadorMascotas {

	private static PreparedStatement consultaInsertarMascota;
	private static PreparedStatement consultaMascotaPorPatente;
	private static PreparedStatement consultaTodasMascotas;

	static {

		try {
			consultaInsertarMascota = DatabaseManager.getConnection().prepareStatement(
					"INSERT INTO MASCOTAS(NRO_PATENTE,TIPO,NOMBRE,EDAD,CI_CLIENTE) VALUES(?,?,?,?,?)");
			consultaMascotaPorPatente = DatabaseManager.getConnection()
					.prepareStatement("SELECT * FROM MASCOTAS WHERE NRO_PATENTE=?");
			consultaTodasMascotas = DatabaseManager.getConnection().prepareStatement("SELECT * FROM MASCOTAS");

		} catch (SQLException e) {
			System.out.println("Error al crear consultas preparadas para Manejador de Mascotas");
		}
	}

	public static void guardarMascota(Mascota mascota) throws SQLException {

		consultaInsertarMascota.setString(1, mascota.getNroPatente());
		consultaInsertarMascota.setString(2, mascota.getTipo());
		consultaInsertarMascota.setString(3, mascota.getNombre());
		consultaInsertarMascota.setInt(4, mascota.getEdad());
		consultaInsertarMascota.setString(5, mascota.getCliente().getCi());
		consultaInsertarMascota.executeQuery();

	}

	public static Mascota obtenerMascota(String nroPatente) {

		Mascota resultado = null;

		try {
			consultaMascotaPorPatente.setString(1, nroPatente);
			ResultSet resultadoConsulta = consultaMascotaPorPatente.executeQuery();

			if (resultadoConsulta.next()) {

				String tipo = resultadoConsulta.getString(2);
				String nombre = resultadoConsulta.getString(3);
				int edad = resultadoConsulta.getInt(4);
				String ciCliente = resultadoConsulta.getString(5);

				Cliente cliente = ManejadorClientes.obtenerCliente(ciCliente);

				resultado = new Mascota(nroPatente, tipo, nombre, edad, cliente);

			}

		} catch (SQLException e) {

		}

		return resultado;

	}

	public static ArrayList<Mascota> obtenerTodasMascotas() {

		ArrayList<Mascota> resultado = new ArrayList<>();

		try {
			
			ResultSet resultadoConsulta = consultaTodasMascotas.executeQuery();

			while (resultadoConsulta.next()) {
				
				String nroPatente = resultadoConsulta.getString(1);
				String tipo = resultadoConsulta.getString(2);
				String nombre = resultadoConsulta.getString(3);
				int edad = resultadoConsulta.getInt(4);
				String ciCliente = resultadoConsulta.getString(5);

				Cliente cliente = ManejadorClientes.obtenerCliente(ciCliente);

				Mascota mascota = new Mascota(nroPatente, tipo, nombre, edad, cliente);
				
				resultado.add(mascota);

			}

		} catch (SQLException e) {

		}

		return resultado;

	}

}
