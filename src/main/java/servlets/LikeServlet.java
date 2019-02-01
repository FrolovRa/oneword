package servlets;

import dao.PostDao;
import dao.PostDaoImpl;
import entities.Post;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LikeServlet")
public class LikeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("post_id"));

        HttpSession session = request.getSession();

        User su = (User) session.getAttribute("user");

        PostDao dao = new PostDaoImpl();
        Post post = null;

        for (Post p:su.getPosts()) {
            if(p.getPostId() == id){
                post = p;
            }
        }

        if(post == null) post = dao.getPost(id);

        if (request.getParameter("cancel") == null){
            dao.like(su, post);
            su.getFavorite().add(post);
            response.getWriter().print(post.getLiked().size());
        } else {
            dao.removeLike(su, post);
            su.getFavorite().remove(post);
            response.getWriter().print(post.getLiked().size());
        }

        session.setAttribute("user", su);
    }
}
