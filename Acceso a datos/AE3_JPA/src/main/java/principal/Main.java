package principal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import modelo.entidad.req1.Autor;
import modelo.entidad.req1.Editorial;
import modelo.entidad.req1.Libreria;
import modelo.entidad.req1.Libro;

public class Main {

	public static EntityManagerFactory emf = null;

	public static EntityManager em = null;
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		//Nos conectamos a nuestra base de datos.
		
		emf = Persistence.createEntityManagerFactory("AE3_JPA");
		
		//MÉTODO CREADO PARA INICIAR NUESTRA BASE DE DATOS Y CARGAR TODOS LOS DATOS REQUERIDOS.
		iniciarBBDD();
		
		em = emf.createEntityManager();
		
		// =============== SENTENCIAS JPQL ===============
		
		/*
		 * 1º MOSTRAR TODOS LOS LIBROS DADOS DE ALTA, CON SU EDITORIAL Y AUTOR.
		 */
		
		System.out.println("=============== SENTENCIAS JPQL ===============");
		System.out.println("================= JPQL 1 ==================");
		Query query = em.createQuery("SELECT DISTINCT lib.titulo, lib.editorial, lib.autor FROM Libro lib");
		List <Object[]> resultados = query.getResultList();
		System.out.println("==== Listado de libros con sus autores y editoriales ====");
		for (Object[] p : resultados) {
			// p[0] contiene el titulo del libro.
			// p[1] contiene la editorial asociada al libro.
			// p[2] contiene el autor asociado al libro.
			System.out.println(p[0] + " - " + p[1]+ " - " + p[2]); 
		}
		
		/*
		 * 2º MOSTRAR TODOS LOS AUTORES DADOS DE ALTA, CON SUS LIBROS ASOCIADOS.
		*/
		
		System.out.println("================= JPQL 2 ==================");
		Query query1 = em.createQuery("SELECT DISTINCT lib.autor, lib.titulo FROM Libro lib");
		List <Object[]> resultados1 = query1.getResultList();
		System.out.println("==== Listado de autores con sus libros asociados ====");
		for (Object[] p : resultados1) {
			System.out.println(p[0] + " - " + p[1]);
			
		}
		 
		
		/*
		 * 3º MOSTRAR TODAS LAS LIBRERIAS, CON SOLAMENTE SUS LIBROS ASOCIADOS.
		 * 
		*/
		System.out.println("================= JPQL 3 ==================");
		
		TypedQuery query2 = em.createQuery("SELECT libr FROM Libreria libr ", Libreria.class);
		System.out.println("==== Mostrar todas las librerías, con solamente sus libros asociados ====");
		
		List<Libreria> resultados2 = query2.getResultList();
			for (Libreria p : resultados2) {
				System.out.println("Libreria " + p.getNombre());
				List<Libro> coleccionLibros = p.getLibros();
				System.out.println("Tiene los siguientes libros:");
			
				for (Libro libro : coleccionLibros) {
				System.out.println(libro.getTitulo());
				}
				System.out.println("=============================");
			}
			
			
		/*
		 * 4º MOSTRAR TODAS LOS LIBROS DADOS DE ALTA Y EN LA LIBRERÍA EN LA QUE ESTÁN.
		 * 
		*/
		
		System.out.println("================= JPQL 4 ==================");
		
