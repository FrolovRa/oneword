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

@WebServlet(name = "addPostServlet")
public class AddPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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

        User u = (User) session.getAttribute("user");
        session.setAttribute("user", dao.getUser(u.getId()));

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/mypage.jsp");
        dispatcher.forward(request, response);
    }
}
