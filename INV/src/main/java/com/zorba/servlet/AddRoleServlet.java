package com.zorba.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.zorba.model.Role;
import com.zorba.dao.UserDAO;

@WebServlet("/AddRoleServlet")
public class AddRoleServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String roleName = request.getParameter("roleName");

            Role role = new Role();
            role.setRoleName(roleName);

            UserDAO userDAO = new UserDAO();
            userDAO.addRoleToUser(userId, role);

            response.sendRedirect("viewUser.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp");
        }
    }
}