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

import dao.daoUsuario;

/**
 * Servlet implementation class listarUsuario
 */
public class listarUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession sesion;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public listarUsuario() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		sesion = request.getSession();
		int permisoSesion = (int) sesion.getAttribute("permiso");
		int idUsu = (int) sesion.getAttribute("id");
		
		
		if(permisoSesion==9) {
			String respuestaJson ="";	
			PrintWriter respuesta = response.getWriter();
				
			
					try {
							respuestaJson = daoUsuario.getInstance().listarUsuJson(idUsu);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						respuesta.print(respuestaJson);
			}
		
		}
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
