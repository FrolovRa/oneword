package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "SubscribeServlet")
public class SubscribeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("subscribe"));

        UserDao dao = new UserDaoImpl();

        HttpSession session = request.getSession();

        User su = (User) session.getAttribute("user");
        User u = dao.getUser(id);

        if (request.getParameter("cancel") == null){
            dao.subscribe(su, u);
            response.getWriter().print(u.getFollowers().size() + 1);
        } else {
            dao.unsubscribe(su, u);
            response.getWriter().print(u.getFollowers().size() - 1);
        }
}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
