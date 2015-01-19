package sio.servicio;

import java.util.List;

import sio.entidades.Producto;
import sio.entidades.RegistroEntradaSalida;


/**
 * Interface para las operaciones con los registros entrada-salida
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IRegEntradaSalidaMercanciaServicio {
				
	/**
	 * Permite guardar los registros entradas de mercancia
	 * @param clase objeto registroEntradaSalida
	 * @return objeto clase guardado
	 * @throws Exception excepcion
	 */
	RegistroEntradaSalida guardarRegistroEntradaSalida(RegistroEntradaSalida registroEntradaSalida) throws Exception;
	
	/**
	 * Método buscarRegEntradaSalidaPorProducto
	 * @param clase objeto productoConsulta, producto a consultar
	 * @throws Exception excepción	 
	 * @return List<RegistroEntradaSalida> lista de Proveedores
	 */
	List<RegistroEntradaSalida> buscarRegEntradaSalidaPorProducto(Producto productoConsultar) throws Exception;
}