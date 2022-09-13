package ejercicio3;

public class Paquete {

	//Creo los atributos
	private int idPaquete;	
	private int codigo;
	private float peso;
	//Referencia a la clase:
	private Cartero cartero;
	
	//Genero Getters y Setters
	public int getIdPaquete() {
		return idPaquete;
	}
	public void setIdPaquete(int idPaquete) {
		this.idPaquete = idPaquete;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public float getPeso() {
		return peso;
	}
	public void setPeso(float peso) {
		this.peso = peso;
	}
	public Cartero getCartero() {
		return cartero;
	}
	public void setCartero(Cartero cartero) {
		this.cartero = cartero;
	}
	
	//Genero el Constructor con parámetros
	public Paquete(int idPaquete, int codigo, float peso, Cartero cartero) {
		super();
		this.idPaquete = idPaquete;
		this.codigo = codigo;
		this.peso = peso;
		this.cartero = cartero;
	}
	
	//Genero el Constructor vacío
	public Paquete() {
		
	}
	
	//Genero el to String
	@Override
	public String toString() {
		return "Paquete [idPaquete=" + idPaquete + ", codigo=" + codigo + ", peso=" + peso + ", cartero=" + cartero
				+ "]";
	}
	
}
