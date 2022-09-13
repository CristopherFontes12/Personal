package dto;

import controller.ArticuloControlador;
import modelo.Articulo;
import modelo.TipoIva;
import vista.ArticuloVista;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
			
			
			// Objeto BO
			ArticuloBO articulo = new ArticuloBO();
			
			//obtiene todos los articulos
			 articulo.obtenerArticulos().forEach(System.out::println);
			 
			// actualiza un articulo - Cambio azucar por Azucarlito
				System.out.println("****");
				ArticuloVO artaux = articulo.obtenerArticulo(0);
				artaux.setNombre("Azucarlito x 1kg.");
				articulo.actualizarArticulo(artaux);
				
				// obtiene un Articulo
				System.out.println("****");
				artaux=articulo.obtenerArticulo(0);
				System.out.println(artaux);
				
				//elimina un Articulo
				System.out.println("****");
				artaux=articulo.obtenerArticulo(0);
				articulo.eliminarArticulo(artaux);
				
				//obtiene todos los Articulos
				 articulo.obtenerArticulos().forEach(System.out::println);

		}
	
		
		
	}	
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*	
		//Objeto vista, y modelo creado con el metodo estatico
		
		Articulo modelo = llenarDatosArticulo();
		ArticuloVista vista = new ArticuloVista();
		
		// Se crea un objeto controlador y se pasa el modelo y la vista
		
		ArticuloControlador controlador = new ArticuloControlador(modelo, vista);
		
		// Se muestra los datos del articulo
		
		controlador.actualizarVista();
		
		
		// Se actualiza un articulo y se muestra de nuevo los datos
		
		controlador.setNombre("Pasta de Diente");
		controlador.setPrecio(95F);
		controlador.setIva(TipoIva.MAXIMO);
		controlador.actualizarVista();
		
	}
	

		// Metodo estatico que retorna una Articilo con sus datos
		
		private static Articulo llenarDatosArticulo() {
			Articulo articulo = new Articulo();
			articulo.setNombre("Yerba la Mejor");
			articulo.setPrecio(160F);
			articulo.setIva(TipoIva.MINIMO);
			return articulo;
		}

	*/

