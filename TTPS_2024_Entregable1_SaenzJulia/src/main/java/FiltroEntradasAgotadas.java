
import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



public class FiltroEntradasAgotadas extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public FiltroEntradasAgotadas() {
		super();
	}

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("SOY EL FILTRO");
		
		Integer cantDos = (Integer) getServletContext().getAttribute("cantAbonosDosDias");
		Integer cantTres = (Integer) getServletContext().getAttribute("cantAbonosTresDias");
		
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		if (cantDos <= 0 && cantTres <= 0) {
			// NO HAY MAS ENTRADAS MAN
			res.sendRedirect(req.getContextPath() + "/agotadas.html");
			return;
		} else {
			String diaAbono = request.getParameter("dias");
			if ((diaAbono.equals("dos") && cantDos <= 0) || ((diaAbono.equals("tres") && cantTres <= 0))) {
				res.sendRedirect(req.getContextPath() + "/no-disponibles.html");

				return;
			}
		}
		chain.doFilter(request,response);
	}


}
