package vista;

import java.util.Scanner;

import modelo.negocio.GestorCoches;
import modelo.persistencia.interfaces.DaoCoche;
import modelo.persistencia.mysql.DaoCocheMySql;

/**
 * Aplicación que permite gestionar coches y pasajeros.
 * @author Grupo 5
 * @version 1.0
 * @since2023
 */
public class Aplicacion {
	
	/**
	 * Método principal que muestra el menú principal y
	 *  permite al usuario elegir la opción que desea realizar.
	 * @throws Exception En caso de error en la conexión.
	 * @throws NumberFormatException en caso de que el usuario 
	 * insertar otro dato que no sea un entero.
	 */
	public static void main(String[] args)throws Exception  {
		
        boolean continuar = true;
        Scanner sc = new Scanner(System.in);
        
        try{
        	while(continuar){
		        System.out.println("******Elige el número de la opción que desea efectuar*****\n");
		        System.out.println("1.Añadir coche\n");
		        System.out.println("2.Borrar coche por ID\n");
				System.out.println("3.Consulta coche por ID\n");
				System.out.println("4.Modificar coche por ID\n");
				System.out.println("5.Listado de coches\n");
				System.out.println("6.Gestión de pasajeros\n");
				System.out.println("7.Terminar el programa\n");
					
				DaoCoche dc = new DaoCocheMySql();
				MenuPasajero menuPas = new MenuPasajero();
				GestorCoches gc = new GestorCoches();
				
				
				try {
					int opcion = Integer.parseInt(sc.nextLine());;
					if(opcion<1 || opcion>7) {
						System.out.println("Opción no válida\n");
						
					}else {
						switch(opcion) {
						
						case 1:
							gc.anadirCocheGstor();		
							break;
							
						case 2:							
							gc.borrarCocheGestor();						
							break;
							
						case 3:						
							gc.consultarCocheGestor();							
							break;
							
						case 4:							
							gc.modificarCocheGestor();							
							break;
							
						case 5:					
							dc.ListCoches();						
							break;
							
						case 6:
							menuPas.menu();
							break;
							
						case 7:
							System.out.println("Saliendo.....");
							continuar=false;
							sc.close();
							System.out.println("Fin de sesión");
							
						    break;
						}
					}	
			    }catch(NumberFormatException e) {
				    System.out.println("Dato no válido\n");
			    }
        	}		
		//Recogemos las excepciones con el 'catch'.
	    }catch (Exception e) {
		     System.out.println("Error en la conexion");
		    e.printStackTrace();
        }	
	}
}
