package sio.servicio;

import sio.entidades.Clase;
import java.util.List;


/**
 * Interface para las operaciones con las clases
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IClaseServicio {
				
	 /**
	  * Permite guardar las clases
	  * @param clase objeto clase
	  * @return objeto clase guardado
	  * @throws Exception excepcion
	  */
	Clase guardarClase(Clase clase) throws Exception;
	
	/**
	 * M�todo buscarTodasClases	
	 * @throws Exception excepci�n	 
	 * @return List<Clase> lista de clases
	 */
	List<Clase> buscarTodasClases() throws Exception;
	
	/**
	 * M�todo consultar por idClase
	 * @throws Exception excepci�n	
	 * @param idClase identificador de la clase
	 * @return Clase objeto clase completo
	 */
	Clase buscarClasePorId(Integer idClase) throws Exception;
	
}