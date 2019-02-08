package servlets;

import dao.PostDao;
import dao.PostDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "ShowFollowsServlet")
public class ShowPopupServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Set<User> userSet;
        int id  = Integer.parseInt(request.getParameter("uid"));
        String query = request.getParameter("query");

        User sessionUser = (User) request.getSession().getAttribute("user");

        StringBuilder result = new StringBuilder();

        switch (query) {
            case "follows": {
                UserDao dao = new UserDaoImpl();
                userSet = dao.getUser(id).getFollowers();
                break;
            }
            case "following": {
                UserDao dao = new UserDaoImpl();
                userSet = dao.getUser(id).getFollowing();
                break;
            }
            default: {
                PostDao dao = new PostDaoImpl();
                userSet = dao.getPost(id).getLiked();
                break;
            }
        }

        for (User f : userSet) {
            result.append("<div class=\"window_user\">\n" +
                        "            <div class=\"window_user_name\"> <a href=\"/users/");
            result.append(f.getId());
            result.append("\">");
            result.append(f.getUsername());
            if(sessionUser.equals(f)) {
                result.append("</a></div></div>");
                continue;
            }
            result.append("</a></div>\n" +
                        "            <div class=\"window_btn\" data-id=\"");
            result.append(f.getId());
            result.append("\" onclick=\"subscribe(this)\">");
            if (sessionUser.getFollowing().contains(f)) {
                result.append("unfollow");
            } else {
                result.append("follow");
            }
            result.append("</div>\n" + "</div>");
        }
        response.getWriter().write(result.toString());
    }
}
