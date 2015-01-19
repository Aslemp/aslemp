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

import sio.entidades.FichaTecnica;
import sio.entidades.OrdenProduccion;
import sio.entidades.Producto;
import sio.entidades.SaldoReservadoOrden;
import sio.servicio.IFichaTecnicaServicio;
import sio.servicio.IOrdenProduccionServicio;
import sio.servicio.IProductoServicio;
import sio.servicio.ISaldoReservadoServicio;

/**
 * Managed Bean de la página orden_compra.xhtml.
 * Esta página se encarga de ingresar las entradas de mercancia.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class OrdenProduccionBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(OrdenProduccionBean.class);
	
	/**
	 * Interface del servicio de Producto
	 */
	@EJB
	private IProductoServicio productoServicio;
	
	/**
	 * Interface del servicio de FichaTecnica
	 */
	@EJB
	private IFichaTecnicaServicio fichaTecnicaServicio;
	
	/**
	 * Interface del servicio de la orden de produccion
	 */
	@EJB
	private IOrdenProduccionServicio ordenProduccionServicio;
	
	/**
	 * Interface del servicio del saldo reservado
	 */
	@EJB
	private ISaldoReservadoServicio saldoReservadoServicio;
	
	/**
	 * Objeto Orden de produccion
	 */
	private OrdenProduccion ordenProduccion;
		
	/**
	 * Objeto producto
	 */
	private Producto productoConsultado;	
	
	/**
	 * Objeto saldo reservado
	 */
	private SaldoReservadoOrden saldoReservadoOrden;
	
	private String codigoProducto;
			
	private List<Producto> listaComponentes;
	
	private List<FichaTecnica> listaFichaTecnicaResultado;
	
	private boolean desactivarCantidad=true;
	
	private boolean habilitarGuardar=true;
	
	@PostConstruct
	public void inicializarVariables() {
		ordenProduccion = new OrdenProduccion();
		productoConsultado = new Producto();
		saldoReservadoOrden = new SaldoReservadoOrden();
		listaComponentes = new ArrayList<Producto>();
		listaFichaTecnicaResultado = new ArrayList<FichaTecnica>();
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
	public OrdenProduccionBean() {
		
	}
			
	/**
	 * Permite guardar orden de produccion
	 */
	public void guardarOrdenProduccion(){
		try {	
			boolean validarSaldoProducto=true;
			Producto productoCantidad = new Producto();
			if(ordenProduccion==null || listaComponentes.size()==0){
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
						 "Debe ingresar el producto y agregar componentes antes de guardar",
						 "Debe ingresar el producto y agregar componentes antes de guardar");
				FacesContext.getCurrentInstance().addMessage("messages", facesMsg);
				return;
			}else{
				if(listaFichaTecnicaResultado!=null && listaComponentes.size()>0){					
					for(Producto productoIterar : listaComponentes) {
						if(productoIterar.getCantidadComponente()!=null &&
								ordenProduccion.getCantidad()!=null && 
									productoIterar.getSaldo()!=null){
							if((productoIterar.getCantidadComponente()*ordenProduccion.getCantidad())>productoIterar.getSaldoDisponible()){							
								validarSaldoProducto=false;
								productoCantidad=productoIterar;
								break;
							}
						}
					}
				}
				if(validarSaldoProducto){
					ordenProduccion.setIdOrdenProduccion(null);
					ordenProduccion.setProducto(productoConsultado);
					ordenProduccion.setReservado(true);
					ordenProduccion=ordenProduccionServicio.guardarOrdenProduccion(ordenProduccion);
					
					for(FichaTecnica fichaTecnicaIterar : listaFichaTecnicaResultado) {
						saldoReservadoOrden.setIdSaldoReservadoOrden(null);
						saldoReservadoOrden.setOrdenCompra(ordenProduccion);
						saldoReservadoOrden.setProducto(fichaTecnicaIterar.getComponente());
						saldoReservadoOrden.setSaldoReservado
							(fichaTecnicaIterar.getComponente().getCantidadComponente()*ordenProduccion.getCantidad());
						saldoReservadoServicio.guardarSaldoReservadoOrden(saldoReservadoOrden);
						
						//Se asigna el saldo disponible al producto
						if(fichaTecnicaIterar.getComponente().getSaldoDisponible()==null){
							fichaTecnicaIterar.getComponente().setSaldoDisponible
							(fichaTecnicaIterar.getComponente().getSaldo()-(fichaTecnicaIterar.getComponente().getCantidadComponente()*ordenProduccion.getCantidad()));
						}else if(fichaTecnicaIterar.getComponente().getSaldoDisponible()!=null 
									&& fichaTecnicaIterar.getComponente().getSaldoDisponible()>0){
							fichaTecnicaIterar.getComponente().setSaldoDisponible
								(fichaTecnicaIterar.getComponente().getSaldoDisponible()-(fichaTecnicaIterar.getComponente().getCantidadComponente()*ordenProduccion.getCantidad()));
						}
						productoServicio.guardarProducto(fichaTecnicaIterar.getComponente());
					}					
															
					limpiarOrdenCompra();
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							 "La orden de compra se guardo satisfactoriamente",
							 "La orden de compra se guardo satisfactoriamente");
					FacesContext.getCurrentInstance().addMessage("messages", facesMsg);
				}else{
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							 "El saldo del producto: " + productoCantidad.getNombre() + " no cubre la cantidad necesaria para la orden de compra",
							 "El saldo del producto: " + productoCantidad.getNombre() + " no cubre la cantidad necesaria para la orden de compra");
					FacesContext.getCurrentInstance().addMessage("messages", facesMsg);
				}
			}
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}
	
	/**
	 * Permite consultar la información del producto por el código
	 */
	public void consultarProductoPorCodigo() {
		try {			
			productoConsultado=productoServicio.buscarProductoPorCodigo(codigoProducto);
			listaFichaTecnicaResultado=fichaTecnicaServicio.buscarFichaTecnicaPorProducto(productoConsultado);
			
			if(listaFichaTecnicaResultado!=null && listaFichaTecnicaResultado.size()>0){
				for(FichaTecnica fichaTecnicaIterar : listaFichaTecnicaResultado) {
					if(fichaTecnicaIterar.getComponente()!=null){						
						listaComponentes.add(fichaTecnicaIterar.getComponente());
					}
				}
				desactivarCantidad=false;
			}else{
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						 "El producto ingresado no tiene componentes asociados",
						 "El producto ingresado no tiene componentes asociados");
				FacesContext.getCurrentInstance().addMessage("messages", facesMsg); 
			}
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}
			
	/**
	 * Permite consultar la cantidad disponible para la orden
	 */
	public void calcularCantidadOrden() {
		try {								
			if(listaComponentes!=null && listaComponentes.size()>0 && ordenProduccion!=null){
				Integer cantTotalComponente=0;
				Integer cantTotalDisponibleComponente=0;
				//Se inicializa en cero(0), si se actualiza el valor a uno (1) no debe activarse el botón de guardar
				Integer desactivaHabilitarGuardar=0;
				for(FichaTecnica fichaTecnicaIterar : listaFichaTecnicaResultado) {
					if(fichaTecnicaIterar.getCantidad()!=null && fichaTecnicaIterar.getCantidad()>0){
						cantTotalComponente=ordenProduccion.getCantidad()*fichaTecnicaIterar.getCantidad();
					}else{
						cantTotalComponente=0;
					}
					cantTotalDisponibleComponente=fichaTecnicaIterar.getComponente().getSaldoDisponible();
					fichaTecnicaIterar.getComponente().setCantidadTotalOrden(cantTotalComponente);
					fichaTecnicaIterar.getComponente().setCantidadFaltanteOrden(cantTotalComponente-cantTotalDisponibleComponente);					
					if(cantTotalDisponibleComponente<cantTotalComponente){
						desactivaHabilitarGuardar=1;
						fichaTecnicaIterar.getComponente().setCantidadFaltanteOrden(cantTotalComponente-cantTotalDisponibleComponente);
					}else{
						fichaTecnicaIterar.getComponente().setCantidadFaltanteOrden(0);
					}					
				}
				if(desactivaHabilitarGuardar==0){
					habilitarGuardar=false;
				}				
			}else{
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						 "No hay elementos disponibles para la orden",
						 "No hay elementos disponibles para la orden");
				FacesContext.getCurrentInstance().addMessage("messages", facesMsg); 
			}
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}
	
	/**
	 * Limpia la informacion de la ficha tecnica
	 */
	public void limpiarOrdenCompra() {		
		ordenProduccion = new OrdenProduccion();
		productoConsultado = new Producto(); 
		codigoProducto="";
		listaComponentes.clear();
	}
	
	/**
	 * Elimina el item del componente
	 */
	public void eliminarItemComponente(Producto componenteSeleccionado) {
		if(listaComponentes!=null && listaComponentes.size()>0){
			listaComponentes.remove(componenteSeleccionado);
		}		
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
	 * @return the listaComponentes
	 */
	public List<Producto> getListaComponentes() {
		return listaComponentes;
	}

	/**
	 * @param listaComponentes the listaComponentes to set
	 */
	public void setListaComponentes(List<Producto> listaComponentes) {
		this.listaComponentes = listaComponentes;
	}
	
	
	/**
	 * @return the desactivarCantidad
	 */
	public boolean isDesactivarCantidad() {
		return desactivarCantidad;
	}

	/**
	 * @param desactivarCantidad the desactivarCantidad to set
	 */
	public void setDesactivarCantidad(boolean desactivarCantidad) {
		this.desactivarCantidad = desactivarCantidad;
	}

	/**
	 * @return the ordenProduccion
	 */
	public OrdenProduccion getOrdenProduccion() {
		return ordenProduccion;
	}

	/**
	 * @param ordenProduccion the ordenProduccion to set
	 */
	public void setOrdenProduccion(OrdenProduccion ordenProduccion) {
		this.ordenProduccion = ordenProduccion;
	}

	/**
	 * @return the habilitarGuardar
	 */
	public boolean isHabilitarGuardar() {
		return habilitarGuardar;
	}

	/**
	 * @param habilitarGuardar the habilitarGuardar to set
	 */
	public void setHabilitarGuardar(boolean habilitarGuardar) {
		this.habilitarGuardar = habilitarGuardar;
	}

	/**
	 * @return the listaFichaTecnicaResultado
	 */
	public List<FichaTecnica> getListaFichaTecnicaResultado() {
		return listaFichaTecnicaResultado;
	}

	/**
	 * @param listaFichaTecnicaResultado the listaFichaTecnicaResultado to set
	 */
	public void setListaFichaTecnicaResultado(
			List<FichaTecnica> listaFichaTecnicaResultado) {
		this.listaFichaTecnicaResultado = listaFichaTecnicaResultado;
	}

	
	
}