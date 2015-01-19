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
 * The persistent class for the clase database table.
 * 
 */
@Entity
@NamedQuery(name=Clase.Q_BUSCAR_TODAS_CLASES, query="SELECT c FROM Clase c")
public class Clase implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de instancia estática Q_BUSCAR_TODAS_CLASES
	 */
	public static final String Q_BUSCAR_TODAS_CLASES = "Clase.buscarTodasClases";

	@Id
	@SequenceGenerator(name="CLASE_IDCLASE_GENERATOR", sequenceName="SEQ_IDE_CLASE", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CLASE_IDCLASE_GENERATOR")
	@Column(name="id_clase")
	private Integer idClase;

	private String codigo;

	private String nombre;
		
	@OneToMany(mappedBy="clase")	
	private List<Grupo> grupos;

	public Clase() {
	}

	public Integer getIdClase() {
		return this.idClase;
	}

	public void setIdClase(Integer idClase) {
		this.idClase = idClase;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Grupo> getGrupos() {
		return this.grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idClase == null) ? 0 : idClase.hashCode());
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
		Clase other = (Clase) obj;
		if (idClase == null) {
			if (other.idClase != null)
				return false;
		} else if (!idClase.equals(other.idClase))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	
	
}