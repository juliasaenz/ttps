package practica3;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

@WebFilter(urlPatterns = "/*")
public class FiltroLogDeAccesos implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		// TODO Auto-generated method stub
		String metodo = httpRequest.getMethod();
        String recursoSolicitado = httpRequest.getRequestURI();
        String versionProtocolo = httpRequest.getProtocol();
        String requestLine = metodo + " " + recursoSolicitado + " " + versionProtocolo;
        Date fecha = new Date();
        String userAgent = httpRequest.getHeader("User-Agent");
		String mensaje = request.getLocalAddr() + " " + requestLine + " " + fecha.toString() + " " + userAgent;
		System.out.println(mensaje);

	}

}