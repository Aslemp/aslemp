package sio.entidades;

import java.io.Serializable;

import javax.persistence.*;


/**
 * The persistent class for the grupo database table.
 * 
 */
@Entity
@NamedQuery(name=Grupo.Q_BUSCAR_TODOS_GRUPOS, query="SELECT g FROM Grupo g")
public class Grupo implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Variable de instancia estática Q_BUSCAR_TODOS_PROVEEDORES
	 */
	public static final String Q_BUSCAR_TODOS_GRUPOS = "Grupo.buscarTodosGrupos";
	
	@Id
	@SequenceGenerator(name="GRUPO_IDGRUPO_GENERATOR", sequenceName="SEQ_IDE_GRUPO", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="GRUPO_IDGRUPO_GENERATOR")
	@Column(name="id_grupo")
	private Integer idGrupo;

	private String codigo;

	private String nombre;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name="id_clase")
	private Clase clase;

	public Grupo() {
	}

	public Integer getIdGrupo() {
		return this.idGrupo;
	}

	public void setIdGrupo(Integer idGrupo) {
		this.idGrupo = idGrupo;
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

	public Clase getClase() {
		return this.clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idGrupo == null) ? 0 : idGrupo.hashCode());
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
		Grupo other = (Grupo) obj;
		if (idGrupo == null) {
			if (other.idGrupo != null)
				return false;
		} else if (!idGrupo.equals(other.idGrupo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Clase: " + clase.getCodigo() + " - Grupo: " + codigo;
	}
	
	

}