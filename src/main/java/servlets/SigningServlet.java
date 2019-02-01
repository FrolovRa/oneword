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

@WebServlet(name = "signingServlet")
public class SigningServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("name");
        String pass = request.getParameter("pass");

        User user = new User(username, pass);
        UserDao dao = new UserDaoImpl();

        user.setId(dao.addUser(user));

        HttpSession session = request.getSession();
        session.setAttribute("user", user);

        response.sendRedirect("/my-page");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("signin.jsp");
        dispatcher.forward(request, response);
    }
}
