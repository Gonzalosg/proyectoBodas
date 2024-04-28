package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.usuario;

public class daoUsuario {
	
	private Connection con = null;
	private static daoUsuario instance = null;
	
	public daoUsuario() throws SQLException {
		if(con==null) {
			con = conexionBD.getConnection();
		}
	}
	
	/**
	 * Patron Singleton
	 * @return
	 * @throws SQLException
	 */
	public static daoUsuario getInstance() throws SQLException {
		
		if(instance==null) {
			instance = new daoUsuario();
		}
		return instance;
	}
	
	public void insertar(usuario u) throws SQLException {
		
		PreparedStatement ps = con.prepareStatement("INSERT INTO usuario(nombre,apellido1,apellido2,email,fechaBoda,hashContrasenia,permiso)VALUES(?,?,?,?,?,?,?)");
		ps.setString(1,u.getNombre());
		ps.setString(2,u.getApellido1());
		ps.setString(3,u.getApellido2());
		ps.setString(4,u.getEmail());
		ps.setString(5,u.getFechaBoda());
		ps.setString(6,u.getHashContrasenia());
		ps.setInt(7,u.getPermiso());
		
		int filas = ps.executeUpdate();
		ps.close();
	}
	
	
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
	
	public usuario obtenerPorId(int id) throws SQLException {
		
		String sql = "SELECT * FROM usuario where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,id);
		
		ResultSet rs = ps.executeQuery();
		
		rs.next();
		
		usuario u = new usuario(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7), rs.getInt(8));
		
		return u;
		
	}
	
	
	public String listarJson () throws SQLException {
		String json = "";
		Gson gson = new Gson ();
		
		json = gson.toJson(this.listar());
		
		
		return json;
	}

	
	
}
