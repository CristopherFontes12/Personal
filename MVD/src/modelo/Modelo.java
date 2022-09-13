package modelo;

public class Modelo {

	private int numeroUno;
	private int numeroDos;
	private int resultado;
	
	
	
	public Modelo() {
		super();
	}



	public Modelo(int numeroUno, int numeroDos, int resultado) {
		super();
		this.numeroUno = numeroUno;
		this.numeroDos = numeroDos;
		this.resultado = resultado;
	}
	
	
	
	public int getNumeroUno() {
		return numeroUno;
	}
	public void setNumeroUno(int numeroUno) {
		this.numeroUno = numeroUno;
	}
	public int getNumeroDos() {
		return numeroDos;
	}
	public void setNumeroDos(int numeroDos) {
		this.numeroDos = numeroDos;
	}
	public int getResultado() {
		return resultado;
	}
	public void setResultado(int resultado) {
		this.resultado = resultado;
	}

	
	public int multiplicar() {
		
		this.resultado = this.numeroUno * this.numeroDos;
		return this.resultado;
		
	}
	
	
	
}
