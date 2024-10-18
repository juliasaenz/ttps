package ttps.clasificados;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Usuario> usuarios = new ArrayList<Usuario>();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        
        Usuario admin = new Administrador("admin","admin");
        this.usuarios.add(admin);
        Publicador pub = new Publicador("pub","pub");
        this.usuarios.add(pub);
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//encontrar usuario que matchea con el request
		Usuario user = usuarios.stream()
				.filter(u -> u.userIsValid(request.getParameter("username"),request.getParameter("password")))
				.findFirst().orElse(null);
		// si hay uno correr el redirect
		if(user != null) {
			//response.sendRedirect(user.redirect());
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/menu");
			if(dispatcher != null) {
				request.setAttribute("user", user);
				dispatcher.forward(request, response);
			}
		} else {
			// sino llevo a error
			response.sendRedirect("./error.html");
		}

	}

}
