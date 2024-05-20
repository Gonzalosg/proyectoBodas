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

public class daoUsuario {
	
	HttpSession sesion;
	
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
	
	public void insertarServicio (servicio s, int id) throws SQLException {
						
		String sql2 = "INSERT INTO tienda_usuario (idUsuario, idServicio, nombreServicio, precioServicio) VALUES (?,?,?,?) where idUsuario='"+id+"'";
		PreparedStatement ps2 = con.prepareStatement(sql2);
		ps2.setInt(1, id);
		ps2.setInt(2, s.getIdServicio());
		ps2.setString(3, s.getNombreServicio());
		ps2.setFloat(4, s.getPrecioServicio());
		ps2.executeUpdate();
		
		ps2.close();
		
		
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
		
		ps.executeUpdate();
		ps.close();
	}
	
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
	
	public void delete (int id) throws SQLException {
		String sql = "delete from usuario where id=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
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
	
	
	public String listarJson () throws SQLException {
		String json = "";
		Gson gson = new Gson ();
		
		json = gson.toJson(this.listar());
		
		
		return json;
	}
	

	
	
	public void comprarServicio (servicio u) throws SQLException {
		String sql = "INSERT INTO tienda (idCliente, idServicio) VALUES (?,?)";
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.setInt(1, u.getIdUsuario() );
		ps.setInt(2,u.getIdServicio());
		
		ps.executeUpdate();
		ps.close();
		
	}

	
	
}
