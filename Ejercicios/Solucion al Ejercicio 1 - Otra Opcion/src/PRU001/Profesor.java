package PRU001;

public class Profesor {

	private int idProfesor;
	private String nombre;
	private String telefono;

	public Profesor(int idProfesor, String nombre, String telefono) {
		
		this.idProfesor = idProfesor;
		this.nombre = nombre;
		this.telefono = telefono;
	}
	public Profesor() {
		
	}
	public int getIdProfesor() {
		return idProfesor;
	}
	public void setIdProfesor(int idProfesor) {
		this.idProfesor = idProfesor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	@Override
	public String toString() {
		return "Profesor [idProfesor=" + idProfesor + ", nombre=" + nombre + ", telefono=" + telefono + "]";
	}
	
	
	
}
