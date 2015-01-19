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
import sio.entidades.Producto;
import sio.servicio.IFichaTecnicaServicio;
import sio.servicio.IProductoServicio;

/**
 * Managed Bean de la página entrada_mercancia.xhtml. Esta página se encarga de
 * ingresar las entradas de mercancia.
 * 
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 21-nov-2014 11:39:00 a.m.
 */
@ManagedBean
@ViewScoped
public class FichaTecnicaBean implements Serializable {

	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;

	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger
			.getLogger(FichaTecnicaBean.class);

	/**
	 * Interface del servicio de producto
	 */
	@EJB
	private IProductoServicio productoServicio;

	/**
	 * Interface del servicio de FichaTecnica
	 */
	@EJB
	private IFichaTecnicaServicio fichaTecnicaServicio;

	/**
	 * Objeto producto
	 */
	private Producto productoConsultado;

	/**
	 * Objeto producto
	 */
	private Producto componenteConsultado;

	private String codigoProducto;

	private String codigoComponente;

	private List<FichaTecnica> listaComponentesFicha;

	private boolean habilitarAgregar = true;

	/**
	 * Objeto ficha tecnica
	 */
	private FichaTecnica fichaTecnica;

	/**
	 * Objeto ficha tecnica
	 */
	private FichaTecnica fichaTecnicaComponente;

	private Integer cantidadComponente;

	@PostConstruct
	public void inicializarVariables() {
		fichaTecnica = new FichaTecnica();
		fichaTecnicaComponente = new FichaTecnica();
		listaComponentesFicha = new ArrayList<FichaTecnica>();
		cantidadComponente = 0;
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> it = context.getMessages();
		while (it.hasNext()) {
			it.next();
			it.remove();
		}
	}

	/**
	 * Constructor por defecto
	 */
	public FichaTecnicaBean() {

	}

