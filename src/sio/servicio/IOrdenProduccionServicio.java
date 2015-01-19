package sio.servicio;

import sio.entidades.OrdenProduccion;


/**
 * Interface para las operaciones con las clases
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IOrdenProduccionServicio {
				
	 /**
	  * Permite guardar la orden de produccion
	  * @param ordenCompra objeto ordenProduccion
	  * @return objeto ordenProduccion guardado
	  * @throws Exception excepcion
	  */
	OrdenProduccion guardarOrdenProduccion(OrdenProduccion ordenProduccion) throws Exception;
			
}