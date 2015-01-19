package sio.servicio.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.Grupo;
import sio.servicio.IGrupoServicio;


/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class GrupoServicio implements IGrupoServicio {
			
	/**
	 * Constructor
	 */
	public GrupoServicio() {
		
	}
	
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(GrupoServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public Grupo guardarGrupo(Grupo grupo){
		try {
			entityManager.getTransaction().begin();		    			
			if(grupo.getIdGrupo()==null){
				entityManager.persist(grupo);
			}else{
				entityManager.merge(grupo);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException e) {
			LOG.info("Fin guardar grupo("
					+ grupo.toString() + ")");			
		}
		return grupo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Grupo> buscarTodosGrupos()
			throws Exception {
		LOG.info("Iniciando buscarTodosGrupos");
		List<Grupo> listaGrupos = new ArrayList<Grupo>();
		try {				
			Query query = entityManager.createNamedQuery(Grupo.Q_BUSCAR_TODOS_GRUPOS);
			listaGrupos = query.getResultList();			
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarTodosGrupos");
		return listaGrupos;
	}
		
	@Override
	public Grupo buscarGrupoPorId(Integer idGrupo)
			throws Exception {
		LOG.info("Iniciando buscarGrupoPorId");
		Grupo grupoResultado = new Grupo();
		try {				
			grupoResultado = entityManager.find(Grupo.class, idGrupo);					
		} catch (RuntimeException re) {
			re.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();	
		}
		LOG.info("Fin buscarGrupoPorId");
		return grupoResultado;
	}
}