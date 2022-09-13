package controller;
import modelo.Articulo;
import modelo.TipoIva;
import vista.ArticuloVista;


public class ArticuloControlador {

	// Objeto vista y modelo
	private ArticuloVista vista;
	private Articulo modelo;

	
	
	// Constructor para inicializar el modelo y la vista
	
	public ArticuloControlador(Articulo modelo, ArticuloVista vista) {
		super();
		this.vista = vista;
		this.modelo = modelo;
	}
	
	// Getters y Setters para el modelo (Articulo)

	public String getNombre() {
		return modelo.getNombre();
	}
	public void setNombre(String nombre) {
		this.modelo.setNombre(nombre);
	}
	public float getPrecio() {
		return modelo.getPrecio();
	}
	public void setPrecio(float precio) {
		this.modelo.setPrecio(precio);
	}
	public TipoIva getIva() {
		return modelo.getIva();
	}
	public void setIva(TipoIva iva) {
		this.modelo.setIva(iva);;
	}
	

	
// Pasa el modelo a la vista para presetar los datos
	
	public void actualizarVista() {
		
		vista.imprimirArticulo(modelo.getNombre(),modelo.getPrecio(),modelo.getIva());
		
		
	}


	
	
}
