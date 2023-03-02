package modelo.entidad.req2;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/*
 * Esta clase contendrá la información de los clientes que consumen
 * las aplicaciones que guardaremos en nuestra BBDD.
 * 
 * La tabla clientes tiene una relación M,N con la entidad Aplicación.
 * 1 cliente puede consumir una o varias aplicaciones y a su vez, 
 * 1 aplicación es consumida por 1 o varios usuarios.
 */

@Entity
@Table(name="Clientes")
public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String email;
	private int edad;
	
	@ManyToMany(mappedBy="listaClientes", cascade=CascadeType.PERSIST)
	private List<Aplicacion> aplicaciones;

	public int getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public int getEdad() {
		return edad;
	}

	public List<Aplicacion> getAplicaciones() {
		return aplicaciones;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public void setAplicaciones(List<Aplicacion> aplicaciones) {
		this.aplicaciones = aplicaciones;
	}

	public Cliente(int id, String email, int edad, List<Aplicacion> aplicaciones) {
		super();
		this.id = id;
		this.email = email;
		this.edad = edad;
		this.aplicaciones = aplicaciones;
	}

	public Cliente() {
		super();
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", email=" + email + ", edad=" + edad + ", aplicaciones=" + aplicaciones + "]";
	}
	
	

}
