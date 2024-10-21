package ttps.clasificados;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class Menu
 */
@WebServlet("/menu") // Make sure to add this annotation to map the servlet
public class Menu extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Menu() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the user object from the request
        Usuario user = (Usuario) request.getAttribute("user");

        // Prepare the response
        response.setContentType("text/plain"); // Set the response content type
        if (user != null) {
            // Assuming Usuario has getUsername() and getPassword() methods
            String username = user.getUsuario();
            String password = user.getPassword(); // Be cautious with exposing passwords

            // Write the username and password to the response
            response.getWriter().append("Username: ").append(username).append("\n");
            response.getWriter().append("Password: ").append(password).append("\n");
        } else {
            response.getWriter().append("No user found.");
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Usuario user = (Usuario) request.getAttribute("user");

        // response
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8"); // Set character encoding if needed
        StringBuilder htmlResponse = new StringBuilder();

        // HTML
        htmlResponse.append("<!DOCTYPE html>");
        htmlResponse.append("<html lang='en'>");
        htmlResponse.append("<head>");
        htmlResponse.append("<meta charset='UTF-8'>");
        htmlResponse.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        htmlResponse.append("<title>User Menu</title>");
        htmlResponse.append("<style>");
        htmlResponse.append("body { font-family: Arial, sans-serif; background-color: #f4f4f4; margin: 0; padding: 20px; }");
        htmlResponse.append("h1 { color: #333; }");
        htmlResponse.append(".menu { margin-top: 20px; padding: 10px; background-color: #fff; border-radius: 5px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }");
        htmlResponse.append(".button { display: inline-block; margin: 5px; padding: 10px 15px; background-color: #007BFF; color: white; text-decoration: none; border-radius: 5px; transition: background-color 0.3s; }");
        htmlResponse.append(".button:hover { background-color: #0056b3; }");
        htmlResponse.append("</style>");
        htmlResponse.append("</head>");
        htmlResponse.append("<body>");

        if (user != null) {
            String username = user.getUsuario();
            List<String> options = user.getOptions();

            htmlResponse.append("<h1>Hello, ").append(username).append("!</h1>");
            htmlResponse.append("<div class='menu'>");

            // menu buttons
            for (String option : options) {
                htmlResponse.append("<a class='button' href='#'>").append(option).append("</a>");
            }

            htmlResponse.append("</div>"); // close menu
        } else {
            htmlResponse.append("<h1>No user found.</h1>");
        }

        htmlResponse.append("</body>");
        htmlResponse.append("</html>");

        // Write the HTML response to the output
        response.getWriter().write(htmlResponse.toString());
    }

}