package sio.servicio;

import sio.entidades.SaldoReservadoOrden;

/**
 * Interface para las operaciones con las clases
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface ISaldoReservadoServicio {
				
	 /**
	  * Permite guardar la información asociada al saldo reservado
	  * @param saldoReservadoOrden objeto saldoReservadoOrden
	  * @return objeto saldoReservadoOrden guardado
	  * @throws Exception excepcion
	  */
	SaldoReservadoOrden guardarSaldoReservadoOrden(SaldoReservadoOrden saldoReservadoOrden) throws Exception;
			
}