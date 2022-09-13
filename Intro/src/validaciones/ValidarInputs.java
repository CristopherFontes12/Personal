package validaciones;

import java.awt.event.KeyEvent;

public class ValidarInputs {
		
	public static void ValidarSoloLetras(KeyEvent e) {
		if (!Character.isLetter(e.getKeyChar())   && e.getKeyChar() != KeyEvent.VK_SPACE && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar letras");
			return;
		}
	}
	
	public static void ValidarSoloNumeros(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar numeros");
			return;
		}
	}
	
	public static void ValidarSoloNumerosYComa(KeyEvent e) {
		if (!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_PERIOD && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar numeros");
			return;
		}
	}
	
	public static void ValidarNumerosYLetras(KeyEvent e) {
		if (!Character.isLetterOrDigit(e.getKeyChar()) && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se pueden ingresar letras y numeros, no simbolos");
			return;
		}
	}
	
	public static void ValidarFechas(KeyEvent e) {
		if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar() != '-' && e.getKeyChar() != KeyEvent.VK_BACK_SPACE) {
			e.consume();
			Mensajes.MostrarError("En este campo solo se puede ingresar numeros y \"-\"");
			return;
		}
	}
}
