package modelo;

public class Articulo {
	
	private String nombre;
	private float precio;
	private TipoIva iva;
	
	

	
	public Articulo() {
		super();
	}


	public Articulo(String nombre, float precio, TipoIva iva) {
		super();
		this.nombre = nombre;
		this.precio = precio;
		this.iva = iva;
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public float getPrecio() {
		return precio;
	}
	public void setPrecio(float precio) {
		this.precio = precio;
	}
	public TipoIva getIva() {
		return iva;
	}
	public void setIva(TipoIva iva) {
		this.iva = iva;
	}


	@Override
	public String toString() {
		return "Articulo [nombre=" + nombre + ", precio=" + precio + ", iva=" + iva + "]";
	}
	
	
	
	

}
