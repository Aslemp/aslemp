package sio.entidades;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;


/**
 * The persistent class for the proveedor database table.
 * 
 */
@Entity
@NamedQuery(name=Proveedor.Q_BUSCAR_TODOS_PROVEEDORES, query="SELECT p FROM Proveedor p")
public class Proveedor implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de instancia estática Q_BUSCAR_TODOS_PROVEEDORES
	 */
	public static final String Q_BUSCAR_TODOS_PROVEEDORES = "Proveedor.buscarTodosProveedores";

	@Id
	@SequenceGenerator(name="PROVEEDOR_IDPROVEEDOR_GENERATOR", sequenceName="SEQ_IDE_PROVEEDOR", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PROVEEDOR_IDPROVEEDOR_GENERATOR")
	@Column(name="id_proveedor")
	private Integer idProveedor;

	@Column(name="correo_electronico")
	private String correoElectronico;

	private String direccion;

	private String municipio;

	private String nit;

	private String nombre;

	@Column(name="plazo_pago")
	private Integer plazoPago;

	@Column(name="promedio_venta")
	private Integer promedioVenta;

	private String telefono;

	@Column(name="tiempo_entrega")
	private Integer tiempoEntrega;
	
	@OneToMany(mappedBy="proveedor")
	private List<Producto> productos;
	
	@OneToMany(mappedBy="proveedor")
	private List<EntradaSalidaMercancia> entradaMercancias;
	
	public Proveedor() {
	}

	public Integer getIdProveedor() {
		return this.idProveedor;
	}

	public void setIdProveedor(Integer idProveedor) {
		this.idProveedor = idProveedor;
	}

	public String getCorreoElectronico() {
		return this.correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMunicipio() {
		return this.municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getNit() {
		return this.nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getPlazoPago() {
		return this.plazoPago;
	}

	public void setPlazoPago(Integer plazoPago) {
		this.plazoPago = plazoPago;
	}

	public Integer getPromedioVenta() {
		return this.promedioVenta;
	}

	public void setPromedioVenta(Integer promedioVenta) {
		this.promedioVenta = promedioVenta;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Integer getTiempoEntrega() {
		return this.tiempoEntrega;
	}

	public void setTiempoEntrega(Integer tiempoEntrega) {
		this.tiempoEntrega = tiempoEntrega;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idProveedor == null) ? 0 : idProveedor.hashCode());
		result = prime * result
				+ ((tiempoEntrega == null) ? 0 : tiempoEntrega.hashCode());
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
		Proveedor other = (Proveedor) obj;
		if (idProveedor == null) {
			if (other.idProveedor != null)
				return false;
		} else if (!idProveedor.equals(other.idProveedor))
			return false;
		if (tiempoEntrega == null) {
			if (other.tiempoEntrega != null)
				return false;
		} else if (!tiempoEntrega.equals(other.tiempoEntrega))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
			
}