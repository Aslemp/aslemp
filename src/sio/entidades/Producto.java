package sio.entidades;

import java.io.Serializable;
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
import javax.persistence.Transient;

/**
 * The persistent class for the producto database table.
 * 
 */
@Entity
@NamedQueries({
	@NamedQuery(name=Producto.Q_BUSCAR_TODOS_PRODUCTOS, query="SELECT p FROM Producto p ORDER BY p.nombre"),
	@NamedQuery(name = Producto.Q_BUSCAR_PRODUCTO_POR_CODIGO, query = "SELECT p FROM Producto p WHERE p.codigo = :codigo") })
public class Producto implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de instancia estática Q_BUSCAR_TODOS_PRODUCTOS
	 */
	public static final String Q_BUSCAR_TODOS_PRODUCTOS = "Producto.buscarTodosProductos";
	
	/**
	 * Variable de instancia estática Q_BUSCAR_PRODUCTO_POR_CODIGO
	 */
	public static final String Q_BUSCAR_PRODUCTO_POR_CODIGO = "Producto.buscarProductoPorCodigo";
	
	@Id
	@SequenceGenerator(name="PRODUCTO_IDPRODUCTO_GENERATOR", sequenceName="SEQ_IDE_PRODUCTO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PRODUCTO_IDPRODUCTO_GENERATOR")
	@Column(name="id_producto")
	private Integer idProducto;

	private String codigo;

	private String lote;

	private String nombre;

	private String peso;

	@Column(name="promedio_venta")
	private Integer promedioVenta;

	@Column(name="tiempo_entrega")
	private String tiempoEntrega;

	private String ubicacion;
	
	private Integer saldo;
	
	@Column(name="saldo_disponible")
	private Integer saldoDisponible;
	
	@Column(name="cantidad_componente")
	private Integer cantidadComponente;
	
	@Transient
	private Integer cantidadTotalOrden;
	
	@Transient
	private Integer cantidadFaltanteOrden;
	
	@Transient
	private Integer cantidadComponenteVisualizar;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_unidad")
	private UnidadMedida unidadMedida;
		
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_grupo")
	private Grupo grupo;
	
	@OneToMany(mappedBy="producto")
	private List<RegistroEntradaSalida> registroEntradaSalidas;
		
	@OneToMany(mappedBy="producto")
	private List<FichaTecnica> productosFichaTecnica;
	
	@OneToMany(mappedBy="componente")
	private List<FichaTecnica> componentesFichaTecnica;
		
	public Producto() {
	}

	public Integer getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Integer idProducto) {
		this.idProducto = idProducto;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getLote() {
		return this.lote;
	}

	public void setLote(String lote) {
		this.lote = lote;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPeso() {
		return this.peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public Integer getPromedioVenta() {
		return this.promedioVenta;
	}

	public void setPromedioVenta(Integer promedioVenta) {
		this.promedioVenta = promedioVenta;
	}

	public String getTiempoEntrega() {
		return this.tiempoEntrega;
	}

	public void setTiempoEntrega(String tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	public String getUbicacion() {
		return this.ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	
	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}

	/**
	 * @return the saldo
	 */
	public Integer getSaldo() {
		return saldo;
	}

	/**
	 * @param saldo the saldo to set
	 */
	public void setSaldo(Integer saldo) {
		this.saldo = saldo;
	}

	

	/**
	 * @return the cantidadComponente
	 */
	public Integer getCantidadComponente() {
		return cantidadComponente;
	}

	/**
	 * @param cantidadComponente the cantidadComponente to set
	 */
	public void setCantidadComponente(Integer cantidadComponente) {
		this.cantidadComponente = cantidadComponente;
	}

	/**
	 * @return the saldoDisponible
	 */
	public Integer getSaldoDisponible() {
		return saldoDisponible;
	}

	/**
	 * @param saldoDisponible the saldoDisponible to set
	 */
	public void setSaldoDisponible(Integer saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	/**
	 * @return the cantidadTotalOrden
	 */
	public Integer getCantidadTotalOrden() {
		return cantidadTotalOrden;
	}

	/**
	 * @param cantidadTotalOrden the cantidadTotalOrden to set
	 */
	public void setCantidadTotalOrden(Integer cantidadTotalOrden) {
		this.cantidadTotalOrden = cantidadTotalOrden;
	}

	/**
	 * @return the cantidadFaltanteOrden
	 */
	public Integer getCantidadFaltanteOrden() {
		return cantidadFaltanteOrden;
	}

	/**
	 * @param cantidadFaltanteOrden the cantidadFaltanteOrden to set
	 */
	public void setCantidadFaltanteOrden(Integer cantidadFaltanteOrden) {
		this.cantidadFaltanteOrden = cantidadFaltanteOrden;
	}

	/**
	 * @return the cantidadComponenteVisualizar
	 */
	public Integer getCantidadComponenteVisualizar() {
		return cantidadComponenteVisualizar;
	}

	/**
	 * @param cantidadComponenteVisualizar the cantidadComponenteVisualizar to set
	 */
	public void setCantidadComponenteVisualizar(Integer cantidadComponenteVisualizar) {
		this.cantidadComponenteVisualizar = cantidadComponenteVisualizar;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idProducto == null) ? 0 : idProducto.hashCode());
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
		Producto other = (Producto) obj;
		if (idProducto == null) {
			if (other.idProducto != null)
				return false;
		} else if (!idProducto.equals(other.idProducto))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}

	
}