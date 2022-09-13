package PRU001;

import java.sql.Date;

public class Persona {
	private int idPersona;
	private String nombre;
	private Date fechaIngreso;
	private Date fechaNacimiento;
	private TipoClase tipoclase;
	private Profesor profesor;

	public Persona(int idPersona, String nombre, Date fechaIngreso, Date fechaNacimiento, TipoClase tipoclase,
			Profesor profesor) {

		this.idPersona = idPersona;
		this.nombre = nombre;
		this.fechaIngreso = fechaIngreso;
		this.fechaNacimiento = fechaNacimiento;
		this.tipoclase = tipoclase;
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
	public TipoClase getTipoclase() {
		return tipoclase;
	}
	public void setTipoclase(TipoClase tipoclase) {
		this.tipoclase = tipoclase;
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
				+ ", fechaNacimiento=" + fechaNacimiento + ", tipoclase=" + tipoclase + ", profesor=" + profesor + "]";
	}
	
	

}
