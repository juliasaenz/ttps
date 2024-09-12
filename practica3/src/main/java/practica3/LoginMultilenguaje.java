package practica3;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

@WebServlet("/login")
public class LoginMultilenguaje extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String language = (String) request.getAttribute("idioma");
        if (language == null) {
            language = Locale.getDefault().getLanguage(); // Default language
        }
        ResourceBundle bundle = ResourceBundle.getBundle("MessagesBundle", language);

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");

        String title = bundle.getString("login.title");
        String usernameLabel = bundle.getString("login.username");
        String passwordLabel = bundle.getString("login.password");
        String submitButton = bundle.getString("login.submit");

        response.getWriter().println("<html><head><title>" + title + "</title></head><body>");
        response.getWriter().println("<h1>" + title + "</h1>");
        response.getWriter().println("<form action=\"/login\" method=\"post\">");
        response.getWriter().println("<label for=\"username\">" + usernameLabel + ":</label>");
        response.getWriter().println("<input type=\"text\" id=\"username\" name=\"username\"><br>");
        response.getWriter().println("<label for=\"password\">" + passwordLabel + ":</label>");
        response.getWriter().println("<input type=\"password\" id=\"password\" name=\"password\"><br>");
        response.getWriter().println("<input type=\"submit\" value=\"" + submitButton + "\">");
        response.getWriter().println("</form>");
        response.getWriter().println("</body></html>");
    }
}
