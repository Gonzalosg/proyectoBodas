package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * clase encargada de realizar la conexion con la base de datos.
 */
public class conexionBD {
	
	/**
	 * Atributo publico y estático para que pueda ser utilizado desde otras clases y que inicializa la conexion a base de datos a null.
	 */
	public static Connection instance = null;
	
	/**
	 * Atributo publico y estático para que pueda ser accesible desde otras clases y final para que no pueda ser modificado.
	 * establece la conexion a base de datos mediante una URL.
	 */
	public static final String JDBC_URL= "jdbc:mysql://localhost:3306/proyectoint";
	
	/**
	 * 
	 * @return devuelve la conexion
	 * @throws SQLException lanza un error tipo SQL si hubiese algún error conectando a la BBDD
	 */
	public static Connection getConnection() throws SQLException {
		if(instance ==null) {
			instance = DriverManager.getConnection(JDBC_URL, "root","");
		}
		
		return instance;
	}
	
}