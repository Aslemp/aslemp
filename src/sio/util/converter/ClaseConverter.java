package sio.util.converter;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import sio.entidades.Clase;
import sio.servicio.impl.ClaseServicio;

//@FacesConverter(value = "ClaseConverter", forClass=Clase.class)
public class ClaseConverter implements Converter {
		
	/**
	 * Interface del servicio de PlantillaCorreo
	 */
	@Inject
	private ClaseServicio claseServicioBuscar;
			
	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {	             
        if (arg2 == null || arg2.isEmpty()) {
            return null;
        }	        
        Integer idClase = Integer.parseInt(arg2);
        Clase clase = new Clase();
        try {
        	/*ClaseServicio claseServicio = arg0.getApplication()
                     .evaluateExpressionGet(arg0, "#{claseServicio}",
                    		 ClaseServicio.class);             
        	clase =  claseServicio.buscarClasePorId(idClase);*/
        	clase =  claseServicioBuscar.buscarClasePorId(idClase);
			return clase;
		} catch (Exception e) {				
			e.printStackTrace();
			 return null;
		}		   
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		try {
			if (object == null || object.toString()==null || object.toString().isEmpty()) {
				return null;
			}
		} catch (NullPointerException e) {
			return null;
		}
		try {			
			if(object instanceof Clase){				
				return String.valueOf(((Clase) object).getIdClase());
			}else{
				return null;
			}
		} catch (ClassCastException e) {
			throw new IllegalArgumentException("El Objeto " + object
					+ " es del tipo " + object.getClass().getName()
					+ "; se esperaba otro tipo", e);
		} catch (Exception e) {
			throw new IllegalArgumentException("El Objeto " + object
					+ " presetó un problema al obtener su representación en String " + 
					e.getMessage(), e);
		} 
	}		
	
}