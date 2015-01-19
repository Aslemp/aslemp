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
	 * Método buscarTodasFichaTecnica	
	 * @throws Exception excepción	 
	 * @return List<FichaTecnica> lista de FichaTecnica
	 */
	List<FichaTecnica> buscarTodasFichaTecnica() throws Exception;
	
	/**
	 * Método consultar por idFichaTecnica
	 * @throws Exception excepción	
	 * @param idFichaTecnica identificador de la FichaTecnica
	 * @return FichaTecnica objeto FichaTecnica completo
	 */
	FichaTecnica buscarFichaTecnicaPorId(Integer idFichaTecnica) throws Exception;
	
	/**
	 * Método consultar por producto
	 * @throws Exception excepción	
	 * @param productoParametro parametro del producto a buscar
	 * @return FichaTecnica objeto FichaTecnica completo
	 */
	List<FichaTecnica> buscarFichaTecnicaPorProducto(Producto productoParametro) throws Exception;
	
	/**
	 * Método eliminar ficha tecnica
	 * @throws Exception excepción	
	 * @param fichaTecnica objeto a eliminar	 
	 */
	void eliminarFichaTecnica(FichaTecnica fichaTecnica) throws Exception;
	
}