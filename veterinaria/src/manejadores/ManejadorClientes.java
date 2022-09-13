package manejadores;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import objetos.Cliente;

public class ManejadorClientes {

	private static PreparedStatement consultaInsertarCliente;
	private static PreparedStatement consultaClientePorCi;
	private static PreparedStatement consultaTodosClientes;

	static {

		try {
			consultaInsertarCliente = DatabaseManager.getConnection()
					.prepareStatement("INSERT INTO CLIENTES(CI,NOMBRE,APELLIDO) VALUES(?,?,?)");
			consultaClientePorCi = DatabaseManager.getConnection()
					.prepareStatement("SELECT * FROM CLIENTES WHERE CI=?");
			consultaTodosClientes = DatabaseManager.getConnection().prepareStatement("SELECT * FROM CLIENTES");

		} catch (SQLException e) {
			System.out.println("Error al crear consultas preparadas para Manejador de Clientes");
		}
	}

	public static void guardarCliente(Cliente cliente) throws SQLException {

		consultaInsertarCliente.setString(1, cliente.getCi());
		consultaInsertarCliente.setString(2, cliente.getNombre());
		consultaInsertarCliente.setString(3, cliente.getApellido());
		consultaInsertarCliente.executeQuery();

	}

	public static Cliente obtenerCliente(String ci) {

		Cliente resultado = null;

		try {
			consultaClientePorCi.setString(1, ci);
			ResultSet resultadoConsulta = consultaClientePorCi.executeQuery();

			if (resultadoConsulta.next()) {

				String nombre = resultadoConsulta.getString(1);
				String apellido = resultadoConsulta.getString(2);

				resultado = new Cliente(nombre, apellido, ci);

			}

		} catch (SQLException e) {

		}

		return resultado;

	}

	public static  List<Cliente> obtenerTodosClientes() {

		 List<Cliente> resultado = new ArrayList<>();

		try {
			ResultSet resultadoConsulta = consultaTodosClientes.executeQuery();

			while (resultadoConsulta.next()) {

				String nombre = resultadoConsulta.getString(1);
				String apellido = resultadoConsulta.getString(2);
				String ci = resultadoConsulta.getString(3);

				Cliente c = new Cliente(nombre, apellido, ci);
				resultado.add(c);

			}

		} catch (SQLException e) {

		}

		return resultado;
	}

}
