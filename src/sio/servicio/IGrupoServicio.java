package sio.servicio;

import java.util.List;

import sio.entidades.Grupo;


/**
 * Interface para las operaciones con los grupos
 * @author Luis Fernando Martinez Galvez
 * @version 1.0
 * @created 18-nov-2014 10:00:00 a.m.
 */
public interface IGrupoServicio {
				
	 /**
	  * Permite guardar los grupos
	  * @param producto objeto grupo
	  * @return objeto grupo guardado
	  * @throws Exception excepcion
	  */
	Grupo guardarGrupo(Grupo grupo) throws Exception;
	
	/**
	 * M�todo buscarTodosGrupos	
	 * @throws Exception excepci�n	 
	 * @return List<Grupo> lista de grupos
	 */
	List<Grupo> buscarTodosGrupos() throws Exception;
	
	/**
	 * M�todo consultar por idGrupo
	 * @throws Exception excepci�n	
	 * @param idGrupo identificador del grupo
	 * @return Grupo objeto grupo completo
	 */
	Grupo buscarGrupoPorId(Integer idGrupo) throws Exception;
	
}