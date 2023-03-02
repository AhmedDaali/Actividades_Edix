package modelo.entidad.req1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/*
 * Con esta anotación podemos decirle a JPA que esta clase será
 * una tabla de base de datos, la tabla se llamará como la clase 
 * en LowerCase.
 * LA CLASE DEBE SEGUIR LA CONVENCIÓN JABABEAN (atributos privados y 
 * getter y setter)
 */

@Entity

//Cambiamos el nombre de la tabla "autor" por "autores".

@Table(name = "autores")

/*
 * Para que JAXB sepa convertir objetos a XML y viceversa, utilizamos 
 * un sistema de anotaciones.
 * 
 * Esta etiqueta establece el nombre del nodo raíz en el XML. 
 * ES OBLIGATORIA.
 */

@XmlRootElement(name="autor")

/*
 * Con la siguiente anotación colocamos las etiquetas en el orden
 * que queramos.
 */
@XmlType(propOrder = {
		"id",
	    "nombre",
	    "apellidos",
	    "fechaNacimiento",
	    "libros"
	})

public class Autor {
	
	//la entidad debe de tener un id, en este caso sera 
	//la propiedad id, esto lo hacemos poniendo @Id justo
	//encima de la propiedad que queremos que sea primary key.
	//Todos los demás atributos van a ser campos de la tabla.
	
	//El motor de bbdd que nos va a autogenerar
	//el id cada vez que mandemos un objeto para persistir.
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String apellidos;
	
	/*
	 * Usamos la anotación @Temporal (TemporalType.DATE) para indicar como debe
	 * ser convertido el campo FECHA de la bbdd. Esta anotación ignora la hora, quedando acotado 
	 * el campo a solo la FECHA.
	 * 
	 * Para el req1 utilizo el formato DATE, ya que en la bbdd se guarda correctamente. 
	 * 
	 * Modifico a tipo String para el req3, no aparece correctamente la fecha en formato XML.
	 * */
	
	private String fechaNacimiento;
	
	/*
	 * Un autor puede escribir muchos libros. Relacion 1-N.
	 * 
	 * La anotación OneToMany, en la clase Autor, es para hacer relaciones bidireccionales
	 * ya que no es obligatorio ponerla en este punto. Si lo es en el extremo de "muchos (N)", 
	 * en este caso en la clase Libro pondremos la anotación @ManyToOne por encima del
	 * atributo que relaciona ambas tablas, autor.
	 * 
	 * En la clase libro pondremos la anotación para la fk.
	 * 
	 * MappedBy = Hay que poner con cual de todos los posibles atributos de la clase
	 * Libro, está relacionado.
	 */
	@OneToMany(mappedBy = "autor", cascade = CascadeType.PERSIST)
	private List<Libro> libros;
	
	
	public Autor() {	
		libros = new ArrayList<Libro>();
	}
	
	
	public Autor(Integer id, String nombre, String apellidos, String fechaNacimiento, List<Libro> libros) {
		super();
		this.id=id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.libros = libros;
	}
	
	@XmlAttribute(name = "id")
	public Integer getId() {
		return id;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	@XmlElement
	public String getApellidos() {
		return apellidos;
	}

	@XmlElement
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	/*
	 * Establecemos que cada libro del array se serialice a una etiqueta XML
	 * y cuyo nombre será "libro". Esto se hace siempre encima del getter
	 * del atributo que quieres serializar.
	 * 
	 * La segunda etiqueta crea una etiqueta que agrupa las etiquetas "libro"
	 * para así diferenciar el nivel de cada etiqueta y poder agrupar todos los
	 * elemento "libro" dentro de la etiqueta "libros"
	 */
	
	@XmlElement(name = "libro")
	@XmlElementWrapper(name="libros")
	public List<Libro> getLibros() {
		return libros;
	}


	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}


	
	
	
	
	
}
