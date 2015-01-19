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

import sio.entidades.Grupo;
import sio.entidades.Producto;
import sio.entidades.Proveedor;
import sio.entidades.UnidadMedida;
import sio.servicio.IGrupoServicio;
import sio.servicio.IProductoServicio;
import sio.servicio.IProveedorServicio;
import sio.servicio.IUnidadMedidaServicio;

/**
 * Managed Bean de la página producto.xhtml.
 * Esta página se encarga de ingresar la información del producto.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class ProductoBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(ProductoBean.class);
	
	/**
	 * Interface del servicio de Producto
	 */
	@EJB
	private IProductoServicio productoServicio;
	
	/**
	 * Interface del servicio de Proveedor
	 */
	@EJB
	private IProveedorServicio proveedorServicio;
	
	/**
	 * Interface del servicio de Grupo
	 */
	@EJB
	private IGrupoServicio grupoServicio;
	
	/**
	 * Interface del servicio unidad de medida
	 */
	@EJB
	private IUnidadMedidaServicio unidadMedidaServicio;
	
	/**
	 * Objeto producto
	 */
	private Producto producto;
	
	/**
	 * Objeto proveedor
	 */
	private Proveedor proveedorSeleccionado;
	
	private List<Proveedor> listaProveedores;
	
	private String idProveedorSeleccionado;
	
	/**
	 * Objeto grupo
	 */
	private Grupo grupoSeleccionado;
	
	private List<Grupo> listaGrupos;
	
	private String idGrupoSeleccionado;
	
	/**
	 * Objeto unidadMedida
	 */
	private UnidadMedida unidadMedidaSeleccionada;
	
	private List<UnidadMedida> listaUnidadesMedida;
	
	private String idUnidadMedidaSeleccionada;
	
	@PostConstruct
	public void inicializarVariables() {		
		producto = new Producto();
		listaProveedores = new ArrayList<Proveedor>();
		proveedorSeleccionado = new Proveedor();
		buscarProveedores();
		buscarGrupo();
		buscarUnidadMedida();
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
	public ProductoBean() {
		
	}
			
	/**
	 * Permite guardar el producto
	 */
	public void guardarProducto() {
		try {
			producto.setIdProducto(null);
			if(producto.getLote().equals("")){
				producto.setLote(null);
			}
			if(producto.getNombre().equals("")){
				producto.setNombre(null);
			}
			if(producto.getPeso().equals("")){
				producto.setPeso(null);
			}
			if(producto.getPromedioVenta()==0){
				producto.setPromedioVenta(null);
			}
			if(producto.getTiempoEntrega().equals("")){
				producto.setTiempoEntrega(null);
			}
			if(producto.getUbicacion().equals("")){
				producto.setUbicacion(null);
			}
			
			proveedorSeleccionado=proveedorServicio.
					buscarProveedorPorId(Integer.parseInt(idProveedorSeleccionado));
			grupoSeleccionado=grupoServicio.
					buscarGrupoPorId(Integer.parseInt(idGrupoSeleccionado));
			unidadMedidaSeleccionada=unidadMedidaServicio.
					buscarUnidadMedidaPorId(Integer.parseInt(idUnidadMedidaSeleccionada));
			
			producto.setProveedor(proveedorSeleccionado);
			producto.setGrupo(grupoSeleccionado);
			producto.setUnidadMedida(unidadMedidaSeleccionada);
			productoServicio.guardarProducto(producto);
			limpiarProducto();
			FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,
					 "El producto se guardo satisfactoriamente",
					 "El producto se guardo satisfactoriamente");
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
	 * Limpia la informacion del producto
	 */
	public void limpiarProducto() {
		producto = new Producto();
		idProveedorSeleccionado="";
		idGrupoSeleccionado="";
		idUnidadMedidaSeleccionada="";
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
			
	/**
	 * Permite buscar grupos
	 */
	public void buscarGrupo() {
		try {					
			listaGrupos=grupoServicio.buscarTodosGrupos();			
		} catch (RuntimeException re) {
        	LOGGER.error(re);
			re.printStackTrace();
		}catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();	
		}
		
	}
	
	/**
	 * Permite buscar grupos
	 */
	public void buscarUnidadMedida() {
		try {					
			listaUnidadesMedida=unidadMedidaServicio.buscarTodasUnidadesMedida();			
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
		
	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
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

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}

	public List<Grupo> getListaGrupos() {
		return listaGrupos;
	}

	public void setListaGrupos(List<Grupo> listaGrupos) {
		this.listaGrupos = listaGrupos;
	}

	public String getIdGrupoSeleccionado() {
		return idGrupoSeleccionado;
	}

	public void setIdGrupoSeleccionado(String idGrupoSeleccionado) {
		this.idGrupoSeleccionado = idGrupoSeleccionado;
	}

	public UnidadMedida getUnidadMedidaSeleccionada() {
		return unidadMedidaSeleccionada;
	}

	public void setUnidadMedidaSeleccionada(UnidadMedida unidadMedidaSeleccionada) {
		this.unidadMedidaSeleccionada = unidadMedidaSeleccionada;
	}

	public List<UnidadMedida> getListaUnidadesMedida() {
		return listaUnidadesMedida;
	}

	public void setListaUnidadesMedida(List<UnidadMedida> listaUnidadesMedida) {
		this.listaUnidadesMedida = listaUnidadesMedida;
	}

	public String getIdUnidadMedidaSeleccionada() {
		return idUnidadMedidaSeleccionada;
	}

	public void setIdUnidadMedidaSeleccionada(String idUnidadMedidaSeleccionada) {
		this.idUnidadMedidaSeleccionada = idUnidadMedidaSeleccionada;
	}
}