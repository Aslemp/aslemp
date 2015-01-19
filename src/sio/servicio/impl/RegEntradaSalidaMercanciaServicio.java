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
import sio.entidades.RegistroEntradaSalida;
import sio.servicio.IRegEntradaSalidaMercanciaServicio;

/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class RegEntradaSalidaMercanciaServicio implements IRegEntradaSalidaMercanciaServicio {
			
	/**
	 * Constructor
	 */
	public RegEntradaSalidaMercanciaServicio() {
		
	}
			
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(RegEntradaSalidaMercanciaServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public RegistroEntradaSalida guardarRegistroEntradaSalida(RegistroEntradaSalida registroEntradaSalida){
		LOG.info("Iniciando guardarEntradaMercancia");	
		try {			
			entityManager.getTransaction().begin();
			if(registroEntradaSalida.getIdRegEntradaSalida()==null){
				entityManager.persist(registroEntradaSalida);
			}else{
				entityManager.merge(registroEntradaSalida);
			}					
			entityManager.getTransaction().commit();			
		} catch (RuntimeException re) {
			re.printStackTrace();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return registroEntradaSalida;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<RegistroEntradaSalida> buscarRegEntradaSalidaPorProducto(Producto productoConsultar)
			throws Exception {
		LOG.info("Iniciando buscarRegEntradaSalidaPorProducto");
		List<RegistroEntradaSalida> listaRegEntradaSalidaResultado = new  ArrayList<RegistroEntradaSalida>();		
		try {				
			Query query = entityManager.createNamedQuery(RegistroEntradaSalida.Q_BUSCAR_ENTRADA_MERCANCIA_POR_PRODUCTO);			
            query.setParameter("producto", productoConsultar);
            listaRegEntradaSalidaResultado = query.getResultList();            
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarRegEntradaSalidaPorProducto");
		return listaRegEntradaSalidaResultado;
	}
}