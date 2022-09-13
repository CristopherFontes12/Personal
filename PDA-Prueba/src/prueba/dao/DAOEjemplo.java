package prueba.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import prueba.ConnectionBD;

public class DAOEjemplo {

	private static final String FIND_ALL = "SELECT {} FROM tabla";
	private static final String FIND_BY_ID = "SELECT {} FROM tabla WHERE id_tabla = ?";
	private static final String INSERT = "INSERT INTO tabla VALUES()";
	private static final String UPDATE = "UPDATE tabla SET {} WHERE id_tabla = ?";
	private static final String DELETE = "DELETE FROM tabla WHERE id_tabla = ?";

	public static List<Ejemplo> findAll() throws SQLException {
		Statement s = ConnectionBD.getConnection().createStatement();
		ResultSet rs = s.executeQuery(FIND_ALL);

		List<Ejemplo> lista = new LinkedList<>();
		while (rs.next()) {
			Ejemplo objs = new Ejemplo();
			// TODO: Setear campos del Objeto
			lista.add(objs);
		}

		return lista;
	}

	public static Ejemplo findById(Long id) throws SQLException {
		PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(FIND_BY_ID);
		ps.setLong(1, id);
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {
			Ejemplo obj = new Ejemplo();
			// TODO: Setear campos
			return obj;
		} else {
			return null;
		}
	}

	public static boolean insert(Ejemplo c) throws SQLException {
		PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(INSERT);
		// TODO: Setear campos a insertar
		return ps.executeUpdate() > 0;
	}

	public static boolean update(Long id, Ejemplo c) throws SQLException {
		PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(UPDATE);
		ps.setLong(1, id);
		// TODO: Setear campos a modificar
		return ps.executeUpdate() > 0;
	}

	public static boolean delete(Long id) throws SQLException {
		PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(DELETE);
		ps.setLong(1, id);
		return ps.executeUpdate() > 0;
	}
}
