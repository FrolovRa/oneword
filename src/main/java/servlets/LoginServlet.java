package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;


import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Base64;

/*
 *
 * Servlet for handling request from "/login" URL
 *
 * method doGet forwarding to index.jsp page with information about error
 * method doPost create http session and validate login with password
 *
 */

@WebServlet(name = "LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(true);
        String username = request.getParameter("name");
        String pass = request.getParameter("pass");

        UserDao dao = new UserDaoImpl();
        User user = dao.LogInUser(username, pass);

        if(user != null){
            if(user.getUsername().equals(username) & user.getPassword().equals(pass)){
                session.setAttribute("user", user);
                session.setAttribute("role", "user");
                response.sendRedirect("/my-page");
            } else {
                request.setAttribute("userName", "not correct password or username");
                doGet(request,response);
            }
        } else {
            request.setAttribute("username", username);
            doGet(request, response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request,response);
    }
}
