package servlets;

import app.TimeOfPublications;
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
import java.io.Writer;

/*
 *
 * Servlet for handling request from "/my-page" URL
 *
 * method doGet return a refreshed session user
 * method doPost create and put a post object to database
 *
 */

@WebServlet(name = "CreatePostServlet")
public class CreatePostServlet extends HttpServlet {

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
        Writer out = response.getWriter();
        out.write(
                "<div class=\"frame new-post\">\n" +
                "           <p> " +
                TimeOfPublications.getTimeDifference(post.getDate()) +
                "          </p>\n" +
                "          <div class=\"post\" data-id=\""+ post.getPostId() + "\">\n" +
                "           <div class=\"rmv_btn\" onclick=\"remove(this)\"></div> " +
                "            <div class=\"like\" onclick=\"like(this)\">\n  " +
                "            </div>\n" + "<div class=\"like_count\" onclick=\"openLiked(this)\">" +
                        post.getLiked().size() + "</div>" +
                "            <p class=\"content\">" + post.getWord() + "</p>\n" +
                "            <div class=\"username_wrapper\"><h6 class=\"username\">"
                        + post.getOwner_id().getUsername() + "</h6> </div>"  +
                "          </div>\n" +
                "        </div>");
    }
}


