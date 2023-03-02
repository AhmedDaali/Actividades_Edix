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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@Entity

@Table (name = "librerias")
@XmlRootElement (name="libreria")

public class Libreria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre;
	private String nombreDueño;
	private String direccion;
	
	/*
	 * Tenemos una relación N-M con la entidad Libro. Ya que una librería puede tener
	 * varios libros. 
	 * 
	 * Solo hacemos cascade cuando damos de alta.
	 * En este caso utilizamos también @JoinTable para dar instrucciones para crear la tabla intermedia que se 
	 * crea automaticamente al existir una relacion N-M. Este JoinTable podemos ponerle en cualquiera de las dos tablas.
	 * 1. name: Nombre de la tabla intermedia.
	 * 2. joinColumns: Las columnas FK y PK que va a aportar la entidad Libreria.
	 * 3. inverseJoinColumns: las columnas FK y PK que va a aportar la entidad Libro.
	 */
	
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="librerias_libros",
	   joinColumns= { @JoinColumn(name="fk_id_libreria", referencedColumnName="id") }, //FK que aporta Libreria
	   inverseJoinColumns= { @JoinColumn(name="fk_id_libro", referencedColumnName="id")}) //FKs que aportan el resto de entidades
	private List<Libro> libros;
	
	

	public Libreria() {
		libros = new ArrayList<Libro>();
	}
	
	public Libreria(Integer id, String nombre, String nombreDueño, String direccion, List<Libro> libros) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.nombreDueño = nombreDueño;
		this.direccion = direccion;
		this.libros = libros;
	}


	@XmlAttribute(name = "id")
	public Integer getIdLibreria() {
		return id;
	}

	@XmlElement
	public String getNombre() {
		return nombre;
	}

	@XmlElement
	public String getNombreDueño() {
		return nombreDueño;
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

	public void setIdLibreria(Integer id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setNombreDueño(String nombreDueño) {
		this.nombreDueño = nombreDueño;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public void setColeccionLibros(List<Libro> libros) {
		this.libros = libros;
	}
			
	
}
