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

import sio.entidades.UnidadMedida;
import sio.servicio.IUnidadMedidaServicio;

/**
 * Managed Bean de la página unidadMedida.xhtml.
 * Esta página se encarga de ingresar la información de las unidades de medida.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class UnidadMedidaBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(UnidadMedidaBean.class);
	
	/**
	 * Interface del servicio de ProveedorServicio
	 */
	@EJB
	private IUnidadMedidaServicio unidadMedidaServicio;
	
	/**
	 * Objeto unidadMedida
	 */
	private UnidadMedida unidadMedida;	
	
	@PostConstruct
	public void inicializarVariables() {		
		unidadMedida = new UnidadMedida();
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
	public UnidadMedidaBean() {
		
	}	
	
	/**
	 * Permite guardar la unidad de medida
	 */
	public void guardarUnidad() {
		try {
			unidadMedida.setIdUnidad(null);
			unidadMedidaServicio.guardarUnidadMedida(unidadMedida);
			limpiarUnidadMedida();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					 "La unidad de medida se guardo satisfactoriamente",
					 "La unidad de medida se guardo satisfactoriamente");
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
	 * Limpia la informacion del proveedor
	 */
	public void limpiarUnidadMedida() {
		unidadMedida = new UnidadMedida();
	}
	
	public String volverMenu() {
		return "volverMenu";
	}

	public UnidadMedida getUnidadMedida() {
		return unidadMedida;
	}

	public void setUnidadMedida(UnidadMedida unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
				
}