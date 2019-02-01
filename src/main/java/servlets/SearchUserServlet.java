package servlets;

import dao.UserDaoImpl;
import entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SearchUserServlet")
public class SearchUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String search = request.getParameter("search");

        UserDaoImpl dao = new UserDaoImpl();
        StringBuilder result = new StringBuilder();

        for (User u:dao.searchUser(search)) {
            result.append("<li class=\"search_result_user\"><a");
            result.append(" href='/users/");
            result.append(u.getId());
            result.append("' data-user-id='");
            result.append(u.getId());
            result.append("' >");
            result.append(u.getUsername());
            result.append("</a></li>");
        }

        response.getWriter().write( result.toString() );
    }
}