	/**
	 * Permite guardar ficha tecnica
	 */
	public void guardarFichaTecnica() {
		try {
			if (productoConsultado == null || componenteConsultado == null
					|| listaComponentesFicha.size() == 0) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Debe ingresar el producto y agregar componentes antes de guardar",
						"Debe ingresar el producto y agregar componentes antes de guardar");
				FacesContext.getCurrentInstance().addMessage("messages",
						facesMsg);
				return;
			} else {
				for (FichaTecnica fichaTecnicaIterar : listaComponentesFicha) {
					fichaTecnicaServicio
							.guardarFichaTecnica(fichaTecnicaIterar);
				}
				limpiarFichaTecnica();
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"La ficha tecnica se guardo satisfactoriamente",
						"La ficha tecnica se guardo satisfactoriamente");
				FacesContext.getCurrentInstance().addMessage("messages",
						facesMsg);
			}
		} catch (RuntimeException re) {
			LOGGER.error(re);
			re.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Permite consultar la información del producto por el código
	 */
	public void consultarProductoPorCodigo() {
		try {
			productoConsultado = productoServicio
					.buscarProductoPorCodigo(codigoProducto);
			listaComponentesFicha = fichaTecnicaServicio
					.buscarFichaTecnicaPorProducto(productoConsultado);
			if (listaComponentesFicha.size() == 0) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_INFO,
						"El producto ingresado no tiene componentes asociados, favor agreguelos",
						"El producto ingresado no tiene componentes asociados, favor agreguelos");
				FacesContext.getCurrentInstance().addMessage("messages",
						facesMsg);
			}
		} catch (RuntimeException re) {
			LOGGER.error(re);
			re.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Permite consultar la información del componente por el código
	 */
	public void consultarComponentePorCodigo() {
		try {
			componenteConsultado = productoServicio
					.buscarProductoPorCodigo(codigoComponente);
			if (componenteConsultado != null
					&& componenteConsultado.getIdProducto() != null) {
				habilitarAgregar = false;
			} else {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"No se encuentra información del producto con el código ingresado",
						"No se encuentra información del producto con el código ingresado");
				FacesContext.getCurrentInstance().addMessage("messages",
						facesMsg);
			}

		} catch (RuntimeException re) {
			LOGGER.error(re);
			re.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Permite agregar componentes a la lista
	 */
	public void agregarComponente() {
		try {
			if (productoConsultado == null) {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Debe cargar el producto, antes de seleccionar los componentes",
						"Debe cargar el producto, antes de seleccionar los componentes");
				FacesContext.getCurrentInstance().addMessage("messages",
						facesMsg);
				return;
			}
			if (componenteConsultado != null
					&& productoConsultado.getIdProducto() != componenteConsultado
							.getIdProducto()
					&& componenteConsultado.getNombre() != null
					&& !componenteConsultado.getNombre().equals("")) {
				fichaTecnicaComponente = new FichaTecnica();
				fichaTecnicaComponente.setIdFichaTecnica(null);
				componenteConsultado.setCantidadComponenteVisualizar(cantidadComponente);
				fichaTecnicaComponente.setComponente(componenteConsultado);
				fichaTecnicaComponente.setProducto(productoConsultado);
				fichaTecnicaComponente.setCantidad(cantidadComponente);
				listaComponentesFicha.add(fichaTecnicaComponente);

				componenteConsultado = new Producto();
				codigoComponente = "";
				habilitarAgregar = true;
			} else {
				FacesMessage facesMsg = new FacesMessage(
						FacesMessage.SEVERITY_ERROR,
						"Debe seleccionar el componente o el componente ingresado es invalido",
						"Debe seleccionar el componente");
				FacesContext.getCurrentInstance().addMessage("messages",
						facesMsg);
			}
		} catch (RuntimeException re) {
			LOGGER.error(re);
			re.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}

	}

	/**
	 * Limpia la informacion de la ficha tecnica
	 */
	public void limpiarFichaTecnica() {
		codigoProducto = "";
		codigoComponente = "";
		productoConsultado = new Producto();
		componenteConsultado = new Producto();
		listaComponentesFicha.clear();
		cantidadComponente=0;
	}

	/**
	 * Elimina el item del componente
	 */
	public void eliminarItemComponente(FichaTecnica fichaTecnicaSeleccionada) {
		try {
			if (listaComponentesFicha != null
					&& listaComponentesFicha.size() > 0) {
				listaComponentesFicha.remove(fichaTecnicaSeleccionada);
				fichaTecnicaServicio
						.eliminarFichaTecnica(fichaTecnicaSeleccionada);

			}
		} catch (RuntimeException re) {
			LOGGER.error(re);
			re.printStackTrace();
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
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
	 * @param productoConsultado
	 *            the productoConsultado to set
	 */
	public void setProductoConsultado(Producto productoConsultado) {
		this.productoConsultado = productoConsultado;
	}

	/**
	 * @return the componenteConsultado
	 */
	public Producto getComponenteConsultado() {
		return componenteConsultado;
	}

	/**
	 * @param componenteConsultado
	 *            the componenteConsultado to set
	 */
	public void setComponenteConsultado(Producto componenteConsultado) {
		this.componenteConsultado = componenteConsultado;
	}

	/**
	 * @return the codigoProducto
	 */
	public String getCodigoProducto() {
		return codigoProducto;
	}

	/**
	 * @param codigoProducto
	 *            the codigoProducto to set
	 */
	public void setCodigoProducto(String codigoProducto) {
		this.codigoProducto = codigoProducto;
	}

	/**
	 * @return the codigoComponente
	 */
	public String getCodigoComponente() {
		return codigoComponente;
	}

	/**
	 * @param codigoComponente
	 *            the codigoComponente to set
	 */
	public void setCodigoComponente(String codigoComponente) {
		this.codigoComponente = codigoComponente;
	}

	/**
	 * @return the habilitarAgregar
	 */
	public boolean isHabilitarAgregar() {
		return habilitarAgregar;
	}

	/**
	 * @param habilitarAgregar
	 *            the habilitarAgregar to set
	 */
	public void setHabilitarAgregar(boolean habilitarAgregar) {
		this.habilitarAgregar = habilitarAgregar;
	}

	/**
	 * @return the fichaTecnica
	 */
	public FichaTecnica getFichaTecnica() {
		return fichaTecnica;
	}

	/**
	 * @param fichaTecnica
	 *            the fichaTecnica to set
	 */
	public void setFichaTecnica(FichaTecnica fichaTecnica) {
		this.fichaTecnica = fichaTecnica;
	}

	/**
	 * @return the listaComponentesFicha
	 */
	public List<FichaTecnica> getListaComponentesFicha() {
		return listaComponentesFicha;
	}

	/**
	 * @param listaComponentesFicha
	 *            the listaComponentesFicha to set
	 */
	public void setListaComponentesFicha(
			List<FichaTecnica> listaComponentesFicha) {
		this.listaComponentesFicha = listaComponentesFicha;
	}

	/**
	 * @return the fichaTecnicaComponente
	 */
	public FichaTecnica getFichaTecnicaComponente() {
		return fichaTecnicaComponente;
	}

	/**
	 * @param fichaTecnicaComponente
	 *            the fichaTecnicaComponente to set
	 */
	public void setFichaTecnicaComponente(FichaTecnica fichaTecnicaComponente) {
		this.fichaTecnicaComponente = fichaTecnicaComponente;
	}

	/**
	 * @return the cantidadComponente
	 */
	public Integer getCantidadComponente() {
		return cantidadComponente;
	}

	/**
	 * @param cantidadComponente
	 *            the cantidadComponente to set
	 */
	public void setCantidadComponente(Integer cantidadComponente) {
		this.cantidadComponente = cantidadComponente;
	}

}