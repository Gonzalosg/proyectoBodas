package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.daoUsuario;

/**
 * Servlet implementation class gestionUsuario
 */
public class gestionUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public gestionUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String email = request.getParameter("email");
		String fechaBoda= request.getParameter("fechaBoda");
		String contrasenia = request.getParameter("contrasenia");
		int permiso=9;
		
		usuario u = new usuario(nombre, apellido1, apellido2, email, fechaBoda, contrasenia, permiso);
		System.out.println(u+ "Usuario creado");
		
		try {
			u.insertar();
			System.out.println(u+" usuario insertado");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
