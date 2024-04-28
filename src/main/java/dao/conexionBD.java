package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
	
	
	public static Connection instance = null;
	public static final String JDBC_URL= "jdbc:mysql://localhost:3306/proyectoint";
	
	public static Connection getConnection() throws SQLException {
		if(instance ==null) {
			instance = DriverManager.getConnection(JDBC_URL, "root","");
		}
		
		return instance;
	}
	
}