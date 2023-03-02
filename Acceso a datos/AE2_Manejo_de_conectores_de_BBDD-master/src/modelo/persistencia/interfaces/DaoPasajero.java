package modelo.persistencia.interfaces;

import java.util.List;
import modelo.entidad.Pasajero;

/**
 * Interfaz usada para especificar los métodos  que tienen
 *  que gestionar los pasajeros.
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public interface DaoPasajero {
	
	/**
	 * Método que da de alta un pasajero en la BBDD. Se generará el ID de manera
	 * automática.
	 * @param p el pasajero a dar de alta
	 * @return true en caso de que se haya dado de alta. false en caso de error
	 * con la BBDD.
	 */
	boolean addPasajero(Pasajero p);
	/**
	 * Método que borra un pasajero en la BBDD por su id.
	 * @param id del pasajero a borrar.
	 * @return true en caso de que se haya borrado. false en caso de error
	 * con la BBDD.
	 */
	boolean deletePasajero(int id);

	/**
	 * Método que obtiene un pasajero en la BBDD por su id.
	 * @param id del pasajero a obtener.
	 * @return pasajero solicitado
	 */
	Pasajero obtenerPasajero(int id);
	/**
	 * Método que obtiene la lista de pasajeros en la BBDD.
	 * @param no necsita parámetro.
	 * @return lista de pasajeros que hay en la BBDD.
	 */
	List<Pasajero> listPasajeros();
	/**
	 * Método que añade un coche a un pasajero en la BBDD por id.
	 * Antes muestra la lista de coches para eligir.
	 * @param p el pasajero a añadir al coche.
	 * @return true en caso de que se haya añadido. False en caso de error
	 * con la BBDD.
	 */
	boolean addPasajeroCoche(Pasajero p);
	/**
	 * Método que elimina un coche a un pasajero en la BBDD por el id de cada uno .
	 * @param p el pasajero a quien hay que borrar el coche.
	 * @return true en caso de que se haya eliminado. False en caso de error
	 * con la BBDD.
	 */
	boolean deletePasajeroCoche(Pasajero p);
	/**
	 * Método que obtiene la lista de pasajeros de un coche en la BBDD 
	 * mediate el id del coche.
	 * @param idCoche, id del coche al que queremos ver sus pasajeros.
	 * @return lista de pasajeros de un coche.
	 */
	List<Pasajero> listPasajerosCoche(int idCoche);

}
