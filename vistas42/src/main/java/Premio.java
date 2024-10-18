

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Premio
 */
@WebServlet("/premio")
public class Premio extends HttpServlet {
	private int count = 0;
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Premio() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getParameter("nombre"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		count++;
		/**response.getWriter().append("Served at: ")
		.append(request.getParameter("nombre"))
		.append("cantidad de veces llamado: ")
		.append(Integer.toString(count));*/
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body>");
		out.println("<h1> Hola " + request.getParameter("nombre")+ "</h1>");
		out.print("</body></html>");
		out.close();
	}

}
