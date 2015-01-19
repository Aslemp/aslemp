package sio.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.Proveedor;
import sio.servicio.IProveedorServicio;


/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class ProveedorServicio implements IProveedorServicio {
			
	/**
	 * Constructor
	 */
	public ProveedorServicio() {
		
	}
	
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ProveedorServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public Proveedor guardarProveedor(Proveedor proveedor){
		try {
			entityManager.getTransaction().begin();		    			
			if(proveedor.getIdProveedor()==null){
				entityManager.persist(proveedor);
			}else{
				entityManager.merge(proveedor);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException e) {
			LOG.info("Fin guardar proveedor("
					+ proveedor.toString() + ")");			
		}
		return proveedor;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Proveedor> buscarTodosProveedores()
			throws Exception {
		LOG.info("Iniciando buscarTodosProveedores");
		List<Proveedor> listaProveedores = new ArrayList<Proveedor>();
		try {				
			Query query = entityManager.createNamedQuery(Proveedor.Q_BUSCAR_TODOS_PROVEEDORES);
			listaProveedores = query.getResultList();			
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarTodosProveedores");
		return listaProveedores;
	}
		
	@Override
	public Proveedor buscarProveedorPorId(Integer idProveedor)
			throws Exception {
		LOG.info("Iniciando buscarProveedorPorId");
		Proveedor proveedorResultado = new Proveedor();
		try {				
			proveedorResultado = entityManager.find(Proveedor.class, idProveedor);					
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarProveedorPorId");
		return proveedorResultado;
	}
}