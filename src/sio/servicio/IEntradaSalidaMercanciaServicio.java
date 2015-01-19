package sio.servicio;

import java.util.List;

import sio.entidades.EntradaSalidaMercancia;


/**
 * Interface para las operaciones con las clases
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IEntradaSalidaMercanciaServicio {
				
	 /**
	  * Permite guardar las entradas de mercancia
	  * @param clase objeto entradaMercancia
	  * @return objeto clase guardado
	  * @throws Exception excepcion
	  */
	EntradaSalidaMercancia guardarEntradaMercancia(EntradaSalidaMercancia entradaMercancia) throws Exception;
	
	/**
	 * Método buscarTodasEntradasMercancia	
	 * @throws Exception excepción	 
	 * @return List<Clase> lista de entradas de mercancia
	 */
	List<EntradaSalidaMercancia> buscarTodasEntradasMercancia() throws Exception;		
}