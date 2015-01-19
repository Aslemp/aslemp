package sio.servicio.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sio.entidades.SaldoReservadoOrden;
import sio.servicio.ISaldoReservadoServicio;

/**
 * Clase que permite persistir objetos en la base de datos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 16-nov-2014 08:20:00 p.m.
 */
@Stateless
public class SaldoReservadoServicio implements ISaldoReservadoServicio {
			
	/**
	 * Constructor
	 */
	public SaldoReservadoServicio() {
		
	}
			
	/**
	 * Constante que representa la instancia del Log
	 */
	private static final Logger LOG = LoggerFactory
			.getLogger(SaldoReservadoServicio.class);
	
	/**
	 * Entidad de persistencia inyectada
	 */
	@PersistenceContext(unitName="sioPU")
    private EntityManager entityManager;
	
	@Override
	public SaldoReservadoOrden guardarSaldoReservadoOrden(SaldoReservadoOrden saldoReservadoOrden){
		LOG.info("Iniciando guardarOrdenCompra");	
		try {			
			entityManager.getTransaction().begin();		    			
			if(saldoReservadoOrden.getIdSaldoReservadoOrden()==null){
				entityManager.persist(saldoReservadoOrden);
			}else{
				entityManager.merge(saldoReservadoOrden);
			}
			entityManager.getTransaction().commit();			
		} catch (RuntimeException re) {
			re.printStackTrace();			
		}catch (Exception e) {
			e.printStackTrace();
		}
		return saldoReservadoOrden;
	}
		
}