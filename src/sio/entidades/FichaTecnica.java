package sio.entidades;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the ficha_tecnica database table.
 * 
 */
@Entity
@Table(name="ficha_tecnica")
@NamedQueries({
	@NamedQuery(name=FichaTecnica.Q_BUSCAR_TODAS_FICHAS_TECNICAS, query="SELECT f FROM FichaTecnica f"),
	@NamedQuery(name = FichaTecnica.Q_BUSCAR_FICHA_TECNICA_POR_PRODUCTO, query = "SELECT f FROM FichaTecnica f WHERE f.producto = :producto") })
public class FichaTecnica implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable de instancia estática Q_BUSCAR_TODAS_FICHAS_TECNICAS
	 */
	public static final String Q_BUSCAR_TODAS_FICHAS_TECNICAS = "FichaTecnica.buscarTodasFichasTecnicas";
	
	/**
	 * Variable de instancia estática Q_BUSCAR_FICHA_TECNICA_POR_PRODUCTO
	 */
	public static final String Q_BUSCAR_FICHA_TECNICA_POR_PRODUCTO = "FichaTecnica.buscarTodasFichasTecnicasPorProducto";
	
	@Id
	@SequenceGenerator(name="FICHA_TECNICA_IDFICHATECNICA_GENERATOR", sequenceName="SEQ_IDE_FICHA_TECNICA", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="FICHA_TECNICA_IDFICHATECNICA_GENERATOR")
	@Column(name="id_ficha_tecnica")
	private Integer idFichaTecnica;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_prod_ficha_tecnica")
	private Producto producto;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_componente")
	private Producto componente;
	
	private Integer cantidad;
		
	public FichaTecnica() {
	}

	public Integer getIdFichaTecnica() {
		return this.idFichaTecnica;
	}

	public void setIdFichaTecnica(Integer idFichaTecnica) {
		this.idFichaTecnica = idFichaTecnica;
	}

	/**
	 * @return the producto
	 */
	public Producto getProducto() {
		return producto;
	}

	/**
	 * @param producto the producto to set
	 */
	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	/**
	 * @return the componente
	 */
	public Producto getComponente() {
		return componente;
	}

	/**
	 * @param componente the componente to set
	 */
	public void setComponente(Producto componente) {
		this.componente = componente;
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

	
}