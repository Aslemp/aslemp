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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the entrada_mercancia database table.
 * 
 */
@Entity
@Table(name="entrada_salida_mercancia")
@NamedQueries({
	@NamedQuery(name=EntradaSalidaMercancia.Q_BUSCAR_TODAS_ENTRADA_MERCANCIA, query="SELECT e FROM EntradaSalidaMercancia e"),
	@NamedQuery(name = EntradaSalidaMercancia.Q_BUSCAR_ENTRADA_MERCANCIA_POR_CODIGO, query = "SELECT em FROM EntradaSalidaMercancia em WHERE em.producto = :producto") })
public class EntradaSalidaMercancia implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de instancia estática Q_BUSCAR_TODAS_ENTRADA_MERCANCIA
	 */
	public static final String Q_BUSCAR_TODAS_ENTRADA_MERCANCIA = "EntradaMercancia.buscarTodasEntradaMercancia";
	
	/**
	 * Variable de instancia estática Q_BUSCAR_ENTRADA_MERCANCIA_POR_CODIGO
	 */
	public static final String Q_BUSCAR_ENTRADA_MERCANCIA_POR_CODIGO = "EntradaMercancia.buscarEntradaMercanciaPorCodigo";

	@Id
	@SequenceGenerator(name="ENTRADA_MERCANCIA_IDENTRADA_GENERATOR", sequenceName="SEQ_IDE_ENTRADA_MERCANCIA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ENTRADA_MERCANCIA_IDENTRADA_GENERATOR")
	@Column(name="id_entrada_salida")
	private Integer idEntrada;

	private Integer cantidad;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_recibo")
	private Date fechaRecibo;

	private String lote;

	private String remision;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_producto")
	private Producto producto;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
	
	private Integer valor;
	
	@OneToMany(mappedBy="entradaSalidaMercancia")
	private List<RegistroEntradaSalida> registroEntradaSalidas;

	public EntradaSalidaMercancia() {
	}

	public Integer getIdEntrada() {
		return this.idEntrada;
	}

	public void setIdEntrada(Integer idEntrada) {
		this.idEntrada = idEntrada;
	}

	public Integer getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaRecibo() {
		return this.fechaRecibo;
	}

	public void setFechaRecibo(Date fechaRecibo) {
		this.fechaRecibo = fechaRecibo;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getRemision() {
		return this.remision;
	}

	public void setRemision(String remision) {
		this.remision = remision;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Proveedor getProveedor() {
		return this.proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	/**
	 * @return the valor
	 */
	public Integer getValor() {
		return valor;
	}

	/**
	 * @param valor the valor to set
	 */
	public void setValor(Integer valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idEntrada == null) ? 0 : idEntrada.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntradaSalidaMercancia other = (EntradaSalidaMercancia) obj;
		if (idEntrada == null) {
			if (other.idEntrada != null)
				return false;
		} else if (!idEntrada.equals(other.idEntrada))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EntradaMercancia [idEntrada=" + idEntrada + "]";
	}

}