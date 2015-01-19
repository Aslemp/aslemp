package sio.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;

import sio.entidades.EntradaSalidaMercancia;
import sio.entidades.Producto;
import sio.entidades.Proveedor;
import sio.entidades.RegistroEntradaSalida;
import sio.servicio.IEntradaSalidaMercanciaServicio;
import sio.servicio.IProductoServicio;
import sio.servicio.IProveedorServicio;
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
public class SalidaMercanciaBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(SalidaMercanciaBean.class);
			
	/**
	 * Interface del servicio de Proveedor
	 */
	@EJB
	private IProveedorServicio proveedorServicio;
			
	/**
	 * Interface del servicio de entrada de mercancia
	 */
	@EJB
	private IEntradaSalidaMercanciaServicio entradaMercanciaServicio;
	
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
	
	/**
	 * Objeto entrada mercancia
	 */
	private EntradaSalidaMercancia entradaMercancia;
	
	/**
	 * Objeto proveedor
	 */
	private Proveedor proveedorSeleccionado;
	
	private List<Proveedor> listaProveedores;
	
	private String idProveedorSeleccionado;	
	
	/**
	 * Objeto prodcuto
	 */
	private Producto productoConsultado;
	
	private String codigoProducto;
	
	private String tipoMovimiento;
	
	private boolean habilitarGuardar=true;
	
	private Integer saldoVisualizar;
	
	@PostConstruct
	public void inicializarVariables() {		
		entradaMercancia = new EntradaSalidaMercancia();
		entradaMercancia.setProducto(new Producto());
		listaProveedores = new ArrayList<Proveedor>();
		proveedorSeleccionado = new Proveedor();
		saldoVisualizar=0;
		buscarProveedores();		
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
	public SalidaMercanciaBean() {
		
	}
			
	/**
	 * Permite guardar el producto
	 */
	public void guardarSalidaMercancia() {
		try {
			Integer saldoProducto=0;
			Integer saldoInicial=0;			
			Integer saldoDisponible=0;
			String tipoMovimiento="SM";
			RegistroEntradaSalida registroEntradaSalida = new RegistroEntradaSalida();
			proveedorSeleccionado=proveedorServicio.
					buscarProveedorPorId(Integer.parseInt(idProveedorSeleccionado));						
			entradaMercancia.setProveedor(proveedorSeleccionado);			
			entradaMercancia.setProducto(productoConsultado);								
			if(productoConsultado.getSaldo()==null || 
					(productoConsultado.getSaldo()!=null && productoConsultado.getSaldo()<0)){
				FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
						 "No se pueden ejecutar salidas del inventario",
						 "No se pueden ejecutar salidas del inventario");
				FacesContext.getCurrentInstance().addMessage("messages", facesMsg); 
			}else{
				saldoInicial=productoConsultado.getSaldo();
				saldoProducto=productoConsultado.getSaldo()-entradaMercancia.getCantidad();
				saldoDisponible=productoConsultado.getSaldoDisponible()-entradaMercancia.getCantidad();
				if(saldoProducto<0){
					FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
							 "No se puede sacar esta cantidad del inventario, excede la cantidad disponible",
							 "No se puede sacar esta cantidad del inventario, excede la cantidad disponible");
					FacesContext.getCurrentInstance().addMessage("messages", facesMsg); 
				}
			}
			
			entradaMercanciaServicio.guardarEntradaMercancia(entradaMercancia);
			
			
			productoConsultado.setSaldo(saldoProducto);
			productoConsultado.setSaldoDisponible(saldoDisponible);
			productoServicio.guardarProducto(productoConsultado);
			
			registroEntradaSalida.setIdRegEntradaSalida(null);
			registroEntradaSalida.setTipoMovimiento(tipoMovimiento);
			registroEntradaSalida.setFechaRegistro(new Date());
			registroEntradaSalida.setEntradaSalidaMercancia(entradaMercancia);			
			registroEntradaSalida.setProducto(productoConsultado);
			registroEntradaSalida.setSaldoInicial(saldoInicial);
			registroEntradaSalida.setCantidad(entradaMercancia.getCantidad());
			registroEntradaSalida.setSaldoFinal(saldoProducto);
			regEntradaSalidaMercanciaServicio.guardarRegistroEntradaSalida(registroEntradaSalida);
			
			limpiarEntradaMercancia();
			
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					 "La entrada de mercancia se guardo satisfactoriamente",
					 "La entrada de mercancia se guardo satisfactoriamente");
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
	 * Permite guardar el producto
	 */
	public void consultarProductoPorCodigo() {
		try {			
			productoConsultado=productoServicio.buscarProductoPorCodigo(codigoProducto);
			if(productoConsultado.getSaldo()==null){
				saldoVisualizar=0;
			}else{
				saldoVisualizar=productoConsultado.getSaldo();
			}
			habilitarGuardar=false;
						
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
		entradaMercancia = new EntradaSalidaMercancia();
		idProveedorSeleccionado="";
		codigoProducto="";
		productoConsultado=new Producto();
		tipoMovimiento="";
		saldoVisualizar=0;
		
	}
	
	/**
	 * Permite buscar clases
	 */
	public void buscarProveedores() {
		try {					
			listaProveedores=proveedorServicio.buscarTodosProveedores();			
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}			
	
	public String volverMenu() {
		return "volverMenu";
	}	
			
	public Proveedor getProveedorSeleccionado() {
		return proveedorSeleccionado;
	}

	public void setProveedorSeleccionado(Proveedor proveedorSeleccionado) {
		this.proveedorSeleccionado = proveedorSeleccionado;
	}

	public List<Proveedor> getListaProveedores() {
		return listaProveedores;
	}

	public void setListaProveedores(List<Proveedor> listaProveedores) {
		this.listaProveedores = listaProveedores;
	}

	public String getIdProveedorSeleccionado() {
		return idProveedorSeleccionado;
	}

	public void setIdProveedorSeleccionado(String idProveedorSeleccionado) {
		this.idProveedorSeleccionado = idProveedorSeleccionado;
	}

	/**
	 * @return the entradaMercancia
	 */
	public EntradaSalidaMercancia getEntradaMercancia() {
		return entradaMercancia;
	}

	/**
	 * @param entradaMercancia the entradaMercancia to set
	 */
	public void setEntradaMercancia(EntradaSalidaMercancia entradaMercancia) {
		this.entradaMercancia = entradaMercancia;
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
	 * @return the tipoMovimiento
	 */
	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	/**
	 * @param tipoMovimiento the tipoMovimiento to set
	 */
	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
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
	 * @return the saldoVisualizar
	 */
	public Integer getSaldoVisualizar() {
		return saldoVisualizar;
	}

	/**
	 * @param saldoVisualizar the saldoVisualizar to set
	 */
	public void setSaldoVisualizar(Integer saldoVisualizar) {
		this.saldoVisualizar = saldoVisualizar;
	}	
}