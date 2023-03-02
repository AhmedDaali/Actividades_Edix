package principal;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import modelo.entidad.req1.Autor;
import modelo.entidad.req1.Editorial;
import modelo.entidad.req1.Libreria;
import modelo.entidad.req1.Libro;

public class LibreriaToXML {

	public static void main(String[] args) {
		JAXBContext contexto; //Contexto de JAXB para la clase Autor.
		try {
			contexto = JAXBContext.newInstance(Editorial.class);
		} catch (JAXBException e) {
			System.out.println("Error creando el contexto para la clase Editorial");
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
			
			Libro libro1 = new Libro(null, "Harry Potter y la piedra filosofal", 15, deBolsillo, null, rowling); //JK y pertenence a la editorial DeBolsillo
			Libro libro2 = new Libro(null, "Harry Potter y el cáliz de fuego", 8, deBolsillo, null, rowling); //JK y pertenence a la editorial DeBolsillo
			Libro libro3 = new Libro(null, "La chica de nieve", 11, salamandra, null, castillo); //Javoer castillo y pertenence a la editorial Salamandra
			Libro libro4 = new Libro(null, "El juego del alma", 6, salamandra, null, castillo); //Javier castillo y pertenence a la editorial Salamandra
			Libro libro5 = new Libro(null, "Harry Potter y el prisionero de azkaban", 15, salamandra, null, rowling); //JK y pertenence a la editorial Salamndra
			Libro libro6 = new Libro(null, "El quijote", 29, salamandra, null, cervantes); //Cervantes y pertenence a la editorial Salamandra
			Libro libro7 = new Libro(null, "Entremeses", 11, deBolsillo, null, cervantes); //Cervantes y pertenence a la editorial DeBolsillo
			Libro libro8 = new Libro(null, "El dia que se perdió la cordura", 10, deBolsillo, null, castillo); //Javier Castillo y pertenence a la editorial DeBolsillo
			
			
			//ASIGNAMOS A LA LIBRERIA1 "LIBROS BARATOS" LOS LIBROS 1, 2, 3 Y 4.
			// ============ LIBROS LIBRERIA "LIBROS BARATOS"  ============ 
			
			List<Libro> librosLib1 = new ArrayList<Libro>();
			librosLib1.add(libro1);
			librosLib1.add(libro2);
			librosLib1.add(libro3);
			librosLib1.add(libro4);
			
			lib1.setColeccionLibros(librosLib1);
			
			//GENERAMOS XML CON LOS LIBROS DE LA LIBRERIA "LIBROS BARATOS"			
			m.marshal(lib1, System.out);
			m.marshal(lib1, new File ("LIBROS_BARATOS.xml"));
			System.out.println("El archivo LIBROS_BARATOS.xml ha sido creado con exito,"
								+ " refresque su eclipse :)");
			
			
			
			//ASIGNAMOS A LA LIBRERIA2 "LIBROS VINTAGE" LOS LIBROS 5, 6,7 Y 8.
			// ============ LIBROS LIBRERIA "LIBROS VINTAGE"  ============ 
			
			List<Libro> librosLib2 = new ArrayList<Libro>();
			librosLib2.add(libro5);
			librosLib2.add(libro6);
			librosLib2.add(libro7);
			librosLib2.add(libro8);
			
			lib2.setColeccionLibros(librosLib2);
			
			//GENERAMOS XML CON LOS LIBROS DE LA LIBRERIA "LIBROS VINTAGE"			
			m.marshal(lib2, System.out);
			m.marshal(lib2, new File ("LIBROS_VINTAGE.xml"));
			System.out.println("El archivo LIBROS_VINTAGE.xml ha sido creado con exito,"
								+ " refresque su eclipse :)");
			
		
			
						
		} catch (JAXBException e) {
			System.out.println("Error convertiendo el objeto a formato XML");
			e.printStackTrace();
		}
	}


}


