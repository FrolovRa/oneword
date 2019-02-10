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

@WebServlet(name = "RemoveServlet")
public class RemoveServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int postId = Integer.parseInt(request.getParameter("postid"));

        PostDao dao = new PostDaoImpl();

        dao.removePost(dao.getPost(postId));

        response.sendRedirect("/my-page");

//        request.getSession().setAttribute("user", );

    }
}
