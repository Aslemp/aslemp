package sio.servicio.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.OrdenProduccion;
import sio.servicio.IOrdenProduccionServicio;

/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class OrdenProduccionServicio implements IOrdenProduccionServicio {
			
	/**
	 * Constructor
	 */
	public OrdenProduccionServicio() {
		
	}
			
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(OrdenProduccionServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public OrdenProduccion guardarOrdenProduccion(OrdenProduccion ordenProduccion){
		LOG.info("Iniciando guardarOrdenCompra");	
		try {			
			entityManager.getTransaction().begin();		    			
			if(ordenProduccion.getIdOrdenProduccion()==null){
				entityManager.persist(ordenProduccion);
			}else{
				entityManager.merge(ordenProduccion);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException re) {
			re.printStackTrace();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return ordenProduccion;
	}
		
}