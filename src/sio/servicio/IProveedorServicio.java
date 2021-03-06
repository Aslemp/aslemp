package sio.servicio;

import java.util.List;

import sio.entidades.Proveedor;


/**
 * Interface para las operaciones con los proveedores
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IProveedorServicio {
				
	 /**
	  * Permite guardar los proveedores
	  * @param proveedores objeto proveedores
	  * @return objeto proveedores guardado
	  * @throws Exception excepcion
	  */
	Proveedor guardarProveedor(Proveedor proveedor) throws Exception;
	
	/**
	 * M�todo buscarTodosProveedores	
	 * @throws Exception excepci�n	 
	 * @return List<Proveedor> lista de Proveedores
	 */
	List<Proveedor> buscarTodosProveedores() throws Exception;
	
	/**
	 * M�todo consultar por idProveedor
	 * @throws Exception excepci�n	
	 * @param idProveedor identificador del proveedor
	 * @return Proveedor objeto proveedor completo
	 */
	Proveedor buscarProveedorPorId(Integer idProveedor) throws Exception;
}