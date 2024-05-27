package modelo;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import com.google.gson.Gson;

import dao.daoUsuario;

/**
 * Clase usuario, a través de esta clase seremos capaces de trabajar con objetos de tipo usuario
 * de forma que podamos crear, modificar, listar y eliminar dichos objetos en nuestra aplicación Web.
 */

public class usuario {
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String fechaBoda;
	private String hashContrasenia;
	private int permiso;
	private int servicio;
	
	/**
	 * Constructor vacío, o constructor por defecto de tipo usuario, puede ser útil para inicializar un objeto 
	 * con valores predeterminados o para preparar un objeto para una posterior inicialización.
	 */
	public usuario() {
		
	}
	/**
	 * Constructor del objeto usuario con todos los atributos necesarios para poder listar más adelante
	 * necesitaremos insertar en la BBDD
	 * @param id atributo unico de cada objeto usuario que nos permite diferenciarlo del resto
	 * @param nombre atributo que sirve para almacenar el nombre del usuario
	 * @param apellido1 atributo que sirve para almacenar el primerl apellido del usuario
	 * @param apellido2 atributo que sirve para almacenar el segundo apellido del usuario
	 * @param email atributo que que sirve para almacenar el correo electrónico del usuario y que posteriormente necesitará para hacer el login
	 * @param fechaBoda atributo que sirve para almacenar la fecha de la boda de los novios, de esa forma le servirá
	 * @param hashContrasenia atributo que sirve para almacenar la contraseña encriptada del usuario para que pueda acceder a su cuenta
	 * @param permiso atributo que sirve para dar un nivel de permisos concreto.
	 */
	
	public usuario(int id, String nombre, String apellido1, String apellido2, String email, String fechaBoda,
			String hashContrasenia, int permiso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.fechaBoda = fechaBoda;
		this.hashContrasenia = hashContrasenia;
		this.permiso = permiso;
	}
	
	
	
/**
 * Constructor del objeto usuario con todos los atributos necesarios para crear el objeto que más adelante
 * necesitaremos insertar en la BBDD
 * @param nombre atributo que sirve para almacenar el nombre del usuario
 * @param apellido1 atributo que sirve para almacenar el primerl apellido del usuario
 * @param apellido2 atributo que sirve para almacenar el segundo apellido del usuario
 * @param email atributo que que sirve para almacenar el correo electrónico del usuario y que posteriormente necesitará para hacer el login
 * @param fechaBoda atributo que sirve para almacenar la fecha de la boda de los novios, de esa forma le servirá
 * @param hashContrasenia atributo que sirve para almacenar la contraseña encriptada del usuario para que pueda acceder a su cuenta
 * @param permiso atributo que sirve para dar un nivel de permisos concreto.
 */
	
