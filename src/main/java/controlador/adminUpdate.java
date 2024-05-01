package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.sql.SQLException;

/**
 * Servlet implementation class adminUpdate
 */
	public class adminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public adminUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		int opcion = Integer.parseInt(request.getParameter("op"));
		
		if(opcion==2) {
			usuario u = new usuario();
			try {
				u.obtenerPorId(id);
				out.print(u.dameJson());
				
				System.out.println(u.dameJson());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String nombre = request.getParameter("nombre");
		String apellido1 = request.getParameter("apellido1");
		String apellido2 = request.getParameter("apellido2");
		String email = request.getParameter("email");
		String fechaBoda= request.getParameter("fechaBoda");
		String contrasenia = request.getParameter("contrasenia");
		int permiso=9;
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(contrasenia.getBytes());
		    byte[] digest = md.digest();
		    String myHash = new String(digest);
		    usuario u = new usuario(nombre, apellido1, apellido2, email, fechaBoda,myHash, permiso);
			
			u.setId(id);
			u.update();
			response.sendRedirect("galeriaUsuarios.html");
		
		    
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
}
