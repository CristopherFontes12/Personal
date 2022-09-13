package PATRONES;

import java.sql.Date;

public class Programa {
	private int idPrograma;
	private String descripcion;
	private Date fechaIngreso;
	private int tiempo;

	private Estado estado;
	private Desarrollador desarrollador;

	public Programa(int idPrograma, String descripcion, Date fechaIngreso, int tiempo, Estado estado,
			Desarrollador desarrollador) {
		super();
		this.idPrograma = idPrograma;
		this.descripcion = descripcion;
		this.fechaIngreso = fechaIngreso;
		this.tiempo = tiempo;
		this.estado = estado;
		this.desarrollador = desarrollador;
	}

	public Programa() {

	}

	public int getIdPrograma() {
		return idPrograma;
	}

	public void setIdPrograma(int idPrograma) {
		this.idPrograma = idPrograma;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public int getTiempo() {
		return tiempo;
	}

	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Desarrollador getDesarrollador() {
		return desarrollador;
	}

	public void setDesarrollador(Desarrollador desarrollador) {
		this.desarrollador = desarrollador;
	}

	@Override
	public String toString() {
		return "Programa [idPrograma=" + idPrograma + ", descripcion=" + descripcion + ", fechaIngreso=" + fechaIngreso
				+ ", tiempo=" + tiempo + ", estado=" + estado + ", desarrollador=" + desarrollador + "]";
	}

}
