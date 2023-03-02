package modelo.entidad;

/**
 * La clase Pasajero representa un pasajero con atributos como id un identificador único, 
 * el nombre, la edad, el peso y idCoche un identificador único del coche.
 *
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public class Pasajero {
	
	private int id;
	private String nombre;
	private int edad;
	private float peso;
	private int idCoche;
	private Coche coche;
	
	/**
	 * Constructor que recibe todos los atributos de la clase Pasajero.
	 *
	 * @param id Identificador único del pasajero
	 * @param nombre Nombre del pasajero
	 * @param edad Edad del pasajero
	 * @param peso Peso del pasajero
	 * @param idCoche Identificador único del coche
	 */
	public Pasajero(int id, String nombre, int edad, float peso, int idCoche) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.peso = peso;
		this.idCoche = idCoche;
	}
	/**
	 * Constructor vacio de la clase Pasajero.
	 */
	public Pasajero() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public int getIdCoche() {
		return idCoche;
	}

	public void setIdCoche(int idCoche) {
		this.idCoche = idCoche;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	@Override
	public String toString() {
		return "Pasajero [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", peso=" + peso + ", idCoche="
				+ idCoche + "]";
	}

	
}
