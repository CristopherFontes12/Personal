package mvc;
import java.awt.EventQueue;

import controlador.Controlador;
import modelo.Modelo;
import vista.Vista;

public class Main {

		public static void main(String[] args) {
			
			
			Modelo mod = new Modelo();
			Vista view = new Vista();
			
			Controlador ctrl = new Controlador(view, mod);
						
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						Vista frame = new Vista();
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		});
	}

}