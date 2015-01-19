package sio.bean;

import java.io.Serializable;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import sio.entidades.Clase;
import sio.servicio.IClaseServicio;

/**
 * Managed Bean de la página producto.xhtml.
 * Esta página se encarga de ingresar la información del producto.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class ClaseBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(ClaseBean.class);
	
	/**
	 * Interface del servicio de PlantillaCorreo
	 */
	@EJB
	private IClaseServicio claseServicio;
	
	/**
	 * Objeto clase
	 */
	private Clase clase;
	
	@PostConstruct
	public void inicializarVariables() {		
		clase = new Clase();
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
	public ClaseBean() {
		
	}
			
	/**
	 * Permite guardar el producto
	 */
	public void guardarClase() {
		try {			
			clase.setIdClase(null);
			claseServicio.guardarClase(clase);
			limpiarClase();			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					 "La clase se guardo satisfactoriamente",
					 "La clase se guardo satisfactoriamente");
			FacesContext.getCurrentInstance().addMessage("messages", facesMsg); 
		} catch (Exception e) {            
			LOGGER.error(e);
        }
		
	}
	
	
	
	/**
	 * Limpia la informacion de la clase
	 */
	public void limpiarClase() {
		clase = new Clase();
	}
	
	public String volverMenu() {
		return "volverMenu";
	}

	public Clase getClase() {
		return clase;
	}

	public void setClase(Clase clase) {
		this.clase = clase;
	}	
		
}