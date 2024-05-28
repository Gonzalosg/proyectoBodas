package modelo;

import java.sql.SQLException;

import dao.daoServicio;
import dao.daoUsuario;
	
/**
 * Clase servicio, a través de esta clase seremos capaces de trabajar con objetos de tipo servicio
 * de forma que podamos crear y listar dichos objetos servicio en nuestra aplicación Web.
 */

public class servicio {
	int idUsuario;
	int idServicio;
	String nombreServicio="";
	String descripcionServicio="";
	float precioServicio;

	
	/**
	 * Constructor vacío, o constructor por defecto de tipo servicio, puede ser útil para inicializar un objeto 
	 * con valores predeterminados o para preparar un objeto para una posterior inicialización.
	 */
	public servicio() {
		
	}
	
	/**
	 * Constructor del objeto servicio
	 * @param nombreServicio atríbuto que hace referencia al nombre del servicio contratado.
	 * @param descripcionServicio atríbuto que hace referencia a la descripcion del servicio contratado.
	 * @param precioServicio atríbuto que hace referencia al precio del servicio contratado.
	 */
	public servicio(String nombreServicio, String descripcionServicio, float precioServicio) {
		super();
		this.nombreServicio = nombreServicio;
		this.descripcionServicio = descripcionServicio;
		this.precioServicio = precioServicio;
	}

	/**
	 * Constructor del objeto servicio, con los atributos necesarios para poder insertar nuestro objeto servicio en nuestra tabla tienda.
	 * @param idUsuario atributo único que hace referencia al "id" del usuario para poder relacionar nuestras tablas.
	 * @param idServicio  atributo unico de cada objeto servicio que nos permite diferenciar los distintos tipos de servicios.
	 */
	public servicio(int idUsuario, int idServicio) {
		super();
		this.idUsuario = idUsuario;
		this.idServicio = idServicio;
	}


	/**
	 * Constructor del objeto servicio con todos los atributos de dicho objeto.
	 * @param idUsuario atributo único que hace referencia al "id" del usuario para poder relacionar nuestras tablas.
	 * @param idServicio atributo unico de cada objeto servicio que nos permite diferenciar los distintos tipos de servicios.
	 * @param nombreServicio atríbuto que hace referencia al nombre del servicio contratado.
	 * @param precioServicio atríbuto que hace referencia al precio del servicio contratado.
	 * @param descripcionServicio atríbuto que hace referencia a la descripcion del servicio contratado.
	 */
	public servicio(int idUsuario, int idServicio, String nombreServicio, String descripcionServicio, float precioServicio) {
		super();
		this.idUsuario = idUsuario;
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
		this.descripcionServicio = descripcionServicio;
		this.precioServicio = precioServicio;
	}
	
	/**
	 * Constructor del objeto servicio con los atributos necesarios para sacar el objeto servicio de la tabla servicio.
	 * @param idServicio atributo unico de cada objeto servicio que nos permite diferenciar los distintos tipos de servicios.
	 * @param nombreServicio atríbuto que hace referencia al nombre del servicio contratado.
	 * @param descripcionServicio atríbuto que hace referencia al precio del servicio contratado.
	 * @param precioServicio atríbuto que hace referencia a la descripcion del servicio contratado.
	 */
	public servicio(int idServicio, String nombreServicio, String descripcionServicio, float precioServicio) {
		super();
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
		this.descripcionServicio = descripcionServicio;
		this.precioServicio = precioServicio;
	}

	/**
	 * Método que mediante el uso del patrón Singleton llama a clase daoServicio, conecta con la base de datos e inserta el objeto servicio en la base datos
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public void crearServicio  () throws SQLException {
		daoServicio.getInstance().comprarServicio(this);
	}
	
	/**
	 * Método que mediante el uso del patrón Singleton llama a la clase daoServicio, conecta con la base de datos y comprueba que
	 * idUsuario no se encuentre ya en la tabla servicios de la base de datos.
	 * @param idUsuario atributo unico que identifica al usuario como contratador de un servicio de la tienda
	 * @return devuelve el propio método checkServicio
	 * @throws SQLException lanza un error tipo SQL si hubiese algun error conectando a la BBDD
	 */
	public int checkServicio (int idUsuario) throws SQLException {
		return  daoServicio.getInstance().checkServicio(idUsuario);
	}

	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "idUsuario" de una instancia de la clase.
	 * @return devuelve el valor del atributo "idUsuario".
	 */
	
	public int getIdUsuario() {
		return idUsuario;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "idUsuario" de una instancia de la clase.
	 * @param idUsuario atributo unico de cada objeto servicio que nos permite diferenciarlo del resto y tiene relacion con el id del usuario
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "idServicio" de una instancia de la clase.
	 * @return devuelve el valor del atributo "idServicio".
	 */
	public int getIdServicio() {
		return idServicio;
	}
	
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "idServicio" de una instancia de la clase.
	 * @param idServicio atributo unico de cada objeto que nos permite diferenciarlo del resto
	 */
	
	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "nombreServicio" de una instancia de la clase.
	 * @return devuelve el valor del atributo "nombreServicio".
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "nombreServicio" de una instancia de la clase.
	 * @param nombreServicio atributo que sirve para almacenar el nombre del servicio contratado por el usuario
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "precioServicio" de una instancia de la clase.
	 * @return devuelve el valor del atributo "precioServicio".
	 */
	public float getPrecioServicio() {
		return precioServicio;
	}
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "precioServicio" de una instancia de la clase.
	 * @param precioServicio atributo que sirve para almacenar el precio del servicio contratado por el usuario
	 */
	public void setPrecioServicio(float precioServicio) {
		this.precioServicio = precioServicio;
	}
	
	/**
	 * método comunmente denominado "getter" se utiliza para obtener el valor del atributo "descripcionServicio" de una instancia de la clase.
	 * @return devuelve el valor del atributo "descripcionServicio".
	 */

	public String getDescripcionServicio() {
		return descripcionServicio;
	}
	
	/**
	 * método comunmente denominado "setter" se utiliza para dar o modificar el valor del atributo "descripcionServicio" de una instancia de la clase.
	 * @param descripcionServicio atributo que sirve para almacenar la descripcion del servicio contratado por el usuario
	 */
	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}
	
	/**
	 * Método que devuelve una cadena de String que representa el objeto servicio con sus atributos
	 * 
	 */
	@Override
	public String toString() {
		return "servicio [idUsuario=" + idUsuario + ", idServicio=" + idServicio + ", nombreServicio=" + nombreServicio
				+ ", descripcionServicio=" + descripcionServicio + ", precioServicio=" + precioServicio + "]";
	}

	
	
	
	
	
	
	
}



