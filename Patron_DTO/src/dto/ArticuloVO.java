package dto;

import modelo.TipoIva;

public class ArticuloVO {
	
	// Clace Value Object para Articulo, atributos, constructor, getters
	// setters y metodo toString
	private int id;
	private String nombre;
	private float precio;
	private TipoIva iva;
	
	public ArticuloVO(int id,String nombre,float precio,TipoIva iva) {
		this.id=id;
		this.nombre=nombre;
		this.precio=precio;
		this.iva=iva;
	}
	public int getId() {
		return id;
	}
    public void setId(int id) {
    	this.id=id;
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
		return "id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", iva=" + iva;
	}
	
	
	

}
