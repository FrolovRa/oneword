package servlets;

import dao.PostDao;
import dao.PostDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import entities.Post;
import entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*
 *
 * Servlet for handling request from "/my-page" URL
 *
 * method doGet return a refreshed session user
 * method doPost create and put a post object to database
 *
 */

@WebServlet(name = "HomePageServlet")
public class HomePageServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDao dao = new UserDaoImpl();
        HttpSession session = request.getSession();

        if (session.getAttribute("role").equals("user")) {
            User u = (User) session.getAttribute("user");
            session.setAttribute("user", dao.getUser(u.getId()));

            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mypage.jsp");
            dispatcher.forward(request, response);
        }  else {
            response.sendRedirect("/");
        }
    }
}