		Query query3 = em.createQuery("SELECT lib FROM Libro lib", Libro.class);
		System.out.println("-----> Mostrar todos los libros dados de alta, con su correspondiente libreria <-----");
		List<Libro> resultados3 = query3.getResultList();
			for (Libro p : resultados3) {
				System.out.println("El libro " + p.getTitulo() + " :");
				
				List<Libreria> librerias = p.getLibrerias();
				System.out.println("Está en estas librerías: ");
				
				for (Libreria l : librerias) {
				System.out.println(l.getNombre());
				}
			}
		
			
			em.close();
			emf.close();
			
		}
		
		
	private static void iniciarBBDD() {
		
		em = emf.createEntityManager();
		em.getTransaction().begin();
		
		/*
		 * El ejercicio nos pide:
		 * 
		 * 1. Dar de alta 3 autores.
		 * La entidad Autor tiene relación 1-N con la entidad Libro.
		 */
		
		Autor cervantes = new Autor(null,"Miguel", "De Cervantes", "29-09-1547", null);
		//cervantes.setFechaNacimiento(new Date(-353, 8, 29)); //29-09-1547
		
		Autor rowling = new Autor(null,"J.K", "Rowling", "31-7-1965", null);
		//rowling.setFechaNacimiento(new Date(65, 6, 31)); //31-7-1965
		
		Autor castillo = new Autor(null,"Javier", "Castillo", "23-8-1987", null);
		//castillo.setFechaNacimiento(new Date(87, 7, 23)); //23-8-1987

		
		/*
		 * 2. Dar de alta 2 editoriales.
		 * La entidad Editorial tiene relacion M-N con libro.
		 */
		
		Editorial deBolsillo = new Editorial(null, "DEBOLSILLO", "C/Inventada", null);
		Editorial salamandra = new Editorial(null, "Salamandra", "Poligono La algodonera, 24", null);
		
		/*
		 * 3. Dar de alta 8 libros. Cada libro será escrito por uno de los autores dados de alta
		 * y pertenecerá a una de las editoriales dados de alta previamente.
		 * Creamos los libros y le asignamos su autor y editorial.
		 * La entidad libro tiene relacion N-1 con la entidad Autor y con Editorial.
		 * La relacion entre Libro y Libreria es N-M.
		 * 
		 */
		Libro libro1 = new Libro(null, "Harry Potter y la piedra filosofal", 15, deBolsillo, null, rowling); //JK y pertenence a la editorial DeBolsillo
		Libro libro2 = new Libro(null, "Harry Potter y el cáliz de fuego", 8, deBolsillo, null, rowling); //JK y pertenence a la editorial DeBolsillo
		Libro libro3 = new Libro(null, "La chica de nieve", 11, salamandra, null, castillo); //Javoer castillo y pertenence a la editorial Salamandra
		Libro libro4 = new Libro(null, "El juego del alma", 6, salamandra, null, castillo); //Javier castillo y pertenence a la editorial Salamandra
		Libro libro5 = new Libro(null, "Harry Potter y el prisionero de azkaban", 15, salamandra, null, rowling); //JK y pertenence a la editorial Salamndra
		Libro libro6 = new Libro(null, "El quijote", 29, salamandra, null, cervantes); //Cervantes y pertenence a la editorial Salamandra
		Libro libro7 = new Libro(null, "Entremeses", 11, deBolsillo, null, cervantes); //Cervantes y pertenence a la editorial DeBolsillo
		Libro libro8 = new Libro(null, "El dia que se perdió la cordura", 10, deBolsillo, null, castillo); //Javier Castillo y pertenence a la editorial DeBolsillo
		
		
		
		//BIDIRECCIONAMOS LOS AUTORES CON SUS LIBROS. 1 AUTOR ESCRIBE MUCHOS LIBROS.
		
		//JK Rowling escribe los libros 1,2 y 5.
		
		List<Libro> librosRowling = new ArrayList<Libro>();
		librosRowling.add(libro1);
		librosRowling.add(libro2);
		librosRowling.add(libro5);
		
		rowling.setLibros(librosRowling);
		
		//Javier Castillo escribe los libros 3,4 y 8.
		
		List<Libro> librosCastillo = new ArrayList<Libro>();
		librosCastillo.add(libro3);
		librosCastillo.add(libro4);
		librosCastillo.add(libro8);
		
		castillo.setLibros(librosCastillo);
		
		//Cervantes escribe los libros 6 y 7.
		
		List<Libro> librosCervantes = new ArrayList<Libro>();
		librosCervantes.add(libro6);
		librosCervantes.add(libro7);
		
		cervantes.setLibros(librosCervantes);
		
		
		//BIDIRECCIONAMOS LAS EDITORIALES CON LOS LIBROS. 1 EDITORIAL EDITA MUCHOS LIBROS.
		
		//La editorial DeBolsillo tiene los libros 1, 2, 7 y 8.
		
		List<Libro> librosDeBolsillo = new ArrayList<Libro>();
		librosDeBolsillo.add(libro1);
		librosDeBolsillo.add(libro2);
		librosDeBolsillo.add(libro7);
		librosDeBolsillo.add(libro8);
		
		deBolsillo.setLibros(librosDeBolsillo);

		
		//La editorial Salamandra tiene los libros 3, 4, 5 y 6.
		
		List<Libro> librosSalamandra = new ArrayList<Libro>();
		librosSalamandra.add(libro3);
		librosSalamandra.add(libro4);
		librosSalamandra.add(libro5);
		librosSalamandra.add(libro6);
				
		salamandra.setLibros(librosSalamandra);
		
		
		/*
		 * 4. Dar de alta 2 librerias. Cada librería tendrá 4 libros dados de alta previamente.
		 * La entidad libreria tiene una relación N-M con la entidad Libro.
		 */
		
		Libreria lib1 = new Libreria(null, "Libros Baratos", "Jose Antonio", "C/Sin nombre, 8", null);
		Libreria lib2 = new Libreria(null, "Libros Vintage", "Ramon Alguacil", "C/Alcarria, sn", null);
		
		
		/*
		 * RELACIONES N-M (ManyToMany) ---> Un libro va a estar en varias librerias, según pide el ejercicio
		 *  y a su vez una librería tiene MUCHOS libros.
		 * 
		 * ASIGNAMOS LAS LIBRERÍAS A LOS LIBROS.
		 */
		
		//ASIGNAMOS A LA LIBRERIA1 "LIBROS BARATOS" LOS LIBROS 1, 2, 3 Y 4.
		
		List<Libro> librosLib1 = new ArrayList<Libro>();
		librosLib1.add(libro1);
		librosLib1.add(libro2);
		librosLib1.add(libro3);
		librosLib1.add(libro4);
		
		lib1.setColeccionLibros(librosLib1);
		
		//ASIGNAMOS A LA LIBRERIA2 "LIBROS VINTAGE" LOS LIBROS 5, 6,7 Y 8.
		List<Libro> librosLib2 = new ArrayList<Libro>();
		librosLib2.add(libro5);
		librosLib2.add(libro6);
		librosLib2.add(libro7);
		librosLib2.add(libro8);
		
		lib2.setColeccionLibros(librosLib2);
		
		//HACEMOS LA RELACIÓN BIDIRECCIONAL, POR LO QUE AHORA TENEMOS QUE
		//AÑADIR LA LISTA DE LIBRERIAS, A LOS LIBROS.
		//EN NUESTRO CASO, LOS 4 PRIMEROS LIBROS TIENEN UNA LIBRERÍA Y LOS 4 SIGUIENTES, OTRA.
		//CADA LIBRO TENDRÁ UNA LISTA DE UN ELEMENTO, CON LA LIBRERIA A LA QUE CORRESPONDEN. AUN QUE 
		//SABEMOS QUE CADA LIBRO PUEDE ESTAR EN MUCHAS LIBRERIAS.
		
		//LIBROS QUE ESTÁN EN LA LIBRERIA 1
		
		List<Libreria> libLibro1 = new ArrayList<Libreria>();
		libLibro1.add(lib1);
		libro1.setLibrerias(libLibro1);
		
		List<Libreria> libLibro2 = new ArrayList<Libreria>();
		libLibro2.add(lib1);
		libro2.setLibrerias(libLibro2);
		
		List<Libreria> libLibro3 = new ArrayList<Libreria>();
		libLibro3.add(lib1);
		libro3.setLibrerias(libLibro3);
		
		List<Libreria> libLibro4 = new ArrayList<Libreria>();
		libLibro4.add(lib1);
		libro4.setLibrerias(libLibro4);
		
		//LIBROS QUE ESTÁN EN LA LIBRERIA 2
		
		List<Libreria> libLibro5 = new ArrayList<Libreria>();
		libLibro5.add(lib2);
		libro5.setLibrerias(libLibro5);
		
		List<Libreria> libLibro6 = new ArrayList<Libreria>();
		libLibro6.add(lib2);
		libro6.setLibrerias(libLibro6);
		
		List<Libreria> libLibro7 = new ArrayList<Libreria>();
		libLibro7.add(lib2);
		libro7.setLibrerias(libLibro7);
		
		List<Libreria> libLibro8 = new ArrayList<Libreria>();
		libLibro8.add(lib2);
		libro8.setLibrerias(libLibro8);
		
		
		
		
		em = emf.createEntityManager(); //Se conecta a la base de datos.
		em.getTransaction().begin();
		
		/*
		 * Persistimos las librerias y las editoriales ya que, al ser relaciones N-M con libros y tener un Cascade de tipo
		 * PERSIST, hasta que no demos de alta estas librerias y editoriales, no se nos van a crear en la BBDD. 
		 * De esta manera evitamos tambien que si borramos un libro, se borre su libreria y editorial asociadas, y a su vez 
		 * se eliminen todos los datos asociados a ellas.
		 */
		em.persist(lib1); 
		em.persist(lib2); 
		em.persist(deBolsillo); 
		em.persist(salamandra);
		
		
		em.getTransaction().commit();
		
		System.out.println("====== Información introducida en la Base de Datos ======");
		
		em.close();
		
	}

}
