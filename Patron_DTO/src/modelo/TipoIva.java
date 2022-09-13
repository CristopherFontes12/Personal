package modelo;

public enum TipoIva {
	
	
	MAXIMO("maximo",22),MINIMO("minimo",10),EXENTO("exento",0);
	private String nombre;
	private int iva;
	private TipoIva(String nombre, int iva) {
		
		this.nombre = nombre;
		this.iva = iva;
		
	}
	public String getNombre() {
		return nombre;
	}
	public int getIva() {
		return iva;
	}
	

}
