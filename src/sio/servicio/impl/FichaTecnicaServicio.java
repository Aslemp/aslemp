package sio.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.FichaTecnica;
import sio.entidades.Producto;
import sio.servicio.IFichaTecnicaServicio;

/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class FichaTecnicaServicio implements IFichaTecnicaServicio {
			
	/**
	 * Constructor
	 */
	public FichaTecnicaServicio() {
		
	}
			
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(FichaTecnicaServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public FichaTecnica guardarFichaTecnica(FichaTecnica fichaTecnica){
		LOG.info("Iniciando guardarClase");	
		try {			
			entityManager.getTransaction().begin();		    			
			if(fichaTecnica.getIdFichaTecnica()==null){
				entityManager.persist(fichaTecnica);
			}else{
				entityManager.merge(fichaTecnica);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException re) {
			re.printStackTrace();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return fichaTecnica;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FichaTecnica> buscarTodasFichaTecnica()
			throws Exception {
		LOG.info("Iniciando buscarTodasFichaTecnica");
		List<FichaTecnica> listaFichasTecnica = new ArrayList<FichaTecnica>();
		try {				
			Query query = entityManager.createNamedQuery(FichaTecnica.Q_BUSCAR_TODAS_FICHAS_TECNICAS);
			listaFichasTecnica = query.getResultList();			
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarTodasFichaTecnica");
		return listaFichasTecnica;
	}
		
	@Override
	public FichaTecnica buscarFichaTecnicaPorId(Integer idFichaTecnica)
			throws Exception {
		LOG.info("Iniciando buscarFichaTecnicaPorId");
		FichaTecnica fichaTecnicaResultado = new FichaTecnica();
		try {				
			fichaTecnicaResultado = entityManager.find(FichaTecnica.class, idFichaTecnica);					
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarFichaTecnicaPorId");
		return fichaTecnicaResultado;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<FichaTecnica> buscarFichaTecnicaPorProducto(Producto productoParametro)
			throws Exception {
		LOG.info("Iniciando buscarFichaTecnicaPorProducto");
		List<FichaTecnica> listaFichaTecnicaResultado = new  ArrayList<FichaTecnica>();		
		try {				
			Query query = entityManager.createNamedQuery(FichaTecnica.Q_BUSCAR_FICHA_TECNICA_POR_PRODUCTO);			
            query.setParameter("producto", productoParametro);
            listaFichaTecnicaResultado = query.getResultList();            
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarFichaTecnicaPorProducto");
		return listaFichaTecnicaResultado;
	}
	
	@Override
	public void eliminarFichaTecnica(FichaTecnica fichaTecnica)
			throws Exception {
		LOG.info("Iniciando eliminarFichaTecnica");		
		try {
			entityManager.getTransaction().begin();			
			entityManager.remove(entityManager.merge(fichaTecnica));
			entityManager.getTransaction().commit();
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin eliminarFichaTecnica");		
	}
}