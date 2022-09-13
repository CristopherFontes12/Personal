package clases;

public class Avion extends Vehiculo {
	public double longitud;
	public int cantPasajeros;
	
	
	@Override
	public String toString() {
		return "Avion [longitud=" + longitud + ", cantPasajeros=" + cantPasajeros + ", idVehiculo=" + idVehiculo
				+ ", nombre=" + nombre + ", color=" + color + ", duenio=" + duenio + "]";
	}

	public Avion(int idVehiculo, String nombre, String color, double longitud, int cantPasajeros) {
		super(idVehiculo, nombre, color);
		this.longitud = longitud;
		this.cantPasajeros = cantPasajeros;
	}

	public Avion() {
	}
	
	public double getLongitud() {
		return longitud;
	}
	
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
	public int getCantPasajeros() {
		return cantPasajeros;
	}
	public void setCantPasajeros(int cantPasajeros) {
		this.cantPasajeros = cantPasajeros;
	}
	
	
}
