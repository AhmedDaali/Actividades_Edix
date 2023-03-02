package modelo.entidad;

/**
 * La clase Coche representa un coche con atributos como la matrícula, la marca,
 * el modelo, el color y un identificador único.
 *
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public class Coche {
	
	private int id;
	private String matricula,marca, modelo, color;
	
	/**
	 * Constructor que recibe todos los atributos de la clase Coche.
	 *
	 * @param id Identificador único del coche
	 * @param matricula Matrícula del coche
	 * @param marca Marca del coche
	 * @param modelo Modelo del coche
	 * @param color Color del coche
	 */
	public Coche(int id, String matricula, String marca,String modelo, String color) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.color = color;
	}
	/**
	 * Constructor vacio de la clase Coche.
	 */
	public Coche() {
		super();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}
	@Override
	public String toString() {
		return "Coche [id=" + id + ", matricula=" + matricula + ", marca="+marca+", modelo=" + modelo + ", color=" + color + "]";
	}

}
