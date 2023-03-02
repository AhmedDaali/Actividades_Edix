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
import modelo.entidad.Pasajero;
import modelo.persistencia.interfaces.DaoPasajero;

/**
 * Clase implementa la interfaz DaoPasajero y 
 * a la vez conecta con la base de datos
 * @author Grupo5
 * @version 1.0
 * @since 2023
 */
public class DaoPasajeroMySql implements DaoPasajero{
	
	private Connection conexion;
	/**
	 * Abre una conexión a la base de datos.
	 * 
	 * @return true si la conexión se abrió correctamente, false en caso contrario.
	 */
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/ae2";
		String usuario = "root";
		String password = "";
		try {
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
		}catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean addPasajero(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		
		String query = "insert into pasajeros (nombre, edad, peso) "
				+ " values(?,?,?)";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ps.setString(1, p.getNombre());
			ps.setInt(2, p.getEdad());
			ps.setFloat(3, p.getPeso());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				alta = false;
			System.out.println("Pasajero añadido\n");
		} catch (SQLException e) {
			System.out.println("alta -> Error al insertar: " + p);
			alta = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return alta;
	}
	@Override
	public boolean deletePasajero(int id) {
		if(!abrirConexion()){
			return false;
		}
		
		boolean borrado = true;
		String query = "DELETE FROM pasajeros WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
			System.out.println("Pasajero borrado \n");
			
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
	public Pasajero obtenerPasajero(int id) {
		if(!abrirConexion()){
			return null;
		}		
		Pasajero pasajero = null;
		
		String query = "SELECT id, nombre,edad,peso,id_coche FROM pasajeros "
				+ "WHERE id = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			
			//ResulSet: Una tabla de datos que representa un conjunto de resultados de una base de datos,
			//que generalmente se genera al ejecutar una instrucción que consulta la base de datos.
			
			//executeQuery: Ejecuta la consulta SQL en este objeto PreparedStatement y
			//devuelve el objeto ResultSet generado por la consulta.
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				pasajero.setIdCoche(rs.getInt(5));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el "
					+ "coche con id " + id);
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}	
		return pasajero;
	}

	@Override
	public List<Pasajero> listPasajeros() {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "SELECT id, nombre,edad,peso,id_coche FROM pasajeros ";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				pasajero.setIdCoche(rs.getInt(5));
				
				listaPasajeros.add(pasajero);	
			}
			for (Pasajero p : listaPasajeros) {
				  System.out.println(p+"\n");
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPasajeros;
	}

	@Override
	public boolean addPasajeroCoche(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean agregado = true;
		String query = "UPDATE pasajeros SET id_coche=? WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, p.getIdCoche());
			ps.setInt(2, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				agregado = false;
			System.out.println("El pasajero modificado: "+ p.toString()+"\n");
		} catch (SQLException e) {
			
			System.out.println("modificar -> error al modificar el "
					+ " coche " + p);
			agregado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return agregado;
	}

	@Override
	public boolean deletePasajeroCoche(Pasajero p) {
		if(!abrirConexion()){
			return false;
		}
		boolean borrado = true;
		String query = "UPDATE pasajeros SET id_coche=NULL WHERE id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, p.getId());
			
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0)
				borrado = false;
			
			System.out.println("Coche eliminado \n");
			
		} catch (SQLException e) {
			
			System.out.println("borrar -> error al modificar el "
					+ " coche " + p);
			borrado = false;
			e.printStackTrace();
		} finally{
			cerrarConexion();
		}
		
		return borrado;
	}

	@Override
	public List<Pasajero> listPasajerosCoche(int idCoche) {
		if(!abrirConexion()){
			return null;
		}		
		List<Pasajero> listaPasajeros = new ArrayList<>();
		
		String query = "SELECT id, nombre,edad,peso, id_coche FROM pasajeros "
				+ "WHERE id_coche = ?";
;
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, idCoche);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Pasajero pasajero = new Pasajero();
				pasajero = new Pasajero();
				pasajero.setId(rs.getInt(1));
				pasajero.setNombre(rs.getString(2));
				pasajero.setEdad(rs.getInt(3));
				pasajero.setPeso(rs.getFloat(4));
				pasajero.setIdCoche(rs.getInt(5));
				
				listaPasajeros.add(pasajero);	
			}
			System.out.println("Los pasajeros del coche con id: " + idCoche + "\n" );
			for (Pasajero p : listaPasajeros) {
				  System.out.println(p + "\n");
			}

		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los pasajeros");
			e.printStackTrace();
		} finally {
			cerrarConexion();
		}
		
		
		return listaPasajeros;
	}

}
