package modelo.entidad.req2;

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

/*
 * Esta clase contendrá la información de las Aplicaciones desarrolladas 
 * por las empresas que tengamos en la BBDD.
 * 
 * La entidad Aplicación tiene una relación N,1 con la entidad Empresa.
 * 1 empresa puede hacer 1 o varias aplicaciones.
 * 
 * La relación que tiene con la entidad Cliente, será N,M.
 * Una aplicación puede ser consumida por varios clientes, y a su vez,
 * un cliente consume varias aplicaciones. 
 * Esta relación generará una tabla intermedia con las pk de ambas.
 */

@Entity
@Table (name = "Aplicaciones")
public class Aplicacion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nombre;
	private String categoria;
	
	
	@ManyToMany(cascade=CascadeType.PERSIST)
	@JoinTable(name="aplicaciones_clientes",
	   joinColumns= { @JoinColumn(name="fk_id_aplicacion", referencedColumnName="id") }, 
	   inverseJoinColumns= { @JoinColumn(name="fk_id_cliente", referencedColumnName="id")}) 
	private List<Cliente> listaClientes;
	
	
	
	@ManyToOne
	@JoinColumn(name = "fk_id_empresa", referencedColumnName = "id")
	private Empresa empresa;



	public int getId() {
		return id;
	}



	public String getNombre() {
		return nombre;
	}



	public String getCategoria() {
		return categoria;
	}



	public List<Cliente> getListaClientes() {
		return listaClientes;
	}



	public Empresa getEmpresa() {
		return empresa;
	}



	public void setId(int id) {
		this.id = id;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}



	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}



	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}



	public Aplicacion(int id, String nombre, String categoria, List<Cliente> listaClientes, Empresa empresa) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.categoria = categoria;
		this.listaClientes = listaClientes;
		this.empresa = empresa;
	}



	public Aplicacion() {
		super();
	}



	@Override
	public String toString() {
		return "Aplicacion [id=" + id + ", nombre=" + nombre + ", categoria=" + categoria + ", listaClientes="
				+ listaClientes + ", empresa=" + empresa + "]";
	}
	
	
	

}
