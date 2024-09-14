import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/Estadisticas")
public class Estadisticas extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Estadisticas() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    Integer cantVendidosDos = (Integer) getServletContext().getAttribute("cantAbonosDosDias");
	    Integer cantVendidosTres = (Integer) getServletContext().getAttribute("cantAbonosTresDias");
	    Integer cantTotalDos = (Integer) getServletContext().getAttribute("AbonosDosDiasTotales");
	    Integer cantTotalTres = (Integer) getServletContext().getAttribute("AbonosTresDiasTotales");

	    response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");

	    response.getWriter().write("<!DOCTYPE html>");
	    response.getWriter().write("<html><head>");
	    response.getWriter().write("<style>");
	    response.getWriter().write("body { font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }");
	    response.getWriter().write(".flex { display: flex; flex-direction: column; align-items: center; justify-content: center; gap: 1em; }");
	    response.getWriter().write("p { font-size: 14px; }");
	    response.getWriter().write("</style>");
	    response.getWriter().write("</head><body>");

	    response.getWriter().write("<div class='flex'>");
	    response.getWriter().write("<h1>Estadísticas de Abonos</h1>");
	    response.getWriter().write("<p>Abonos para dos días vendidos: " + (cantTotalDos-cantVendidosDos) + " de " + cantTotalDos + "</p>");
	    response.getWriter().write("<p>Abonos para tres días vendidos: " + (cantTotalTres-cantVendidosTres) + " de " + cantTotalTres + "</p>");
	    response.getWriter().write("</div>");

	    response.getWriter().write("</body></html>");
	}

}
