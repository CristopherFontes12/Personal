package modal;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

import dao.CarreraDao;
import dao.DatabaseManager;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Connection conn = DatabaseManager.getConnection();
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					GUI frame = new GUI();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
		

		try {
			
			// Cantidad de carreras existentes 
			
			System.out.println("Existen " + CarreraDao.cuentaCarrera() + " Carreras disponibles");
			
			
			// Muestro todas las carreras con las universidades que contiene. 
			
			LinkedList<Carrera> carreras = null;
			carreras = CarreraDao.todasCarreras();
			
			for (Carrera carr : carreras) {
				System.out.println(carr);
			}
			
			// Busco una carrera por ID 
			
			System.out.println("La carrera 4 que estamos buscando es: " + CarreraDao.buscarCarreraPorId((long) 4));
			
			
			// Creando una carrear nueva 
			
//			Carrera c = new Carrera();
//			c.setNombre("Analista de requerimiento");
//			c.setUniversidad(new Universidad(1l));
//			
//			System.out.println(CarreraDao.crearCarrera(c));
			
//			// Hacer un Update de una carrera 
//			
//			Carrera carr = new Carrera();
//			carr.setNombre("Analista de Testing");
//			carr.setUniversidad(new Universidad(1l));
//			
//			System.out.println(CarreraDao.modificarCarrera(21l, carr));
			
			// Hacer un Delete de una carrera
			
			
			System.out.println(CarreraDao.eliminarCarrera(21l));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
