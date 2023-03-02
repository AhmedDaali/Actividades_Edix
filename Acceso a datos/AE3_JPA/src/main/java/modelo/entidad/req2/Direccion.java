package modelo.entidad.req2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/*
 * Esta clase será la entidad que contenga los datos de la dirección
 * de las empresas que guardemos en nuestra BBDD.
 * 
 * 1 empresa tiene 1 dirección ---> 1,1.
 */

@Entity

@Table (name ="Direcciones")
public class Direccion {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tipoVia;
	private String nombreVia;
	
	@OneToOne
	@JoinColumn(name = "fk_id_empresa", referencedColumnName = "id")
	private Empresa empresa;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipoVia() {
		return tipoVia;
	}

	public void setTipoVia(String tipoVia) {
		this.tipoVia = tipoVia;
	}

	public String getNombreVia() {
		return nombreVia;
	}

	public void setNombreVia(String nombreVia) {
		this.nombreVia = nombreVia;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	

	public Direccion(int id, String tipoVia, String nombreVia, Empresa empresa) {
		super();
		this.id = id;
		this.tipoVia = tipoVia;
		this.nombreVia = nombreVia;
		this.empresa = empresa;
	}
	
	
	public Direccion() {
		super();
	}

	@Override
	public String toString() {
		return "Direccion [id=" + id + ", tipoVia=" + tipoVia + ", nombreVia=" + nombreVia + ", empresa=" + empresa
				+ "]";
	}
	
	
	
	

}