	public usuario(String nombre, String apellido1, String apellido2, String email, String fechaBoda,
			String hashContrasenia, int permiso) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.fechaBoda = fechaBoda;
		this.hashContrasenia = hashContrasenia;
		this.permiso = permiso;
	}
	
	
	/**
	 * Método que mediante el uso del patrón Singleton llama a clase daoUsuario, conecta con la base de datos e inserta el objeto usuario en la base datos
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public void insertar () throws SQLException {
		
		daoUsuario.getInstance().insertar(this);
	}
	
	
	/**
	 * Método que  mediante el uso del patrón Singleton llama a clase daoUsuario, conecta con la base de datos y 
	 * permite actualizar el objeto usuario que está insertado en la BBDD
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public void update () throws SQLException {
		
		daoUsuario.getInstance().update(this);
	}

	/**
	 * Método que  mediante el uso del patrón Singleton llama a clase daoUsuario, conecta con la base de datos y nos permite eliminar
	 * un usuario concreto pasandole el atríbuto "id".
	 * @param id atributo unico de cada objeto que nos permite diferenciarlo del resto
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */ 
	public void delete (int id) throws SQLException {
	daoUsuario.getInstance().delete(id);
}
	
	/**
	 * Método que utilizamos varias veces para rescatar los datos del objeto usuario insertado en la BBDD
	 * pasandole el atributo "id" que es único de cada objeto.
	 * @param id atributo unico de cada objeto que nos permite diferenciarlo del resto
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public void obtenerPorId(int id) throws SQLException {
		
		usuario aux = daoUsuario.getInstance().obtenerPorId(id);
		
		this.setId(aux.getId());
		this.setNombre(aux.getNombre());
		this.setApellido1(aux.getApellido1());
		this.setApellido2(aux.getApellido2());
		this.setEmail(aux.getEmail());
		this.setFechaBoda(aux.getFechaBoda());
		this.setHashContrasenia(aux.getHashContrasenia());
		this.setPermiso(aux.getPermiso());
	}
	
	/**
	 * En este método llamamos al método logeando de daoUsuario para autenticar al usuario actual y, si la autenticación es exitosa, 
	 * actualizamos los datos del usuario actual con los datos de la base de datos y nos devuelve true, en caso contrario este método no devuelve nada
	 * @return ok: devuelve true si el usuario existe.
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public boolean logeo () throws SQLException {
		
		boolean ok = false;
		
		usuario aux = daoUsuario.getInstance().logeando(this);
		
		if(aux!=null) {
			this.setId(aux.getId());
			this.setNombre(aux.getNombre());
			this.setApellido1(aux.getApellido1());
			this.setApellido2(aux.getApellido2());
			this.setEmail(aux.getEmail());
			this.setFechaBoda(aux.getFechaBoda());
			this.setHashContrasenia(aux.getHashContrasenia());
			this.setPermiso(aux.getPermiso());
			
			ok=true;
		}
		
		return ok;
	}
	
	/**
	 * método que nos devuelve el objeto en forma de Json para poder pintar en el HTML
	 * @return devuelve el objeto en Json
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public String dameJson () throws SQLException {
		String json = "";
		Gson gson = new Gson ();
		
		json = gson.toJson(this);
		
		
		return json;
	}
	/**
	 * Método estático al que le pasameos el parámetro contraseña para encriptarla
	 * @param input: nombre por defecto, que en este caso corresponde a la constraseña que queremos encriptar.
	 * @return devuelve la constreña encrtiptada
	 */
	public static String getMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
	
	
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "id" de una instancia de la clase.
	 * @return devuelve el valor del atributo "id".
	 */
	public int getId() {
		return id;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "id" de una instancia de la clase.
	 * @param id: atributo unico de cada objeto que nos permite diferenciarlo del resto
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "nombre" de una instancia de la clase.
	 * @return devuelve el valor del atributo "nombre".
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "nombre" de una instancia de la clase.
	 * @param nombre: atributo que sirve para almacenar el nombre del usuario
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "apellido1" de una instancia de la clase.
	 * @return devuelve el valor del atributo "apellido1".
	 */
	public String getApellido1() {
		return apellido1;
	}
	/**
	 método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "apellido1" de una instancia de la clase.
	 * @param apellido1: atributo que sirve para almacenar el apellido1 del usuario
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "apellido2" de una instancia de la clase.
	 * @return devuelve el valor del atributo "apellido2".
	 */
	public String getApellido2() {
		return apellido2;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "apellido2" de una instancia de la clase.
	 * @param apellido2: atributo que sirve para almacenar el apellido2 del usuario
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "email" de una instancia de la clase.
	 * @return email:devuelve el valor del atributo "email".
	 */
	public String getEmail() {
		return email;
	}
	/**
	* método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "email" de una instancia de la clase.
	 * @param email: atributo que sirve para almacenar el email del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "fechaBoda" de una instancia de la clase.
	 * @return fechaBoda: devuelve el valor del atributo "fechaBoda".
	 */
	public String getFechaBoda() {
		return fechaBoda;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "fechaBoda" de una instancia de la clase.
	 * @param fechaBoda: atributo que sirve para almacenar el email del usuario.
	 */
	public void setFechaBoda(String fechaBoda) {
		this.fechaBoda = fechaBoda;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "hashContrasenia" de una instancia de la clase.
	 * @return hashContrasenia: devuelve el valor del atributo "hashContrasenia".
	 */
	public String getHashContrasenia() {
		return hashContrasenia;
	}
	
	
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "hashContrasenia" de una instancia de la clase. 
	 * @param contrasenia atributo que sirve para almacenar el contraseña del usuario.
	 */
	public void setHashContrasenia(String contrasenia) {
		this.hashContrasenia = contrasenia;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "permiso" de una instancia de la clase.
	 * @return permiso: devuelve el valor del atributo "permiso".
	 */
	public int getPermiso() {
		return permiso;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "permiso" de una instancia de la clase.
	 * @param permiso: atributo que sirve para almacenar el permiso del usuario.
	 */
	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}
	/**
	* método comunmente denominado "getter" se utiliza para obtener el valor del atributo "servicio" de una instancia de la clase.
	 * @return servicio: devuelve el valor del atributo "servicio". 
	 */
	public int getServicio() {
		return servicio;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "servicio" de una instancia de la clase.
	 * @param servicio: atributo que sirve para almacenar el servicio contratado por el usuario.
	 */
	public void setServicio(int servicio) {
		this.servicio = servicio;
	}
	/**
	 * Método que devuelve una cadena de String que representa el objeto usuario con sus atributos
	 * 
	 */
	
	@Override
	public String toString() {
		return "usuario [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", email=" + email + ", fechaBoda=" + fechaBoda + ", hashContrasenia=" + hashContrasenia
				+ ", permiso=" + permiso + ", servicio=" + servicio + "]";
	}
	
	


	
	
	
	
	
	
	
	
}

