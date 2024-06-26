package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.servicio;
import modelo.usuario;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		sesion = request.getSession(false);
		
		if(sesion!=null) {
			sesion.invalidate();
			response.sendRedirect("home.html");
		}else {
			response.sendRedirect("Accede.html");
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
		
		String email = request.getParameter("emailLog");
		String pass = request.getParameter("contraseniaLog");
		
		usuario u = new usuario();
		u.setEmail(email);
		u.setHashContrasenia(usuario.getMD5(pass));
		
		try {
			if(u.logeo()) {
				
				sesion = request.getSession();
				servicio s = new servicio();
				int existeServicio = s.checkServicio(u.getId());
				sesion.setAttribute("existeServicio", existeServicio);
				sesion.setAttribute("permiso",u.getPermiso());
				sesion.setAttribute("id", u.getId());
				response.sendRedirect("home.html");
			
				
			}else {
				response.sendRedirect("Accede.html"); 
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
	}
	

}
