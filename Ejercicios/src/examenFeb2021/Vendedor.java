package examenFeb2021;

public class Vendedor {
	
	public enum Zona {
		NORTE,
		SUR,
		CENTRO,
		ESTE,
		OESTE
	}

	private int idVendedor;
	private String nombre;
	private Zona zona;
	
	public Vendedor() {
		super();
	}
	
	
	public Vendedor(int idVendedor, String nombre, Zona zona) {
		super();
		this.idVendedor = idVendedor;
		this.nombre = nombre;
		this.zona = zona;
	}
	public int getIdVendedor() {
		return idVendedor;
	}
	public void setIdVendedor(int idVendedor) {
		this.idVendedor = idVendedor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Zona getZona() {
		return zona;
	}
	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	@Override
	public String toString() {
		return "idVendedor= " + idVendedor + ", nombre= " + nombre + ", zona= " + zona;
	}
	
	
	
	
	
}
