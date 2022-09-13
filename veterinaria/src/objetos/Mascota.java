package objetos;

public class Mascota {

	private String nroPatente;
	private String tipo;
	private String nombre;
	private int edad;
	private Cliente cliente;

	public Mascota(String nroPatente, String tipo, String nombre, int edad, Cliente cliente) {
		this.nroPatente = nroPatente;
		this.tipo = tipo;
		this.nombre = nombre;
		this.edad = edad;
		this.cliente = cliente;
	}

	public String getNroPatente() {
		return nroPatente;
	}

	public void setNroPatente(String nroPatente) {
		this.nroPatente = nroPatente;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	@Override
	public String toString(){
		return this.nroPatente + "," + this.nombre + "," + this.tipo + "," + this.edad + "," + this.cliente.getCi() + "," + this.cliente.getNombre();
	}

	
}
