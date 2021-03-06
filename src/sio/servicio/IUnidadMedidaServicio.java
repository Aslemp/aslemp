package sio.servicio;

import java.util.List;

import sio.entidades.UnidadMedida;


/**
 * Interface para las operaciones con las unidades de medida
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IUnidadMedidaServicio {
				
	 /**
	  * Permite guardar las unidades de medida
	  * @param unidadMedida objeto unidadMedida
	  * @return objeto unidadMedida guardado
	  * @throws Exception excepcion
	  */
	UnidadMedida guardarUnidadMedida(UnidadMedida unidadMedida) throws Exception;
	
	/**
	 * M�todo buscarTodasUnidadesMedida	
	 * @throws Exception excepci�n	 
	 * @return List<UnidadMedida> lista de unidades de medida
	 */
	List<UnidadMedida> buscarTodasUnidadesMedida() throws Exception;
	
	/**
	 * M�todo consultar por idUnidadMedida
	 * @throws Exception excepci�n	
	 * @param idUnidadMedida identificador de la unidad de medida
	 * @return UnidadMedida objeto unidadMedida completo
	 */
	UnidadMedida buscarUnidadMedidaPorId(Integer idUnidadMedida) throws Exception;
	
}