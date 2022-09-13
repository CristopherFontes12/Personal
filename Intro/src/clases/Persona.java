package clases;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Objects;

public class Persona {
	
	public int idPersona;
	public String nombre;
	public String apellidos;
	public String dptoResidencia;
	public LinkedList<Vehiculo> vehiculos = new LinkedList<Vehiculo>();
	private int cantHijos;
	private LocalDate fechaNacimiento;
	
	
	public Persona(int idPersona, String persona, String apellidos, String dptoResidencia, int cantHijos,
			LocalDate fechaNacimiento, LinkedList<Vehiculo> vehiculos) {
		super();
		this.vehiculos = vehiculos;
		this.idPersona = idPersona;
		this.nombre = persona;
		this.apellidos = apellidos;
		this.dptoResidencia = dptoResidencia;
		this.cantHijos = cantHijos;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(apellidos, cantHijos, dptoResidencia, fechaNacimiento, idPersona, nombre, vehiculos);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Persona other = (Persona) obj;
		return Objects.equals(apellidos, other.apellidos) && cantHijos == other.cantHijos
				&& Objects.equals(dptoResidencia, other.dptoResidencia)
				&& Objects.equals(fechaNacimiento, other.fechaNacimiento) && idPersona == other.idPersona
				&& Objects.equals(nombre, other.nombre) && Objects.equals(vehiculos, other.vehiculos);
	}


	public Persona() {
		super();
		// TODO Auto-generated constructor stub
	}


	public LinkedList<Vehiculo> getVehiculos() {
		return vehiculos;
	}


	public void setVehiculos(LinkedList<Vehiculo> vehiculos) {
		this.vehiculos = vehiculos;
	}


	public int getIdPersona() {
		return idPersona;
	}
	public void setIdPersona(int idPersona) {
		this.idPersona = idPersona;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String persona) {
		this.nombre = persona;
	}
	
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDptoResidencia() {
		return dptoResidencia;
	}
	public void setDptoResidencia(String dptoResidencia) {
		this.dptoResidencia = dptoResidencia;
	}
	public int getCantHijos() {
		return cantHijos;
	}
	public void setCantHijos(int cantHijos) {
		this.cantHijos = cantHijos;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", apellidos=" + apellidos
				+ ", dptoResidencia=" + dptoResidencia + ", cantHijos=" + cantHijos + ", fechaNacimiento="
				+ fechaNacimiento + "]";
	}

	public String listar() {
		return idPersona + " - " + nombre + " " + apellidos + " " + " Hijos - "  + cantHijos +" Vehiculos: "+vehiculos;
	}
	
}
