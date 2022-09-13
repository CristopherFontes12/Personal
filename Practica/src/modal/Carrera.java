package modal;

public class Carrera {
	
	private Long idCarrera;
	private String nombre;
	private Universidad universidad;
	
	
	public Carrera(Long idCarrera, String nombre, Universidad universidad) {
		super();
		this.idCarrera = idCarrera;
		this.nombre = nombre;
		this.universidad = universidad;
	}
		
	
	public Carrera() {
		super();
	}


	public Long getIdCarrera() {
		return idCarrera;
	}
	public void setIdCarrera(Long idCarrera) {
		this.idCarrera = idCarrera;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Universidad getUniversidad() {
		return universidad;
	}
	public void setUniversidad(Universidad universidad) {
		this.universidad = universidad;
	}


	@Override
	public String toString() {
		return "Carreras [idCarrera=" + idCarrera + ", nombre=" + nombre + ", universidad=" + universidad + "]";
	}
	


}
