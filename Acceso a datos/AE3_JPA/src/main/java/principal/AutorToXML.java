package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import modelo.entidad.req1.Autor;
import modelo.entidad.req1.Editorial;
import modelo.entidad.req1.Libreria;
import modelo.entidad.req1.Libro;

public class AutorToXML {
	
	
	
	
	public static void main(String[] args) {
		
		JAXBContext contexto; //Contexto de JAXB para la clase Autor.
		try {
			contexto = JAXBContext.newInstance(Autor.class);
		} catch (JAXBException e) {
			System.out.println("Error creando el contexto para la clase Autor");
			e.printStackTrace();			
			return;//Dejamos de ejecutar el metodo main.
		} 
		
		Marshaller m;
		
		try {
			
			m = contexto.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			
			/*
			 * Damos de alta 3 autores.
			 */
			
			Autor cervantes = new Autor(null,"Miguel", "De Cervantes", "29-09-1547", null);
			
			Autor rowling = new Autor(null,"J.K", "Rowling", "31-7-1965", null);
			
			Autor castillo = new Autor(null,"Javier", "Castillo", "23-8-1987", null);
			
			
			/*
			 * Damos de alta 2 Editoriales.
			 */
			
			Editorial deBolsillo = new Editorial(null, "DEBOLSILLO", "C/Inventada", null);
			Editorial salamandra = new Editorial(null, "Salamandra", "Poligono La algodonera, 24", null);
			
			/*
			 * Damos de alta 2 Librerias.
			 */
			
			Libreria lib1 = new Libreria(null, "Libros Baratos", "Jose Antonio", "C/Sin nombre, 8", null);
			Libreria lib2 = new Libreria(null, "Libros Vintage", "Ramon Alguacil", "C/Alcarria, sn", null);
			
			/*
			 * Damos de alta 8 libros, sin autores, ni librerias, ni editoriales asociadas.
			 */
			
			Libro libro1 = new Libro(null, "Harry Potter y la piedra filosofal", 15, deBolsillo, null, null); //JK y pertenence a la editorial DeBolsillo
			Libro libro2 = new Libro(null, "Harry Potter y el cáliz de fuego", 8, deBolsillo, null, null); //JK y pertenence a la editorial DeBolsillo
			Libro libro3 = new Libro(null, "La chica de nieve", 11, salamandra, null, null); //Javoer castillo y pertenence a la editorial Salamandra
			Libro libro4 = new Libro(null, "El juego del alma", 6, salamandra, null, null); //Javier castillo y pertenence a la editorial Salamandra
			Libro libro5 = new Libro(null, "Harry Potter y el prisionero de azkaban", 15, salamandra, null, null); //JK y pertenence a la editorial Salamndra
			Libro libro6 = new Libro(null, "El quijote", 29, salamandra, null, null); //Cervantes y pertenence a la editorial Salamandra
			Libro libro7 = new Libro(null, "Entremeses", 11, deBolsillo, null, null); //Cervantes y pertenence a la editorial DeBolsillo
			Libro libro8 = new Libro(null, "El dia que se perdió la cordura", 10, deBolsillo, null, null); //Javier Castillo y pertenence a la editorial DeBolsillo
			
			
			// 1º - Asociamos los libros a sus autores correspondientes.
			
			
			// ======== LIBROS DE JK ROWLING  ======== 
			
			List<Libro> librosRowling = new ArrayList<Libro>();
			librosRowling.add(libro1);
			librosRowling.add(libro2);
			librosRowling.add(libro5);
			
			rowling.setLibros(librosRowling);
			
			//GENERAMOS XML CON LOS LIBROS DE JK ROWLING
			m.marshal(rowling, new File ("JK_ROWLING.xml"));
			System.out.println("El archivo JK_ROWLING.xml ha sido creado con exito,"
					+ " refresque su eclipse :)");
			
			
			
			// ======== LIBROS DE JAVIER CASTILLO ======== 
			List<Libro> librosCastillo = new ArrayList<Libro>();
			librosCastillo.add(libro3);
			librosCastillo.add(libro4);
			librosCastillo.add(libro8);
			
			castillo.setLibros(librosCastillo);
			
			//GENERAMOS XML CON LOS LIBROS DE JAVIER CASTILLO
			m.marshal(castillo, new File ("JAVIER_CASTILLO.xml"));
			System.out.println("El archivo JAVIER_CASTILLO.xml ha sido creado con exito,"
					+ " refresque su eclipse :)");
					
			
			
			// ======== LIBROS DE CERVANTES ======== 
			List<Libro> librosCervantes = new ArrayList<Libro>();
			librosCervantes.add(libro6);
			librosCervantes.add(libro7);
			
			cervantes.setLibros(librosCervantes);
			
			//GENERAMOS XML CON LOS LIBROS DE JAVIER CASTILLO
			m.marshal(cervantes, new File ("CERVANTES.xml"));
			System.out.println("El archivo CERVANTES.xml ha sido creado con exito,"
					+ " refresque su eclipse :)");
			
			
			
		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato XML");
			e.printStackTrace();
		}
		
		

	}

}
