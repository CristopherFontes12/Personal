package PATRONES;

import java.sql.Date;

public class Desarrollador {

	private int cedula;
	private String nombre;
	private Date fechaIngreso;
	
	private Categoria categoria;

	public Desarrollador(int cedula, String nombre, Date fechaIngreso,  Categoria categoria) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.fechaIngreso = fechaIngreso;
		
		this.categoria = categoria;
	}

	public Desarrollador() {

	}

	public int getCedula() {
		return cedula;
	}

	public void setCedula(int cedula) {
		this.cedula = cedula;
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

	

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Desarrollador [cedula=" + cedula + ", nombre=" + nombre + ", fechaIngreso=" + fechaIngreso
				  + ", categoria=" + categoria + "]";
	}

}
