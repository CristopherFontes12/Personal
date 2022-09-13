package prueba;

import javax.swing.JOptionPane;

public class Mensajes {
	public static void Error(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Operacion Fallida", JOptionPane.ERROR_MESSAGE);
	}
	
	public static void Exito(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Operacion Exitosa", JOptionPane.INFORMATION_MESSAGE);
	}
}
