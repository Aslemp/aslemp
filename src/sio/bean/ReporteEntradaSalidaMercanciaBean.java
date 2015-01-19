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

import sio.entidades.Producto;
import sio.entidades.RegistroEntradaSalida;
import sio.servicio.IProductoServicio;
import sio.servicio.IRegEntradaSalidaMercanciaServicio;

/**
 * Managed Bean de la página entrada_mercancia.xhtml.
 * Esta página se encarga de ingresar las entradas de mercancia.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class ReporteEntradaSalidaMercanciaBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(ReporteEntradaSalidaMercanciaBean.class);
						
	/**
	 * Interface del servicio de producto
	 */
	@EJB
	private IProductoServicio productoServicio;
	
	/**
	 * Interface del servicio de entrada salida
	 */
	@EJB
	private IRegEntradaSalidaMercanciaServicio regEntradaSalidaMercanciaServicio;
							
	private List<RegistroEntradaSalida> listaRegistroEntradaSalida;
		
	/**
	 * Objeto prodcuto
	 */
	private Producto productoConsultado;
	
	private String codigoProducto;
	
	private List<Producto> listaProductos;
				
	@PostConstruct
	public void inicializarVariables() {					
		listaRegistroEntradaSalida = new ArrayList<RegistroEntradaSalida>();
		listaProductos = new ArrayList<Producto>();
		productoConsultado = new Producto();			
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> it = context.getMessages();
		try {	
			listaProductos=productoServicio.buscarTodosProductos();
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		while ( it.hasNext() ) {
		    it.next();
		    it.remove();
		}
	}

	/**
	 * Constructor por defecto
	 */	
	public ReporteEntradaSalidaMercanciaBean() {
		
	}				
	
	/**
	 * Permite guardar el producto
	 */
	public void consultarProductoPorCodigo() {
		try {		
			productoConsultado=productoServicio.buscarProductoPorCodigo(codigoProducto);						
			listaRegistroEntradaSalida=regEntradaSalidaMercanciaServicio.buscarRegEntradaSalidaPorProducto(productoConsultado);
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}
	
	/**
	 * Limpia la informacion del producto
	 */
	public void limpiarEntradaMercancia() {		
		codigoProducto="";
		productoConsultado=new Producto();
		
	}
			
	public String volverMenu() {
		return "volverMenu";
	}	
						
	/**
	 * @return the productoConsultado
	 */
	public Producto getProductoConsultado() {
		return productoConsultado;
	}

	/**
	 * @param productoConsultado the productoConsultado to set
	 */
	public void setProductoConsultado(Producto productoConsultado) {
		this.productoConsultado = productoConsultado;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}
		
	/**
	 * @return the listaRegistroEntradaSalida
	 */
	public List<RegistroEntradaSalida> getListaRegistroEntradaSalida() {
		return listaRegistroEntradaSalida;
	}

	/**
	 * @param listaRegistroEntradaSalida the listaRegistroEntradaSalida to set
	 */
	public void setListaRegistroEntradaSalida(
			List<RegistroEntradaSalida> listaRegistroEntradaSalida) {
		this.listaRegistroEntradaSalida = listaRegistroEntradaSalida;
	}

	/**
	 * @return the listaProductos
	 */
	public List<Producto> getListaProductos() {
		return listaProductos;
	}

	/**
	 * @param listaProductos the listaProductos to set
	 */
	public void setListaProductos(List<Producto> listaProductos) {
		this.listaProductos = listaProductos;
	}	
}
