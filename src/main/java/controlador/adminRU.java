package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import com.mysql.cj.Session;

import dao.daoUsuario;

/**
 * Servlet implementation class adminRU
 */
public class adminRU extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession sesion;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminRU() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		sesion = request.getSession();
		int idSesion = (int) sesion.getAttribute("id");
		int permisoSesion = (int) sesion.getAttribute("permiso");
		
		System.out.println(idSesion);
		System.out.println(permisoSesion);
		
		if(idSesion>0 && permisoSesion==1) {
			String respuestaJson ="";	
			PrintWriter respuesta = response.getWriter();
		
				try {
						respuestaJson = daoUsuario.getInstance().listarJson();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					respuesta.print(respuestaJson);
		}else {
			response.sendRedirect("Accede.html");
		}
		
		
	
				
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
