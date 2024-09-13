

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class AbonosListener
 *
 */
@WebListener
public class AbonosListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public AbonosListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext contexto = sce.getServletContext();
    	// inicializar los contadores
    	int cantAbonosDosDias = Integer.parseInt(contexto.getInitParameter("cantAbonosDosDias"));
    	int cantAbonosTresDias = Integer.parseInt(contexto.getInitParameter("cantAbonosTresDias"));
    	
    	contexto.setAttribute("cantAbonosDosDias", cantAbonosDosDias);
    	contexto.setAttribute("cantAbonosTresDias", cantAbonosTresDias);
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }
	
}
