package dto;

import java.util.ArrayList;
import java.util.List;

import modelo.TipoIva;

public class ArticuloBO {
	
	// lista de tipo de Articulos
			private List<ArticuloVO> articulos;
			// constructor se guarda en la lista 3 articulos
			public ArticuloBO() {
				articulos = new ArrayList<>();
				ArticuloVO art1= new ArticuloVO(0,"Azucar",35F,TipoIva.MINIMO);
				ArticuloVO art2= new ArticuloVO(1,"Jabon",42F,TipoIva.MAXIMO);
				ArticuloVO art3= new ArticuloVO(2,"Leche",38F,TipoIva.EXENTO);
				articulos.add(art1);
				articulos.add(art2);
				articulos.add(art3);
			}
			
			// elimina el articulo que se le pasa como parametro
			public void eliminarArticulo(ArticuloVO articulo) {
				articulos.remove(articulo.getId());
				System.out.println("Articulo "+articulo.getId()+ " -- "+ articulo.getNombre()+" eliminado satisfactoriamente");
			}
			
			// Obtiene toda la lista de articulos
			
			public List<ArticuloVO> obtenerArticulos(){
				return articulos;
			}
			
			// Obtiene un articulo de acuerdo al id pasado como parametro
			
			public ArticuloVO obtenerArticulo(int id) {
				return articulos.get(id);
			}

			// Actualizar el Articulo que se le pasa como parametro
			public void actualizarArticulo(ArticuloVO articulo) {
				articulos.get(articulo.getId()).setNombre(articulo.getNombre());
				articulos.get(articulo.getId()).setPrecio(articulo.getPrecio());
				articulos.get(articulo.getId()).setIva(articulo.getIva());
				System.out.println("Articulo id:"+articulo.getId()+" actualizado satisfactoriamente");
			}
		
		

}
