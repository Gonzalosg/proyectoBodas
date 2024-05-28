package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.servicio;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class contratarServicio
 */
public class contratarServicio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	HttpSession sesion;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public contratarServicio() {
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
		
		HttpSession sesion = request.getSession(false);
		int idUsuario;
		if (sesion!=null) {
			idUsuario =(int) sesion.getAttribute("id");
			int opcionServicio = Integer.parseInt(request.getParameter("boton"));
			
			servicio s = new servicio(idUsuario,opcionServicio);
			
			try {
				int existe = s.checkServicio(idUsuario);
				if(existe==0) {
					s.crearServicio();
					response.sendRedirect("graciasPorSuCompra.html");
				}else {
					response.sendRedirect("ServicioYaContratado.html");
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			response.sendRedirect("home.html");
		}
		
		
		
	}

}
