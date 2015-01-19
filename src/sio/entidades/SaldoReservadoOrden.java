package sio.entidades;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * The persistent class for the saldo_reservado_orden database table.
 * 
 */
@Entity
@Table(name="saldo_reservado_orden")
@NamedQuery(name="SaldoReservadoOrden.findAll", query="SELECT s FROM SaldoReservadoOrden s")
public class SaldoReservadoOrden implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SALDO_RESERVADO_ORDEN_IDSALDORESERVADOORDEN_GENERATOR", 
					   sequenceName="SEQ_IDE_SALDO_RESERVADO_ORDEN", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SALDO_RESERVADO_ORDEN_IDSALDORESERVADOORDEN_GENERATOR")
	@Column(name="id_saldo_reservado_orden")
	private Integer idSaldoReservadoOrden;

	@Column(name="saldo_reservado")
	private Integer saldoReservado;

	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_orden_compra")
	private OrdenProduccion ordenCompra;

	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_componente")
	private Producto producto;

	public SaldoReservadoOrden() {
	}

	public Integer getIdSaldoReservadoOrden() {
		return this.idSaldoReservadoOrden;
	}

	public void setIdSaldoReservadoOrden(Integer idSaldoReservadoOrden) {
		this.idSaldoReservadoOrden = idSaldoReservadoOrden;
	}

	public Integer getSaldoReservado() {
		return this.saldoReservado;
	}

	public void setSaldoReservado(Integer saldoReservado) {
		this.saldoReservado = saldoReservado;
	}

	public OrdenProduccion getOrdenCompra() {
		return this.ordenCompra;
	}

	public void setOrdenCompra(OrdenProduccion ordenCompra) {
		this.ordenCompra = ordenCompra;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idSaldoReservadoOrden == null) ? 0 : idSaldoReservadoOrden
						.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SaldoReservadoOrden other = (SaldoReservadoOrden) obj;
		if (idSaldoReservadoOrden == null) {
			if (other.idSaldoReservadoOrden != null)
				return false;
		} else if (!idSaldoReservadoOrden.equals(other.idSaldoReservadoOrden))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "SaldoReservadoOrden [idSaldoReservadoOrden="
				+ idSaldoReservadoOrden + "]";
	}
	
}