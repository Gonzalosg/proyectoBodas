package modelo;

import java.sql.SQLException;

import dao.daoUsuario;

public class usuario {
	private int id;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String email;
	private String fechaBoda;
	private String contrasenia;
	private int permiso;
	
	public usuario() {
		
	}

	public usuario(int id, String nombre, String apellido1, String apellido2, String email, String fechaBoda,
			String contrasenia, int permiso) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.fechaBoda = fechaBoda;
		this.contrasenia = contrasenia;
		this.permiso = permiso;
	}

	public usuario(String nombre, String apellido1, String apellido2, String email, String fechaBoda,
			String contrasenia, int permiso) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.fechaBoda = fechaBoda;
		this.contrasenia = contrasenia;
		this.permiso = permiso;
	}
	
	public void insertar() throws SQLException {
		
		daoUsuario.getInstance().insertar(this);
		
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

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
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
				+ ", email=" + email + ", fechaBoda=" + fechaBoda + ", contrasenia=" + contrasenia + ", permiso="
				+ permiso + "]";
	}

	
	
	
	
	
	
	
	
}

