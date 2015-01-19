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

import sio.entidades.Proveedor;
import sio.servicio.IProveedorServicio;

/**
 * Managed Bean de la página proveedor.xhtml.
 * Esta página se encarga de ingresar la información del proveedor.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class ProveedorBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(ProveedorBean.class);
	
	/**
	 * Interface del servicio de ProveedorServicio
	 */
	@EJB
	private IProveedorServicio proveedorServicio;
	
	/**
	 * Objeto proveedores
	 */
	private Proveedor proveedor;	
	
	@PostConstruct
	public void inicializarVariables() {		
		proveedor = new Proveedor();
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
	public ProveedorBean() {
		
	}	
	
	/**
	 * Permite guardar el proveedor
	 */
	public void guardarProveedor() {
		try {
			proveedor.setIdProveedor(null);
			proveedorServicio.guardarProveedor(proveedor);
			limpiarProveedor();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					 "El proveedor se guardo satisfactoriamente",
					 "El proveedor se guardo satisfactoriamente");
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
	public void limpiarProveedor() {
		proveedor = new Proveedor();
	}
	
	public String volverMenu() {
		return "volverMenu";
	}
			
	public Proveedor getProveedor() {
		return proveedor;
	}

	public void setProveedor(Proveedor proveedor) {
		this.proveedor = proveedor;
	}
}