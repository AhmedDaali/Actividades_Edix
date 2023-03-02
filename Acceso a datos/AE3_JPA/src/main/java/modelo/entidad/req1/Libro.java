package modelo.entidad.req1;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity

@Table (name = "libros")

@XmlRootElement (name="libro")

//Podemos hacer que las etiquetas salgan en un determinado orden
//etiqueta opcional
@XmlType(propOrder = {
		"id",
	    "titulo",
	    "precio",
	    "editorial",
	    "autor",
	    "librerias"
	})

public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private int precio;
	
	/*
	 * La relacion con la entidad Editorial, va a ser N-1. Entendemos que
	 * muchos libros,puede ser editados por 1 editoriales.
	 * Sin embargo una Editorial puede tener
	 * muchos libros editados.
	 * 
	 */
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="fk_id_editorial", referencedColumnName = "id")
	private Editorial editorial;
	
	/*
	 * En libro, la relacion con Autor es de "muchos a uno". La FK va a a estar 
	 * en este lado, ya que es el lado de "muchos", por lo que la @JoinColumn 
	 * estará en este lado.
	 * 
	 * Name = va a ser el nombre de la columna.
	 * ReferencedColumnName = nombre de la columna de la entidad con quien se referencia.
	 */
	@ManyToOne (cascade = CascadeType.PERSIST)//No ponemos cascade.ALL, ya que si borramos un libro, borrariamos tambien el autor.
	@JoinColumn(name="fk_id_autor", referencedColumnName = "id")
	private Autor autor;
	
	/*
	 * La relación con libreria va a ser N-M. Según nos pide el ejercicio, un libro
	 * puede estar en varias librerias y una libreria tiene varios libros.
	 * 
	 * Usamos el CascadeType.PERSIST porque el ALL es peligrosisimo. Si borramos 
	 * un libro, borraremos también las librerias donde se encuentran y esas librerias 
	 * contendrán otros libros que también serán borrados.
	 */
	@ManyToMany(mappedBy = "libros" ,cascade=CascadeType.PERSIST)
	private List<Libreria> librerias;
	
	
	public Libro() {
		librerias = new ArrayList<Libreria>();
	}

	
	public Libro(Integer id, String titulo, int precio, Editorial editorial, List<Libreria> librerias, Autor autor) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.precio = precio;
		this.editorial = editorial;
		this.librerias = librerias;
		this.autor = autor;
	}
	
	//etiqueta que hace que el id de la persona se serialize como atributo de libro.
	
	@XmlAttribute(name = "id")
	public Integer getId() {
		return id;
	}
	
	@XmlElement
	public String getTitulo() {
		return titulo;
	}

	@XmlElement
	public float getPrecio() {
		return precio;
	}

	@XmlElement
	public Editorial getEditorial() {
		return editorial;
	}

	@XmlElement
	public Autor getAutor() {
		return autor;
	}
	
	
	@XmlElement(name = "libreria")
	@XmlElementWrapper(name="librerias")
	public List<Libreria> getLibrerias() {
		return librerias;
	}


	public void setLibrerias(List<Libreria> librerias) {
		this.librerias = librerias;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public void setEditorial(Editorial editorial) {
		this.editorial = editorial;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	


	
	
	
	
	
}
