package clases;

public class Vehiculo {

	public int idVehiculo;
	public String nombre;
	public String color;
	public Persona duenio;
	
	
	
	/*public String toString() {
		return "Vehiculo [idVehiculo=" + idVehiculo + ", nombre=" + nombre + ", color=" + color + ", duenio=" + duenio
				+ "]";
	}*/

	public Vehiculo(int idVehiculo, String nombre, String color) {
		this.idVehiculo = idVehiculo;
		this.nombre = nombre;
		this.color = color;
	}
	
	public Vehiculo() {
		super();
	}
	
	public Persona getDuenio() {
		return duenio;
	}

	public void setDuenio(Persona duenio) {
		this.duenio = duenio;
	}

	public int getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(int idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	
	
	
	
}
