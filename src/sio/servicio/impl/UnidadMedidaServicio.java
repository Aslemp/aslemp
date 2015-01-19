package sio.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.UnidadMedida;
import sio.servicio.IUnidadMedidaServicio;

/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class UnidadMedidaServicio implements IUnidadMedidaServicio {
			
	/**
	 * Constructor
	 */
	public UnidadMedidaServicio() {
		
	}
			
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(UnidadMedidaServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public UnidadMedida guardarUnidadMedida(UnidadMedida unidadMedida){
		LOG.info("Iniciando guardarUnidadMedida");	
		try {			
			entityManager.getTransaction().begin();		    			
			if(unidadMedida.getIdUnidad()==null){
				entityManager.persist(unidadMedida);
			}else{
				entityManager.merge(unidadMedida);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException re) {
			re.printStackTrace();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return unidadMedida;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<UnidadMedida> buscarTodasUnidadesMedida()
			throws Exception {
		LOG.info("Iniciando buscarTodasUnidadesMedida");
		List<UnidadMedida> listaUnidadesMedida = new ArrayList<UnidadMedida>();
		try {				
			Query query = entityManager.createNamedQuery(UnidadMedida.Q_BUSCAR_TODAS_UNIDADES);
			listaUnidadesMedida = query.getResultList();			
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarTodasUnidadesMedida");
		return listaUnidadesMedida;
	}
		
	@Override
	public UnidadMedida buscarUnidadMedidaPorId(Integer idUnidadMedida)
			throws Exception {
		LOG.info("Iniciando buscarUnidadesMedidaPorId");
		UnidadMedida unidadMedidaResultado = new UnidadMedida();
		try {				
			unidadMedidaResultado = entityManager.find(UnidadMedida.class, idUnidadMedida);					
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarUnidadesMedidaPorId");
		return unidadMedidaResultado;
	}
}