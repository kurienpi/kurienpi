<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.zorba.model.User"%>
<%@ page import="com.zorba.dao.UserDAO"%>

<%
    try {
        // Get form parameters
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        int mobile = Integer.parseInt(request.getParameter("mobile"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Create User object
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setMobile(mobile);
        user.setUsername(username);
        user.setPassword(password);

        // Save user
        UserDAO userDao = new UserDAO();
        userDao.saveUser(user);

        // Redirect to success page
        response.sendRedirect("success.jsp");
    } catch (Exception e) {
        e.printStackTrace();
        response.sendRedirect("error.jsp");
    }
%>