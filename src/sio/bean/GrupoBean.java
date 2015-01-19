package sio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import sio.entidades.Clase;
import sio.entidades.Grupo;
import sio.servicio.IClaseServicio;
import sio.servicio.IGrupoServicio;

/**
 * Managed Bean de la página producto.xhtml.
 * Esta página se encarga de ingresar la información del producto.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class GrupoBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(GrupoBean.class);
	
	/**
	 * Interface del servicio de PlantillaCorreo
	 */
	@EJB
	private IGrupoServicio grupoServicio;
	
	/**
	 * Interface del servicio de PlantillaCorreo
	 */
	@EJB
	private IClaseServicio claseServicio;
	
	/**
	 * Objeto grupo
	 */
	private Grupo grupo;
	
	/**
	 * Objeto clase
	 */
	private Clase claseSeleccionada;
	
	private List<Clase> listaClases;
	
	private String idClaseSeleccionada;
	
	@PostConstruct
	public void inicializarVariables() {		
		grupo = new Grupo();
		listaClases = new ArrayList<Clase>();
		claseSeleccionada = new Clase();
		buscarClases();
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> it = context.getMessages();
		while ( it.hasNext() ) {
		    it.next();
		    it.remove();
		}
	}

	/**
	 * Constructor por defecto
	 */	
	public GrupoBean() {
		
	}
			
	/**
	 * Permite guardar el producto
	 */
	public void guardarGrupo() {
		try {
			grupo.setIdGrupo(null);
			claseSeleccionada=claseServicio.buscarClasePorId(Integer.parseInt(idClaseSeleccionada));
			grupo.setClase(claseSeleccionada);
			grupoServicio.guardarGrupo(grupo);
			limpiarGrupo();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					 "El grupo se guardo satisfactoriamente",
					 "El grupo se guardo satisfactoriamente");
			FacesContext.getCurrentInstance().addMessage("messages", facesMsg); 
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}
	
	/**
	 * Permite buscar clases
	 */
	public void buscarClases() {
		try {					
			listaClases=claseServicio.buscarTodasClases();			
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}
	
	/**
	 * Limpia la informacion de la clase
	 */
	public void limpiarGrupo() {
		grupo = new Grupo();
	}
	
	public String volverMenu() {
		return "volverMenu";
	}

	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

	public Clase getClaseSeleccionada() {
		return claseSeleccionada;
	}

	public void setClaseSeleccionada(Clase claseSeleccionada) {
		this.claseSeleccionada = claseSeleccionada;
	}

	public List<Clase> getListaClases() {
		return listaClases;
	}

	public void setListaClases(List<Clase> listaClases) {
		this.listaClases = listaClases;
	}

	public IClaseServicio getClaseServicio() {
		return claseServicio;
	}

	public void setClaseServicio(IClaseServicio claseServicio) {
		this.claseServicio = claseServicio;
	}

	public String getIdClaseSeleccionada() {
		return idClaseSeleccionada;
	}

	public void setIdClaseSeleccionada(String idClaseSeleccionada) {
		this.idClaseSeleccionada = idClaseSeleccionada;
	}	
		
}