package modelo.negocio;

import java.util.Scanner;

import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.interfaces.DaoPasajero;
import modelo.persistencia.mysql.DaoCocheMySql;
import modelo.persistencia.mysql.DaoPasajeroMySql;

/**
 * Clase actuara de interlocutora entre la base de datos y la aplicación. 
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public class GestorPasajeros {
	
	Scanner sc = new Scanner(System.in);
	Pasajero pasajero = new Pasajero();
	DaoPasajero dp = new DaoPasajeroMySql();
	DaoCoche dc = new DaoCocheMySql();
	
	/**
	 * Método para añadir un pasajero a la base de datos.
	 * Pide al usuario que escriba el nombre, edad y peso del pasajero que desea añadir.
	 * Establece los valores en el objeto pasajero.
	 * Luego llama al método addPasajero en la clase DaoPasajeroMySql para añadir el coche en la base de datos.
	 */
	public void anadirPasajeroGestor() {
		System.out.println("Por favor, escribe el nombre del pasajero a añadir: ");
		String nombre = sc.nextLine();
		System.out.println("Por favor, escribe la edad del pasajero a añadir: ");
		int edad = Integer.parseInt(sc.nextLine());
		System.out.println("Por favor, escribe el peso del pasajero a añadir: ");
		float peso = Float.parseFloat(sc.nextLine());
		
		pasajero.setNombre(nombre);
		pasajero.setEdad(edad);
		pasajero.setPeso(peso);

		dp.addPasajero(pasajero);
	}
	
	/**
	 * Método para borrar un pasajero  de la base de datos.
	 * Pide al usuario que escriba el id del pasajero  que desea borrar.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, se llama al método deletePasajero en la clase DaoPasajeroMySql para borrar el pasajero .
	 */
	public void borrarPasjeroGestor () {
		System.out.println("Por favor, escribe el id del pasajero a borrar: ");
		int id = Integer.parseInt(sc.nextLine());
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
		    dp.deletePasajero(id);
		}
	}
	
	/**
	 * Método para consultar un pasajero  de la base de datos.
	 * Pide al usuario que escriba el id del pasajero  que desea consultar.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, se llama al método obtenerPasajero en la clase DaoPasajeroMySql para consultar el pasajero .
	 */
	public void obtenerPasajeroGestor() {
		System.out.println("Por favor, escribe el id del pasajero a consultar: ");
		int id = Integer.parseInt(sc.nextLine());
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
		System.out.println(dp.obtenerPasajero(id)+"\n");
		}	
	}

	/**
	 * Método para añadir un pasajero a un coche en la base de datos.
	 * Pide al usuario que escriba el id del pasajero  que desea añadir.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, llama primero al método  listCohes de la clase DaoCochesMySql
	 * para enseñar al usuario la lista de coches a eligir.
	 * Luego pide al usuario el id del coche al que se le añadirá el pasajero,
	 * entonces llama al método addPasajeroCoche en la clase DaoPasajeroMySql para añadir el pasajero .
	 */
	public void andirCochePasGestor () {
		System.out.println("Por favor, escribe el id del pasajero: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
			System.out.println("Por favor, escoja el id del coche de la lista seguiente: \n");
			dc.ListCoches();
			int idCoche = sc.nextInt();sc.nextLine();
			
			pasajero.setId(id);
			pasajero.setIdCoche(idCoche);
			dp.addPasajeroCoche(pasajero);
		     }
	}
	
	/**
	 * Método para borrar un pasajero de un coche en la base de datos.
	 * Pide al usuario que escriba el id del pasajero  que desea borrar.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, se llama al método borrarCochePasGestor en la clase DaoPasajeroMySql para consultar el pasajero .
	 */
	public void borrarCochePasGestor () {
		System.out.println("Por favor, escribe el id del pasajero: ");
		int id = sc.nextInt();sc.nextLine();
		
		if(dp.obtenerPasajero(id)==null){
			System.out.println("Este id del pasajero no existe en la base de datos\n");
		}else {
			
			pasajero.setId(id);
			dp.deletePasajeroCoche(pasajero);
		     }
	}
	
	/**
	 * Método para consultar los pasajeros de un coche en la base de datos.
	 * Pide al usuario que escriba el id del coche que desea consultar.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, se llama al método listPasajerosCoche en la clase DaoPasajeroMySql
	 *  para obtener la lista de pasajeros de un  coche y se muestra en pantalla.
	 */
	public void ListCochePasGestor () {
		System.out.println("Por favor, escribe el id del coche: ");
		int idCoche = sc.nextInt();sc.nextLine();
		
		if(dc.obtenerCoche(idCoche)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
			dp.listPasajerosCoche(idCoche);
		}
	}

}
