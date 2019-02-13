package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ShowUserServlet")
public class ShowUserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        int id = Integer.valueOf(pathInfo.substring(1));

        UserDao dao = new UserDaoImpl();
        User u = dao.getUser(id);

        HttpSession session = request.getSession();
        if (session.getAttribute("role").equals("user")){
            User su = (User) session.getAttribute("user");

            if(su.getId() == id) {
                response.sendRedirect("/my-page");
            } else {
                request.setAttribute("user", u);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/usersPage.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("user", u);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/usersPage.jsp");
            dispatcher.forward(request, response);
        }

    }
}
