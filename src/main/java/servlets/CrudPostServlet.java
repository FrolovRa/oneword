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

@WebServlet(name = "CrudPostServlet")
public class CrudPostServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf8");
        String word = request.getParameter("word");

        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("user");

        Post post = new Post(word);
        PostDao dao = new PostDaoImpl();

        post.setOwner_id(u);

        dao.addPost(post);
        u.getPosts().add(post);

        session.setAttribute("user", u);
        response.sendRedirect("/my-page");
    }

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
