package sio.servicio.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.Clase;
import sio.servicio.IClaseServicio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class ClaseServicio implements IClaseServicio {
			
	/**
	 * Constructor
	 */
	public ClaseServicio() {
		
	}
			
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(ClaseServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public Clase guardarClase(Clase clase){
		LOG.info("Iniciando guardarClase");	
		try {			
			entityManager.getTransaction().begin();		    			
			if(clase.getIdClase()==null){
				entityManager.persist(clase);
			}else{
				entityManager.merge(clase);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException re) {
			re.printStackTrace();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return clase;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Clase> buscarTodasClases()
			throws Exception {
		LOG.info("Iniciando buscarClases");
		List<Clase> listaClases = new ArrayList<Clase>();
		try {				
			Query query = entityManager.createNamedQuery(Clase.Q_BUSCAR_TODAS_CLASES);
			listaClases = query.getResultList();			
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarClases");
		return listaClases;
	}
		
	@Override
	public Clase buscarClasePorId(Integer idClase)
			throws Exception {
		LOG.info("Iniciando buscarClasePorId");
		Clase claseResultado = new Clase();
		try {				
			claseResultado = entityManager.find(Clase.class, idClase);					
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarClasePorId");
		return claseResultado;
	}
}