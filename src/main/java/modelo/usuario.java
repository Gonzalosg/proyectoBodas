package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.daoUsuario;

public class usuario {
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String fechaBoda;
	private String hashContrasenia;
	private int permiso;
	
	public usuario() {
		
	}

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
	
	public void insertar () throws SQLException {
		
		daoUsuario.getInstance().insertar(this);
	}
	
	
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
	
	public String dameJson () throws SQLException {
		String json = "";
		Gson gson = new Gson ();
		
		json = gson.toJson(this);
		
		
		return json;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaBoda() {
		return fechaBoda;
	}

	public void setFechaBoda(String fechaBoda) {
		this.fechaBoda = fechaBoda;
	}

	public String getHashContrasenia() {
		return hashContrasenia;
	}

	public void setHashContrasenia(String contrasenia) {
		this.hashContrasenia = contrasenia;
	}

	public int getPermiso() {
		return permiso;
	}

	public void setPermiso(int permiso) {
		this.permiso = permiso;
	}

	@Override
	public String toString() {
		return "usuario [id=" + id + ", nombre=" + nombre + ", apellido1=" + apellido1 + ", apellido2=" + apellido2
				+ ", email=" + email + ", fechaBoda=" + fechaBoda + ", contrasenia=" + hashContrasenia + ", permiso="
				+ permiso + "]";
	}

	
	
	
	
	
	
	
	
}

