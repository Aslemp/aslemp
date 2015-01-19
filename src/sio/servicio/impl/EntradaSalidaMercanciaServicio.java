package sio.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.EntradaSalidaMercancia;
import sio.servicio.IEntradaSalidaMercanciaServicio;

/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class EntradaSalidaMercanciaServicio implements IEntradaSalidaMercanciaServicio {
			
	/**
	 * Constructor
	 */
	public EntradaSalidaMercanciaServicio() {
		
	}
			
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(EntradaSalidaMercanciaServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public EntradaSalidaMercancia guardarEntradaMercancia(EntradaSalidaMercancia entradaMercancia){
		LOG.info("Iniciando guardarEntradaMercancia");	
		try {			
			entityManager.getTransaction().begin();
			if(entradaMercancia.getIdEntrada()==null){
				entityManager.persist(entradaMercancia);
			}else{
				entityManager.merge(entradaMercancia);
			}					
			entityManager.getTransaction().commit();			
		} catch (RuntimeException re) {
			re.printStackTrace();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return entradaMercancia;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<EntradaSalidaMercancia> buscarTodasEntradasMercancia()
			throws Exception {
		LOG.info("Iniciando buscarTodasEntradasMercancia");
		List<EntradaSalidaMercancia> listaEntradaMercancia = new ArrayList<EntradaSalidaMercancia>();
		try {				
			Query query = entityManager.createNamedQuery(EntradaSalidaMercancia.Q_BUSCAR_TODAS_ENTRADA_MERCANCIA);			
			listaEntradaMercancia = query.getResultList();			
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarTodasEntradasMercancia");
		return listaEntradaMercancia;
	}		
}