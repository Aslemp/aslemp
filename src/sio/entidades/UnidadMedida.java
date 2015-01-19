package sio.entidades;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the unidad_medida database table.
 * 
 */
@Entity
@Table(name="UNIDAD_MEDIDA")
@NamedQuery(name=UnidadMedida.Q_BUSCAR_TODAS_UNIDADES, query="SELECT u FROM UnidadMedida u")
public class UnidadMedida implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable de instancia estática Q_BUSCAR_TODAS_UNIDADES
	 */
	public static final String Q_BUSCAR_TODAS_UNIDADES = "UnidadMedida.buscarTodasUnidades";
	
	@Id
	@SequenceGenerator(name="UNIDAD_MEDIDA_IDUNIDAD_GENERATOR", sequenceName="SEQ_IDE_UNIDAD", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="UNIDAD_MEDIDA_IDUNIDAD_GENERATOR")
	@Column(name="id_unidad")
	private Integer idUnidad;

	private String descripcion;

	private String nombre;

	public UnidadMedida() {
	}

	public Integer getIdUnidad() {
		return this.idUnidad;
	}

	public void setIdUnidad(Integer idUnidad) {
		this.idUnidad = idUnidad;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((idUnidad == null) ? 0 : idUnidad.hashCode());
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
		UnidadMedida other = (UnidadMedida) obj;
		if (idUnidad == null) {
			if (other.idUnidad != null)
				return false;
		} else if (!idUnidad.equals(other.idUnidad))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UnidadMedida [idUnidad=" + idUnidad + ", nombre=" + nombre
				+ "]";
	}

}