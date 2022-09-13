package vista;

import modelo.TipoIva;

public class ArticuloVista {
	
	public void imprimirArticulo (String nombre, float precio, TipoIva iva) {
		System.out.println("==== Datos Articulo ====");
		System.out.println("Nombre : "+ nombre);
		System.out.println("Precio $ : "+ precio);
		System.out.println("Tipo de Iva : "+ iva);
		System.out.println("--- "+ iva.getIva()+ " %");
		System.out.println("----------- Fin Consulta -----------");
		System.out.println();
		
		
	}
	
	

}
