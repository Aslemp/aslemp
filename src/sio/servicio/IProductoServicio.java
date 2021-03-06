package sio.servicio;

import java.util.List;

import sio.entidades.Producto;


/**
 * Interface para las operaciones con los proveedores
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IProductoServicio {
				
	 /**
	  * Permite guardar los productos
	  * @param producto objeto producto
	  * @return objeto producto guardado
	  * @throws Exception excepcion
	  */
	Producto guardarProducto(Producto producto) throws Exception;
	
	/**
	 * M�todo consultar producto por codigo
	 * @throws Exception excepci�n	
	 * @param codigo , codigo del producto
	 * @return Producto objeto producto completo
	 */
	Producto buscarProductoPorCodigo(String codigo) throws Exception;
	
	/**
	 * M�todo consultar todos productos
	 * @throws Exception excepci�n		
	 * @return List<Producto> lista de productos
	 */
	List<Producto> buscarTodosProductos() throws Exception;
	
}