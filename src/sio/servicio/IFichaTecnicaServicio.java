package sio.servicio;

import java.util.List;

import sio.entidades.FichaTecnica;
import sio.entidades.Producto;


/**
 * Interface para las operaciones con las clases
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IFichaTecnicaServicio {
				
	 /**
	  * Permite guardar la ficha tecnica
	  * @param fichaTecnica objeto fichaTecnica
	  * @return objeto fichaTecnica guardado
	  * @throws Exception excepcion
	  */
	FichaTecnica guardarFichaTecnica(FichaTecnica fichaTecnica) throws Exception;
	
	/**
	 * M�todo buscarTodasFichaTecnica	
	 * @throws Exception excepci�n	 
	 * @return List<FichaTecnica> lista de FichaTecnica
	 */
	List<FichaTecnica> buscarTodasFichaTecnica() throws Exception;
	
	/**
	 * M�todo consultar por idFichaTecnica
	 * @throws Exception excepci�n	
	 * @param idFichaTecnica identificador de la FichaTecnica
	 * @return FichaTecnica objeto FichaTecnica completo
	 */
	FichaTecnica buscarFichaTecnicaPorId(Integer idFichaTecnica) throws Exception;
	
	/**
	 * M�todo consultar por producto
	 * @throws Exception excepci�n	
	 * @param productoParametro parametro del producto a buscar
	 * @return FichaTecnica objeto FichaTecnica completo
	 */
	List<FichaTecnica> buscarFichaTecnicaPorProducto(Producto productoParametro) throws Exception;
	
	/**
	 * M�todo eliminar ficha tecnica
	 * @throws Exception excepci�n	
	 * @param fichaTecnica objeto a eliminar	 
	 */
	void eliminarFichaTecnica(FichaTecnica fichaTecnica) throws Exception;
	
}