package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);

        String username = request.getParameter("name");
        String pass = request.getParameter("pass");

        UserDao dao = new UserDaoImpl();
        User user = dao.LogInUser(username, pass);

        if(user != null){
            if( user.getUsername().equals(username) & user.getPassword().equals(pass)){
                session.setAttribute("user", user);
                response.sendRedirect("/my-page");
            }else {
                request.setAttribute("userName", "not correct password or username");
                doGet(request,response);
            }
        }
        else {
            request.setAttribute("userName", username);
            doGet(request, response);
        }

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/");
        dispatcher.forward(request,response);
    }
}