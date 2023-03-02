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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Una editorial puede tener muchos libros. 1-N.
 * Ponemos la anotación @OneToMany para hacer la relación bidireccional.
 * No es obligatorio ponerla en este punto. Si lo es en el extremo de "muchos (N)", 
 * en este caso en la clase Libro pondremos la anotación @ManyToOne encima del atributo
 * que relaciona ambas tablas, editorial.
 */

@Entity

@Table(name = "editoriales")

@XmlRootElement (name="editorial")
@XmlType(propOrder = {
		"id",
	    "nombre",
	    "direccion",
	    "libros"
	})

public class Editorial {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Integer id;
	private String nombre;
	private String direccion;
	
	@OneToMany(mappedBy = "editorial", cascade = CascadeType.PERSIST)
	private List<Libro> libros;
	
	
	public Editorial() {
		libros = new ArrayList<Libro>();
	}

	
	public Editorial(Integer id, String nombre, String direccion, List<Libro> libros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
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
	public String getDireccion() {
		return direccion;
	}
	
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


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}	
	
}
