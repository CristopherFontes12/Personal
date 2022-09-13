package ejercicio1;

import java.sql.Date;

public class Persona {
	private int idPersona;
	private String nombre;
	private Date fechaIngreso;
	private Date fechaNacimiento;
	private TipoClase tipo;
	private Profesor profesor;
	public Persona(int idPersona, String nombre, Date fechaIngreso, Date fechaNacimiento, TipoClase tipo,
			Profesor profesor) {
		this.idPersona = idPersona;
		this.nombre = nombre;
		this.fechaIngreso = fechaIngreso;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = tipo;
		this.profesor = profesor;
	}
	
	public Persona(String nombre, Date fechaIngreso, Date fechaNacimiento, TipoClase tipo,
			Profesor profesor) {
		
		this.nombre = nombre;
		this.fechaIngreso = fechaIngreso;
		this.fechaNacimiento = fechaNacimiento;
		this.tipo = tipo;
		this.profesor = profesor;
	}
	public Persona() {
		
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

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public TipoClase getTipo() {
		return tipo;
	}

	public void setTipo(TipoClase tipo) {
		this.tipo = tipo;
	}

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	@Override
	public String toString() {
		return "Persona [idPersona=" + idPersona + ", nombre=" + nombre + ", fechaIngreso=" + fechaIngreso
				+ ", fechaNacimiento=" + fechaNacimiento + ", tipo=" + tipo + ", profesor=" + profesor + "]";
	}
	
	
	
	

}
