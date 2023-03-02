package modelo.persistencia.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import modelo.entidad.Coche;
import modelo.persistencia.interfaces.DaoCoche;

/**
 * Clase implementa la interfaz DaoCoche y 
 * a la vez conecta con la base de datos
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public class DaoCocheMySql implements DaoCoche {


	private Connection conexion;
	
	/**
	 * Abre una conexión a la base de datos.
	 * 
	 * @return true si la conexión se abrió correctamente, false en caso contrario.
	 */
	public boolean abrirConexion(){
		
		// Paso 1: Establecemos los parametros de conexión con la base de datos
		String url = "jdbc:mysql://localhost:3306/ae2";
		String usuario = "root";
		String password = "";
		try {
			// Paso 2: Interactuar con la BD
			conexion = DriverManager.getConnection(url,usuario,password);
		}catch(CommunicationsException ce) {
			System.out.println("No hay conexión con la base de datos" );
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
	/**
	 * Cierra la conexión a la base de datos.
	 * 
	 * @return true si la conexión se cerró correctamente, false en caso contrario.
	 */
	public boolean cerrarConexion(){
		try {
			conexion.close();
		}catch (NullPointerException ne){
			System.out.println("No se pudo cerrar la conexión");	
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addCoche(Coche c) {
		//Primero, se llama al método "abrirConexion()".
		//Este método se encarga de establecer 
		//una conexión con la base de datos.
		//Si la conexión no se puede abrir, se devuelve "null".
		if(!abrirConexion()){
			return false;
		}
		//Se crea una variable "alta" que se inicializa en true. 
		//Esta variable se utilizará para indicar si 
		//el alta se realizó correctamente o no.
		boolean alta = true;
		//Se crea una variable "query" que contiene
		//una consulta SQL que selecciona
		//todos los campos de la tabla "coches" menos id que 
		//se generará automáticamente en la base de datos.
		String query = "insert into coches ( matricula, marca, modelo, color) "
				+ " values(?,?,?,?)";
		try {
			//Se prepara la query con valores parametrizables (?)
			//para los campos matricula, marca, modelo y 
			//color del objeto "Coche".
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());
			
			//Se ejecuta la query con el método "executeUpdate()".
			//Este método devuelve el número de filas afectadas
			//por la consulta. Si el número de filas afectadas es 0,
			//significa que la inserción no se realizó correctamente y 
			//se establece "alta" en false.
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
			System.out.println("Coche añadido\n");
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + c);
			alta = false;
			e.printStackTrace();
	    //Se cierra la conexión con la base de datos mediante
		//el método "cerrarConexion()".		
		} finally{
			cerrarConexion();
		}
		//Finalmente, se devuelve el valor de "alta".
		return alta;
		
	}

	@Override
	public boolean deleteCoche(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		//Se crea una variable "query" que contiene una consulta SQL 
		//para borrar un registro de la tabla "coches"
		//con un "id" específico.
		String query = "DELETE FROM coches WHERE id = ?";
		try {
			//Se prepara la consulta "query" para que pueda ser ejecutada,
			//utilizando el método "prepareStatement". 
			//Se utiliza un valor parametrizable "?" para especificar
			//el "id" del registro a borrar.
			PreparedStatement ps = conexion.prepareStatement(query);
			//Se asigna el valor del "id" del registro a borrar al valor
			//parametrizable en la consulta, utilizando el método "setInt".
			ps.setInt(1, id);
			//Se ejecuta la consulta "query" utilizando el método "executeUpdate",
			//que devuelve el número de filas afectadas por la consulta.
			//Si no se borra ninguna fila, se establece "borrado" a "false".
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
			System.out.println("Coche borrado \n");
			
		} catch (SQLException e) {
			borrado = false;
			System.out.println("baja -> No se ha podido dar de baja"
					+ " el id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		return borrado; 
	}

	@Override
	public boolean updateCoche(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "UPDATE coches SET matricula=?, marca=?, modelo=?, color=? WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setString(4, c.getColor());
			ps.setInt(5, c.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				modificado = false;
			System.out.println("El coche modificado: "+ c.toString()+"\n");
		} catch (SQLException e) {
			
			System.out.println("modificar -> error al modificar el "
					+ " coche " + c);
			modificado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return modificado;
	}

	@Override
	public Coche obtenerCoche(int id) {
		//Primero, se llama al método "abrirConexion()".
		//Este método se encarga de establecer una conexión con la base de datos.
		//Si la conexión no se puede abrir, se devuelve "null".
		if(!abrirConexion()){
			return null;
		}		
		Coche coche = null;
		//Se crea una variable "query" que contiene una consulta SQL que selecciona
		//todos los campos de la tabla "coches" cuyo id sea igual 
		//al id proporcionado como argumento.
		String query = "SELECT id,matricula,marca,modelo,color FROM coches "
				+ "WHERE id = ?";
		try {
			//Se crea un objeto "PreparedStatement" llamado "ps" que utiliza
			//la conexión y la consulta SQL para crear una consulta parametrizada.
			PreparedStatement ps = conexion.prepareStatement(query);
			
			//Se establece el valor del parámetro de la consulta utilizando el método "setInt"
			//y se ejecuta la consulta mediante el método "executeQuery". 
			//El resultado se almacena en un objeto "ResultSet".
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			// Se entra en un bucle "while" que se ejecuta mientras haya más resultados en el "ResultSet".
            //Dentro del bucle, se crea un objeto "Coche" y se le asignan los valores de cada 
			//uno de los campos recuperados de la base de datos.
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setColor(rs.getString(5));
			}
			
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "coche con id " + id);
			e.printStackTrace();
	    //Finalmente, dentro del bloque finally, se llama al método "cerrarConexion()"
		//para cerrar la conexión con la base de datos.
		} finally {
			cerrarConexion();
		}
		
		//Después de salir del bloque finally, se devuelve el objeto "Coche" obtenido.
		return coche;
	}

	@Override
	public List<Coche> ListCoches() {
		if(!abrirConexion()){
			return null;
		}		
		List<Coche> listaCoches = new ArrayList<>();
		
		String query = "SELECT id,matricula, marca,modelo,color FROM coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setColor(rs.getString(5));
				
				listaCoches.add(coche);
				
			     }
			for (Coche c : listaCoches) {
				  System.out.println(c+"\n");
			}

		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los "
					+ "coches");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaCoches;
	}

}
