package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Coche;

/**
 * Interfaz usada para especificar los métodos  que tienen
 *  que gestionar los coches.
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public interface DaoCoche {
	
	/**
	 * Método que da de alta un coche en la BBDD. Se generará el ID de manera
	 * automática.
	 * @param c el coche a dar de alta
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 */
	boolean addCoche(Coche c);
	/**
	 * Método que borra un coche en la BBDD por su id.
	 * @param id del coche a borrar.
	 * @return true en caso de que se haya borrado. false en caso de error
	 * con la BBDD.
	 */
	boolean deleteCoche(int id);
	/**
	 * Método que modifica un coche en la BBDD por su id.
	 * @param c el  coche  a modificar
	 * @return true en caso de que se haya modificado. False en caso de error
	 * con la BBDD.
	 */
	boolean updateCoche(Coche c);
	/**
	 * Método que obtiene un coche en la BBDD por su id.
	 * @param id del coche  a obtener.
	 * @return Coche solicitado
	 */
	Coche obtenerCoche(int id);
	/**
	 * Método que obtiene la lista de coches en la BBDD.
	 * @param no necsita parámetro.
	 * @return lista de coches que hay en la BBDD.
	 */
	List<Coche> ListCoches();

}
