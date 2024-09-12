package practica3;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.util.Locale;

/**
 * Servlet Filter implementation class FiltroIdiomaCliente
 */
@WebFilter(urlPatterns = "/*")
public class FiltroIdiomaCliente extends HttpFilter implements Filter {

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public FiltroIdiomaCliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String idioma = request.getLocale().getLanguage();
		
		ServletRequest newRequest = request;
		newRequest.setAttribute("idioma", idioma);
		// pass the request along the filter chain
		chain.doFilter(newRequest, response);
	}

}
