package examenFeb2021;

import java.sql.Date;

public class Cliente {
	
	private int idCliente;
	private String Nombre;
	private Date fechaIngreso;
	private String ciudad;
	private Vendedor vendedor;
	
	public Cliente() {
		super();
	}
	public Cliente(int idCliente, String nombre, Date fechaIngreso, String ciudad, Vendedor vendedor) {
		super();
		this.idCliente = idCliente;
		Nombre = nombre;
		this.fechaIngreso = fechaIngreso;
		this.ciudad = ciudad;
		this.vendedor = vendedor;
	}

	
	
	public Cliente(String nombre, Date fechaIngreso, String ciudad, Vendedor vendedor) {
		super();
		Nombre = nombre;
		this.fechaIngreso = fechaIngreso;
		this.ciudad = ciudad;
		this.vendedor = vendedor;
	}
	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}
	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	@Override
	public String toString() {
		return "idCliente=" + idCliente + ", Nombre=" + Nombre + ", fechaIngreso=" + fechaIngreso + ", ciudad="
				+ ciudad + ", vendedor=" + vendedor;
	}
	
	
}
