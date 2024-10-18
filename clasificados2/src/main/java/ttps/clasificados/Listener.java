package ttps.clasificados;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
	
	public void contextDestroyed(ServletContextEvent sce) {
	}
	
	public void contextInitialized(ServletContextEvent sce) {
		String nombre = sce.getServletContext().getInitParameter("nombreSitio");
		String mail = sce.getServletContext().getInitParameter("mailSitio");
		String contacto = sce.getServletContext().getInitParameter("contactoSitio");
		
		SitioClasificado sitio = new SitioClasificado(nombre,mail,contacto);
		
		ServletContext contexto = sce.getServletContext();
		
		contexto.setAttribute("sitio", sitio);
		
	}

}
