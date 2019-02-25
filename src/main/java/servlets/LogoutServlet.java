package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
 *
 * Servlet for handling request from "/login" URL
 *
 * method doGet forwarding to index.jsp page with information about error
 * method doPost create http session and validate login with password
 *
 */

@WebServlet(name = "LogoutServlet")
public class LogoutServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("user");
        request.getSession().setAttribute("role","guest");
    }
}
