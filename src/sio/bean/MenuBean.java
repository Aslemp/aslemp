package sio.bean;

import java.io.Serializable;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.apache.log4j.Logger;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

/**
 * Managed Bean (WBO) del menu de la aplicacion.
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 11-nov-2014 02:14:00 p.m.
 */
@ManagedBean
public class MenuBean implements Serializable {
	
	/**
	 * Variable de serializacion
	 */
	private static final long serialVersionUID = 7457169225823098127L;
	
	private MenuModel model;
	
	private MenuModel model1;
	
	private boolean menuProducto=false;
	
	private boolean menuProveedor=false;
	
	private boolean menuClase=false;
	
	private boolean menuGrupo=false;
	
	private boolean menuEntradaSalida=false;
	
	private boolean menuUnidadMedida=false;
	
	private String pagina="";
			
	@PostConstruct
	public void inicializarVariables() {				
		FacesContext context = FacesContext.getCurrentInstance();
		Iterator<FacesMessage> it = context.getMessages();
		while ( it.hasNext() ) {
		    it.next();
		    it.remove();
		}
		
		model = new DefaultMenuModel();
		
		model1 = new DefaultMenuModel();
        
        //Primer submenu
        DefaultSubMenu menu = new DefaultSubMenu("Menu Inventario");
        
        DefaultMenuItem item = new DefaultMenuItem("Producto");
        //item.setUrl("/paginas/producto.xhtml");
        item.setUrl("body.faces");        
        item.setIcon("");
        menu.addElement(item);
                       
        //Segundo submenu         
        DefaultMenuItem item2 = new DefaultMenuItem("Proveedor");
        item2.setUrl("/paginas/proveedor.xhtml");
        item2.setIcon("");
        menu.addElement(item2);
        
        //Tercer submenu         
        DefaultMenuItem item3 = new DefaultMenuItem("Clase");
        item3.setUrl("/paginas/clase.xhtml");
        item3.setIcon("");
        menu.addElement(item3);
        
        //Cuarto submenu         
        DefaultMenuItem item4 = new DefaultMenuItem("Grupo");
        item4.setUrl("/paginas/grupo.xhtml");
        item4.setIcon("");
        menu.addElement(item4);
        
        //Quinto submenu         
        DefaultMenuItem item5 = new DefaultMenuItem("Unidad de medida");
        item5.setUrl("/paginas/unidad_medida.xhtml");
        item5.setIcon("");
        menu.addElement(item5);
        
        //Sexto submenu         
        DefaultMenuItem item6 = new DefaultMenuItem("Entrada de mercancia");
        item6.setUrl("/paginas/entrada_mercancia.xhtml");
        item6.setIcon("");
        menu.addElement(item6);
        
        //Septimo submenu         
        DefaultMenuItem item7 = new DefaultMenuItem("Salida de mercancia");
        item7.setUrl("/paginas/salida_mercancia.xhtml");
        item7.setIcon("");
        menu.addElement(item7);
        
        //Octavo submenu         
        DefaultMenuItem item8 = new DefaultMenuItem("Reporte entrada-salida");
        item8.setUrl("/paginas/reporte_entrada_salida_mercancia.xhtml");
        item8.setIcon("");
        menu.addElement(item8);
                        
        model.addElement(menu);
        
        //Segundo submenu
        DefaultSubMenu menu1 = new DefaultSubMenu("Menu Produccion");
        
        //Noveno submenu         
        DefaultMenuItem item9 = new DefaultMenuItem("Ficha técnica");
        item9.setUrl("/paginas/ficha_tecnica.xhtml");
        item9.setIcon("");
        menu1.addElement(item9);
        
        //Decimo submenu         
        DefaultMenuItem item10 = new DefaultMenuItem("Orden de produccion");
        item10.setUrl("/paginas/orden_produccion.xhtml");
        item10.setIcon("");
        menu1.addElement(item10);
        
        model1.addElement(menu1);
        
        //pagina="/paginas/producto.xhtml";
        
	}

	/**
	 * Constructor por defecto
	 */	
	public MenuBean() {
		
	}

	public void cargarMenuProducto(){		
		pagina="/paginas/producto.xhtml";
	}
	
	public void prueba(){		
		pagina="prueba";
	}
	
	/**
	 * Se agrega variable de log
	 */
	protected static final Logger LOGGER = Logger.getLogger(MenuBean.class);

	public MenuModel getModel() {
		return model;
	}

	public void setModel(MenuModel model) {
		this.model = model;
	}

	/**
	 * @return the model1
	 */
	public MenuModel getModel1() {
		return model1;
	}

	/**
	 * @param model1 the model1 to set
	 */
	public void setModel1(MenuModel model1) {
		this.model1 = model1;
	}

	/**
	 * @return the menuProducto
	 */
	public boolean isMenuProducto() {
		return menuProducto;
	}

	/**
	 * @param menuProducto the menuProducto to set
	 */
	public void setMenuProducto(boolean menuProducto) {
		this.menuProducto = menuProducto;
	}

	/**
	 * @return the menuProveedor
	 */
	public boolean isMenuProveedor() {
		return menuProveedor;
	}

	/**
	 * @param menuProveedor the menuProveedor to set
	 */
	public void setMenuProveedor(boolean menuProveedor) {
		this.menuProveedor = menuProveedor;
	}

	/**
	 * @return the menuClase
	 */
	public boolean isMenuClase() {
		return menuClase;
	}

	/**
	 * @param menuClase the menuClase to set
	 */
	public void setMenuClase(boolean menuClase) {
		this.menuClase = menuClase;
	}

	/**
	 * @return the menuGrupo
	 */
	public boolean isMenuGrupo() {
		return menuGrupo;
	}

	/**
	 * @param menuGrupo the menuGrupo to set
	 */
	public void setMenuGrupo(boolean menuGrupo) {
		this.menuGrupo = menuGrupo;
	}

	/**
	 * @return the menuEntradaSalida
	 */
	public boolean isMenuEntradaSalida() {
		return menuEntradaSalida;
	}

	/**
	 * @param menuEntradaSalida the menuEntradaSalida to set
	 */
	public void setMenuEntradaSalida(boolean menuEntradaSalida) {
		this.menuEntradaSalida = menuEntradaSalida;
	}

	/**
	 * @return the menuUnidadMedida
	 */
	public boolean isMenuUnidadMedida() {
		return menuUnidadMedida;
	}

	/**
	 * @param menuUnidadMedida the menuUnidadMedida to set
	 */
	public void setMenuUnidadMedida(boolean menuUnidadMedida) {
		this.menuUnidadMedida = menuUnidadMedida;
	}

	/**
	 * @return the pagina
	 */
	public String getPagina() {
		return pagina;
	}

	/**
	 * @param pagina the pagina to set
	 */
	public void setPagina(String pagina) {
		this.pagina = pagina;
	}
		
}