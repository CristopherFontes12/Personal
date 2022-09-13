package modal;

import java.util.LinkedList;

public class Universidad {
	private Long idUniversidad;
	private String nombre;
	private String ubicacion;
	private LinkedList<Carrera> carreras;
	
	
	
	
	

	public Universidad(Long idUniversidad) {
		super();
		this.idUniversidad = idUniversidad;
	}


	public Universidad() {
		super();
	}
	
	
	public Universidad(Long idUniversidad, String nombre, String ubicacion, LinkedList<Carrera> carreras) {
		super();
		this.idUniversidad = idUniversidad;
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.carreras = carreras;
	}
	
	
	public Long getIdUniversidad() {
		return idUniversidad;
	}
	public void setIdUniversidad(Long idUniversidad) {
		this.idUniversidad = idUniversidad;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public LinkedList<Carrera> getCarreras() {
		return carreras;
	}
	public void setCarreras(LinkedList<Carrera> carreras) {
		this.carreras = carreras;
	}


	@Override
	public String toString() {
		return "Universidad [idUniversidad=" + idUniversidad + ", nombre=" + nombre + ", ubicacion=" + ubicacion
				+ ", carreras=" + carreras + "]";
	}
	
	
	

}
