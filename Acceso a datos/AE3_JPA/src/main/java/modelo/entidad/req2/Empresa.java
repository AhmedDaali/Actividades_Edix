package modelo.entidad.req2;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/*
 * Esta clase contendrá la información de las empresas de
 * nuestra BBDD. 
 * 
 * Empresa tiene relación 1,1 con dirección.
 * Mientras que la relación con Aplicacion es 1,N. Una empresa
 * puede desarrollar 1 o varias Aplicaciones mientras que 1 aplicación
 * es desarrollada por una empresa.
 * Al ser 1,N pasamos la pk de empresa (id) a la entidad Aplicación como fk.
 */
@Entity
@Table (name = "Empresas")
public class Empresa {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	
	@OneToMany (mappedBy = "empresa")
	private List<Aplicacion> Aplicaciones;
	
	@OneToOne (mappedBy = "empresa")
	private Direccion direccion;

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public List<Aplicacion> getAplicaciones() {
		return Aplicaciones;
	}

	public Direccion getDireccion() {
		return direccion;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		Aplicaciones = aplicaciones;
	}

	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}

	public Empresa(int id, String nombre, List<Aplicacion> aplicaciones, Direccion direccion) {
		super();
		this.id = id;
		this.nombre = nombre;
		Aplicaciones = aplicaciones;
		this.direccion = direccion;
	}

	public Empresa() {
		super();
	}

	@Override
	public String toString() {
		return "Empresa [id=" + id + ", nombre=" + nombre + ", Aplicaciones=" + Aplicaciones + ", direccion="
				+ direccion + "]";
	}

	
}
