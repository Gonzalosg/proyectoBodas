package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import jakarta.servlet.http.HttpSession;
import modelo.servicio;
import modelo.usuario;
/**
 * clase "Data Access Object de Usuario, el patrón Arquitectónico Data Access Object (DAO),
 * encapsula toda la lógica de acceso de datos al resto de la aplicación.
 */
public class daoUsuario {

	

	private Connection con = null;
	private static daoUsuario instance = null; 

	/**
	 * método que nos conecta con la Base de datos llamando al método getConnection de la clase ConxionBD, si la conexion es nula, establece una nueva conexión.
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	
	public daoUsuario() throws SQLException {
		if(con==null) {
			con = conexionBD.getConnection();
		}
	}

	/**
	 * Este método implementa el patrón Singleton,si no existe un instancia crea una nueva llamando a daoUsuario, de esta forma no necesitamos llamar
	 * a daoUsuario en cada método para poder conectarlo a la base de datos.
	 * @return instance devuelve la instancia única de daoUsuario
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public static daoUsuario getInstance() throws SQLException {

		if(instance==null) {
			instance = new daoUsuario();
		}
		return instance;
	}

	/**
	 * Método mediante el cual insertamos un objeto usuario a la base de datos cuando este se registra en la página web.
	 * Se prepara una sentencia SQL para insertar los valores de los atributos del objeto usuario que se recogerán posteriormente 
	 * en el servlet gestionUsuario 
	 * @param u  objeto de tipo usuario insertado en la BBDD
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public void insertar(usuario u) throws SQLException {

		PreparedStatement ps = con.prepareStatement("INSERT INTO usuario(nombre,apellido1,apellido2,email,fechaBoda,hashContrasenia,permiso)VALUES(?,?,?,?,?,?,?)");
		ps.setString(1,u.getNombre());
		ps.setString(2,u.getApellido1());
		ps.setString(3,u.getApellido2());
		ps.setString(4,u.getEmail());
		ps.setString(5,u.getFechaBoda());
		ps.setString(6,u.getHashContrasenia());
		ps.setInt(7,u.getPermiso());

		ps.executeUpdate();
		ps.close();
	}
	/**
	 * Método mediante el cual actualizamos los datos de un usuario ya existente en nuestra tabla de la BBDD-
	 * Se prepara una sentencia SQL para actualizar los valores de los atributos del objeto usuario que se recogerán posteriormente en el servlet adminUpdate
	 * @param u  objeto tipo usuario que sustituirá al anteriormente insertado en la BBDD
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public void update(usuario u) throws SQLException {

		String sql = "UPDATE usuario SET nombre=?,apellido1=?,apellido2=?,email=?,fechaBoda=?,hashContrasenia=?,permiso=? WHERE id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1,u.getNombre());
		ps.setString(2,u.getApellido1());
		ps.setString(3,u.getApellido2());
		ps.setString(4,u.getEmail());
		ps.setString(5,u.getFechaBoda());
		ps.setString(6,u.getHashContrasenia());
		ps.setInt(7,u.getPermiso());
		ps.setInt(8, u.getId());

		ps.executeUpdate();
		ps.close();		
	}
	
	/**
	 * Método encargado de borrar los datos de un usuario existente en la BBDD a través de su atributo único "id"
	 * Se prepara una sentencia SQL para borrar los datos existentes en la tabla que correspondan con el "id" que se recogerá en el servlet adminEliminar.
	 * @param id  atributo unico de cada objeto usuario que nos permite diferenciarlo del resto
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public void delete (int id) throws SQLException {
		String sql = "delete from usuario where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * Método que crea un ArrayList de tipo usuario que nos permite mediante una sentencia SQL recoger todos los objetos usuario de la tabla homónima
	 * y poder guardarlos dentro de este.
	 * @return ls  nombre del ArrayList dondo guardaremos todos nuestros objetos usuario recogidos de la BBDD de la tabla usuario.
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */

	public ArrayList <usuario> listar () throws SQLException{
		String sql = "SELECT * from usuario";
		PreparedStatement ps = con.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();

		ArrayList<usuario> ls = null;

		while(rs.next()) {
			if(ls==null) {
				ls = new ArrayList <usuario>();
			}
			ls.add(new usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getInt(8)));
		}
		return ls;
	}
	
	/**
	 * Método que nos permite recoger un objeto usuario preguntando por su atributo único "id".
	 * Mediante una sentencia SQL recogemos los campos que pertenecen al objeto cuyo "id" coincida con el que pidamos a la función, de esta forma
	 * creamos un nuevo objeto usuario y le asignamos esos valores recogidos. 
	 * @param id atributo unico de cada objeto usuario que nos permite diferenciarlo del resto
	 * @return devuelve un objeto de tipo usuario con los valores recogidos de los campos de la BBDD
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public usuario obtenerPorId(int id) throws SQLException {

		String sql = "SELECT * FROM usuario where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);

		ResultSet rs = ps.executeQuery();

		rs.next();

		usuario u = new usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getInt(8));

		return u;

	}
	
	/**
	 * Método que nos permite autenticar un usuario.
	 * Mediante una sentencia SQL preguntamos si el email y la contraseña coinciden con alguno previamente insetado en la BBDD, en caso afirmativo
	 * devuelve el objeto usuario con todos sus atributos correspondientes.
	 * @param u objeto de tipo usuario que queremos comprobar si existe en la BBDD
	 * @return devuelve el objeto usuario una vez confirmado que existe.
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public usuario logeando (usuario u) throws SQLException {

		String sql = "SELECT * FROM usuario where email=? AND hashContrasenia=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, u.getEmail());
		ps.setString(2, u.getHashContrasenia());	

		ResultSet rs = ps.executeQuery();	
		usuario aux=null;

		if(rs.next()) {
			aux = new usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getInt(8));


		}

		return aux;
	}

	/**
	 * Método que devuelve en formato Json todos los objetos usuarios recogidos por el método listar
	 * @return json devuelve la lista en formato Json
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public String listarJson () throws SQLException {
		String json = "";
		Gson gson = new Gson ();

		json = gson.toJson(this.listar());


		return json;
	}
	
	public String listarUsuJson(int id) throws SQLException {
		String json = "";
		Gson gson = new Gson ();

		json = gson.toJson(this.obtenerPorId(id));


		return json;
	}








}
