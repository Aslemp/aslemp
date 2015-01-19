package sio.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;


/**
 * The persistent class for the registro_entrada_salida database table.
 * 
 */
@Entity
@Table(name="registro_entrada_salida")
@NamedQueries({
	@NamedQuery(name=RegistroEntradaSalida.Q_BUSCAR_ENTRADA_SALIDA_MERCANCIA, query="SELECT res FROM RegistroEntradaSalida res"),
	@NamedQuery(name = RegistroEntradaSalida.Q_BUSCAR_ENTRADA_MERCANCIA_POR_PRODUCTO, query = "SELECT res FROM RegistroEntradaSalida res WHERE res.producto = :producto") })
public class RegistroEntradaSalida implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de instancia estática Q_BUSCAR_ENTRADA_SALIDA_MERCANCIA
	 */
	public static final String Q_BUSCAR_ENTRADA_SALIDA_MERCANCIA = "RegistroEntradaSalida.buscarTodosRegistrosEntradaSalida";
	
	/**
	 * Variable de instancia estática Q_BUSCAR_ENTRADA_MERCANCIA_POR_PRODUCTO
	 */
	public static final String Q_BUSCAR_ENTRADA_MERCANCIA_POR_PRODUCTO = "RegistroEntradaSalida.buscarEntradaSalidaPorProducto";
	
	@Id
	@SequenceGenerator(name="REGISTRO_ENTRADA_SALIDA_IDREGENTRADASALIDA_GENERATOR", sequenceName="SEQ_IDE_REG_ENTRADA_SALIDA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REGISTRO_ENTRADA_SALIDA_IDREGENTRADASALIDA_GENERATOR")
	@Column(name="id_reg_entrada_salida")
	private Integer idRegEntradaSalida;

	@Column(name="saldo_final")
	private Integer saldoFinal;

	@Column(name="saldo_inicial")
	private Integer saldoInicial;

	@Column(name="tipo_movimiento")
	private String tipoMovimiento;
	
	@Column(name="cantidad")
	private Integer cantidad;
		
	@Temporal(TemporalType.DATE)
	@Column(name="fecha_registro")
	private Date fechaRegistro;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_entrada_salida")
	private EntradaSalidaMercancia entradaSalidaMercancia;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_producto")
	private Producto producto;

	public RegistroEntradaSalida() {
	}

	public Integer getIdRegEntradaSalida() {
		return this.idRegEntradaSalida;
	}

	public void setIdRegEntradaSalida(Integer idRegEntradaSalida) {
		this.idRegEntradaSalida = idRegEntradaSalida;
	}

	public Integer getSaldoFinal() {
		return this.saldoFinal;
	}

	public void setSaldoFinal(Integer saldoFinal) {
		this.saldoFinal = saldoFinal;
	}

	public Integer getSaldoInicial() {
		return this.saldoInicial;
	}

	public void setSaldoInicial(Integer saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getTipoMovimiento() {
		return this.tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public EntradaSalidaMercancia getEntradaSalidaMercancia() {
		return this.entradaSalidaMercancia;
	}

	public void setEntradaSalidaMercancia(EntradaSalidaMercancia entradaSalidaMercancia) {
		this.entradaSalidaMercancia = entradaSalidaMercancia;
	}

	public Producto getProducto() {
		return this.producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the cantidad
	 */
	public Integer getCantidad() {
		return cantidad;
	}

	/**
	 * @param cantidad the cantidad to set
	 */
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * @return the fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @param fechaRegistro the fechaRegistro to set
	 */
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((idRegEntradaSalida == null) ? 0 : idRegEntradaSalida
						.hashCode());
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
		RegistroEntradaSalida other = (RegistroEntradaSalida) obj;
		if (idRegEntradaSalida == null) {
			if (other.idRegEntradaSalida != null)
				return false;
		} else if (!idRegEntradaSalida.equals(other.idRegEntradaSalida))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RegistroEntradaSalida [idRegEntradaSalida="
				+ idRegEntradaSalida + "]";
	}

}