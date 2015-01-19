package sio.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.Producto;
import sio.servicio.IProductoServicio;


/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class ProductoServicio implements IProductoServicio {
			
	/**
	 * Constructor
	 */
	public ProductoServicio() {
		
	}
	
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ProductoServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public Producto guardarProducto(Producto producto){
		try {
			entityManager.getTransaction().begin();
			if(producto.getIdProducto()==null){
				entityManager.persist(producto);
			}else{
				entityManager.merge(producto);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException e) {
			LOG.info("Fin guardar producto("
					+ producto.toString() + ")");			
		}
		return producto;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Producto buscarProductoPorCodigo(String codigo)
			throws Exception {
		LOG.info("Iniciando buscarProductoPorCodigo");
		List<Producto> listaProductoResultado = new  ArrayList<Producto>();
		Producto productoResultado = new  Producto();
		try {				
			Query query = entityManager.createNamedQuery(Producto.Q_BUSCAR_PRODUCTO_POR_CODIGO);			
            query.setParameter("codigo", codigo);
            listaProductoResultado = query.getResultList();
            if(listaProductoResultado!=null && listaProductoResultado.size()>0){
            	productoResultado=listaProductoResultado.get(0);
            }
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarProductoPorCodigo");
		return productoResultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> buscarTodosProductos()
			throws Exception {
		LOG.info("Iniciando buscarTodosProductos");
		List<Producto> listaProductos = new  ArrayList<Producto>();		
		try {				
			Query query = entityManager.createNamedQuery(Producto.Q_BUSCAR_TODOS_PRODUCTOS);
			listaProductos = query.getResultList();            
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarTodosProductos");
		return listaProductos;
	}
}