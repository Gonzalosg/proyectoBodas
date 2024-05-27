package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import modelo.servicio;
	/**
	 * Clase mediante la cual definimos objetos de tipo servicio para poder insertar en la base de datos una vez el usuario los
	 * agregue desde la tienda de la aplicacion web.
	 */
	public class daoServicio {

	private Connection con = null;
	private static daoServicio instance = null;
	/**
	 * método que nos conecta con la Base de datos llamando al método getConnection de la clase ConexionBD, si la conexion es nula, establece una nueva conexión.
	 * @throws SQLException: lanza un error tipo SQL si hubiese algún error conectando a la BBDD
	 */
	public daoServicio() throws SQLException {
		if(con==null) {
			con = conexionBD.getConnection();
		}
	}
	/**
	 * Este método implementa el patron Singleton,si no existe un instancia crea una nueva llamando a daoUsuario, de esta forma no necesitamos llamar
	 * a daoUsuario en cada método para poder conectarlo a la base de datos.
	 * @return instance: devuelve la instancia unica de daoUsuario
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public static daoServicio getInstance() throws SQLException {
		
		if(instance==null) {
			instance = new daoServicio();
		}
		return instance;
	}

	/**
	 * Método mediante el cual insertamos un objeto servicio a la base de datos cuando lo añade desde la tienda..
	 * Se prepara una sentencia SQL para insertar los valores de los atributos del objeto servicio que se recogeran posteriormente 
	 * en el servlet contratarServicio 
	 * @param u  objeto de tipo servicio insertado en la BBDD
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */

	public void comprarServicio (servicio u) throws SQLException {
		String sql = "INSERT INTO tienda (idCliente, idServicio) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, u.getIdUsuario() );
		ps.setInt(2,u.getIdServicio());
		
		ps.executeUpdate();
		ps.close();
		
	}
	/**
	 * Método booleano
	 * @param idCliente
	 * @return
	 * @throws SQLException
	 */
	public int checkServicio (int idCliente) throws SQLException {
		
		String checkCliente = "SELECT COUNT(*) FROM tienda WHERE idCliente = ?";
		PreparedStatement checkPs = con.prepareStatement(checkCliente);
		
		checkPs.setInt(1, idCliente);
		
		ResultSet rs = checkPs.executeQuery();
		rs.next();
		
		int contador = rs.getInt(1);
		
		rs.close();
		
		return contador;
		
		
	}
	
	public servicio listarServicio (int idCliente) throws SQLException {
		String sql = "SELECT * FROM servicio WHERE idServicio in (SELECT idServicio FROM tienda WHERE idCliente =?)";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,idCliente);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		servicio s = new servicio(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getFloat(4));
		System.out.println(s);
		return s;
		
	}
	
	public String listarJson (int id) throws SQLException {
		String json = "";
		Gson gson = new Gson ();

		json = gson.toJson(this.listarServicio(id));
		System.out.println(json);


		return json;
	}


	
}
