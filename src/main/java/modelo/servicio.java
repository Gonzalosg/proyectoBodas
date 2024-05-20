package modelo;

import java.sql.SQLException;

import dao.daoUsuario;

public class servicio {
	int idUsuario;
	int idServicio;
	String nombreServicio="";
	float precioServicio;

	public servicio() {
		
	}
	
	
	
	public servicio(int idUsuario, int idServicio) {
		super();
		this.idUsuario = idUsuario;
		this.idServicio = idServicio;
	}



	public servicio(int idUsuario, int idServicio, String nombreServicio, float precioServicio) {
		super();
		this.idUsuario = idUsuario;
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
		this.precioServicio = precioServicio;
	}
	
	public void crearServicio  () throws SQLException {
		daoUsuario.getInstance().comprarServicio(this);
	}


	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public int getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(int idServicio) {
		this.idServicio = idServicio;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public float getPrecioServicio() {
		return precioServicio;
	}

	public void setPrecioServicio(float precioServicio) {
		this.precioServicio = precioServicio;
	}

	@Override
	public String toString() {
		return "servicio [idUsuario=" + idUsuario + ", idServicio=" + idServicio + ", nombreServicio=" + nombreServicio
				+ ", precioServicio=" + precioServicio + "]";
	}
	
	
	
	
	
	
}



