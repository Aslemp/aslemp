package sio.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the orden_compra database table.
 * 
 */
@Entity
@Table(name="orden_compra")
@NamedQuery(name="OrdenProduccion.findAll", query="SELECT o FROM OrdenProduccion o")
public class OrdenProduccion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ORDEN_COMPRA_IDORDENCOMPRA_GENERATOR", sequenceName="SEQ_IDE_ORDEN_COMPRA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ORDEN_COMPRA_IDORDENCOMPRA_GENERATOR")
	@Column(name="id_orden_compra")
	private Integer idOrdenProduccion;

	private Integer cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_entrega")
	private Date fechaEntrega;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_inicio")
	private Date fechaInicio;

	private Boolean reservado;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_prod_ficha_tecnica")
	private Producto producto;
	
	@OneToMany(mappedBy="ordenCompra")
	private List<SaldoReservadoOrden> saldoReservadoOrdens;
	
	private String codigo;
	
	public OrdenProduccion() {
	}

	

	/**
	 * @return the idOrdenProduccion
	 */
	public Integer getIdOrdenProduccion() {
		return idOrdenProduccion;
	}



	/**
	 * @param idOrdenProduccion the idOrdenProduccion to set
	 */
	public void setIdOrdenProduccion(Integer idOrdenProduccion) {
		this.idOrdenProduccion = idOrdenProduccion;
	}



	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaEntrega() {
		return this.fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Boolean getReservado() {
		return this.reservado;
	}

	public void setReservado(Boolean reservado) {
		this.reservado = reservado;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public List<SaldoReservadoOrden> getSaldoReservadoOrdens() {
		return this.saldoReservadoOrdens;
	}

	public void setSaldoReservadoOrdens(List<SaldoReservadoOrden> saldoReservadoOrdens) {
		this.saldoReservadoOrdens = saldoReservadoOrdens;
	}
	
	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @param codigo the codigo to set
	 */
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idOrdenProduccion == null) ? 0 : idOrdenProduccion.hashCode());
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
		OrdenProduccion other = (OrdenProduccion) obj;
		if (idOrdenProduccion == null) {
			if (other.idOrdenProduccion != null)
				return false;
		} else if (!idOrdenProduccion.equals(other.idOrdenProduccion))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "OrdenProduccion [idOrdenProduccion=" + idOrdenProduccion + "]";
	}
	
	
	
}