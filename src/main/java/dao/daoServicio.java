package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.servicio;

public class daoServicio {

	private Connection con = null;
	private static daoServicio instance = null;
	
	public daoServicio() throws SQLException {
		if(con==null) {
			con = conexionBD.getConnection();
		}
	}
	
public static daoServicio getInstance() throws SQLException {
		
		if(instance==null) {
			instance = new daoServicio();
		}
		return instance;
	}





	
}
