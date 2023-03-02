package modelo.negocio;

import java.util.Scanner;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.mysql.DaoCocheMySql;

/**
 * Clase actuara de interlocutora entre la base de datos y la aplicación.
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public class GestorCoches {
	
	Scanner sc = new Scanner(System.in);
	Coche coche = new Coche();
	DaoCoche dc = new DaoCocheMySql();
	
	/**
	 * Método para añadir un coche a la base de datos.
	 * Pide al usuario que escriba la matrícula, marca, modelo y color del coche que desea añadir.
	 * Establece los valores en el objeto coche.
	 * Luego llama al método addCoche en la clase DaoCocheMySql para añadir el coche en la base de datos.
	 */
	public void anadirCocheGstor() {
		System.out.println("Por favor, escribe la matrícula del coche a añadir: ");
		String matricula = sc.nextLine();
		System.out.println("Por favor, escribe la marca del coche a añadir: ");
		String marca = sc.nextLine();
		System.out.println("Por favor, escribe el modelo del coche a añadir: ");
		String modelo = sc.nextLine();
		System.out.println("Por favor, escribe el color del coche a añadir: ");
		String color = sc.nextLine();
	
		coche.setMatricula(matricula);
		coche.setMarca(marca);
		coche.setModelo(modelo);
		coche.setColor(color);
		
		dc.addCoche(coche);	
	}
	
	/**
	 * Método para borrar un coche de la base de datos.
	 * Pide al usuario que escriba el id del coche que desea borrar.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, se llama al método deleteCoche en la clase DaoCocheMySql para borrar el coche.
	 */
	public void borrarCocheGestor () {
		System.out.println("Por favor, escribe el id del coche a borrar: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dc.obtenerCoche(id)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
			dc.deleteCoche(id);
		}
	}
	/**
	 * Método para consultar un coche en la base de datos.
	 * Pide al usuario que escriba el id del coche que desea consultar.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, se llama al método obtenerCoche en la clase DaoCocheMySql para obtener el coche
	 * y se muestra en pantalla.
	 */
	public void consultarCocheGestor() {
		System.out.println("Por favor, escribe el id del coche a consultar: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dc.obtenerCoche(id)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
		      System.out.println(dc.obtenerCoche(id)+"\n");
		}
	}
	/**
	 * Método para modificar un coche en la base de datos.
	 * Pide al usuario que escriba el id del coche que desea modificar.
	 * Si el id no existe en la base de datos, se muestra un mensaje indicando que el id no existe.
	 * Si el id existe, se llama al método updateCoche en la clase DaoCocheMySql para modificar el coche
	 * y se muestra en pantalla.
	 */
	public void modificarCocheGestor() {
		System.out.println("Por favor, escribe el id del coche a modificar: ");
		int id = Integer.parseInt(sc.nextLine());
		
		if(dc.obtenerCoche(id)==null){
			System.out.println("Este id del coche no existe en la base de datos\n");
		}else {
			System.out.println("Por favor, escribe la matrícula del coche a modificar: ");
			String matricula = sc.nextLine();
			System.out.println("Por favor, escribe la marca del coche a modificar: ");
			String marca = sc.nextLine();
			System.out.println("Por favor, escribe el modelo del coche a modificar: ");
			String modelo = sc.nextLine();
			System.out.println("Por favor, escribe el color del coche a modificar: ");
			String color = sc.nextLine();
			
			coche.setId(id);
			coche.setMatricula(matricula);
			coche.setMarca(marca);
			coche.setModelo(modelo);
			coche.setColor(color);
			
			dc.updateCoche(coche);
		}
	}

}
