package ejercicio3;

public class Cartero {

	//Creo los atributos
	private int idCartero;
	private String nombre;
	private int ci;
	private boolean esEmpleado;
	
	//Genero Getters y Setters
	public int getIdCartero() {
		return idCartero;
	}
	public void setIdCartero(int idCartero) {
		this.idCartero = idCartero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCi() {
		return ci;
	}
	public void setCi(int ci) {
		this.ci = ci;
	}
	public boolean getEsEmpleado() {
		return esEmpleado;
	}
	public void setEsEmpleado(boolean esEmpleado) {
		this.esEmpleado = esEmpleado;
	}
	
	//Genero Constructor con Parámetros
	public Cartero(int idCartero, String nombre, int ci, boolean esEmpleado) {
		super();
		this.idCartero = idCartero;
		this.nombre = nombre;
		this.ci = ci;
		this.esEmpleado = esEmpleado;
	}
	
	//Genero Constructor vacío
	public Cartero() {
		
	}
	
	//Genero el to String
	@Override
	public String toString() {
		return "Cartero [idCartero=" + idCartero + ", nombre=" + nombre + ", ci=" + ci + ", esEmpleado=" + esEmpleado
				+ "]";
	}

}
